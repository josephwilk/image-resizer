(ns image-resizer.crop
  (:require
   [image-resizer.util :refer :all])
  (:import
   [org.imgscalr Scalr]))

(defn crop-fn [width height]
  (fn [image]
    (let [buffered (buffered-image image)
          [original-width original-height] (dimensions buffered)
          crop-width (min original-width width)
          crop-height (min original-height height)]
      (Scalr/crop buffered 0 0 crop-width crop-height nil))))

(defn crop-width-fn [width]
  (fn [image]
    (let [buffered (buffered-image image)
          [_ height] (dimensions buffered)]
      ((crop-fn width height) image))))

(defn crop-height-fn [height]
  (fn [image]
    (let [buffered (buffered-image image)
          [width _] (dimensions buffered)]
      ((crop-fn width height) image))))
