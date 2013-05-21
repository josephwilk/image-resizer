(ns image-resizer.unit.t-resize
  (:require [midje.sweet :refer :all]
            [image-resizer.scale-methods :refer :all]
            [image-resizer.resize :refer :all]
            [image-resizer.unit.support :refer :all]))

(fact "ultra quality"
  test-image => (size-of 243972)
  ((resize-fn 500 500 ultra-quality) test-image) => (size-of 21999))

(fact "quality"
  test-image => (size-of 243972)
  ((resize-fn 500 500 quality) test-image) => (size-of 23555))

(fact "speed"
  test-image => (size-of 243972)
  ((resize-fn 500 500 speed) test-image) => (size-of 24903))

(fact "balanced"
  test-image => (size-of 243972)
  ((resize-fn 500 500 balanced) test-image) => (size-of 21603))