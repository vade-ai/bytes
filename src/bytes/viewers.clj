(ns bytes.viewers
  "Custom Clerk viewers for spaced repetition cards."
  (:require [nextjournal.clerk :as clerk]
            [bytes.cards :as cards]))

;; Card Viewer Definition
(def card-viewer
  "A custom Clerk viewer that renders spaced repetition cards inline.

  The card is displayed as a styled box with the question visible.
  In Stage 2, this will be enhanced with ClojureScript for interactivity."
  {:pred (fn [x]
           ;; Check if this is a card by looking for namespaced keys
           (and (map? x)
                (contains? x ::cards/id)
                (contains? x ::cards/question)))
   :transform-fn (fn [wrapped]
                   ;; Clerk wraps the value - extract it from :nextjournal/value
                   (let [card (:nextjournal/value wrapped)
                         {::cards/keys [id question answer concept-tags]} card]
                     {:id id
                      :question question
                      :answer answer
                      :tags (vec (or concept-tags #{}))}))
   :render-fn '(fn [card]
                 (let [[shown? set-shown!] (reagent.core/use-state false)]
                   [:div.card-container
                    {:class "my-4 p-4 border-2 border-blue-400 rounded-lg bg-blue-50"
                     :data-card-id (:id card)}

                    ;; Card header with icon
                    [:div.card-header
                     {:class "flex items-center gap-2 mb-2"}
                     [:span.card-icon {:class "text-blue-600"} "🎯"]
                     [:span.card-label {:class "text-sm font-semibold text-blue-600 uppercase"}
                      "Review Card"]]

                    ;; Question
                    [:div.card-question
                     {:class "text-lg font-medium mb-3 text-gray-800"}
                     (:question card)]

                    ;; Show Answer button or Answer content
                    (if shown?
                      ;; Answer content (when shown)
                      [:div.card-answer
                       {:class "p-3 bg-white rounded border border-gray-200"}
                       [:div {:class "text-sm text-gray-500 mb-1"} "Answer:"]
                       [:div {:class "text-gray-800"} (:answer card)]]

                      ;; Show Answer button (when hidden)
                      [:button.show-answer-btn
                       {:class "px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 transition"
                        :on-click #(set-shown! true)}
                       "Show Answer"])

                    ;; Tags (if present)
                    (when (seq (:tags card))
                      [:div.card-tags
                       {:class "flex gap-2 mt-3 flex-wrap"}
                       (for [tag (:tags card)]
                         [:span.tag
                          {:key (str tag)
                           :class "text-xs px-2 py-1 bg-blue-100 text-blue-700 rounded"}
                          (name tag)])])

                    ;; Card ID for debugging (will remove in production)
                    [:div.card-debug
                     {:class "text-xs text-gray-400 mt-2"}
                     (str "Card ID: " (:id card))]]))})

;; Helper function to display a card
(defn show-card
  "Display a card using the custom viewer.

  Usage:
  (show-card (cards/card {:id \"my-card\"
                          :question \"What is 2+2?\"
                          :answer \"4\"}))"
  [card]
  ;; Extract the data directly here and pass as simple map
  (let [{::cards/keys [id question answer concept-tags]} card]
    (clerk/html
     [:div.card-container
      {:class "my-4 p-4 border-2 border-blue-400 rounded-lg bg-blue-50"
       :data-card-id id}

      ;; Card header with icon
      [:div.card-header
       {:class "flex items-center gap-2 mb-2"}
       [:span.card-icon {:class "text-blue-600"} "🎯"]
       [:span.card-label {:class "text-sm font-semibold text-blue-600 uppercase"}
        "Review Card"]]

      ;; Question
      [:div.card-question
       {:class "text-lg font-medium mb-3 text-gray-800"}
       question]

      ;; Answer (initially hidden, revealed on click)
      [:div.card-answer-section
       ;; Show Answer button
       [:button.show-answer-btn
        {:class "px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 transition"
         :on-click #(let [target (.-target %)
                          answer-div (.-nextElementSibling target)]
                      (set! (.. target -style -display) "none")
                      (set! (.. answer-div -style -display) "block"))}
        "Show Answer"]

       ;; Answer content (hidden by default)
       [:div.card-answer
        {:class "p-3 bg-white rounded border border-gray-200"
         :style {:display "none"}}
        [:div {:class "text-sm text-gray-500 mb-1"} "Answer:"]
        [:div {:class "text-gray-800"} answer]]]

      ;; Tags (if present)
      (when (seq concept-tags)
        [:div.card-tags
         {:class "flex gap-2 mt-3 flex-wrap"}
         (for [tag concept-tags]
           [:span.tag
            {:key (str tag)
             :class "text-xs px-2 py-1 bg-blue-100 text-blue-700 rounded"}
            (name tag)])])

      ;; Card ID for debugging
      [:div.card-debug
       {:class "text-xs text-gray-400 mt-2"}
       (str "Card ID: " id)]])))

;; Helper function to display multiple cards
(defn show-cards
  "Display multiple cards in sequence.

  Usage:
  (show-cards cards/example-cards)"
  [cards]
  (clerk/html
   [:div.cards-collection
    (for [card cards]
      ^{:nextjournal.clerk/width :full}
      (show-card card))]))

;; Add the card viewer to Clerk's viewer registry
;; This allows cards to be automatically rendered without wrapping in show-card
(clerk/add-viewers! [card-viewer])

(comment
  ;; Test the viewer with an example card
  (show-card (cards/card {:id "test-1"
                          :question "What is the capital of France?"
                          :answer "Paris"
                          :concept-tags #{:geography :europe}}))

  ;; Test with multiple cards
  (show-cards cards/example-cards))
