(ns ticker.test.formatter
  (:use clojure.test
	ticker.formatter))


(deftest test-app
  (testing "format single link"
	   (is (= "<a href=http://bla>http://bla</a>" (format-link "http://bla") )))
  
  (testing "format single link with dots"
	   (is (= "<a href=http://www.bla.com>http://www.bla.com</a>" (format-link "http://www.bla.com") )))

  (testing "format single link with minus"
	   (is (= "<a href=http://www.bla-bla.com>http://www.bla-bla.com</a>" (format-link "http://www.bla-bla.com") )))

  (testing "format single link with https"
	   (is (= "<a href=https://www.bla.com>https://www.bla.com</a>" (format-link "https://www.bla.com") )))

  (testing "format single link with slash"
	   (is (= "<a href=https://www.bla.com/blub>https://www.bla.com/blub</a>" (format-link "https://www.bla.com/blub") )))

  (testing "format link with text around"
	   (is (= "here is the link <a href=http://bla>http://bla</a> grate or not?" (format-link "here is the link http://bla grate or not?") ))))


