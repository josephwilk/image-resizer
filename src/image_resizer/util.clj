(ns image-resizer.util
(:import
   [java.io InputStream File Serializable]
   [java.net URL]
   [javax.imageio ImageIO]
   [javax.imageio.stream ImageInputStream]
   [java.awt.image BufferedImage]))

(defn ^BufferedImage buffered-image [image]
  (condp instance? image
    BufferedImage image
    File (ImageIO/read ^File image)
    InputStream (ImageIO/read ^InputStream image)
    ImageInputStream (ImageIO/read ^ImageInputStream image)
    URL (ImageIO/read ^URL image)))

(defn dimensions [^BufferedImage image]
  [(.getWidth image) (.getHeight image)])
