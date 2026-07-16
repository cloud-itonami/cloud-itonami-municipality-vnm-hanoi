(ns ordinance.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest hanoi-has-spec-basis
  (let [sb (facts/spec-basis "hanoi")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://") sb))
    (is (every? :ordinance/number sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "ho-chi-minh-city")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["hanoi" "ho-chi-minh-city"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ho-chi-minh-city"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= 2 (count (facts/by-topic "hanoi" :governance))))
  (is (empty? (facts/by-topic "hanoi" :labor)))
  (is (empty? (facts/by-topic "ho-chi-minh-city" :governance))))
