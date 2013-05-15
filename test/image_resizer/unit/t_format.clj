(ns image-resizer.unit.t-format
  (:require
   [midje.sweet          :refer :all]
   [image-resizer.format :refer :all]
   [clojure.java.io      :refer :all])
  (:import
   [javax.imageio ImageIO]))

(def test-image
  (file "test/fixtures/platypus.jpg"))

(def buffered-file
  (ImageIO/read test-image))

(facts "save-as-file"
  (fact "resize a file to a specific width and height outputting result as a file"
    (let [path (.getAbsolutePath test-image)
          new-file (save-as-file buffered-file path)]
      (.exists (as-file new-file)) => truthy)))
