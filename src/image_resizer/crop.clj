(ns image-resizer.crop
  (:require
   [image-resizer.util :refer :all])
  (:import
   [org.imgscalr Scalr]))

(defn- bound-dim [original-dim coord dim]
  (if (> (+ coord dim) original-dim)
    (- original-dim coord)
    dim))

(defn crop-fn [x y width height]
  (fn [image]
    (let [buffered (buffered-image image)
          [original-width original-height] (dimensions buffered)
          crop-width (bound-dim original-width x width)
          crop-height (bound-dim original-height y height)]
      (Scalr/crop buffered x y crop-width crop-height nil))))

(defn crop-width-fn [width]
  (fn [image]
    (let [buffered (buffered-image image)
          [_ height] (dimensions buffered)]
      ((crop-fn 0 0 width height) image))))

(defn crop-height-fn [height]
  (fn [image]
    (let [buffered (buffered-image image)
          [width _] (dimensions buffered)]
      ((crop-fn 0 0 width height) image))))
