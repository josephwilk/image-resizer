(ns image-resizer.ops
  (:import
   [org.imgscalr Scalr]))

(def antialias-op
  Scalr/OP_ANTIALIAS)

(def brighter-op
  Scalr/OP_BRIGHTER)

(def darker-op
  Scalr/OP_DARKER)

(def grayscale-op
  Scalr/OP_GRAYSCALE)