(ns image-resizer.unit.t-core
  (:import
   [javax.imageio ImageIO])
  (:require
   [midje.sweet :refer :all]
   [image-resizer.core :refer :all]
   [clojure.java.io :refer :all]))

(def test-image
  (file "test/fixtures/platypus.jpg"))

(fact "resize a file to a specific width and height respecting dimensions"
  (let [resized-image (resize test-image 10 10)]
    (dimensions (ImageIO/read test-image)) => [600 314]
    (dimensions resized-image) => [10 5]))
