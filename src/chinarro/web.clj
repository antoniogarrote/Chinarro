(ns chinarro.web
  (:gen-class)
  (:use [compojure]
        [compojure.http response]
        [chinarro redis logger css xmpp configuration luque])
  (:import (java.io ByteArrayOutputStream
                    ByteArrayInputStream)))


;;;;;;;;;;;;;;;;;;;;;;;;;
;; Helpers and templates
;;;;;;;;;;;;;;;;;;;;;;;;;

(defn with-layout [title body]
  (html
   (doctype :xhtml)
   [:html
    [:head
     [:title (str title " | Chinarro The Robot")]
     [:link {:href "/pocho.css" :media "screen" :rel "stylesheet" :type "text/css"}]]
    [:body
     [:div {:id "main"}
      [:div {:id "head"}
       [:a {:href "/"}
        [:h1 title]]]
      [:div body]]
     [:div {:id "footer"}
      [:a {:href "http://github.com/antoniogarrote/Chinarro"}
       (str "Chinarro (" (chinarro.luque/random-musing) ")")]
      ":"
      [:a {:href "http://github.com/malditogeek/Pocho"}
       "Pocho es groso, sabelo"]]]]))

(defn auto-link [text]
  (let [tags-map (map (fn [e] [e  (html  [:a {:href (str "/tags/" (. e (replace "#" "")))} e])])
                      (chinarro.redis/tags text))]
    (loop [occs tags-map
           acum text]
      (if (empty? occs)
        acum
        (let [pair (first occs)
              v (nth pair 0)
              l (nth pair 1)]
          (recur (rest occs)
                 (. acum (replace v l))))))))

(defn render-message [message]
  (let [nick (first message)
        txt (nth message 1)]
    [:span
     [:a {:class "nick" :href (str "/users/" (chinarro.redis/format-nick nick))}
      (str nick " : ")]
     (auto-link txt)]))

(defn index [messages tags]
  (html
   (when (empty? messages)
     [:div {:class "message"}
      "hmmm... nothing yet"])
   (map (fn [message]
          [:div (str message)]
          [:div {:class "message"}
           (render-message message)])
         messages)
   (when (not (empty? tags))
     [:div {:id "tags"}
      (map (fn [tag]
             [:div {:class "tag"}
              (auto-link tag)])
           tags)])))


;; Does not sound as well as Sinatra...

(defroutes chinarroweb
  (GET "/"
       (let [title "What's up?"
             time (chinarro.redis/date)
             messages (chinarro.redis/find-messages (chinarro.redis/timeline-key time))
             tags (chinarro.redis/find-tags)]
         (with-layout
           title
           (index messages tags))))

  (GET "/tags/:tag"
       (let [tag (params :tag)
             title (str "#" tag)
             messages (chinarro.redis/find-messages (chinarro.redis/tag-key-hash tag)) ]
         (with-layout
           title
           (index messages []))))

  (GET "/users/:user"
       (let [user (params :user)
             title (str (. user (replace "_" " ")))
             messages (chinarro.redis/find-messages (chinarro.redis/nick-key user))]
         (with-layout
           title
           (index messages []))))

  (GET "/:year/:month/:day"
       (let [y (params :year)
             m (params :month)
             d (params :day)
             title (str y "/" m "/" d)
             messages (chinarro.redis/find-messages (chinarro.redis/timeline-key y m d))
             tags (chinarro.redis/find-tags)]
         (log-warning (str "looking date " (chinarro.redis/timeline-key y m d)))
         (with-layout
           title
           (index messages tags))))

  (GET "/pocho.css"
       (update-response request
                        {:status 200 :headers {"Content-Type" "text/css"}}
                        (pocho-css))))

;; Start your engines

(defn -main [& args]
  (if (or (empty? args)
          (= (first args) "web"))
    (run-server {:port (get chinarro.configuration/chinarro-config :port)}
                "/*" (servlet chinarroweb))
    (if (= (first args) "bot")
      (let [c (chinarro.xmpp/connect (get chinarro.configuration/chinarro-config :jid)
                                     (get chinarro.configuration/chinarro-config :password))
            rooms (get chinarro.configuration/chinarro-config :rooms)]
        (do
          (log-warning (str "Connected to " c " with rooms " rooms))
          (chinarro.xmpp/listen-rooms c rooms)
          (while true (Thread/sleep 3000))))
      (println (str "Unknown parameter: " (first args) ".\nPlease choose 'bot' or 'web'")))))
