(ns bytes.cards
  "Core functions for creating and managing spaced repetition cards."
  (:require [clojure.spec.alpha :as s]))

;; Card Schema Specs
(s/def ::id string?)
(s/def ::question string?)
(s/def ::answer string?)
(s/def ::concept-tags (s/coll-of keyword? :kind set?))
(s/def ::article string?)

(s/def ::card
  (s/keys :req [::id ::question ::answer]
          :opt [::concept-tags ::article]))

;; Card Constructor
(defn card
  "Create a spaced repetition card with the given attributes.

  Required:
  - id: Unique identifier (string)
  - question: The question to ask (string)
  - answer: The expected answer (string)

  Optional:
  - concept-tags: Set of keywords categorizing the concept
  - article: Path to the source article

  Example:
  (card {:id \"qc-basis-states\"
         :question \"What are the two basis states of a qubit?\"
         :answer \"|0⟩ and |1⟩\"
         :concept-tags #{:quantum-computing :qubits}
         :article \"notebooks/articles/quantum_intro.md\"})"
  [{:keys [id question answer concept-tags article]}]
  (let [c {::id id
           ::question question
           ::answer answer}]
    (cond-> c
      concept-tags (assoc ::concept-tags concept-tags)
      article (assoc ::article article))))

;; Helper Functions
(defn card-id
  "Extract the ID from a card."
  [card]
  (::id card))

(defn card-question
  "Extract the question from a card."
  [card]
  (::question card))

(defn card-answer
  "Extract the answer from a card."
  [card]
  (::answer card))

(defn card-tags
  "Extract the concept tags from a card."
  [card]
  (::concept-tags card #{}))

(defn validate-card
  "Validate a card against the spec. Returns card if valid, throws if invalid."
  [card]
  (if (s/valid? ::card card)
    card
    (throw (ex-info "Invalid card"
                    {:card card
                     :explanation (s/explain-str ::card card)}))))

;; Collection Operations
(defn cards-by-tag
  "Filter a collection of cards by concept tag."
  [cards tag]
  (filter #(contains? (card-tags %) tag) cards))

(defn cards-by-article
  "Filter cards by source article."
  [cards article-path]
  (filter #(= (::article %) article-path) cards))

;; Example Cards (for testing)
(def example-cards
  [(card {:id "qc-basis-states"
          :question "What are the two basis states of a qubit?"
          :answer "|0⟩ and |1⟩"
          :concept-tags #{:quantum-computing :qubits :basics}})

   (card {:id "qc-superposition"
          :question "What does it mean for a qubit to be in superposition?"
          :answer "A qubit exists in a linear combination of |0⟩ and |1⟩ until measured"
          :concept-tags #{:quantum-computing :qubits :superposition}})

   (card {:id "qc-x-gate"
          :question "What does the X gate do to |0⟩?"
          :answer "Flips it to |1⟩"
          :concept-tags #{:quantum-computing :gates}})])
