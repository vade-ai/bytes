/**
 * Vade Bytes Spaced Repetition System
 * Inspired by quantum.country's mnemonic medium
 */

class SpacedRepetition {
  constructor() {
    this.storage = new SRStorage();
    this.prompts = [];
    this.currentPromptIndex = null;
  }

  /**
   * Initialize SR system on page load
   */
  init() {
    this.findPrompts();
    this.renderPrompts();
    this.updateProgress();
  }

  /**
   * Find all SR prompts in the document
   */
  findPrompts() {
    const promptElements = document.querySelectorAll('[data-sr-prompt]');
    this.prompts = Array.from(promptElements).map((el, index) => {
      const promptData = JSON.parse(el.getAttribute('data-sr-prompt'));
      return {
        id: promptData.id || `prompt-${index}`,
        question: promptData.question,
        answer: promptData.answer,
        explanation: promptData.explanation || '',
        element: el,
        ...this.storage.getPromptState(promptData.id || `prompt-${index}`)
      };
    });
  }

  /**
   * Render interactive UI for each prompt
   */
  renderPrompts() {
    this.prompts.forEach((prompt, index) => {
      const container = document.createElement('div');
      container.className = 'sr-prompt-container';
      container.innerHTML = `
        <div class="sr-prompt" data-prompt-index="${index}">
          <div class="sr-prompt-question">
            <p>${prompt.question}</p>
          </div>
          <div class="sr-prompt-actions">
            <button class="sr-reveal-btn" data-index="${index}">
              Show Answer
            </button>
          </div>
          <div class="sr-prompt-answer" style="display: none;">
            <div class="sr-answer-content">
              <p>${prompt.answer}</p>
              ${prompt.explanation ? `<p class="sr-explanation"><em>${prompt.explanation}</em></p>` : ''}
            </div>
            <div class="sr-feedback-buttons">
              <button class="sr-feedback-btn" data-index="${index}" data-difficulty="hard">
                Hard
              </button>
              <button class="sr-feedback-btn" data-index="${index}" data-difficulty="medium">
                Medium
              </button>
              <button class="sr-feedback-btn" data-index="${index}" data-difficulty="easy">
                Easy
              </button>
            </div>
          </div>
        </div>
      `;

      prompt.element.appendChild(container);

      // Add event listeners
      const revealBtn = container.querySelector('.sr-reveal-btn');
      const feedbackBtns = container.querySelectorAll('.sr-feedback-btn');

      revealBtn.addEventListener('click', () => this.revealAnswer(index));
      feedbackBtns.forEach(btn => {
        btn.addEventListener('click', (e) => {
          const difficulty = e.target.getAttribute('data-difficulty');
          this.recordFeedback(index, difficulty);
        });
      });
    });
  }

  /**
   * Reveal answer for a prompt
   */
  revealAnswer(index) {
    const prompt = this.prompts[index];
    const answerDiv = prompt.element.querySelector('.sr-prompt-answer');
    const revealBtn = prompt.element.querySelector('.sr-reveal-btn');

    answerDiv.style.display = 'block';
    revealBtn.style.display = 'none';

    // Track that answer was revealed
    this.storage.updatePromptState(prompt.id, {
      lastSeen: Date.now(),
      timesRevealed: (prompt.timesRevealed || 0) + 1
    });
  }

  /**
   * Record user feedback on difficulty
   */
  recordFeedback(index, difficulty) {
    const prompt = this.prompts[index];
    const promptElement = prompt.element.querySelector('.sr-prompt');

    // Calculate next review interval using simplified SM-2 algorithm
    const interval = this.calculateInterval(prompt, difficulty);

    // Update state
    this.storage.updatePromptState(prompt.id, {
      lastReviewed: Date.now(),
      nextReview: Date.now() + interval,
      difficulty: difficulty,
      reviewCount: (prompt.reviewCount || 0) + 1,
      easeFactor: this.calculateEaseFactor(prompt.easeFactor || 2.5, difficulty)
    });

    // Visual feedback
    promptElement.classList.add('sr-prompt-answered');
    promptElement.classList.add(`sr-difficulty-${difficulty}`);

    setTimeout(() => {
      promptElement.classList.remove('sr-prompt-answered');
    }, 1000);

    // Update progress display
    this.updateProgress();
  }

