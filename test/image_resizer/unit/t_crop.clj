(ns image-resizer.unit.t-crop
  (:require
   [midje.sweet                :refer :all]
   [image-resizer.unit.support :refer :all]
   [image-resizer.crop         :refer :all]))

(facts "about cropping to width and height"
  (fact "crops the image to the given width and height"
    ((crop-fn 0 0 100 200) test-image) => (dimensions-of [100 200]))

  (fact "does not crop the width when the given width is bigger than images width"
    ((crop-fn 0 0 12345 200) test-image) => (dimensions-of [600 200]))

  (fact "does not crop the height when the given height is bigger than images height"
    ((crop-fn 0 0 100 12345) test-image) => (dimensions-of [100 314])))

(facts "about cropping to width and height from a starting coordinate"
  (fact "crops the image to the given width and height starting at a coord"
    ((crop-fn 80 30 100 200 ) test-image) => (dimensions-of [100 200])))


(facts "about cropping to width"
  (fact "crops the image to the given width"
    ((crop-width-fn 100) test-image) => (dimensions-of [100 314]))

  (fact "does not crop when the given width is bigger than images width"
    ((crop-width-fn 12345) test-image) => (dimensions-of [600 314])))

(facts "about cropping to height"
  (fact "crops the image to the given height"
    ((crop-height-fn 200) test-image) => (dimensions-of [600 200]))

  (fact "does not crop when the given height is bigger than images height"
    ((crop-height-fn 12345) test-image) => (dimensions-of [600 314])))

