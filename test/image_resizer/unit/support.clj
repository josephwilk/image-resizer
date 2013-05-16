(ns image-resizer.unit.support
  (:require
   [clojure.java.io    :refer :all]
   [image-resizer.util :refer :all])
  (:import
   [javax.imageio ImageIO]))

(def test-image
  (file "test/fixtures/platypus.jpg"))

(defn dimensions-of [[width height]]
  (fn [file]
    (let [buffered-file (if (= java.io.File (type file)) (ImageIO/read file) file)]
      (= [width height]
         (dimensions buffered-file)))))
