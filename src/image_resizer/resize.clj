(ns image-resizer.resize
  (:require
   [image-resizer.scale-methods :refer :all]
   [image-resizer.modes         :refer :all]
   [image-resizer.util          :refer :all])
  (:import
   [java.awt.image BufferedImageOp BufferedImage]
   [org.imgscalr Scalr Scalr$Method]))

(def ^{:tag (Class/forName "[Ljava.awt.image.BufferedImageOp;")} no-ops
  (into-array BufferedImageOp []))

(defn resize-height-fn
  ([height] (resize-height-fn height automatic))
  ([height ^Scalr$Method scale-method]
     (fn [file]
       (Scalr/resize (buffered-image file) scale-method fit-height-mode (int height) no-ops))))

(defn resize-width-fn
  ([width] (resize-width-fn width automatic))
  ([width ^Scalr$Method scale-method]
     (fn [file]
       (Scalr/resize (buffered-image file) scale-method fit-width-mode (int width) no-ops))))

(defn force-resize-fn
  ([width height] (force-resize-fn width height automatic))
  ([width height ^Scalr$Method scale-method]
     (fn [file]
       (Scalr/resize (buffered-image file) scale-method fit-exact-mode (int width) (int height) no-ops))))

(defn resize-fn
  ([width height] (resize-fn width height automatic))
  ([width height ^Scalr$Method scale-method]
     (fn [file]
       (Scalr/resize (buffered-image file) scale-method (int width) (int height) no-ops))))
