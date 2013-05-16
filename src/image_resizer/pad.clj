(ns image-resizer.pad
  (:require
   [image-resizer.util :refer :all])
  (:import
   [org.imgscalr Scalr]))

(defn pad-fn [padding-size]
  (fn [image]
    (Scalr/pad (buffered-image image) padding-size nil)))