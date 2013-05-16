(ns image-resizer.unit.t-core
  (:import
   [javax.imageio ImageIO])
  (:require
   [midje.sweet        :refer :all]
   [image-resizer.core :refer :all]
   [clojure.java.io    :refer :all]
   [clojure.java.shell :as shell]))

(namespace-state-changes (after :facts (shell/sh "rm" "-f" "test/fixtures/")))

(def test-image
  (file "test/fixtures/platypus.jpg"))

(defn dimensions-of [[width height]]
  (fn [file]
    (let [buffered-file (if (= java.io.File (type file)) (ImageIO/read file) file)]
      (= [width height]
         (dimensions buffered-file)))))

(fact "resize to width"
  (resize-to-width test-image 44) => (dimensions-of [44 23]))

(fact "resize to height"
  (resize-to-height test-image 66) => (dimensions-of [126 66]))

(facts "resize"
  (fact "resize a file to a specific width and height respecting dimensions"
    (let [resized-image (resize test-image 10 10)]
      test-image    => (dimensions-of [600 314])
      resized-image => (dimensions-of [10 5]))))

(fact "force a resize"
  (force-resize test-image 100 1) => (dimensions-of [100 1]))

(fact "crop"
  (crop test-image 30 40) => (dimensions-of [30 40]))

(fact "crop to width"
  (crop-to-width test-image 30) => (dimensions-of [30 314]))

(fact "crop to height"
  (crop-to-height test-image 30) => (dimensions-of [600 30]))

(fact "resize the image and crop to reach the desired size"
  (resize-and-crop test-image 44 10) => (dimensions-of [44 10]))