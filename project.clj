(defproject smallworks "1.0.0-SNAPSHOT"
  :description "Upload your build"
  :url "https://smallworks.herokuapp.com"
  :license {:name "Eclipse Public License v1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]
                 [ring/ring-jetty-adapter "1.2.2"]
                 [environ "0.5.0"]
                 [digest "1.4.4"]
                 [joda-time "2.4"]
                 [clj-aws-s3 "0.3.9" :exclusions [joda-time]]]
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.2.1"]]
  :hooks [environ.leiningen.hooks]
  :main smallworks.web
  :uberjar-name "smallworks.jar"
  :profiles {
    :production {:env {:production true}}
    :dev {:test-paths ["smallworks/test"]}
    :test {:dependencies [[ring-mock "0.1.5"]]}
  })