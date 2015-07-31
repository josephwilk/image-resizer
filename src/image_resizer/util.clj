(ns image-resizer.util
(:import
   [java.io File]
   [javax.imageio ImageIO]
   [java.awt.image BufferedImage]))

(defn buffered-image [image]
  (if (instance? BufferedImage image)
    image
    (ImageIO/read ^java.io.ByteArrayInputStream image)))

(defn dimensions [image]
  [(.getWidth image) (.getHeight image)])
