(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest hanoi-has-culture-basis
  (let [sb (facts/spec-basis "hanoi")]
    (is (= 9 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "hanoi" (:culture/municipality %)) sb))
    (is (every? #(= "VNM" (:culture/country %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-municipality-has-no-basis
  (is (nil? (facts/spec-basis "da-nang")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["hanoi" "da-nang"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["da-nang"] (:missing-municipalities c)))))

(deftest by-kind-filters
  (is (= 3 (count (facts/by-kind "hanoi" :dish))))
  (is (= ["hanoi.beverage.egg-coffee"]
         (mapv :culture/id (facts/by-kind "hanoi" :beverage))))
  (is (= ["hanoi.craft.bat-trang-ceramics"]
         (mapv :culture/id (facts/by-kind "hanoi" :craft))))
  (is (empty? (facts/by-kind "da-nang" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
