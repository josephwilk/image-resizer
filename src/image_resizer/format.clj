(ns image-resizer.format
  (:require
   [image-resizer.fs :as fs]
   [image-resizer.core :refer :all])
  (:import
   [java.io File ByteArrayOutputStream ByteArrayInputStream]
   [javax.imageio ImageIO]))

(defn as-file [buffered-file file-with-path]
  (let [new-dimensions (dimensions buffered-file)
        resized-file (File. (fs/new-filename file-with-path new-dimensions))]
    (ImageIO/write buffered-file (fs/extension file-with-path) resized-file)
    (.getAbsolutePath resized-file)))

(defn as-stream [buffered-image mime-type]
  (let [baos (ByteArrayOutputStream.)]
    (ImageIO/write buffered-image mime-type baos)
    (ByteArrayInputStream. (.toByteArray baos))))