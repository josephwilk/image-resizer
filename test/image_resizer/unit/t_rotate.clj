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

(fact "it should rotate 90 dregrees counter clockwise"
  test-image => (dimensions-of [600 314])
  ((rotate-90-counter-clockwise-fn) test-image) => (dimensions-of [314 600]))

(fact "it should rotate 180 dregrees counter clockwise"
  test-image => (dimensions-of [600 314])
  ((rotate-180-counter-clockwise-fn) test-image) => (dimensions-of [600 314]))

(fact "it should rotate 270 dregrees counter clockwise"
  test-image => (dimensions-of [600 314])
  ((rotate-270-counter-clockwise-fn) test-image) => (dimensions-of [314 600]))
