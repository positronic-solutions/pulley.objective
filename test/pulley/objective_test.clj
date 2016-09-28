(ns pulley.objective-test
  (:require [clojure.test :refer :all]
            [pulley.objective :refer :all]))

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
