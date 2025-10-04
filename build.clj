(ns build
  (:require [scicloj.clay.v2.api :as clay]))

(defn -main [& _args]
  (clay/make! {:format [:quarto :html]
               :base-source-path "notebooks"
               :source-path ["index.clj"
                             "intro_to_algorithms.clj"]
               :base-target-path "public"
               :book {:title "Vade Bytes"}
               :clean-up-target-dir true})
  (println "Site built successfully!"))

(comment
  (-main))
