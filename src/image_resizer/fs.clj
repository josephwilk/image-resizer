(ns image-resizer.fs
  (:require
   [clojure.string :as str]))

(defn- filename [name]
  (second (re-find #"([^/]+)(?=\.\w+$)" name)))

(defn- path [name]
  (second (re-find #"(.*)/" name)))

(defn ^String extension [name]
  (re-find #"\w+$" name))

(defn ^String new-filename [file-with-path dimensions]
  (format "%s/%s_%s.%s" (path file-with-path)
                        (filename file-with-path)
                        (str/join "x" dimensions)
                        (extension file-with-path)))
