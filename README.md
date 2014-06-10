# secure-rand

A Clojure library designed to generate secure random float,int,bytes and strings based on [java.security.SecureRandom](http://docs.oracle.com/javase/7/docs/api/java/security/SecureRandom.html).

## Usage

``` clojure
[secure-rand "0.1"]
```

### Secure version of  rand, rand-int and rand-nth

``` clojure
(ns test
  (:refer-clojure :exclude [rand rand-int rand-nth])
  (:use [secure-rand.core :only [rand rand-int rand-nth]]))

(rand)
(rand 10)
(rand-int 100)
(rand-nth (range 10))
```

### Other functions

``` clojure
(secure-rand.core/bytes size)
```

Returns a secure random byte array of the specified size.

``` clojure
(secure-rand.core/base64 size)
```

Return a secure random base64 string of the specified size in bytes.

``` clojure
(secure-rand.core/base32 size)
```
Return a secure random base32 string of the specified size in bytes.

``` clojure
(secure-rand.core/hex size)
```
Return a secure random hex string of the specified size in bytes.

## License

Copyright © 2014 Dennis Zhuang [killme2008@gmail.com]

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
