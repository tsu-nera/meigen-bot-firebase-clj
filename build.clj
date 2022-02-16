(ns build
  (:require [clojure.tools.build.api :as b]))

(def lib 'tsu-nera/meingen-bot-firebase)
(def version (format "0.1.%s" (b/git-count-revs nil)))

(def target-dir "target")
(def class-dir (str target-dir "/" "classes"))
(def jar-file (format "%s/%s-%s.jar" target-dir (name lib) version))

(def src ["src/main/clojure"])
(def basis (b/create-basis {:project "deps.edn"}))

;; clj -T:build clean
(defn clean [_]
  "Delete the build target directory"
  (println (str "Cleaning " target-dir))
  (b/delete {:path "target"}))

;; clj -T:build jar
(defn jar [_]
  "Create the jar from a source pom and source files"
  (b/write-pom {:class-dir class-dir
                :lib       lib
                :version   version
                :basis     basis
                :src-dirs  src})
  (b/copy-dir {:src-dirs   src
               :target-dir class-dir})
  (b/jar {:class-dir class-dir
          :jar-file  jar-file}))
