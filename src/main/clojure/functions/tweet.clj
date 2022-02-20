(ns functions.tweet
  (:require
   [cheshire.core :as json]
   [ring.middleware.json :refer [wrap-json-params wrap-json-response]]
   [ring.middleware.keyword-params :refer [wrap-keyword-params]]
   [ring.middleware.params :refer [wrap-params]]
   [ring.util.response :as response]
   [ring.middleware.params :refer [wrap-params]]
   [meigen-bot.twitter.private-client :as private]
   ))


(defn handler [req]
  (prn req)
  (let [params (:params req)
        status (:status params)
        tweet  (private/update-status status)]
    (response/response "OK")))


(def app
  (-> handler
      wrap-keyword-params
      wrap-json-params
      wrap-json-response
      wrap-params
      ))
