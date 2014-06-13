(ns ticker.views.layout
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.form :refer :all]))

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
      [:p username]])]
  (form-to [:post "/"]
     [:p "Name:"]
     (text-field "username" "Benutzername goes here")

     [:p "Message:"]
     (text-area {:rows 10 :cols 40} "message" "Message goes here")

     [:br]
     (submit-button "comment")))
