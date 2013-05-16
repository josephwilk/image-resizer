(ns image-resizer.unit.t-pad
  (:require
   [midje.sweet                :refer :all]
   [image-resizer.unit.support :refer :all]
   [image-resizer.pad          :refer :all]
   [clojure.java.io            :refer :all]))

(fact "it should pad an image"
  test-image => (dimensions-of [600 314])
  ((pad-fn 10) test-image)   => (dimensions-of [620 334]))
