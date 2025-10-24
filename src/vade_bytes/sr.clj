(ns vade-bytes.sr
  "Spaced Repetition helpers for Vade Bytes blog posts.

   This namespace provides utilities to embed spaced repetition prompts
   within Clay blog posts, inspired by quantum.country's mnemonic medium."
  (:require [scicloj.kindly.v4.kind :as kind]
            [clojure.data.json :as json]))

(defn prompt
  "Create a spaced repetition prompt.

   Parameters:
   - id: Unique identifier for this prompt (required for tracking progress)
   - question: The question to ask the reader
   - answer: The answer to reveal
   - explanation: Optional additional explanation or context

   Example:
   ```clojure
   (sr/prompt
     {:id \"qubit-superposition-def\"
      :question \"What is superposition in quantum computing?\"
      :answer \"A qubit in superposition is in a combination of both |0⟩ and |1⟩ states simultaneously.\"
      :explanation \"This is different from classical bits which can only be in one state at a time.\"})
   ```"
  [{:keys [id question answer explanation]
    :or {explanation ""}}]
  (when-not id
    (throw (ex-info "Spaced repetition prompt requires an :id" {})))
  (when-not question
    (throw (ex-info "Spaced repetition prompt requires a :question" {:id id})))
  (when-not answer
    (throw (ex-info "Spaced repetition prompt requires an :answer" {:id id})))

  ^kind/hiccup
  [:div
   {:data-sr-prompt (json/write-str {:id id
                                     :question question
                                     :answer answer
                                     :explanation explanation})}])

(defn inline-prompt
  "Create an inline spaced repetition prompt (smaller, less prominent).
   Same parameters as `prompt`, but rendered with less visual weight."
  [opts]
  ^kind/hiccup
  [:div.sr-prompt-inline
   {:data-sr-prompt (json/write-str opts)}])

(defn prompt-group
  "Create a group of related spaced repetition prompts.

   Parameters:
   - title: Optional title for the group
   - prompts: Vector of prompt definitions (each a map with :id, :question, :answer)

   Example:
   ```clojure
   (sr/prompt-group
     {:title \"Quantum Gates Basics\"
      :prompts [{:id \"x-gate-def\"
                 :question \"What does the X gate do?\"
                 :answer \"The X gate flips a qubit from |0⟩ to |1⟩ and vice versa.\"}
                {:id \"h-gate-def\"
                 :question \"What does the Hadamard (H) gate do?\"
                 :answer \"The H gate creates superposition by putting a qubit into an equal mix of |0⟩ and |1⟩.\"}]})
   ```"
  [{:keys [title prompts]}]
  ^kind/hiccup
  [:div.sr-prompt-group
   (when title
     [:h4.sr-group-title title])
   (for [prompt-data prompts]
     [:div
      {:key (:id prompt-data)
       :data-sr-prompt (json/write-str prompt-data)}])])

(comment
  ;; Example usage in a blog post

  (require '[vade-bytes.sr :as sr])

  ;; Single prompt
  (sr/prompt
    {:id "clojure-immutable-def"
     :question "Why are immutable data structures important in Clojure?"
     :answer "Immutable data structures prevent accidental mutation, making code easier to reason about and enabling safe concurrent programming."
     :explanation "Since the data can't change, you don't need locks to protect against race conditions."})

  ;; Inline prompt
  (sr/inline-prompt
    {:id "map-function"
     :question "What does the map function do?"
     :answer "map applies a function to each element of a collection, returning a new collection with the results."})

  ;; Group of prompts
  (sr/prompt-group
    {:title "Clojure Collections"
     :prompts [{:id "vector-access"
                :question "How do you access the first element of a vector?"
                :answer "Use (first v) or (v 0), since vectors implement indexed access."}
               {:id "map-literal"
                :question "What's the literal syntax for a Clojure map?"
                :answer "{:key \"value\", :another-key 42} - curly braces with key-value pairs."}]}))
