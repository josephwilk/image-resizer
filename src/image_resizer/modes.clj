(ns image-resizer.modes
  (:import
   [org.imgscalr Scalr$Mode]))

(def fit-width-mode
  Scalr$Mode/FIT_TO_WIDTH)

(def fit-height-mode
  Scalr$Mode/FIT_TO_HEIGHT)

(def fit-exact-mode
  Scalr$Mode/FIT_EXACT)

