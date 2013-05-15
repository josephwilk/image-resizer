(ns image-resizer.core
  (:require
   [clojure.string   :as str]
   [image-resizer.fs :as fs]
   [image-resizer.modes :refer :all])
  (:import
   [java.io File]
   [javax.imageio ImageIO]
   [org.imgscalr Scalr]))

(defn- buffered-image [file] (ImageIO/read file))

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