(ns image-resizer.resize
  (:require
   [image-resizer.modes :refer :all]
   [image-resizer.util :refer :all])
  (:import
   [org.imgscalr Scalr]))

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