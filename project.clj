(defproject gusqt "0.1.0-SNAPSHOT"
  :description "Grand Unified Software Quality Tool"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [cloverage "1.1.1"]
                 [clj-tagsoup "0.3.0"]
                 [codox "0.10.7"]
                 [enlive "1.1.6"]]
  :main ^:skip-aot gusqt.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
