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
      [:div
       (if (> page 0)
         (link-to (str (if (> page 0 ) (- page 1))) "newer"))
       (if (> page 0)
         (str "&nbsp;&nbsp;&nbsp;"))
       (if (> docsize (* (+ 1 page) 10))
         (link-to (str (if (> docsize actcount) (+ page 1))) "older"))])))

  (defn content [messages page docsize]
    [:div.main {:class "row"}
     [:div [:h1 "ticker"]
      [:h3 {:class "subheader"} "tick it out"]
      [:hr]
      ]
     (form-to [:post "/"]
              [:label "Name"
               [:input {:placeholder "Username goes here" :type "text" :name "username"}]]
              [:label "Message"
               [:textarea {:placeholder "Message goes here" :name "message"}]]
              [:br]
              (submit-button {:class "small round button"} "comment"))
     [:div.content
      (navi page docsize)
      (for [{:keys [username message]} messages]
        [:div {:class "panel"}
         [:h2 (formatter/format-link message)]
         [:p username]])
      (navi page docsize)
      [:br]
      [:br]]])
