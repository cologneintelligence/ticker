(ns ticker.routes.home
  (:require [compojure.core :refer :all]
            [ticker.views.layout :as layout]
            [ticker.models.message :as message]
            [ring.util.response :as resp]))

(defn home []
  (layout/common (layout/content (message/messages))))

(defroutes home-routes
  (GET "/" [] (home))
  (POST "/" [username message] (do
                                 (message/save username message)
                                 (resp/redirect "/")
                                 )))
