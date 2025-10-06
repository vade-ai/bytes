# Vade Bytes

Technical explorations and insights from the Vade team.

## About

Vade Bytes is built with [Clay](https://scicloj.github.io/clay/), enabling literate programming in Clojure with rich, interactive visualizations.

## Writing Posts

### Quick Start

1. Create a new Clojure namespace in `src/` following the pattern:

```clojure
^{:kindly/hide-code true
  :clay {:title "Your Post Title"
         :quarto {:author "Your Name"
                  :date "2025-10-05"
                  :type :post
                  :category :your-category
                  :tags [:tag1 :tag2]}}}
(ns your.namespace.post-name
  (:require [scicloj.kindly.v4.kind :as kind]))

;; # Your Post Title
;;
;; Write prose in comment blocks like this.
```

2. Write code and add visualizations using Kindly:

```clojure
^kind/table
{:x [1 2 3]
 :y [4 5 6]}
```

### Development with Babashka (Recommended)

We provide Babashka tasks for common workflows:

```bash
# Create a new blog post
bb new-post "My Awesome Post"

# Start Clay interactive development (browser-based)
bb dev

# Build and preview the complete static site
bb dev-site   # or bb serve

# Just preview (without rebuilding)
bb preview

# Build the site
bb site              # Full build (Clay + Quarto)
bb build             # Just Clay markdown generation
bb render            # Just Quarto rendering

# Clean build artifacts
bb clean
bb clean-all         # Deep clean including caches

# Check your environment
bb check

# See all tasks
bb tasks
```

### Manual Commands

If you prefer not to use Babashka:

```bash
# Interactive development
clojure -M:clay

# Build static site
clojure -M:clay -A:markdown
quarto render site
```

The built site will be in `site/_site/` directory.

### File Organization

- `src/` - Source namespaces for blog posts
- `site/` - Quarto configuration and static assets
- `temp/` - Temporary build files (git ignored)

### Categories

Use these predefined categories:
- `:ai` - AI & Machine Learning
- `:data` - Data Engineering & Analytics
- `:systems` - System Design & Architecture
- `:clojure` - Clojure & Functional Programming
- `:meta` - About this blog

Tags are flexible - use whatever makes sense for your post.

## Testing the Build Locally

To test the complete build pipeline locally:

```bash
# Clean previous builds (optional)
rm -rf site/_site site/examples temp

# Run the full build
clojure -M:clay -A:markdown
quarto render site

# Preview the site
open site/_site/index.html
# or use: quarto preview site
```

## Publishing

The GitHub Actions workflow automatically builds and deploys when you push to `main`:

1. Write your post in `src/`
2. Preview locally with `clojure -M:clay`
3. Test the full build (see above)
4. Commit and push to `main` branch
5. GitHub Actions will build and deploy to GitHub Pages

**Manual trigger:** You can also trigger the workflow manually from the GitHub Actions tab.

## Technology Stack

- **Clay** - Literate programming for Clojure
- **Kindly** - Rich visualizations and data display
- **Quarto** - Static site generation
- **Noj** - Data science toolkit

## License

Copyright © 2025 Vade AI

Distributed under the Eclipse Public License version 1.0.
