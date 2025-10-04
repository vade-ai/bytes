;; # Introduction to Algorithms 🚀

(ns intro-to-algorithms)

;; ## What is an Algorithm?

;; An **algorithm** is a finite sequence of well-defined instructions to
;; solve a problem or perform a computation. Think of it as a recipe:
;; given some inputs, follow the steps, and you'll get a predictable output.

;; Algorithms are fundamental to computer science because they define
;; *how* we solve problems, not just *what* problem we're solving.

;; ## Key Properties of Algorithms

;; Good algorithms share several important properties:

;; 1. **Input**: Zero or more inputs
;; 2. **Output**: At least one output
;; 3. **Definiteness**: Each step is clear and unambiguous
;; 4. **Finiteness**: Must terminate after a finite number of steps
;; 5. **Effectiveness**: Each operation must be basic enough to be done exactly

;; ## A Simple Example: Finding Maximum

;; Let's look at a concrete algorithm. Here's how to find the maximum
;; value in a list of numbers:

(defn find-max [numbers]
  (if (empty? numbers)
    nil
    (reduce (fn [current-max n]
              (if (> n current-max)
                n
                current-max))
            (first numbers)
            (rest numbers))))

;; Let's test it:

(find-max [3 7 2 9 1 4])

;; The algorithm works by:
;; 1. Starting with the first number as our "current max"
;; 2. Comparing each subsequent number to the current max
;; 3. Updating the current max if we find a larger number
;; 4. Returning the final max after checking all numbers

;; ## Algorithm Complexity

;; When analyzing algorithms, we care about two main resources:
;; - **Time complexity**: How many steps does it take?
;; - **Space complexity**: How much memory does it use?

;; We express these using **Big O notation**, which describes how
;; performance scales with input size.

;; Common complexities (from best to worst):
;; - O(1): Constant time
;; - O(log n): Logarithmic time
;; - O(n): Linear time
;; - O(n log n): Linearithmic time
;; - O(n²): Quadratic time
;; - O(2ⁿ): Exponential time

;; ## Binary Search: A Classic Algorithm

;; Binary search is a efficient way to find an item in a *sorted* list.
;; Instead of checking every element (linear search), it repeatedly
;; divides the search space in half.

(defn binary-search
  "Find the index of target in sorted vector v, or nil if not found"
  [v target]
  (loop [low 0
         high (dec (count v))]
    (when (<= low high)
      (let [mid (quot (+ low high) 2)
            mid-val (get v mid)]
        (cond
          (= mid-val target) mid
          (< mid-val target) (recur (inc mid) high)
          :else (recur low (dec mid)))))))

;; Test it on a sorted vector:

(binary-search [1 3 5 7 9 11 13 15 17 19] 13)

;; The key insight: by comparing with the middle element, we can
;; eliminate half the remaining elements in each step!

;; ## Recap

;; We've covered:
;; - What algorithms are and their key properties
;; - How to analyze algorithm complexity with Big O notation
;; - Two fundamental algorithms: finding maximum (O(n)) and binary search (O(log n))

;; Understanding these basics provides a foundation for learning more
;; sophisticated algorithms and data structures.
