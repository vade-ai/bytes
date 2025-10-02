;; # 📊 Tech Tides
^{:nextjournal.clerk/visibility {:code :hide}}
(ns tech-tides.index
  (:require [nextjournal.clerk :as clerk]))

;; Weekly compilations of everything I learned, wrote, and did. The connective tissue between all my explorations. Think of it as my public weekly review—what worked, what didn't, what I'm thinking about next.

;; ---

;; ## Articles

(clerk/html
 [:div.space-y-6
  [:article.border-l-4.border-indigo-500.pl-4
   [:h3.text-lg.font-bold
    [:a.text-indigo-600.hover:text-indigo-800
     {:href (clerk/doc-url "notebooks/tech_tides/vade_bytes_launch")}
     "Vade Bytes: Learning Out Loud While Building the Dream"]]
   [:div.text-sm.text-gray-600.mt-1 "October 2, 2025"]
   [:p.mt-2.text-gray-700
    "I'm tired of viral hooks and empty content. This is my final attempt at finding my own voice—sharing what I genuinely find interesting while building Vade AI in public."]]])

;; ---

(clerk/html
 [:div.mt-8
  [:a.text-indigo-600.hover:text-indigo-800
   {:href (clerk/doc-url "notebooks/blog_index")}
   "← Back to Home"]])
