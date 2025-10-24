^{:kindly/hide-code true
  :clay {:title "Introduction to Spaced Repetition Learning"
         :quarto {:author "Vade Team"
                  :date "2025-10-23"
                  :type :post
                  :category :learning
                  :tags [:spaced-repetition :learning :memory :cognitive-science]}}}
(ns posts.intro-to-spaced-repetition
  "An introduction to spaced repetition and how it works in Vade Bytes."
  (:require [scicloj.kindly.v4.kind :as kind]
            [vade-bytes.sr :as sr]))

;; # Introduction to Spaced Repetition Learning
;;
;; Welcome to Vade Bytes! This post demonstrates our **spaced repetition system**,
;; inspired by [quantum.country](https://quantum.country)'s mnemonic medium.
;;
;; Spaced repetition is a learning technique that leverages the psychological spacing
;; effect: information is better retained when study sessions are spaced out over time,
;; rather than crammed into a single session.

;; ## How It Works
;;
;; As you read through posts on Vade Bytes, you'll encounter interactive prompts
;; that test your understanding of key concepts. These aren't just passive quizzes—
;; they're an integral part of how the content is designed to be learned.

;; Let's try one now:

(sr/prompt
  {:id "sr-definition"
   :question "What is spaced repetition?"
   :answer "Spaced repetition is a learning technique where review sessions are spaced out over increasing intervals of time."
   :explanation "This takes advantage of the spacing effect, where information is better retained when learning is distributed over time."})

;; ## The Science Behind It
;;
;; Research in cognitive psychology has consistently shown that spacing out learning
;; over time leads to better long-term retention than massed practice (cramming).
;; The effect was first documented by Hermann Ebbinghaus in 1885.

;; Here's a simple visualization of memory retention over time:

^kind/vega-lite
{:data {:values (map (fn [day]
                       {:day day
                        :cramming (* 100 (Math/exp (- (/ day 5.0))))
                        :spaced (+ 50 (* 50 (Math/exp (- (/ day 30.0)))))})
                     (range 0 100 5))}
 :width 600
 :height 300
 :layer [{:mark {:type :line :point false}
          :encoding {:x {:field :day
                         :type :quantitative
                         :title "Days After Learning"}
                     :y {:field :cramming
                         :type :quantitative
                         :title "Retention (%)"
                         :scale {:domain [0 100]}}
                     :color {:value "#ff6b6b"}}}
         {:mark {:type :line :point false}
          :encoding {:x {:field :day :type :quantitative}
                     :y {:field :spaced :type :quantitative}
                     :color {:value "#51cf66"}}}]
 :config {:legend {:orient "bottom"}}}

;; The red line shows memory decay with cramming, while the green line shows
;; the more gradual decay (and thus better retention) with spaced repetition.

;; Let's test your understanding:

(sr/prompt
  {:id "ebbinghaus-discovery"
   :question "Who first documented the spacing effect in learning?"
   :answer "Hermann Ebbinghaus in 1885."
   :explanation "Ebbinghaus conducted pioneering research on memory, including the forgetting curve and the spacing effect."})

;; ## The SM-2 Algorithm
;;
;; Our implementation uses a simplified version of the **SM-2 algorithm**,
;; developed by Piotr Woźniak in 1987 for the SuperMemo software.
;;
;; The algorithm schedules review based on:
;; - How many times you've reviewed the item
;; - How difficult you rated it
;; - An "ease factor" that adjusts based on your performance

;; Here's how it works conceptually:

^kind/table
{:Review [1 2 3 4]
 :Interval ["1 day" "7 days" "21 days" "~2 months"]
 :Note ["First review" "One week later" "Three weeks later" "Increasing intervals"]}

;; The actual intervals adjust based on whether you rate items as Easy, Medium, or Hard.

(sr/prompt-group
  {:title "Algorithm Basics"
   :prompts [{:id "sm2-stands-for"
              :question "What does SM-2 stand for?"
              :answer "SuperMemo 2 - the second algorithm developed by Piotr Woźniak for the SuperMemo software."}
             {:id "sm2-factors"
              :question "What three factors does SM-2 use to schedule reviews?"
              :answer "Number of reviews, difficulty rating, and an ease factor that adjusts over time."
              :explanation "The ease factor makes frequently-hard items appear more often, while easy items get longer intervals."}]})

;; ## How to Use Vade Bytes
;;
;; 1. **Read naturally**: Don't worry about memorizing everything
;; 2. **Answer prompts**: When you see a prompt, try to answer before revealing
;; 3. **Rate difficulty**: After seeing the answer, honestly rate how easy it was
;; 4. **Return regularly**: Come back to review prompts as they come due
;;
;; Your progress is tracked in the right sidebar under "Your Learning Progress".

;; ## Inline Prompts
;;
;; Some prompts are smaller and less visually prominent—we call these "inline prompts".
;; They're useful for quick checks without interrupting the flow too much.

(sr/inline-prompt
  {:id "inline-example"
   :question "What's the main advantage of inline prompts?"
   :answer "They allow quick knowledge checks without disrupting reading flow as much as full prompts."})

;; ## Writing Posts with Spaced Repetition
;;
;; As a content creator on Vade Bytes, you can embed prompts directly in your
;; Clojure source files. Here's an example:

;; ```clojure
;; (require '[vade-bytes.sr :as sr])
;;
;; (sr/prompt
;;   {:id \"unique-id-for-tracking\"
;;    :question \"What is the question you want to ask?\"
;;    :answer \"The answer to reveal.\"
;;    :explanation \"Optional: Additional context or explanation.\"})
;; ```

;; The system handles all the scheduling, progress tracking, and UI automatically!

(sr/prompt
  {:id "sr-integration"
   :question "How do you add a spaced repetition prompt to a Vade Bytes post?"
   :answer "Use (sr/prompt {:id ... :question ... :answer ...}) in your Clojure source file."
   :explanation "The sr namespace provides helpers that Clay renders as interactive HTML components."})

;; ## Benefits of Learning This Way
;;
;; Traditional blog posts are "write-only": you read them once and likely forget
;; most of the content within days. With spaced repetition embedded in the medium
;; itself, Vade Bytes helps you actually **retain** what you read.
;;
;; This is especially valuable for technical content where you need to build on
;; previous knowledge over time.

;; Let's solidify what you've learned:

(sr/prompt-group
  {:title "Review: Key Concepts"
   :prompts [{:id "review-spacing-effect"
              :question "Why does spaced repetition work better than cramming?"
              :answer "The spacing effect: information is better retained when learning is distributed over time rather than massed together."}
             {:id "review-usage"
              :question "What should you do when you see a prompt in a Vade Bytes post?"
              :answer "Try to answer the question yourself, reveal the answer, and honestly rate the difficulty."}
             {:id "review-benefit"
              :question "What's the main advantage of Vade Bytes over traditional blogs?"
              :answer "Content retention - the spaced repetition system helps you actually remember what you read over the long term."}]})

;; ## Next Steps
;;
;; Now that you understand how the system works, explore other posts and see
;; how spaced repetition helps you build deep, lasting knowledge in technical topics.
;;
;; **Pro tip**: Check your learning progress in the right sidebar. As you answer
;; prompts, you'll see them move through different time intervals. Come back
;; regularly to review!
;;
;; Happy learning! 🚀

;; ## Further Reading
;;
;; - [Quantum Country: Mnemonic Medium](https://quantum.country) - The inspiration for our system
;; - [How to Remember Anything](https://ncase.me/remember/) - Interactive explanation of spaced repetition
;; - [SuperMemo: Algorithm SM-2](https://www.supermemo.com/en/blog/application-of-a-computer-to-improve-the-results-obtained-in-working-with-the-supermemo-method)
