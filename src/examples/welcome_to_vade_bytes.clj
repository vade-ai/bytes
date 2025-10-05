^{:kindly/hide-code true
  :clay {:title "Welcome to Vade Bytes"
         :quarto {:author "Vade Team"
                  :date "2025-10-05"
                  :type :post
                  :category :meta
                  :tags [:announcement :introduction]}}}
(ns examples.welcome-to-vade-bytes
  (:require [scicloj.kindly.v4.kind :as kind]))

;; # Welcome to Vade Bytes
;;
;; Welcome to our technical blog! This is an example post demonstrating
;; how to write content using Clay and Clojure.

;; ## Why Literate Programming?
;;
;; Writing blog posts as Clojure namespaces gives us several advantages:
;;
;; - **Executable code** - All code examples are real, tested code
;; - **Interactive development** - See your post as you write it
;; - **Rich visualizations** - Tables, charts, and custom viewers
;; - **Version control** - Blog posts are just code

;; ## A Simple Example
;;
;; Here's a simple calculation:

(def numbers (range 1 11))

;; We can display this as a table:

^kind/table
{:x numbers
 :squares (map #(* % %) numbers)
 :cubes (map #(* % % %) numbers)}

;; ## Visualizations
;;
;; Clay supports various visualization types through Kindly annotations.
;; Here's a simple chart:

^kind/vega-lite
{:data {:values (map (fn [x]
                       {:x x
                        :y (* x x)
                        :z (* x x x)})
                     (range 1 11))}
 :mark :line
 :encoding {:x {:field :x :type :quantitative}
            :y {:field :y :type :quantitative}
            :color {:field "series" :type :nominal}}
 :layer [{:mark :line
          :encoding {:x {:field :x}
                     :y {:field :y}}}
         {:mark :line
          :encoding {:x {:field :x}
                     :y {:field :z}}}]}

;; ## How to Write Posts
;;
;; 1. Create a new `.clj` file in the `src/` directory
;; 2. Add metadata to the namespace form (see top of this file)
;; 3. Write prose in comment blocks (lines starting with `;;`)
;; 4. Write executable Clojure code
;; 5. Use Kindly annotations to create visualizations

;; ## Next Steps
;;
;; - Run `bb dev` to preview your posts interactively
;; - Run `bb site` to build the complete site
;; - Commit and push to publish
;;
;; Happy writing!
