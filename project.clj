(defproject secure-rand "0.1"
  :description "A Clojure library designed to generate secure random float,int,bytes and strings based on java.security.SecureRandom."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [commons-codec "1.6"]]
  :global-vars {*warn-on-reflection* true
                *assert* false})
