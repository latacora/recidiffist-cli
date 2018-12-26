(defproject recidiffist-cli "0.1.0-SNAPSHOT"
  :description "Diffs for structured data, from the CLI"
  :url "https://github.com/latacora/recidiffist"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/tools.cli "0.4.1"]
                 [cheshire "5.8.1"]
                 [recidiffist "0.11.0"]]
  :main ^:skip-aot recidiffist-cli.core
  :target-path "target/%s"
  :native-image {:name "recidiffist"
                 :opts ["--verbose"]}
  :profiles {:uberjar {:aot :all
                       :native-image {:opts ["-Dclojure.compiler.direct-linking=true"]}}})
