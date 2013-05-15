(ns image-resizer.format
  (:require
   [image-resizer.fs :as fs]
   [image-resizer.core :refer :all])
  (:import
   [java.io File]
   [javax.imageio ImageIO]))

(defn save-as-file [original-file buffered-file]
  (let [new-dimensions (dimensions buffered-file)
        name (.getName original-file)
        path (.getAbsolutePath original-file)
        resized-file (File. (fs/new-filename name path new-dimensions))]
    (ImageIO/write buffered-file (fs/extension name) resized-file)
    (.getAbsolutePath resized-file)))