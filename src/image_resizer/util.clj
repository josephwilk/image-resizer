(ns image-resizer.util
(:import
   [java.io File Serializable]
   [javax.imageio ImageIO]
   [java.awt.image BufferedImage]))

(defn buffered-image [image]
  (if (instance? BufferedImage image)
    image
    (ImageIO/read ^java.io.Serializable image)))

(defn dimensions [image]
  [(.getWidth image) (.getHeight image)])
