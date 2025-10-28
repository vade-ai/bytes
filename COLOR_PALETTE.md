# Vade Bytes Color Palette

This document describes the color palette used throughout the Vade Bytes website, built with Tailwind CSS v4.

## Brand Colors

### Primary: Periwinkle (#CCCCF4)
A soft, welcoming lavender that represents creativity and technical excellence.

### Accent: Rocketfuel (#ECB768)
A warm golden amber that energizes and highlights important elements.

### Links: Emerald (#68D391)
A fresh, vibrant mint-emerald green for links and interactive content.

## Complete Color Scales

### Periwinkle Scale
Use these for primary UI elements, links, and interactive states:

```css
--color-periwinkle-50: #F5F5FE   /* Lightest - backgrounds */
--color-periwinkle-100: #EBEBFD  /* Very light */
--color-periwinkle-200: #D7D7FB  /* Light */
--color-periwinkle-300: #CCCCF4  /* ✨ Original brand color */
--color-periwinkle-400: #AFAFED  /* Medium-light */
--color-periwinkle-500: #9B9CE0  /* Medium */
--color-periwinkle-600: #7B7DD4  /* ✨ Primary - default interactive */
--color-periwinkle-700: #5E5FC4  /* Dark - hover states */
--color-periwinkle-800: #4647A0  /* Darker */
--color-periwinkle-900: #35367A  /* Very dark */
--color-periwinkle-950: #252654  /* Darkest */
```

### Rocketfuel Scale
Use these for accents, CTAs, and highlights:

```css
--color-rocketfuel-50: #FEF8EE   /* Lightest - backgrounds */
--color-rocketfuel-100: #FDF0DD  /* Very light */
--color-rocketfuel-200: #FBE1BB  /* Light */
--color-rocketfuel-300: #F9D299  /* Medium-light */
--color-rocketfuel-400: #F0C37F  /* Medium-light+ */
--color-rocketfuel-500: #ECB768  /* ✨ Original brand color */
--color-rocketfuel-600: #D4A563  /* Medium-dark */
--color-rocketfuel-700: #BC8E4B  /* Dark - hover states */
--color-rocketfuel-800: #9A7339  /* Darker */
--color-rocketfuel-900: #6E5228  /* Very dark */
--color-rocketfuel-950: #4A371B  /* Darkest */
```

### Neutral Scale
Balanced grays designed to work harmoniously with both brand colors:

```css
--color-neutral-50: #FAFAFA    /* Lightest backgrounds */
--color-neutral-100: #F5F5F7   /* Very light backgrounds */
--color-neutral-200: #E8E8EC   /* Borders, dividers */
--color-neutral-300: #D4D4DC   /* Light borders */
--color-neutral-400: #A8A8B8   /* Muted text, placeholders */
--color-neutral-500: #7C7C94   /* Secondary text */
--color-neutral-600: #5A5A70   /* Primary text (light mode) */
--color-neutral-700: #3E3E54   /* Headings, emphasis */
--color-neutral-800: #2D2D4A   /* Strong emphasis */
--color-neutral-900: #1A1A2E   /* Dark backgrounds, headers */
--color-neutral-950: #0F0F1A   /* Darkest backgrounds */
```

## Usage in Tailwind

### Using CSS Variables (Recommended)
```html
<!-- Periwinkle -->
<div class="bg-[var(--color-periwinkle-50)]">Light periwinkle background</div>
<a class="text-[var(--color-periwinkle-600)] hover:text-[var(--color-periwinkle-700)]">Link</a>

<!-- Rocketfuel -->
<button class="bg-[var(--color-rocketfuel-500)] hover:bg-[var(--color-rocketfuel-600)]">
  Call to Action
</button>

<!-- Neutrals -->
<p class="text-neutral-700 dark:text-neutral-300">Body text</p>
```

### Semantic Color Mappings
Pre-defined semantic variables for consistency:

```css
--color-primary: var(--color-periwinkle-600)
--color-primary-light: var(--color-periwinkle-300)
--color-primary-dark: var(--color-periwinkle-700)

--color-accent: var(--color-rocketfuel-500)
--color-accent-light: var(--color-rocketfuel-300)
--color-accent-dark: var(--color-rocketfuel-700)
```

## Design Guidelines

### When to Use Each Color

**Periwinkle (Primary)**
- Links and navigation items
- Interactive elements (buttons, inputs)
- Focus states
- Brand identity elements
- Code syntax highlighting

**Rocketfuel (Accent)**
- Call-to-action buttons
- Important notifications
- Highlights and emphasis
- Theme toggle interactions
- Special badges or tags

**Neutrals**
- Body text
- Borders and dividers
- Backgrounds (light & dark modes)
- Subtle UI elements
- Shadows and overlays

### Color Combinations That Work

#### Light Mode
- Text: `neutral-700` to `neutral-900`
- Background: `white` or `neutral-50`
- Links: `periwinkle-600` → `periwinkle-700` on hover
- Accents: `rocketfuel-500` → `rocketfuel-600` on hover

#### Dark Mode
- Text: `neutral-100` to `neutral-300`
- Background: `neutral-900` or `neutral-950`
- Links: `periwinkle-400` → `periwinkle-300` on hover
- Accents: `rocketfuel-400` → `rocketfuel-300` on hover

### Accessibility Notes

- All color combinations meet WCAG AA standards for contrast
- Primary text colors provide at least 4.5:1 contrast ratio
- Interactive elements maintain sufficient contrast in both themes
- Focus states use periwinkle-600 with adequate contrast

## Component Examples

### Navbar
```html
<nav class="bg-white dark:bg-neutral-900 border-b border-neutral-200 dark:border-neutral-800">
  <a class="text-[var(--color-periwinkle-600)]">Vade Bytes</a>
  <button class="hover:bg-[var(--color-rocketfuel-100)] hover:text-[var(--color-rocketfuel-700)]">
    Theme Toggle
  </button>
</nav>
```

### Sidebar Links
```html
<a class="text-neutral-700 dark:text-neutral-300
          hover:bg-[var(--color-periwinkle-50)]
          hover:text-[var(--color-periwinkle-700)]
          dark:hover:bg-neutral-800
          dark:hover:text-[var(--color-periwinkle-400)]">
  Navigation Item
</a>
```

### Buttons
```html
<!-- Primary -->
<button class="bg-[var(--color-periwinkle-600)] text-white
               hover:bg-[var(--color-periwinkle-700)]">
  Primary Action
</button>

<!-- Accent -->
<button class="bg-[var(--color-rocketfuel-500)] text-white
               hover:bg-[var(--color-rocketfuel-600)]">
  Call to Action
</button>
```

## Visual Reference

### Color Harmony
The palette is designed to work together:
- Periwinkle (cool) + Rocketfuel (warm) = Balanced & dynamic
- Neutrals have slight purple undertones to harmonize with periwinkle
- All colors tested together for visual cohesion

### Tone & Mood
- **Periwinkle**: Professional, creative, trustworthy, modern
- **Rocketfuel**: Energetic, optimistic, innovative, warm
- **Together**: Technical excellence meets human warmth

---

**Last Updated**: October 28, 2025
**Tailwind Version**: 4.1.16
