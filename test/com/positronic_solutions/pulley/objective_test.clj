;; Copyright 2016 Positronic Solutions, LLC.
;;
;; This file is part of pulley.objective.
;;
;; pulley.objective is free software: you can redistribute it and/or modify
;; it under the terms of the GNU Lesser General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.
;;
;; pulley.objective is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.
;;
;; You should have received a copy of the GNU Lesser General Public License
;; along with pulley.objective.  If not, see <http://www.gnu.org/licenses/>.
(ns com.positronic-solutions.pulley.objective-test
  (:require [clojure.test :refer :all]
            [com.positronic-solutions.pulley.objective :refer :all]))

(deftest test-attr-lookup
  (let [obj1 (map->object {::foo "foo"
                           ::bar +})
        obj2 (map->object {::foo obj1})]
    (testing "lookup"
      (is (= (obj1 ::foo)
             (obj2 ::foo ::foo)
             "foo"))
      (is (= (obj1 ::bar)
             (obj2 ::foo ::bar)
             +)))
    (testing "invocation"
      (is (= (obj1 ::bar 3 4)
             (obj2 ::foo ::bar 3 4)
             7))
      (is (= (obj1 ::bar 5 6)
             (obj2 ::foo ::bar 5 6)
             11)))
    (testing "lookup failure"
      (testing "basic"
        (is (thrown? IllegalArgumentException
                     #"Attribute not found"
                     (obj1 ::does-not-exist))))
      (testing "nested"
        (is (thrown? IllegalArgumentException
                     #"Attribute not found"
                     (obj2 ::foo ::does-not-exist))))
      (testing "invocation"
        (is (thrown? IllegalArgumentException
                     #"Attribute not found"
                     (obj1 ::does-not-exist 1 2)))))
    (testing "apply"
      (doseq [n (range 1 30)]
        (testing n
          (is (= (apply obj1 ::bar (range n))
                 (apply obj2 ::foo ::bar (range n))
                 (apply + (range n)))))))))

(deftest test-property
  (let [obj1 (map->object {::foo 1
                           ::bar (property :get (fn [instance]
                                                  (instance ::foo)))
                           ::baz (property :get (fn [instance]
                                                  (-> (instance ::bar)
                                                      (inc))))})]
    (is (= (obj1 ::bar)
           (obj1 ::foo)
           1))
    (is (= (obj1 ::baz))
        2)))

(deftest test-method
  (let [obj1 (map->object {::multiple 3
                           ::method (method [self & xs]
                                      (* (self ::multiple)
                                         (reduce + xs)))})]
    (testing "invocation"
      (is (= (obj1 ::method 1)
             3))
      (is (= (obj1 ::method 1 2)
             9)))
    (testing "apply"
      (doseq [n (range 1 30)]
        (testing n
          (is (= (apply obj1 ::method (range n))
                 (* (obj1 ::multiple)
                    (reduce + (range n)))
                 (* 3 (reduce + (range n))))))))))

(deftest test-assoc
  (let [attrs {::foo 1}
        obj   (map->object attrs)]
    (is (= obj attrs))
    (is (= (assoc obj ::foo 2)
           (assoc attrs ::foo 2)))
    (is (= (get (assoc obj ::foo 2) ::foo)
           ((assoc obj ::foo 2) ::foo)
           (get (assoc attrs ::foo 2) ::foo)
           2))))
