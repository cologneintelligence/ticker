(ns ticker.views.layout
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.form :refer :all]
            [ticker.formatter :as formatter])
  (:use hiccup.element ))

(defn common [& body]
  (html5
   [:head
    [:title "tick mal!"]
    (include-css "/css/screen.css")]
   [:body body]))

(defn navi [page docsize]
  (let [pagesize 10 actcount (* page pagesize)]
    (if (> docsize pagesize)
      [:div
       (if (> page 0)
         (link-to (str (if (> page 0 ) (- page 1))) "newer"))
       (if (> page 0)
         (str "&nbsp;&nbsp;&nbsp;"))
       (if (> docsize (* (+ 1 page) 10))
         (link-to (str (if (> docsize actcount) (+ page 1))) "older"))])))

  (defn content [messages page docsize]
    [:div.main
     [:div.title [:h1 "ticker"]]
     (form-to [:post "/"]
              [:p "Name:"]
              (text-field "username" "Username goes here")

              [:p "Message:"]
              (text-area {:rows 4 :cols 40} "message" "Message goes here")

              [:br]
              (submit-button "comment"))
     [:div.content
      (navi page docsize)
      (for [{:keys [username message]} messages]
        [:div
         [:h2 (formatter/format-link message)]
         [:p username]
         [:hr]])
      (navi page docsize)
      [:br]
      [:br]]])
