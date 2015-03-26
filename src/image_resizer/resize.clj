(ns image-resizer.resize
  (:require
   [image-resizer.scale-methods :refer :all]
   [image-resizer.modes         :refer :all]
   [image-resizer.util          :refer :all])
  (:import
   [java.awt.image BufferedImageOp]
   [org.imgscalr Scalr]))

(def no-ops (into-array BufferedImageOp []))

(defn resize-height-fn
  ([height] (resize-height-fn height automatic))
  ([height scale-method]
     (fn [file]
       (Scalr/resize (buffered-image file) scale-method fit-height-mode height no-ops))))

(defn resize-width-fn
  ([width] (resize-width-fn width automatic))
  ([width scale-method]
     (fn [file]
       (Scalr/resize (buffered-image file) scale-method fit-width-mode width no-ops))))

(defn force-resize-fn
  ([width height] (force-resize-fn width height automatic))
  ([width height scale-method]
     (fn [file]
       (Scalr/resize (buffered-image file) scale-method fit-exact-mode width height no-ops))))

(defn resize-fn
  ([width height] (resize-fn width height automatic))
  ([width height scale-method]
     (fn [file]
       (Scalr/resize (buffered-image file) scale-method width height no-ops))))
