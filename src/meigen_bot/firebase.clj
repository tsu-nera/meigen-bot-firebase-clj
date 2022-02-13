(ns meigen-bot.firestore
  (:require [clojure.java.io :as io]
            [environ.core :refer [env]])
  (:import (com.google.auth.oauth2 GoogleCredentials)
           (com.google.firebase FirebaseApp FirebaseOptions)
           (com.google.firebase.cloud FirestoreClient)
           ))

(defn init-firebase [cred-path]
  (let [service-account (io/input-stream cred-path)
        credentials     (GoogleCredentials/fromStream service-account)]
    (-> (FirebaseOptions/builder)
        (.setCredentials credentials)
        (.build)
        (FirebaseApp/initializeApp))))

(def cred-path (env :credentials-path))

(init-firebase cred-path)

(def db (FirestoreClient/getFirestore))

;;;;;;;;;;;;;;;;;;;;;;
;;  Design Journals
;;;;;;;;;;;;;;;;;;;;;;
;; firestore read data.

;; (def query (-> db
;;                (.collection "users")
;;                (.get)))

;; (System/getenv "GOOGLE_APPLICATION_CREDENTIALS")

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

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; https://firebase.google.com/docs/admin/setup/
;; FirebaseOptions options = FirebaseOptions.builder()
;;     .setCredentials(GoogleCredentials.getApplicationDefault())
;;     .setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
;;     .build();
;; FirebaseApp.initializeApp(options);

;; https://firebase.google.com/docs/firestore/quickstart?hl=ja
;; https://github.com/googleapis/java-firestore/tree/main/samples/snippets/src/main/java/com/example/firestore
;;
;; import com.google.auth.oauth2.GoogleCredentials;
;; import com.google.cloud.firestore.Firestore;

;; import com.google.firebase.FirebaseApp;
;; import com.google.firebase.FirebaseOptions;

;; // Use a service account
;; InputStream serviceAccount = new FileInputStream("path/to/serviceAccount.json");
;; GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
;; FirebaseOptions options = new FirebaseOptions.Builder()
;;     .setCredentials(credentials)
;;     .build();
;; FirebaseApp.initializeApp(options);

;; Firestore db = FirestoreClient.getFirestore();

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; import com.google.auth.oauth2.GoogleCredentials;
;; import com.google.cloud.firestore.Firestore;

;; import com.google.firebase.FirebaseApp;
;; import com.google.firebase.FirebaseOptions;

;; // Use the application default credentials
;; GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
;; FirebaseOptions options = new FirebaseOptions.Builder()
;; .setCredentials(credentials)
;; .setProjectId(projectId)
;; .build();
;; FirebaseApp.initializeApp(options);

;; Firestore db = FirestoreClient.getFirestore();
