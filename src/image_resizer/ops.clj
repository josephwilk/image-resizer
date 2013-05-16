(ns image-resizer.ops
  (:import
   [org.imgscalr Scalr]))

(def antialias
  Scalr/OP_ANTIALIAS)

(def brighter
  Scalr/OP_BRIGHTER)

(def darker
  Scalr/OP_DARKER)

(def grayscale
  Scalr/OP_GRAYSCALE)