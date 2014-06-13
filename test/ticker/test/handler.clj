(ns ticker.test.handler
  (:use clojure.test
        ring.mock.request
        ticker.handler)
  (:require [ticker.models.message :as message]))

(deftest test-app
  (with-redefs [message/messages (fn [] "")]
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200)))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
