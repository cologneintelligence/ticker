(ns ticker.db.core
    (:require [monger.core :as mg]
              [monger.collection :as mc]
              [monger.operators :refer :all]))

(defn save-message [username message]
  (let [conn (mg/connect)  db (mg/get-db conn "ticker")]
    (mc/insert db "message"
               {:username username
                :message message
                :timestamp (new java.util.Date)})))

(defn get-messages []
  (let [conn (mg/connect) db (mg/get-db conn "ticker") coll "ticker" exists (mc/exists? db coll)]
    (mc/find-maps db "message")))
