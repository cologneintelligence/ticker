(ns ticker.models.message
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.query :as q]))


(defn mongo-uri []
  (let [env (System/getenv "MONGO_URI")
        prop (System/getProperty "mongo.uri")
        default "mongodb://localhost/ticker"]
    (first (remove nil? [env prop default]))))

(def connected (ref false))

(defn init-mongo []
  (do
    (mg/connect-via-uri! (mongo-uri))
    (dosync
     (ref-set connected true))))

(defn messages []
  (if-not (= @connected true)
    (init-mongo))
  (q/with-collection "message"
    (q/skip 0)
    (q/limit 10)
    (q/sort (sorted-map :timestamp -1))))