  /**
   * Calculate next review interval (simplified SM-2)
   */
  calculateInterval(prompt, difficulty) {
    const easeFactor = prompt.easeFactor || 2.5;
    const reviewCount = prompt.reviewCount || 0;

    let interval;
    if (difficulty === 'hard') {
      interval = 1000 * 60 * 60 * 24; // 1 day
    } else if (difficulty === 'medium') {
      interval = 1000 * 60 * 60 * 24 * 7; // 1 week
    } else { // easy
      // Use SM-2 formula
      if (reviewCount === 0) {
        interval = 1000 * 60 * 60 * 24 * 7; // 1 week
      } else if (reviewCount === 1) {
        interval = 1000 * 60 * 60 * 24 * 21; // 3 weeks
      } else {
        const prevInterval = prompt.lastInterval || (1000 * 60 * 60 * 24 * 21);
        interval = prevInterval * easeFactor;
      }
    }

    return interval;
  }

  /**
   * Calculate ease factor (SM-2)
   */
  calculateEaseFactor(currentEF, difficulty) {
    let quality;
    if (difficulty === 'hard') quality = 1;
    else if (difficulty === 'medium') quality = 3;
    else quality = 5;

    const newEF = currentEF + (0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02));
    return Math.max(1.3, newEF);
  }

  /**
   * Update progress display in sidebar
   */
  updateProgress() {
    const now = Date.now();
    const levels = {
      'long-term': 0,    // > 3 months
      '3-months': 0,     // 1-3 months
      '3-weeks': 0,      // 1-3 weeks
      '1-week': 0,       // < 1 week
      'in-text': 0       // not yet reviewed
    };

    this.prompts.forEach(prompt => {
      const state = this.storage.getPromptState(prompt.id);
      if (!state.lastReviewed) {
        levels['in-text']++;
      } else {
        const daysSince = (now - state.lastReviewed) / (1000 * 60 * 60 * 24);
        if (daysSince > 90) levels['long-term']++;
        else if (daysSince > 30) levels['3-months']++;
        else if (daysSince > 7) levels['3-weeks']++;
        else levels['1-week']++;
      }
    });

    // Update UI
    const progressLevels = document.querySelectorAll('.sr-level');
    if (progressLevels.length > 0) {
      progressLevels[0].querySelector('.sr-level-count').textContent = levels['long-term'];
      progressLevels[1].querySelector('.sr-level-count').textContent = levels['3-months'];
      progressLevels[2].querySelector('.sr-level-count').textContent = levels['3-weeks'];
      progressLevels[3].querySelector('.sr-level-count').textContent = levels['1-week'];
      progressLevels[4].querySelector('.sr-level-count').textContent = levels['in-text'];
    }
  }
}

/**
 * Storage manager for SR state
 */
class SRStorage {
  constructor() {
    this.storageKey = 'vade_bytes_sr_state';
  }

  getState() {
    const data = localStorage.getItem(this.storageKey);
    return data ? JSON.parse(data) : {};
  }

  setState(state) {
    localStorage.setItem(this.storageKey, JSON.stringify(state));
  }

  getPromptState(promptId) {
    const state = this.getState();
    return state[promptId] || {};
  }

  updatePromptState(promptId, updates) {
    const state = this.getState();
    state[promptId] = { ...(state[promptId] || {}), ...updates };
    this.setState(state);
  }

  clearState() {
    localStorage.removeItem(this.storageKey);
  }
}

/**
 * Global initialization function
 */
function initSpacedRepetition() {
  const sr = new SpacedRepetition();
  sr.init();

  // Make SR instance globally available
  window.vadeSR = sr;
}

// Auto-initialize if prompts exist
if (document.readyState === 'loading') {
  document.addEventListener('DOMContentLoaded', initSpacedRepetition);
} else {
  initSpacedRepetition();
}
