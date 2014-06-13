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

(def mongo-db (ref false))

(defn init-mongo []
  (let [{:keys [conn db]} (mg/connect-via-uri (mongo-uri))]
    (dosync
      (ref-set connected true)
      (ref-set mongo-db db))))

(defn messages []
  (if-not (= @connected true)
    (init-mongo))
  (q/with-collection @mongo-db "message"
    (q/skip 0)
    (q/limit 10)
    (q/sort (sorted-map :timestamp -1))))


(defn save [username message]
  (let [conn (mg/connect)  db (mg/get-db conn "ticker")]
    (mc/insert db "message"
               {:username username
                :message message
                :timestamp (new java.util.Date)})))
