(ns image-resizer.unit.t-format
  (:require
   [midje.sweet          :refer :all]
   [image-resizer.format :refer :all]
   [image-resizer.fs     :as fs]
   [clojure.java.io      :as io])
  (:import
   [java.io InputStream]
   [javax.imageio ImageIO]))

(def test-image
  (io/file "test/fixtures/platypus.jpg"))

(def buffered-file
  (ImageIO/read test-image))

(defn is-a [class]
  (fn [thing]
    (instance? class thing)))

(facts "as-file"
  (fact "writes a buffered image to a file"
    (let [path (.getAbsolutePath test-image)
          new-file (as-file buffered-file path)]
      (.exists (io/as-file new-file)) => truthy)))

(facts "as-stream"
  (fact "writes a buffered image to an input stream"
    (let [stream (as-stream buffered-file (fs/extension (.getAbsolutePath test-image)))]
      stream => (is-a InputStream))))