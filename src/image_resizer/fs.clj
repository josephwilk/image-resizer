(ns image-resizer.fs
  (:require
   [clojure.string :as str]))

(defn file-name [file]
  (first (seq (.split (.getName file) "\\."))))

(defn path [file]
  (str/join "/" (butlast (seq (str/split (.getAbsolutePath file) #"/")))))

(defn extension [file]
  (last (seq (str/split (.getName file) #"\."))))

(defn new-filename [file dimensions]
  (str (path file) "/" (file-name file) "_" (str/join "x" dimensions) "." (extension file)))
