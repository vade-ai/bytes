# Bytes - Mnemonic Medium Publishing Platform

A Clerk-based publishing platform combining essay-style articles with embedded spaced repetition prompts, inspired by [Quantum Country](https://quantum.country/).

## Quick Start

### Development

Start the Clerk development server with file watcher:

```bash
bb serve
```

The server will run on http://localhost:7777 and automatically reload when you edit files in `notebooks/` or `src/`.

### Building Static Site

Generate the static site to `public/`:

```bash
bb build
```

The output includes all articles and an index page, ready for deployment.

### Clean Build Artifacts

```bash
bb clean
```

## Project Structure

```
bytes/
├── notebooks/
│   └── articles/          # Article notebooks with embedded cards
│       ├── intro_to_algorithms.clj
│       └── template.md
├── src/
│   └── bytes/
│       ├── cards.clj      # Card data structure and helpers
│       └── viewers.clj    # Custom Clerk viewers for cards
├── dev/
│   └── user.clj           # REPL helpers and build functions
├── public/                # Generated static site (gitignored)
├── bb.edn                 # Babashka tasks
├── deps.edn               # Clojure dependencies
└── index.md               # Landing page
```

## Writing Articles

Articles live in `notebooks/articles/` and can be either:
- `.clj` files (code by default, prose in `;;` comment blocks)
- `.md` files (Markdown by default, code in fenced blocks)

### Creating Spaced Repetition Cards

```clojure
(ns notebooks.articles.my-article
  (:require [bytes.cards :as cards]
            [bytes.viewers :as viewers]))

;; Create a card
(cards/card {:id "unique-card-id"
             :question "What is the answer?"
             :answer "42"
             :concept-tags #{:philosophy :hitchhikers-guide}})

;; Display inline (automatically picks up custom viewer)
(viewers/show-card
  (cards/card {...}))

;; Display multiple cards
(viewers/show-cards [card1 card2 card3])
```

### Card Anatomy

Cards are rendered as styled boxes with:
- Question (always visible)
- "Show Answer" button (currently non-interactive - see IMPLEMENTATION_PLAN.md)
- Answer content (revealed on click - pending)
- Concept tags
- Card ID for debugging

## REPL Workflow

Start a REPL and evaluate forms from `dev/user.clj`:

```clojure
;; Start server
(clerk/serve! {:browse? true :port 7777})

;; With file watcher
(clerk/serve! {:watch-paths ["notebooks" "src"]})

;; Clear cache
(clerk/clear-cache!)

;; Show specific article
(clerk/show! "notebooks/articles/intro_to_algorithms.clj")

;; Build static site
(build-static-site nil)
```

## Deployment

The site automatically deploys to GitHub Pages on every push to `main`.

### Setup GitHub Pages

1. Go to your repository Settings → Pages
2. Under "Build and deployment":
   - Source: GitHub Actions
3. Push to `main` branch to trigger deployment
4. Site will be available at `https://<username>.github.io/<repo-name>/`

### Manual Deployment

```bash
bb build
# Deploy the public/ directory to your hosting service
```

## Dependencies

- **Clojure 1.10.3** - Core language
- **Clerk 0.17.1102** - Notebook system and static site generator
- **SLF4J NOP** - Silence logging warnings

## Development Status

### ✅ Completed (Stage 1)
- Card data structure with EDN schema
- Custom Clerk viewer for rendering cards inline
- Static site generation with babashka tasks
- Sample article with embedded cards
- Clean, focused repository structure

### 🚧 Blocked (Stage 2)
Interactive features are blocked due to Clerk's function serialization limitations. See [IMPLEMENTATION_PLAN.md](IMPLEMENTATION_PLAN.md) for details.

## Contributing

See [IMPLEMENTATION_PLAN.md](IMPLEMENTATION_PLAN.md) for roadmap and technical decisions.

## License

Copyright © 2025
