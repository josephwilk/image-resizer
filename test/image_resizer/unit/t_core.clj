(ns image-resizer.unit.t-core
  (:import
   [javax.imageio ImageIO])
  (:require
   [midje.sweet        :refer :all]
   [image-resizer.core :refer :all]
   [clojure.java.io    :refer :all]
   [clojure.java.shell :as shell]))

(namespace-state-changes (after :facts (shell/sh "rm" "-f" "test/fixtures/platypus_10x5.jpg")))

(def test-image
  (file "test/fixtures/platypus.jpg"))

(facts "resize"
  (fact "resize a file to a specific width and height respecting dimensions"
    (let [resized-image (resize test-image 10 10)]
      (dimensions (ImageIO/read test-image)) => [600 314]
      (dimensions resized-image) => [10 5]))

  (fact "resize a file to a specific width"
    (let [resized-image (resize test-image 100)]
      (dimensions (ImageIO/read test-image)) => [600 314]
      (first (dimensions resized-image)) => 100)))

(facts "resize-to-file"
  (fact "resize a file to a specific width and height"
    (let [resized-image (resize-to-file test-image 10 10)]
      (.exists (as-file "test/fixtures/platypus_10x5.jpg")) => truthy)))



