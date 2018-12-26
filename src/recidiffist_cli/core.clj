(ns recidiffist-cli.core
  (:require [clojure.tools.cli :as cli]
            [clojure.java.io :as io]
            [cheshire.core :as json]
            [recidiffist.stream :as rs]
            [manifold.stream :as ms])
  (:gen-class))

(defn ^:private to-reader
  [s]
  (if (= s "-") *in* (io/reader s)))

(defn -main
  [& args]
  (doseq [diff (->> *in* json/parsed-seq (eduction rs/pairwise-diffing))]
    (json/generate-stream diff *out*)))
