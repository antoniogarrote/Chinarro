(ns chinarro.xmpp
  (:use [ chinarro redis logger])
  (:import (org.jivesoftware.smack XMPPConnection ConnectionConfiguration
                                   PacketListener MessageListener)
           (org.jivesoftware.smack.packet Message)
           (org.jivesoftware.smackx.muc MultiUserChat)))

(defn connect [jid pass]
  (doto (XMPPConnection. (last (.split jid "@")))
    .connect
    (.login (first (.split jid "@")) pass)))

(defn muc [connection room]
  (doto (MultiUserChat. connection room)
    (.join "Chinarro the Robot")))

(defn parse-login [who]
  (let [parsed (.split who "/")]
    (if (= (alength parsed) 1)
      who
      (aget parsed 1))))

(defn packet-listener [room-object]
  (proxy [PacketListener] []
    (processPacket [p]
                   (try
                    (let [body (.getBody p)
                          who (parse-login (.getFrom p))]
                      (do
                        (log-warning (str who " said " body))
                        (when (chinarro.redis/tagsp body)
                          (do
                            (chinarro.redis/save-tags-in-redis who body)
                            (let [msg (.createMessage room-object)]
                              (.setBody msg (str who ": gotcha ;)"))
                              (.sendMessage room-object msg))))))
                     (catch Exception e
                       (prn e))))))

(defn listen-rooms [connection rooms]
  (doseq [room rooms]
    (let [room-object (muc connection room)
          lst (packet-listener room-object)]
      (.addMessageListener room-object lst))))


;;; REPL
;;Jars to add in Slime to execute this code
;;(add-classpath "file:///Users/antonio.garrote/Development/old/chinarro/lib/smack-3.0.4.jar")
;;(add-classpath "file:///Users/antonio.garrote/Development/old/chinarro/lib/smackx-3.0.4.jar")
