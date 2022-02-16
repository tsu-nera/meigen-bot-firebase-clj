(ns functions.helloworld
  (:gen-class
   :name functions.ClojureHelloWorld
   :init init
   :state state
   :constructors {[] []}
   :main false
   :implements [com.google.cloud.functions.HttpFunction]
   :exposes-methods [service]
   )
  (:import
   (com.google.cloud.functions HttpFunction)
   ;; (com.google.cloud.functions HttpRequest HttpResponse)
   ;; (java.io BufferedWriter IOException)
   ))

;; (def ClojureHelloWorld
;;   (proxy [HttpFunction] []
;;     (service [request response]
;;       ;; (let [writer (.getWriter response)]
;;       ;;   (.write writer "Hello World!")))
;;       (println "Hello, Clojure"))))

(defn service [request response]
  ;; (let [writer (.getWriter response)]
  ;;   (.write writer "Hello World!")))
  (println request))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; package functions;

;; import com.google.cloud.functions.HttpFunction;
;; import com.google.cloud.functions.HttpRequest;
;; import com.google.cloud.functions.HttpResponse;
;; import java.io.BufferedWriter;
;; import java.io.IOException;

;; public class HelloWorld implements HttpFunction {
;; // Simple function to return "Hello World"
;; @Override
;; public void service(HttpRequest request, HttpResponse response)
;; throws IOException {
;;                     BufferedWriter writer           = response.getWriter () ;
;;                     writer.write   ("Hello World!") ;
;;                     }
;; }
