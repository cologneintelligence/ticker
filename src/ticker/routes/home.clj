(ns ticker.routes.home
  (:require [compojure.core :refer :all]
            [ticker.views.layout :as layout]
            [ticker.models.message :as message]))

(defn home []
  (layout/common (layout/content (message/messages))))

(defroutes home-routes
  (GET "/" [] (home)))
