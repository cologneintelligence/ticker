(defproject
  ticker
  "0.1.0-SNAPSHOT"
  :repl-options
  {:init-ns ticker.repl}
  :dependencies
  [[ring-server "0.3.1"]
   [com.novemberain/monger "2.0.0-rc1"]
   [environ "0.5.0"]
   [com.taoensso/timbre "3.2.1"]
   [markdown-clj "0.9.44"]
   [org.clojure/clojure "1.6.0"]
   [com.taoensso/tower "2.0.2"]
   [log4j
    "1.2.17"
    :exclusions
    [javax.mail/mail
     javax.jms/jms
     com.sun.jdmk/jmxtools
     com.sun.jmx/jmxri]]
   [selmer "0.6.6"]
   [lib-noir "0.8.3"]]
  :ring
  {:handler ticker.handler/app,
   :init ticker.handler/init,
   :destroy ticker.handler/destroy}
  :cucumber-feature-paths
  ["test/features/"]
  :profiles
  {:uberjar {:aot :all},
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}},
   :dev
   {:dependencies
    [[org.clojure/core.cache "0.6.3"]
     [ring/ring-devel "1.2.2"]
     [clj-webdriver/clj-webdriver "0.6.1"]
     [ring-mock "0.1.5"]],
    :env {:dev true}}}
  :url
  "http://example.com/FIXME"
  :plugins
  [[lein-ring "0.8.10"] [lein-environ "0.5.0"] [lein-cucumber "1.0.2"]]
  :description
  "FIXME: write description"
  :min-lein-version "2.0.0")
