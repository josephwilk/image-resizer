(ns image-resizer.rotate
  (:require
   [image-resizer.util :refer :all])
  (:import
   [org.imgscalr Scalr]
   [org.imgscalr Scalr$Rotation]))

(def counter-clockwise-90
  Scalr$Rotation/CW_270)

(def counter-clockwise-180
  Scalr$Rotation/CW_180)

(def counter-clockwise-270
  Scalr$Rotation/CW_90)

(def flip-horizontal
  Scalr$Rotation/FLIP_HORZ)

(def flip-vertical
  Scalr$Rotation/FLIP_VERT)

(defn flip-vertically-fn []
  (fn [image]
    (Scalr/rotate (buffered-image image) flip-vertical nil)))

(defn flip-horizontally-fn []
  (fn [image]
    (Scalr/rotate (buffered-image image) flip-horizontal nil)))

(defn rotate-90-counter-clockwise-fn []
  (fn [image]
    (Scalr/rotate (buffered-image image) counter-clockwise-90 nil)))

(defn rotate-180-counter-clockwise-fn []
  (fn [image]
    (Scalr/rotate (buffered-image image) counter-clockwise-180 nil)))

(defn rotate-270-counter-clockwise-fn []
  (fn [image]
    (Scalr/rotate (buffered-image image) counter-clockwise-270 nil)))
