(ns ticker.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.form :refer :all]
            [ticker.formatter :as formatter])
  (:use hiccup.element ))

(defn common [& body]
  (html5
   [:head
    [:title "tick mal!"]
    (include-css "/css/foundation.css")
    (include-js "/js/vendor/modernizr.js")]
   [:body body]))

(defn navi [page docsize]
  (let [pagesize 10 actcount (* page pagesize)]
    (if (> docsize pagesize)
      [:ul {:class "pagination"}
       (if (> page 0)
         [:li (link-to (str (if (> page 0 ) (- page 1))) "newer")]
         [:li {:class "unavailable"} (link-to "" "newer")]
       )
       (if (> docsize (* (+ 1 page) 10))
         [:li (link-to (str (if (> docsize actcount) (+ page 1))) "older")]
         [:li {:class "unavailable"} (link-to "" "older")]         
         )
       ])))

  (defn content [messages page docsize]
    [:div.main {:class "large-4 row"}
     [:div [:h1 "Ticker"]
      [:h4 {:class "subheader"} "simple message ticker"]
      ]
     [:div {:class "panel callout"}
     (form-to [:post "/"]
              [:label "Name"
               [:input {:placeholder "Username goes here" :type "text" :name "username"}]]
              [:label "Message"
               [:textarea {:placeholder "Message goes here" :name "message"}]]
              (submit-button {:class "button"} "comment"))]
     [:div.content
      (navi page docsize)
      (for [{:keys [username message timestamp]} messages]
        [:div {:class "panel"}
         [:h3 (formatter/format-link message)]
         [:p (str (.format (java.text.SimpleDateFormat. "dd.MM.yyyy HH:mm") timestamp) " | " username  )] ])
      (navi page docsize)
      [:br]
      [:br]]])
