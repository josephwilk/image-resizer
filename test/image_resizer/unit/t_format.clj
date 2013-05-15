(ns image-resizer.unit.t-format
  (:require
   [midje.sweet        :refer :all]
   [image-resizer.core :refer :all]
   [clojure.java.io    :refer :all]))

(def test-image
  (file "test/fixtures/platypus.jpg"))

(facts "save-as-file"
  (fact "resize a file to a specific width and height outputting result as a file"
    (let [new-file (save-as-file test-image (resize test-image 10 10))]
      (.exists (as-file new-file)) => truthy)))
