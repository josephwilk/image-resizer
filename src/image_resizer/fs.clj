(ns image-resizer.fs
  (:require
   [clojure.string :as str]))

(defn- filename [name]
  (second (re-find #"(.+?)(\.[^.]*$|$)" name)))

(defn- path [path]
  (str/join "/" (butlast (seq (str/split path #"/")))))

(defn extension [name]
  (last (seq (str/split name #"\."))))

(defn new-filename [name file-path dimensions]
  (str (path file-path) "/" (filename name) "_" (str/join "x" dimensions) "." (extension name)))
