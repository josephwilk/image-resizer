(ns image-resizer.modes
  (:import
   [org.imgscalr Scalr$Mode]))

(def ^Scalr$Mode fit-width-mode
  Scalr$Mode/FIT_TO_WIDTH)

(def ^Scalr$Mode fit-height-mode
  Scalr$Mode/FIT_TO_HEIGHT)

(def ^Scalr$Mode fit-exact-mode
  Scalr$Mode/FIT_EXACT)

