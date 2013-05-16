(ns image-resizer.core
  (:require
   [clojure.string   :as str]
   [image-resizer.fs :as fs]
   [image-resizer.resize :refer :all]
   [image-resizer.crop :refer :all]))

(defn dimensions [image]
  [(.getWidth image) (.getHeight image)])

(defn resize-to-width [image width]
  ((resize-width-fn width) image))

(defn resize-to-height [image height]
  ((resize-height-fn height) image))

(defn resize [image width height]
  ((resize-fn width height) image))

(defn force-resize [image width height]
  ((force-resize-fn width height) image))

(defn crop [image width height]
  ((crop-fn width height) image))

(defn crop-to-width [image width]
  ((crop-width-fn width) image))

(defn crop-to-height [image height]
  ((crop-height-fn height) image))

(defn resize-and-crop [image width height]
  (->
   image
   ((resize-fn width height))
   ((crop-fn width height))))
