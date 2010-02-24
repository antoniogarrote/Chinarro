 (ns chinarro.serialization
   (:use (org.danlarkin [json :as json])))

(defn serialize [o]
  (encode-to-str o))

(defn deserialize [string]
  (decode-from-str string))

;;; REPL
;;Jars to add in Slime to execute this code
;;(add-classpath "file:///Users/antonio.garrote/Development/old/chinarro/lib/clojure-new-json-1.1-20091121.091400-1.jar")
