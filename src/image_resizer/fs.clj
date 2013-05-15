(ns image-resizer.fs
  (:require
   [clojure.string :as str]))

(defn- filename [name]
  (second (re-find #"(.+?)(\.[^.]*$|$)" (last (str/split name #"/")))))

(defn- path [name]
  (str/join "/" (butlast (seq (str/split name #"/")))))

(defn extension [name]
  (last (seq (str/split name #"\."))))

(defn new-filename [file-with-path dimensions]
  (str (path file-with-path) "/" (filename file-with-path) "_" (str/join "x" dimensions) "." (extension file-with-path)))
