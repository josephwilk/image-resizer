#Clojure Image Resizer

[![Build Status](https://travis-ci.org/josephwilk/image-resizer.png?branch=master)](https://travis-ci.org/josephwilk/image-resizer)

[![Clojars Project](http://clojars.org/image-resizer/latest-version.svg)](http://clojars.org/image-resizer)

####(Drink to make small)

<img alt="Make smaller" src="http://www.cs.cmu.edu/~rgs/alice03a.gif" height="250px" /> <img alt="Drink to make small" src="http://www.cs.cmu.edu/~rgs/alice04a.gif" height="250px" />

## Why?

* Very fast (supports hardware accelerated operations on most platforms)
* No native libraries to install (I'm looking at you imagemagick)

Image Resizer under the hood wraps [imgscalr](https://github.com/thebuzzmedia/imgscalr).

##Installation

Add the following dependency to your project.clj file:

[![Clojars Project](http://clojars.org/image-resizer/latest-version.svg)](http://clojars.org/image-resizer)

##Usage

### Pipelining Transforms

Image resizer creates tranforms which return fns that apply that transform to an image.

A number of transforms are supported:

* resize
* crop
* pad
* rotate

If you want to perform a number of operations across an image (such as resize, crop, flip & pad):

```clojure
(require [image-resizer.crop :refer :all])
(require [image-resizer.resize :refer :all])
(require [image-resizer.pad :refer :all])
(require [image-resizer.rotate :refer :all])

(-> image
    ((resize-fn 100 100))
    ((crop-fn 100 100))
    ((rotate-vertically-fn))
    ((pad-fn 10)))


;Or if you want to apply a transform to a batch of images in parallel:

(doall (clojure.core/pmap (resize-fn 100 100) [image1 image2 image3 image4]))
```

### Tweaking quality/speed ratio

You can tweak your preferences for a resize transform. Favouring speed or quality:

```clojure
(require [image-resizer.resize :refer :all])
(require [image-resizer.scale-methods :refer :all])

(resize-fn 100 100 ultra-quality) ;best quality
(resize-fn 100 100 speed)         ;fast as possible resize
```

### Lazy helpers around transforms

If creating your own pipelines seems a bit funky, you can use some nice helpers for commmon operations:

```clojure
(require [image-resizer.core :refer :all])

;Resize an image while respecting original ratio
;Notice how the height is not 10 to respect the ratio of the image
(resize (file "white-rabbit.jpg") 10 10)          ; => #<BufferedImage width=10 height=4>

;Resize an image to a width
(resize-to-width (file "queen-of-hearts.jpg") 10) ; => #<BufferedImage width=10 height=4>

;Resize an image to a height
(resize-to-height (file "cheshire-cat.jpg") 10)   ; => #<BufferedImage width=5 height=10>

;Force width and a height resize (ignoring ratios)
(force-resize (file "cheshire-cat.jpg") 10 1000)   ; => #<BufferedImage width=10 height=1000>

;Crop the image width
(crop-to-width (file "tea-party/mad-hatter.jpg") 10) ; => #<BufferedImage width=10 height=1000>

;Crop the image height
(crop-to-height (file "tea-party/mad-hatter.jpg") 10) ; => #<BufferedImage width=1000 height=10>

;Crop the image width and height
(crop (file "tea-party/mad-hatter.jpg") 10 20) ; => #<BufferedImage width=10 height=20>

;Crop the image width and height from a coord
(crop-from (file "tea-party/mad-hatter.jpg") 6 7 10 20) ; => #<BufferedImage width=10 height=20>

;Resize the image maintaining proportions and then crop it to the specified width and height
(resize-and-crop (file "tea-party/mad-hatter.jpg") 10 10) ; => #<BufferedImage width=10 height=10>
```

### Turning BufferedImage into something useful

```Clojure
(require [image-resizer.format :as format])

;Saving as an auto-named file
(format/as-file
  (resize (file "tea-party/mad-hatter.jpg") 10 10)
  "/tmp/tea-party/mad-hatter.jpg") ; => "/tmp/tea-party/mad-hatter_10x5.jpg"

;Saving under a specific name
(format/as-file
  (resize (file "tea-party/mad-hatter.jpg") 10 10)
  "/tmp/tea-party/tiny-hatter.jpg"
  :verbatim) ; => "/tmp/tea-party/tiny-hatter.jpg"

;To a stream (Useful for s3)
(format/as-stream (resize (file "tea-party/mad-hatter.jpg") 10 10) "jpg") ; => #<ByteArrayInputStream>
```

##Contributors

* Joseph wilk (http://blog.josephwilk.net)
* Thiago Nunes (http://www.thiagotnunes.com)

##License
(The MIT License)

Copyright (c) 2013-2015 Joseph Wilk

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
'Software'), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.




