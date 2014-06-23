(ns ticker.routes.home
  (:require [compojure.core :refer :all]
            [ticker.views.layout :as layout]
            [ticker.models.message :as message]
            [ring.util.response :as resp]))

(defn home
  ([page]
     (println (str "size: " (message/size)))
     (layout/common (layout/content (message/messages (* 10 page) (* (+ 1 page) 10)) page (message/size))))
  ([]  (layout/common (layout/content (message/messages 0 10) 0 (message/size)))))


(defroutes home-routes
  (GET "/" [] (home))
  (GET "/:page" [page] (home (read-string page)))
  (POST "/" [username message]
        (do
          (message/save username message)
          (resp/redirect "/"))))
