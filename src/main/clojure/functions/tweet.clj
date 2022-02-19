(ns functions.tweet
  (:require
   [cheshire.core :as json]
   [ring.middleware.json :as m.json]
   [meigen-bot.twitter.private-client :as private]
   ))


(defn handler [req]
  ;; (prn req)
  (let [tweet (private/update-status "test")
        body  (try (json/generate-string req {:pretty true})
                   (catch Exception _e
                     (json/generate-string (dissoc req :body) {:pretty true})))]
    {:status  200
     :headers {"Content-Type" "application/json"}
     :body    (str "OK" "\n")}))


(def app
  (-> handler
      m.json/wrap-json-body
      ;; m.lint/wrap-lint
      ))
