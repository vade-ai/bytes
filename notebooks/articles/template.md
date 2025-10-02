# Article Template

This template demonstrates how to write articles with embedded spaced repetition cards using Clerk.

## Setup

First, require the necessary namespaces:

```clojure
(ns articles.template
  (:require [nextjournal.clerk :as clerk]
            [bytes.cards :as cards]
            [bytes.viewers :as viewers]))
```

## Writing Articles with Cards

### Basic Structure

Articles are written in Markdown with Clojure code fences for executable content. You can embed spaced repetition cards anywhere in your article to test reader comprehension.

### Example: Introduction to Concepts

Let's introduce a simple concept and then test it with a card.

**Concept**: The **Fibonacci sequence** is a series of numbers where each number is the sum of the two preceding ones, typically starting with 0 and 1.

The sequence begins: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...

Now let's test your understanding:

```clojure
(viewers/show-card
  (cards/card {:id "fib-definition"
               :question "What is the rule for generating the Fibonacci sequence?"
               :answer "Each number is the sum of the two preceding numbers"
               :concept-tags #{:mathematics :sequences :fibonacci}}))
```

### More Content

You can continue with more prose and concepts, adding cards as needed to reinforce learning.

```clojure
(viewers/show-card
  (cards/card {:id "fib-start"
               :question "What are the first two numbers in the Fibonacci sequence?"
               :answer "0 and 1"
               :concept-tags #{:mathematics :fibonacci}}))
```

### Using Multiple Cards

You can also display multiple related cards together:

```clojure
(def fibonacci-cards
  [(cards/card {:id "fib-third"
                :question "What is the third number in the Fibonacci sequence?"
                :answer "1 (because 0 + 1 = 1)"
                :concept-tags #{:mathematics :fibonacci}})

   (cards/card {:id "fib-fifth"
                :question "What is the fifth number in the Fibonacci sequence?"
                :answer "3 (because 1 + 2 = 3)"
                :concept-tags #{:mathematics :fibonacci}})])

(viewers/show-cards fibonacci-cards)
```

## Best Practices

### When to Add Cards

- After introducing a new concept or definition
- When presenting a key formula or relationship
- After working through an example
- To test multiple angles of the same concept

### Writing Good Questions

1. **Be specific**: Ask about one thing at a time
2. **Test understanding, not memorization**: Focus on "why" and "how"
3. **Use consistent terminology**: Match the article's language
4. **Vary difficulty**: Mix easy recall with deeper understanding

### Example: Good vs. Poor Questions

❌ Poor: "What is quantum computing?"
✅ Good: "What property of qubits allows them to represent 0 and 1 simultaneously?"

❌ Poor: "Tell me about the X gate"
✅ Good: "What does the X gate do when applied to the state |0⟩?"

## Technical Notes

### Card IDs

- Must be unique across your entire site
- Use descriptive, kebab-case names
- Consider prefixing with article topic: `"qc-superposition"`, `"ml-gradient-descent"`

### Concept Tags

Tags help organize cards for:
- Reviewing by topic
- Building topic dependency graphs
- Generating practice sessions

Use hierarchical tags when appropriate:
```clojure
:concept-tags #{:physics :quantum-mechanics :qubits}
```

## Next Steps

1. Copy this template to create a new article
2. Replace the content with your topic
3. Add cards as you write
4. Preview with `(clerk/show! "notebooks/articles/your-article.md")`
5. Test the reading and review experience
