(ns recidiffist-cli.core
  (:require [clojure.tools.cli :as cli]
            [clojure.java.io :as io]
            [cheshire.core :as json]
            [recidiffist.stream :as rs]
            [manifold.stream :as ms]
            [clojure.data :as cd])
  (:gen-class))

;; This disables Java array diffing. We don't care about that anyway but _also_
;; confuses GraalVM native reflection. See original implementation for details:
;; https://github.com/clojure/clojure/blob/9baebd091b1301aecaaac3b5f9c7ede5dcc58f8c/src/clj/clojure/data.clj#L79
(extend Object
  cd/Diff {:diff-similar @#'cd/atom-diff}
  cd/EqualityPartition {:equality-partition (constantly :atom)})

(defn -main
  [& args]
  (doseq [diff (->> *in* json/parsed-seq (eduction rs/pairwise-diffing))]
    (json/generate-stream diff *out*)))
