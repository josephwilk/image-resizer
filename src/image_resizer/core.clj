(ns image-resizer.core
  (:require
   [clojure.string   :as str]
   [image-resizer.fs :as fs])
  (:import
   [java.io File]
   [javax.imageio ImageIO]
   [org.imgscalr Scalr]
   [org.imgscalr Scalr$Mode]))

(def- fit-width-mode
  Scalr$Mode/FIT_TO_WIDTH)

(def- fit-height-mode
  Scalr$Mode/FIT_TO_HEIGHT)

(defn- buffered-image [file] (ImageIO/read file))

(defn dimensions [image]
  [(.getWidth image) (.getHeight image)])

(defn resize-to-width [file width]
  (Scalr/resize (buffered-image file) fit-width-mode width nil))

(defn resize-to-height [file height]
  (Scalr/resize (buffered-image file) fit-height-mode height nil))

(defn resize [file width height]
  (Scalr/resize (buffered-image file) width height nil))