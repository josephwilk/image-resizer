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

(defn as-stream
  "image-format something like jpg, png.."
  [buffered-image image-format]
  (let [baos (ByteArrayOutputStream.)]
    (ImageIO/write buffered-image image-format baos)
    (ByteArrayInputStream. (.toByteArray baos))))

(defn as-stream-by-mime-type
  "mime-type something like image/jpeg, image/png.."
  [buffered-image mime-type]
  (let [baos (ByteArrayOutputStream.)]
    (let [writer (.next (ImageIO/getImageWritersByMIMEType mime-type))]
        (.setOutput writer (ImageIO/createImageOutputStream baos))
        (.write writer buffered-image)
        (ByteArrayInputStream. (.toByteArray baos)))))
