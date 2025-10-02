# Implementation Plan: Clerk-based Mnemonic Medium Platform

## Overview
Build a publishing platform using Clerk that combines essay-style articles with embedded spaced repetition prompts, inspired by Quantum Country.

## Stage 1: Article Template & Card System
**Goal**: Create foundation for writing articles with embedded spaced repetition cards
**Success Criteria**:
- Can write articles in markdown/clj with embedded cards
- Cards have metadata (ID, question, answer, concept tags)
- Preview articles in Clerk viewer
**Tests**:
- Create sample article with 5+ embedded cards
- Verify cards render distinctly from article content
- Check card metadata is preserved
**Status**: Complete

### Tasks:
- [x] Define card data structure (EDN schema)
- [x] Create `src/bytes/cards.clj` with card helper functions
- [x] Build custom Clerk viewer for rendering cards inline
- [x] Create template article in `notebooks/articles/template.md`
- [x] Test with sample article on a simple topic

## Stage 2: Interactive Spaced Repetition UI
**Goal**: Build ClojureScript viewer component for interactive card answering
**Success Criteria**:
- Users can reveal/hide answers in browser
- Cards track self-reported difficulty (easy/medium/hard)
- State persists in browser localStorage
- Visual design matches reading flow (minimal interruption)
**Tests**:
- Click card to reveal answer
- Rate difficulty and verify localStorage update
- Refresh page and confirm state persists
- Test on mobile viewport
**Status**: Not Started

### Tasks:
- [ ] Create ClojureScript viewer in `src/bytes/viewers/card.cljs`
- [ ] Implement show/hide answer interaction
- [ ] Add difficulty rating buttons (easy/medium/hard)
- [ ] Wire up localStorage for client-side persistence
- [ ] Style component to match Quantum Country aesthetic
- [ ] Test interaction flow end-to-end

## Stage 3: Spaced Repetition Algorithm & Scheduling
**Goal**: Implement SM-2 algorithm to schedule card reviews
**Success Criteria**:
- Calculate next review date based on difficulty rating
- Track review history per card per user
- Display "due for review" cards at session start
- Export/import user progress data
**Tests**:
- Answer card as "easy" → verify 4+ day interval
- Answer as "hard" → verify shorter interval
- Load article with mixed due/not-due cards
- Export progress as EDN, clear storage, re-import
**Status**: Not Started

### Tasks:
- [ ] Implement SM-2 algorithm in `src/bytes/srs/scheduler.clj`
- [ ] Create review session viewer (separate page/mode)
- [ ] Build progress tracking data structure
- [ ] Add import/export functionality for user data
- [ ] Create dashboard showing review statistics
- [ ] Write unit tests for scheduling logic

## Stage 4: Multi-Article Publishing System
**Goal**: Build static site with navigation, index, and article organization
**Success Criteria**:
- Generate static site with multiple articles
- Home page with article gallery/index
- Navigation between articles
- Responsive design with good typography
- Deploy-ready static output
**Tests**:
- Build site with 3+ articles
- Navigate between articles via links
- View on mobile and desktop
- Check all assets load correctly
- Verify prompt state persists across navigation
**Status**: Not Started

### Tasks:
- [ ] Create article index/manifest system
- [ ] Build home page template with article cards
- [ ] Design navigation component (header/footer)
- [ ] Configure `clerk/build-static-app!` for multi-article build
- [ ] Implement responsive CSS (mobile-first)
- [ ] Add metadata (author, date, tags) to articles
- [ ] Test full build and serving of static site

## Stage 5: Enhanced Features & Polish
**Goal**: Add features that enhance learning and user experience
**Success Criteria**:
- Reading progress tracking per article
- Author's notes / expandable sections
- Search across articles
- Optional user accounts (future: sync across devices)
**Tests**:
- Track scroll position and resume reading
- Search for concept across multiple articles
- Expand/collapse author's notes sections
**Status**: Not Started

### Tasks:
- [ ] Implement reading progress indicator
- [ ] Create expandable "author's notes" viewer
- [ ] Build client-side search with lunr.js or similar
- [ ] Add table of contents for long articles
- [ ] Implement dark mode toggle
- [ ] Add social sharing metadata (Open Graph)
- [ ] Performance optimization (lazy loading, code splitting)

---

## Technical Decisions

### Core Stack:
- **Clerk**: Notebook system and static site generator
- **ClojureScript**: Interactive viewer components
- **SQLite**: Optional server-side progress tracking (Stage 3+)
- **localStorage**: Client-side progress persistence (Stage 2)

### Data Structures:
```clojure
;; Card definition
{:card/id "qc-qubit-state-1"
 :card/question "What are the two basis states of a qubit?"
 :card/answer "|0⟩ and |1⟩"
 :card/concept-tags #{:quantum-computing :qubits :basics}
 :card/article "notebooks/articles/quantum_intro.md"}

;; User progress
{:user/id "local-user"
 :progress [{:card-id "qc-qubit-state-1"
             :reviews [{:date #inst "2025-10-01" :difficulty :easy}
                       {:date #inst "2025-10-03" :difficulty :medium}]
             :next-review #inst "2025-10-10"
             :interval-days 7
             :ease-factor 2.5}]}
```

### Spaced Repetition Algorithm (SM-2):
- Initial interval: 1 day
- Easy rating: multiply interval by ease factor (default 2.5)
- Medium: multiply by 1.3
- Hard: reset to 1 day, reduce ease factor
- Track ease factor per prompt (adjusts over time)

---

## Next Steps
1. Start Stage 1 by defining card schema
2. Create sample article as proof of concept
3. Iterate on viewer design for readability
