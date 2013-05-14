(ns image-resizer.core
  (:require
   [clojure.string :as str]
   [image-resizer.fs :as fs])
  (:import
   [java.io File]
   [javax.imageio ImageIO]
   [org.imgscalr Scalr]))

(defn- buffered-image [file] (ImageIO/read file))

(defn dimensions [image]
  [(.getWidth image) (.getHeight image)])

(defn resize
  ([file width]
     (Scalr/resize (buffered-image file) width nil))
  ([file width height]
     (Scalr/resize (buffered-image file) width height nil)))

(defn resize-to-file [file width height]
  (let [resized-buffered-image (Scalr/resize (buffered-image file) width height nil)
        new-dimensions (dimensions resized-buffered-image)
        resized-file (File. (fs/new-filename file new-dimensions))]
    (ImageIO/write resized-buffered-image (fs/extension file) resized-file)))