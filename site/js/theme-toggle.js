// Theme Toggle Functionality
(function() {
  const STORAGE_KEY = 'vade-bytes-theme';
  const DARK_CLASS = 'dark';

  // Get initial theme
  function getInitialTheme() {
    // Check localStorage first
    const stored = localStorage.getItem(STORAGE_KEY);
    if (stored) {
      return stored;
    }

    // Fall back to system preference
    if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {
      return 'dark';
    }

    return 'light';
  }

  // Apply theme to document
  function applyTheme(theme) {
    const html = document.documentElement;

    if (theme === 'dark') {
      html.classList.add(DARK_CLASS);
    } else {
      html.classList.remove(DARK_CLASS);
    }

    // Update icon
    updateIcon(theme);

    // Store preference
    localStorage.setItem(STORAGE_KEY, theme);
  }

  // Update the theme icon
  function updateIcon(theme) {
    const icon = document.getElementById('theme-icon');
    if (!icon) return;

    if (theme === 'dark') {
      // Show sun icon in dark mode
      icon.innerHTML = '<path fill-rule="evenodd" d="M10 2a1 1 0 011 1v1a1 1 0 11-2 0V3a1 1 0 011-1zm4 8a4 4 0 11-8 0 4 4 0 018 0zm-.464 4.95l.707.707a1 1 0 001.414-1.414l-.707-.707a1 1 0 00-1.414 1.414zm2.12-10.607a1 1 0 010 1.414l-.706.707a1 1 0 11-1.414-1.414l.707-.707a1 1 0 011.414 0zM17 11a1 1 0 100-2h-1a1 1 0 100 2h1zm-7 4a1 1 0 011 1v1a1 1 0 11-2 0v-1a1 1 0 011-1zM5.05 6.464A1 1 0 106.465 5.05l-.708-.707a1 1 0 00-1.414 1.414l.707.707zm1.414 8.486l-.707.707a1 1 0 01-1.414-1.414l.707-.707a1 1 0 011.414 1.414zM4 11a1 1 0 100-2H3a1 1 0 000 2h1z" clip-rule="evenodd"></path>';
    } else {
      // Show moon icon in light mode
      icon.innerHTML = '<path d="M17.293 13.293A8 8 0 016.707 2.707a8.001 8.001 0 1010.586 10.586z"></path>';
    }
  }

  // Toggle theme
  function toggleTheme() {
    const html = document.documentElement;
    const currentTheme = html.classList.contains(DARK_CLASS) ? 'dark' : 'light';
    const newTheme = currentTheme === 'dark' ? 'light' : 'dark';

    console.log('Toggling theme from', currentTheme, 'to', newTheme);
    applyTheme(newTheme);
  }

  // Initialize theme immediately (before page renders)
  const initialTheme = getInitialTheme();
  applyTheme(initialTheme);

  // Setup toggle button after DOM loads
  document.addEventListener('DOMContentLoaded', function() {
    // Theme toggle button
    const themeToggle = document.getElementById('theme-toggle');
    if (themeToggle) {
      console.log('Theme toggle button found and event listener attached');
      themeToggle.addEventListener('click', toggleTheme);
    } else {
      console.error('Theme toggle button not found!');
    }

    // Listen for system theme changes
    if (window.matchMedia) {
      window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', function(e) {
        // Only auto-switch if user hasn't set a preference
        if (!localStorage.getItem(STORAGE_KEY)) {
          applyTheme(e.matches ? 'dark' : 'light');
        }
      });
    }
  });
})();
