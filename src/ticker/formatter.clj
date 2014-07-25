(ns ticker.formatter)

(defn format-link [text]
  (clojure.string/replace text #"http[s]?://[0-9A-Za-z\.\-\/#_&\?=]*" #(str "<a href=" %1 ">" %1 "</a>")))
