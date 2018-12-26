(ns recidiffist-cli.core
  (:require [clojure.tools.cli :as cli]
            [clojure.java.io :as io]
            [cheshire.core :as json]
            [recidiffist.stream :as rs]
            [manifold.stream :as ms])
  (:gen-class))


(defn -main
  [& args]
  (doseq [diff (->> *in* json/parsed-seq (eduction rs/pairwise-diffing))]
    (json/generate-stream diff *out*)))
