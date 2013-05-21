(defproject image-resizer "0.1.2"
  :description "Resize/Crop/Pad images"
  :url "https://github.com/josephwilk/image-resizer"
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [org.imgscalr/imgscalr-lib "4.2"]]

  :profiles {:dev {:dependencies [[midje "1.5.1"]]
                   :plugins [[lein-midje "3.0.1"]]}})
