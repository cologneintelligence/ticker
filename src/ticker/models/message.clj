(ns ticker.models.message
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.query :as q]
            [monger.command :as cmd]
            [monger.conversion :refer [from-db-object]]))


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

(defn messages [skip limit]
  (if-not (= @connected true)
    (init-mongo))
  (q/with-collection @mongo-db "message"
    (q/skip skip)
    (q/limit limit)
    (q/sort (sorted-map :timestamp -1))))

(defn size []
  (:count (from-db-object (cmd/collection-stats @mongo-db "message") true)))

(defn save [username message]
    (mc/insert @mongo-db "message"
               {:username username
                :message message
                :timestamp (new java.util.Date)}))
