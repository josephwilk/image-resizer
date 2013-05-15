(ns image-resizer.core
  (:require
   [clojure.string   :as str]
   [image-resizer.fs :as fs])
  (:import
   [java.io File]
   [javax.imageio ImageIO]
   [org.imgscalr Scalr]
   [org.imgscalr Scalr$Mode]))

(def fit-width-mode
  Scalr$Mode/FIT_TO_WIDTH)

(def fit-height-mode
  Scalr$Mode/FIT_TO_HEIGHT)

(defn- buffered-image [file] (ImageIO/read file))

(defn dimensions [image]
  [(.getWidth image) (.getHeight image)])

(defn resize-to-width [file width]
  (Scalr/resize (buffered-image file) fit-width-mode width nil))

(defn resize-to-height [file height]
  (Scalr/resize (buffered-image file) fit-height-mode height nil))

(defn resize
  ([file width]
     (Scalr/resize (buffered-image file) width nil))
  ([file width height]
     (Scalr/resize (buffered-image file) width height nil)))

(defn resize-to-file [file width height]
  (let [resized-buffered-image (Scalr/resize (buffered-image file) width height nil)
        new-dimensions (dimensions resized-buffered-image)

        name (.getName file)
        path (.getAbsolutePath file)

        resized-file (File. (fs/new-filename name path new-dimensions))]
    (ImageIO/write resized-buffered-image (fs/extension name) resized-file)))