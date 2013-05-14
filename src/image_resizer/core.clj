(ns image-resizer.core
  (:require
   [clojure.string :as str])
  (:import
   [java.io File]
   [javax.imageio ImageIO]
   [org.imgscalr Scalr]))

(defn- file-name [file]
  (first (seq (.split (.getName file) "\\."))))

(defn- path [file]
  (str/join "/" (butlast (seq (.split (.getAbsolutePath file) "/")))))

(defn extension [file]
  (last (seq (.split (.getName file) "\\."))))

(defn- new-filename [file dimensions]
  (str (path file) "/" (file-name file) "_" (str/join "x" dimensions) "." (extension file)))

(defn dimensions [image]
  [(.getWidth image) (.getHeight image)])

(defn buffered-image [file] (ImageIO/read file))

(defn resize
  ([file width]
     (Scalr/resize (buffered-image file) width nil))
  ([file width height]
     (Scalr/resize (buffered-image file) width height nil)))

(defn resize-to-file [file width height]
  (let [resized-buffered-image (Scalr/resize (buffered-image file) width height nil)
        new-dimensions (dimensions resized-buffered-image)
        resized-file (File. (new-filename file new-dimensions))]
    (ImageIO/write resized-buffered-image (extension file) resized-file)))