(ns smallworks.web-test
  (:use smallworks.web
    clojure.test
    ring.mock.request
    [aws.sdk.s3 :as s3]))

(deftest test-smallworks
  (testing "not-found"
    (let [response (app (request :get "/404"))]
      (is (= (:status response) 404))))

  (testing "upload"
    (with-redefs [
      s3/put-object (fn [cred bucket key value & [metadata & permissions]])
      s3/generate-presigned-url (fn [cred bucket key & [options]] key)]
      (let [response (app (request :post "/" {:file "filename"}))]
        (is (= (:status response) 200)))))
  )
