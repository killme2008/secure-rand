(ns secure-rand.core
  (:refer-clojure :exclude [rand rand-int rand-nth bytes])
  (:import [java.security SecureRandom]
           [org.apache.commons.codec.binary Base64 Base32 Hex]))

(defmacro wrap-ignore-exception [& body]
  `(try
     ~@body
     (catch Throwable e#)))

(defn- ^SecureRandom new-random
  "Try to create a appropriate SecureRandom.
   http://www.cigital.com/justice-league-blog/2009/08/14/proper-use-of-javas-securerandom/ "
  []
  (doto
      (or
       (wrap-ignore-exception (SecureRandom/getInstance "SHA1PRNG" "SUN"))
       (wrap-ignore-exception (SecureRandom/getInstance "SHA1PRNG"))
       (wrap-ignore-exception (SecureRandom.)))
    (.nextBytes (byte-array 16))))

(defonce ^ThreadLocal threadlocal-random (proxy [ThreadLocal] []
                                           (initialValue [] (new-random))))

(defn rand
  "Returns a secure random floating point number between 0 (inclusive) and
  n (default 1) (exclusive)."
  {:added "0.1"
   :static true}
  ([] (.nextDouble ^SecureRandom (.get threadlocal-random)))
  ([n] (* n (rand))))

(defn rand-int
  "Returns a secure random integer between 0 (inclusive) and n (exclusive)."
  {:added "0.1"
   :static true}
  [n] (int (rand n)))

(defn rand-nth
  "Return a secure random element of the (sequential) collection. Will have
  the same performance characteristics as nth for the given
  collection."
  {:added "0.1"
   :static true}
  [coll]
  (nth coll (rand-int (count coll))))

(defn bytes
  "Returns a secure random byte array of the specified size."
  [size]
  (let [bs (byte-array size)]
    (.nextBytes ^SecureRandom (.get threadlocal-random) bs)
    bs))

(defn base64
  "Return a secure random base64 string of the specified size in bytes."
  [size]
  (String. (Base64/encodeBase64 (bytes size))))

(defn base32
  "Return a secure random base32 string of the specified size in bytes."
  [size]
  (.encodeAsString (Base32.) (bytes size)))

(defn hex
  "Return a secure random hex string of the specified size in bytes."
  [size]
  (String. (Hex/encodeHex (bytes size))))
