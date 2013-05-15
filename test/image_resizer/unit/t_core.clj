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

(facts "resize-to-file"
  (fact "resize a file to a specific width and height outputting result as a file"
    (let [resized-image (resize-to-file test-image 10 10)]
      (.exists (as-file "test/fixtures/platypus_10x5.jpg")) => truthy)))