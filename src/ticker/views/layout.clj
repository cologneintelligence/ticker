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

(defn navi [page size]
  [:div
       (link-to (str (if (> size 9) (+ page 1))) "next")
       (str "&nbsp;&nbsp;&nbsp;")
       (link-to (str (if (> page 0 ) (- page 1))) "previous") ])

(defn content [messages page]
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
    (navi page (count messages))
    (for [{:keys [username message]} messages]
      [:div
       [:h2 (formatter/format-link message)]
       [:p username]
       [:hr]])
    (navi page (count messages))
    [:br]
    [:br]]])
