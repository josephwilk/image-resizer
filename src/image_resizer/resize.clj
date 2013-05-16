(ns image-resizer.resize
  (:require
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

(defn resize-height-fn [height]
  (fn [file]
    (Scalr/resize (buffered-image file) fit-height-mode height nil)))

(defn resize-width-fn [width]
  (fn [file]
    (Scalr/resize (buffered-image file) fit-width-mode width nil)))

(defn force-resize-fn [width height]
  (fn [file]
    (Scalr/resize (buffered-image file) fit-exact-mode width height nil)))

(defn resize-fn [width height]
  (fn [file]
    (Scalr/resize (buffered-image file) width height nil)))