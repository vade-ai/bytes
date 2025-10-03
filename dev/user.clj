(ns user
  (:require [nextjournal.clerk :as clerk]
            [bytes.viewers]))

(defn build-static-site
  "Build static site with all articles"
  [_]
  (clerk/build! {:paths ["notebooks/articles/intro_to_algorithms.clj"]
                 :index "index.md"
                 :out-path "public"
                 :git/sha false}))

(comment
  ;; start without file watcher, open browser when started
  (clerk/serve! {:browse? true :port 7777})

  ;; start with file watcher for these sub-directory paths
  (clerk/serve! {:watch-paths ["notebooks" "src"]})

  ;; start with file watcher and a `show-filter-fn` function to watch
  ;; a subset of notebooks
  (clerk/serve! {:watch-paths ["notebooks" "src"] :show-filter-fn #(clojure.string/starts-with? % "notebooks")})

  (clerk/clear-cache!)

  ;; or call `clerk/show!` explicitly
  (clerk/show! "notebooks/articles/intro_to_algorithms.clj")
  (clerk/show! "index.md")

  ;; produce a static app
  (build-static-site nil))
