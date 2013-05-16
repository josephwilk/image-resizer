(ns image-resizer.unit.t-rotate
  (:require
   [midje.sweet                :refer :all]
   [clojure.java.io            :refer :all]
   [image-resizer.rotate       :refer :all]
   [image-resizer.unit.support :refer :all]))

(fact "it should flip the image vertically"
  test-image => (dimensions-of [600 314])
  ((flip-vertically-fn) test-image)  => (dimensions-of [600 314]))

(fact "it should flip the image horizontally"
  test-image => (dimensions-of [600 314])
  ((flip-horizontally-fn) test-image) => (dimensions-of [600 314]))