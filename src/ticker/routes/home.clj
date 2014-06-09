(ns ticker.routes.home
  (:use compojure.core)
  (:require [ticker.layout :as layout]
            [ticker.util :as util]
            [ticker.db.core :as db]))

(defn home-page [& [username message error]]
  (layout/render "home.html"
                 {:error    error
                  :name     username
                  :message  message
                  :messages (db/get-messages)}))


(defn save-message [username message]
  (cond

    (empty? username)
    (home-page username message "Somebody forgot to leave a name")

    (empty? message)
    (home-page username message "Don't you have something to say?")

    :else
    (do
      (db/save-message username message)
      (home-page))))

  (defroutes home-routes
  (GET "/" [] (home-page))
  (POST "/" [username message] (save-message username message)))
