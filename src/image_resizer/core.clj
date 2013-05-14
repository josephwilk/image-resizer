(ns image-resizer.core
  (:import
   [java.io File]
   [javax.imageio ImageIO]
   [org.imgscalr Scalr]))

(defn dimensions [image]
  [(.getWidth image) (.getHeight image)])

(defn resize [file width height]
  (let [buffered-image (ImageIO/read file)
        output (File. "resized.jpg")]
    (Scalr/resize buffered-image width height nil)))
