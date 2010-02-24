(defproject chinarro "1.0.0-SNAPSHOT"
  :description "Chinarro bot is like Pocho bot but likes parenthesis"
  :dependencies [[org.clojure/clojure "1.1.0"]
                 [org.clojure/clojure-contrib "1.1.0"]
                 [compojure "0.3.2"]
                 [org.clojars.liebke/clojure-new-json "1.1-SNAPSHOT"]
                 [redis-clojure "1.0-SNAPSHOT"]
                 [jivesoftware/smackx "3.0.4"]
                 [jivesoftware/smack "3.0.4"]]
  :main chinarro.web)
