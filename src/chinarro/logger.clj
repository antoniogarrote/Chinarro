(ns chinarro.logger
  (:import (java.util.logging Logger)))

(def *logger* (. Logger (getLogger "chinarro-log")))

(defn log-warning [msg]
  (.warning *logger* msg))
