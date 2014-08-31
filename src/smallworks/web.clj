(ns smallworks.web
  (:use [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
            [aws.sdk.s3 :as s3]
            [clojure.string :only (join)]))

(def credentials {:access-key (env :aws-access-key), :secret-key (env :aws-secret-key)})

(defn uuid [] (str (java.util.UUID/randomUUID)))

(defn upload [file]
  (let [key (uuid) bucket (env :s3-bucket)]
    (s3/put-object credentials bucket key file [:server-side-encryption "AES256"])
    {:status 200
     :body (generate-presigned-url credentials bucket key)}))

(defroutes app
  (POST "/" {{{tempfile :tempfile filename :filename} :file} :params :as params}
     (upload tempfile))
  (route/not-found ""))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))
