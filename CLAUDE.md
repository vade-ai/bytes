# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a collection of **Clerk demo notebooks** showcasing interactive literate programming in Clojure. [Clerk](https://clerk.vision) enables rich, local-first notebook experiences using standard Clojure namespaces and Markdown files.

**Key Architecture Principles:**
- Notebooks live in `notebooks/` as `.clj` or `.md` files
- `.clj` files: Code by default, prose in comment blocks (lines starting with `;;`)
- `.md` files: Markdown by default, executable Clojure in code fences
- Clerk auto-caches computations for performance
- Browser viewer paginates large data structures

## Development Commands

### Starting the Development Server

**Interactive development (recommended):**
```bash
# Start REPL, then evaluate forms from dev/user.clj
# Most editors support "jack-in" with deps.edn
```

**From command line:**
```bash
# Start server with file watcher on port 7777
clj -M:nextjournal/clerk nextjournal.clerk/serve! --watch-paths notebooks --port 7777 --browse

# Or use default port 6677 without browser
clj -M:nextjournal/clerk nextjournal.clerk/serve! --watch-paths notebooks
```

### Common REPL Commands (from dev/user.clj)

```clojure
;; Start server with browser
(clerk/serve! {:browse? true :port 6677})

;; Start with file watcher
(clerk/serve! {:watch-paths ["notebooks" "src"]})

;; Clear cache (useful when debugging)
(clerk/clear-cache!)

;; Show specific notebook
(clerk/show! "notebooks/introduction.clj")

;; Build static site
(clerk/build-static-app! {:paths ["notebooks/introduction.clj" "notebooks/data_science.clj"]})
```

### Building Static Site

```bash
# Uses :nextjournal/clerk alias with predefined paths
clj -X:nextjournal/clerk
```

## Codebase Structure

**Notebook Organization:**
- `notebooks/introduction.clj` - Core Clerk features, viewer API, extensibility
- `notebooks/data_science.clj` - Working with TSV, Excel, SQLite databases
- `notebooks/semantic.clj` - WikiData/semantic web queries
- `notebooks/sicmutils.clj` - Physics simulations (double pendulum)
- `notebooks/rule_30.clj` - Cellular automata with custom viewers
- `notebooks/images.clj` - Image processing examples
- Other notebooks demonstrate controls, git integration, Logo graphics, etc.

**Key Dependencies (deps.edn):**
- `nextjournal/clerk` - Core notebook system
- `next.jdbc`, `meta-csv`, `docjure` - Data ingestion (DB, CSV, Excel)
- `kixi/stats` - Statistical routines
- `sicmutils` - Numerical/physics computations
- `mundaneum` - Semantic web/WikiData queries
- `arrowic` - Box/arrow graph visualizations

**Entry Points:**
- `index.md` - Landing page with gallery of notebooks
- `dev/user.clj` - REPL workflow helpers (comment blocks with examples)

## Clerk-Specific Patterns

### Form Metadata

```clojure
;; Hide code in viewer
^{:nextjournal.clerk/visibility {:code :hide}}

;; Disable caching for this form
^:nextjournal.clerk/no-cache (shuffle (range 100))
```

### Built-in Viewers

```clojure
(clerk/table data)              ;; Tabular data (maps, seqs, or grid)
(clerk/plotly {...})            ;; Plotly charts
(clerk/vl {...})                ;; Vega-Lite visualizations
(clerk/md "markdown string")    ;; Render markdown
(clerk/tex "latex string")      ;; Render TeX/KaTeX
(clerk/html [:div ...])         ;; Hiccup or HTML strings
(clerk/code '(expression))      ;; Syntax-highlighted code
```

### Custom Viewers

Use `clerk/with-viewer` or `clerk/add-viewers!` to create domain-specific visualizations. Viewers consist of:
- `:pred` - Predicate to match data
- `:render-fn` - ClojureScript function (runs in browser)
- `:transform-fn` - Optional JVM-side preprocessing

Example in `notebooks/introduction.clj:204-207`.

## Working with Notebooks

**File Structure:**
- Top of file: namespace with requires
- Comment blocks (`;; text`) become prose in viewer
- Top-level forms are evaluated and displayed
- Use metadata to control caching and visibility

**Data Loading Patterns:**
- CSV: `meta-csv.core/read-csv`
- Excel: `dk.ative.docjure.spreadsheet` (see helper in `data_science.clj:88-99`)
- SQLite: `next.jdbc` with `:dbtype "sqlite"` and `:dbname` pointing to `.db` file
- HTTP: Standard Java `HttpClient` or `babashka.http-client`

**Workflow:**
1. Edit notebook file in your editor
2. Save triggers auto-reload in browser (if watcher running)
3. Or explicitly call `(clerk/show! "path/to/notebook.clj")` from REPL

## Important Notes

- Clojure 1.10.3 used for compatibility with `kixi/stats` and `sicmutils`
- Datasets in `datasets/` directory (TSV, CSV, Excel, SQLite, images)
- Static site generation configured in `:nextjournal/clerk` alias
- Log4j XML config in `resources/log4j.xml` (warnings silenced with slf4j-nop)
