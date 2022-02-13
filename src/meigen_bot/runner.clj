(ns meigen-bot.runner
  (:require
   [taoensso.timbre :as log]
   [meigen-bot.twitter.private-client :as private]
   [meigen-bot.meigen :refer [meigens]]
   [meigen-bot.firebase :refer [db]]))


(defn pick-random [] (rand-nth meigens))

(defn make-status [data]
  (let [{content :content, author :author} data]
    (str content "\n\n" author)))

(defn tweet-random []
  (let [data                               (pick-random)
        {content :content, author :author} data
        status                             (make-status data)]
    (try
      (private/update-status status)
      (log/info "post tweet completed.")
      (log/debug (str content " - " author))
      (catch Exception e (log/error "post tweet Failed." (.getMessage e))))))

(defn -main [& args]
  (tweet-random)
  0)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Design Journals
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; firestore read data.

;; (def doc (-> db
;;              (.collection "users")
;;              (.document "alovlance2")
;;              (.get)))

;; (def data (.getData @doc))

;; // asynchronously retrieve all users
;; ApiFuture<QuerySnapshot> query = db.collection("users").get();
;; // ...
;; // query.get() blocks on response
;; QuerySnapshot querySnapshot = query.get();
;; List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
;; for (QueryDocumentSnapshot document : documents) {
;;                                                   System.out.println ("User: " + document.getId())             ;
;;                                                   System.out.println ("First: " + document.getString("first")) ;
;;                                                   if                 (document.contains("middle"))             {
;;                                                                                                                 System.out.println ("Middle: " + document.getString("middle")) ;
;;                                                                                                                 }
;;                                                   System.out.println ("Last: " + document.getString("last"))   ;
;;                                                   System.out.println ("Born: " + document.getLong("born"))     ;
;;                                                   }

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; firebase write data.
;;
;; https://github.com/googleapis/java-firestore/blob/main/samples/snippets/src/main/java/com/example/firestore/Quickstart.java
;;
;; DocumentReference docRef = db.collection("users").document("alovelace");
;; // Add document data  with id "alovelace" using a hashmap
;; Map<String, Object> data = new HashMap<>();
;; data.put("first", "Ada");
;; data.put("last", "Lovelace");
;; data.put("born", 1815);
;; //asynchronously write data
;; ApiFuture<WriteResult> result = docRef.set(data);
;; // ...
;; // result.get() blocks on response
;; System.out.println("Update time : " + result.get().getUpdateTime());

;; (def docRef (-> db
;;                 (.collection "users")
;;                 (.document "alovlance2")))
;; (def data {"first" "Ada"
;;            "last"  "Lovelance"
;;            "born"  1815})
;; (def result (. docRef set data))
