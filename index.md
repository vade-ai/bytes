```clojure
(ns index
  {:nextjournal.clerk/visibility {:code :hide}}
  (:require [clojure.string :as str]
            [nextjournal.clerk :as clerk]))
```

# Vade Bytes

**Learning out loud while building the dream.**

Raw notes, not performance art. Written for truth, not virality.

---

## Latest Articles

```clojure
(clerk/html
 [:div.space-y-8
  [:article.border-l-4.border-green-500.pl-4
   [:h3.text-xl.font-bold
    [:a.text-green-600.hover:text-green-800
     {:href (clerk/doc-url "notebooks/articles/intro_to_algorithms")}
     "🎯 Introduction to Algorithms"]]
   [:div.text-sm.text-gray-600.mt-1 "Learning Resources • October 3, 2025"]
   [:p.mt-2.text-gray-700
    "Learn fundamental algorithms with embedded spaced repetition cards. Covers algorithm properties, complexity analysis, and classic examples like binary search."]
   [:span.inline-block.px-3.py-1.bg-green-100.text-green-800.text-xs.font-semibold.rounded.mt-2
    "✨ Interactive Learning with Spaced Repetition"]]

  [:article.border-l-4.border-indigo-500.pl-4
   [:h3.text-xl.font-bold
    [:a.text-indigo-600.hover:text-indigo-800
     {:href (clerk/doc-url "notebooks/tech_tides/vade_bytes_launch")}
     "Vade Bytes: Learning Out Loud While Building the Dream"]]
   [:div.text-sm.text-gray-600.mt-1 "Tech Tides • October 2, 2025"]
   [:p.mt-2.text-gray-700
    "I'm tired of viral hooks and empty content. This is my final attempt at finding my own voice—sharing what I genuinely find interesting while building Vade AI in public."]]])
```

---

## Categories

```clojure
(clerk/html
 [:div.grid.md:grid-cols-2.gap-6.mt-8
  ;; Learning Resources - NEW!
  [:a.block.p-6.border-2.border-green-500.rounded-lg.hover:border-green-600.hover:shadow-lg.transition.bg-green-50
   {:href (clerk/doc-url "notebooks/articles/intro_to_algorithms")}
   [:div.flex.items-center.gap-2.mb-2
    [:div.text-2xl "🎯"]
    [:span.text-xs.bg-green-600.text-white.px-2.py-1.rounded "New!"]]
   [:h3.text-lg.font-bold.text-gray-900 "Learning Resources"]
   [:p.text-sm.text-gray-600.mt-2
    "Interactive articles with spaced repetition cards. Learn by reading and reinforce with built-in review prompts."]]

  ;; Tech Tides
  [:a.block.p-6.border.border-gray-300.rounded-lg.hover:border-indigo-600.hover:shadow-lg.transition
   {:href (clerk/doc-url "notebooks/tech_tides/index")}
   [:div.text-2xl.mb-2 "📊"]
   [:h3.text-lg.font-bold.text-gray-900 "Tech Tides"]
   [:p.text-sm.text-gray-600.mt-2
    "Weekly compilations of everything I learned, wrote, and did. The connective tissue between all explorations."]]

  ;; Architect's Journal
  [:a.block.p-6.border.border-gray-300.rounded-lg.hover:border-indigo-600.hover:shadow-lg.transition
   {:href (clerk/doc-url "notebooks/architects_journal/index")}
   [:div.text-2xl.mb-2 "🏗️"]
   [:h3.text-lg.font-bold.text-gray-900 "Architect's Journal"]
   [:p.text-sm.text-gray-600.mt-2
    "Real-world system design at scale. Practical decisions about databases, caching, state management, trade-offs."]]

  ;; Database Deep Dive
  [:a.block.p-6.border.border-gray-300.rounded-lg.hover:border-indigo-600.hover:shadow-lg.transition
   {:href (clerk/doc-url "notebooks/database_deep_dive/index")}
   [:div.text-2xl.mb-2 "🗄️"]
   [:h3.text-lg.font-bold.text-gray-900 "Database Deep Dive"]
   [:p.text-sm.text-gray-600.mt-2
    "How databases actually work, not just how to query them. The internals that make you a better developer."]]

  ;; Founder's Journey
  [:a.block.p-6.border.border-gray-300.rounded-lg.hover:border-indigo-600.hover:shadow-lg.transition
   {:href (clerk/doc-url "notebooks/founders_journey/index")}
   [:div.text-2xl.mb-2 "💡"]
   [:h3.text-lg.font-bold.text-gray-900 "Founder's Journey"]
   [:p.text-sm.text-gray-600.mt-2
    "Actual patterns from founders who built real companies. The messy reality of building a business."]]

  ;; Research Refined
  [:a.block.p-6.border.border-gray-300.rounded-lg.hover:border-indigo-600.hover:shadow-lg.transition
   {:href (clerk/doc-url "notebooks/research_refined/index")}
   [:div.text-2xl.mb-2 "📚"]
   [:h3.text-lg.font-bold.text-gray-900 "Research Refined"]
   [:p.text-sm.text-gray-600.mt-2
    "Leading-edge research papers distilled into grokkable explanations. Complex ideas made useful."]]

  ;; Clojure Chronicles
  [:a.block.p-6.border.border-gray-300.rounded-lg.hover:border-indigo-600.hover:shadow-lg.transition
   {:href (clerk/doc-url "notebooks/clojure_chronicles/index")}
   [:div.text-2xl.mb-2 "⚙️"]
   [:h3.text-lg.font-bold.text-gray-900 "Clojure Chronicles"]
   [:p.text-sm.text-gray-600.mt-2
    "Functional programming principles and Clojure's design decisions. Why simplicity beats complexity."]]

  ;; How To AI
  [:a.block.p-6.border.border-gray-300.rounded-lg.hover:border-indigo-600.hover:shadow-lg.transition
   {:href (clerk/doc-url "notebooks/how_to_ai/index")}
   [:div.text-2xl.mb-2 "🤖"]
   [:h3.text-lg.font-bold.text-gray-900 "How To AI"]
   [:p.text-sm.text-gray-600.mt-2
    "Practical AI workflows and patterns. What works, what doesn't, lessons from mistakes."]]])
```

---

## About

**Vade Bytes is for builders.**

People who are genuinely curious about a variety of topics. Who like to build things and learn by doing. Who are tired of the marketing fluff and performative content that dominates the internet.

What you're signing up for:

✓ **Raw notes, not polished** - You're getting my actual learning process
✓ **Written for truth, not virality** - No hooks, no growth hacks
✓ **Messy middle included** - Mistakes, pivots, the stuff everyone hides
✓ **Fellow traveler, not guru** - I'm figuring this out alongside you
✓ **When I have something to say** - No forced content calendar

I'm not promising a schedule. I'm promising substance.

```clojure
(clerk/html
 [:div.mt-8.text-center
  [:a.inline-block.px-6.py-3.bg-indigo-600.text-white.font-semibold.rounded-lg.hover:bg-indigo-700.transition
   {:href "https://vade.ai/bytes"}
   "Subscribe to Vade Bytes"]
  [:p.text-sm.text-gray-600.mt-4
   "No viral hooks. No follower games. Just genuine curiosity and real building."]])
```
