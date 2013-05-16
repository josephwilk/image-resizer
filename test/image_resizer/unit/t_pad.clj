(ns image-resizer.unit.t-pad
  (:require
   [midje.sweet :refer :all]
   [image-resizer.pad :refer :all]
   [clojure.java.io    :refer :all]))

(def test-image
  (file "test/fixtures/platypus.jpg"))

(fact "it should pad an image"
  ((pad-fn 10) test-image))
