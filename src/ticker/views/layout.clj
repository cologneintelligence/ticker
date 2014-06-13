(ns ticker.views.layout
  (:require [hiccup.page :refer [html5 include-css]]))

(defn common [& body]
  (html5
    [:head
     [:title "tick mal!"]
     (include-css "/css/screen.css")]
    [:body body]))

(defn content [messages]
  [:div.content
   (for [{:keys [username message]} messages]
     [:div
      [:h2 message]
      [:p username]])])
