(ns image-resizer.core
  (:require
   [clojure.string   :as str]
   [image-resizer.fs :as fs]
   [image-resizer.modes :refer :all])
  (:import
   [java.io File]
   [javax.imageio ImageIO]
   [java.awt.image BufferedImage]
   [org.imgscalr Scalr]))

(defn- buffered-image [image]
  (if (instance? BufferedImage image)
    image
    (ImageIO/read image)))

(defn dimensions [image]
  [(.getWidth image) (.getHeight image)])

(defn resize-to-width [file width]
  (Scalr/resize (buffered-image file) fit-width-mode width nil))

(defn resize-to-height [file height]
  (Scalr/resize (buffered-image file) fit-height-mode height nil))

(defn resize [file width height]
  (Scalr/resize (buffered-image file) width height nil))

(defn force-resize [file width height]
  (Scalr/resize (buffered-image file) fit-exact-mode width height nil))

(defn crop [image x y width height]
  (Scalr/crop (buffered-image image) x y width height nil))

(defn crop-to-width [image x width]
  (let [buffered (buffered-image image)
        y 0
        [_ height] (dimensions buffered)]
    (Scalr/crop buffered x y width height nil)))

(defn crop-to-height [image y height]
  (let [buffered (buffered-image image)
        x 0
        [width _] (dimensions buffered)]
    (Scalr/crop buffered x y width height nil)))

(defn resize-and-crop [image width height]
  (let [resized-image (resize image width height)]
      (crop resized-image 0 0 width height)))