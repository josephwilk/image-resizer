#Clojure Image Resizer

[![Build Status](https://travis-ci.org/josephwilk/image-resizer.png?branch=master)](https://travis-ci.org/josephwilk/image-resizer)

####(Drink to make small)

<img alt="Drink to make small" src="http://external.ak.fbcdn.net/safe_image.php?d=AQDnFdmx5U39plmD&w=489&h=696&url=http%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2F6%2F63%2FAlice_par_John_Tenniel_04.png" height="250px" />

## Why?

* Very fast (supports hardware accelerated operations on most platforms)
* No native libraries to install (I'm looking at you imagemagick)

Image Resizer under the hood wraps [imgscalr](http://www.thebuzzmedia.com/software/imgscalr-java-image-scaling-library).

##Installation

Add the following dependency to your project.clj file:

https://clojars.org/image-resizer

##Usage

```clojure
(require [image-resizer.core :refer :all])

;Resize an image while respecting original ratio
;Notice how the height is not 10 to respect the ratio of the image
(resize (file "white-rabbit.jpg") 10 10)          ; => #<BufferedImage width=10 height=4>

;Resize an image to a width
(resize-to-width (file "queen-of-hearts.jpg") 10) ; => #<BufferedImage width=10 height=4>

;Resize an image to a height
(resize-to-height (file "cheshire-cat.jpg") 10)   ; => #<BufferedImage width=5 height=10>

;Turning a BufferedImage into something useful

(require [image-resizer.format :refer :as format])

;Saving as a file
(format/save-as-file (resize (file "tea-party/mad-hatter.jpg") 10 10)
                     "/tmp/tea-party/mad-hatter.jpg") ; => "/tmp/tea-party/mad-hatter_10x5.jpg"
```

##License
(The MIT License)

Copyright (c) 2013 Joseph Wilk

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




