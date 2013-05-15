(ns image-resizer.unit.t-fs
  (:require
   [midje.sweet      :refer :all]
   [image-resizer.fs :refer :all]))

(fact "it should get file extension"
  (extension "thi.ngey.png") => "png")

(fact "it should generate a new filename respecting crazy .s in filename/dirs"
  (new-filename "/t.mp/mon.ke.y.png" [10 10]) => "/t.mp/mon.ke.y_10x10.png")