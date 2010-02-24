(ns chinarro.redis
  (:require redis)
  (:use [chinarro serialization configuration logger])
  (:import (java.util Date)))


(def redis-ns (get chinarro.configuration/chinarro-config :redis-ns))

(def redis-connection {:host (get chinarro.configuration/chinarro-config :redis-host)
                       :port (get chinarro.configuration/chinarro-config :redis-port)
                       :db (get chinarro.configuration/chinarro-config :redis-db)})

(defn tags [msg]
  (doall (re-seq #"#\w+" msg)))

(defn tagsp [msg]
  (> (count (re-seq #"#\w+" msg)) 0))

(defn date []
  (let [date (new Date)]
    [(+ (.getYear date) 1900)
     (.getMonth date)
     (.getDate date)]))

(defn format-nick [nick]
  (.. nick toLowerCase (replace " " "_")))


(defn timeline-key
  ([date]
     (str redis-ns ":timeline:" (reduce #(str %1 ":" %2) date)))
  ([y m d]
     (str redis-ns ":timeline:" (reduce #(str %1 ":" %2) [y m d]))))

(defn tag-key [tag]
  (str redis-ns ":tag:" tag))

(defn tag-key-hash [tag]
  (str redis-ns ":tag:#" tag))

(defn tags-key []
  (str redis-ns ":tags"))

(defn nick-key [nick]
  (str redis-ns (format-nick nick)))


(defn save-tags-in-redis [nick msg]
  (let [tuple (chinarro.serialization/serialize [nick msg])]
    (do

      (redis/with-server
        redis-connection
        (doseq [tag (tags msg)]
          (do
            (redis/lpush (tag-key tag) tuple)
            (redis/sadd (tags-key) tag)))
        (redis/lpush (timeline-key (date)) tuple)
        (redis/lpush (nick-key nick) tuple)))))

(defn find-messages [key]
  (redis/with-server
    redis-connection
    (map #(chinarro.serialization/deserialize %1) (redis/lrange key 0 -1))))

(defn find-tags []
  (redis/with-server
    redis-connection
    (redis/smembers (tags-key))))


;;; REPL
;;Jars to add in Slime to execute this code
;;(add-classpath "file:///Users/antonio.garrote/Development/old/chinarro/lib/redis-clojure-1.0-20091216.162623-1.jar")
