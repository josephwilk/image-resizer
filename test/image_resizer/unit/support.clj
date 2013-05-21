(ns image-resizer.unit.support
  (:require
   [clojure.java.io    :refer :all]
   [image-resizer.util :refer :all]
   [midje.sweet :refer :all])
  (:import
   [javax.imageio ImageIO]
   [java.io ByteArrayOutputStream]))

(def test-image
  (file "test/fixtures/platypus.jpg"))

(defn- size-of-file-type [file]
  (.length file))

(defn- size-of-buffered-image-type [buffered-image]
  (let [baos (ByteArrayOutputStream.)
        file (ImageIO/write buffered-image "jpg" baos)]
    (.close baos)
    (.size baos)))

(defn- size-of-file [file]
  (if (instance? java.io.File file) (size-of-file-type file) (size-of-buffered-image-type file)))

(defn size-of [size]
  (chatty-checker [file]
    (= size (size-of-file file))))

(defn dimensions-of [[width height]]
  (fn [file]
    (let [buffered-file (if (= java.io.File (type file)) (ImageIO/read file) file)]
      (= [width height]
         (dimensions buffered-file)))))
