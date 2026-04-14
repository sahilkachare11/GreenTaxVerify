# Design System Specification: Architectural Integrity

## 1. Overview & Creative North Star
### Creative North Star: "The Modern Registry"
This design system moves away from the rigid, cold bureaucracy of traditional government interfaces. Instead, it adopts the persona of a **Modern Registry**—an experience that feels as authoritative as a marble hall but as fluid and accessible as a high-end digital editorial.

The system breaks the "standard template" look by utilizing **intentional asymmetry** and **tonal layering**. We do not use lines to separate ideas; we use space and light. The goal is to provide a sense of "bureaucratic calm," where the user feels guided through complex tax and environmental verification processes by a sophisticated, invisible hand.

---

## 2. Color & Atmospheric Depth
Our palette is rooted in the "Evergreen" (`#2E7D32`), but we utilize Material 3 tonal palettes to create a sense of environmental prestige.

### The "No-Line" Rule
**Explicit Instruction:** Designers are prohibited from using 1px solid borders for sectioning. Structural boundaries must be defined solely through background color shifts. 
- Use `surface-container-low` for secondary content areas sitting on a `surface` background.
- Use `surface-container-lowest` for primary interactive cards to create a natural, "rising" effect.

### Surface Hierarchy & Nesting
Treat the UI as a series of stacked, physical layers of fine vellum.
- **Base Level:** `surface` (#f8faf8)
- **Content Blocks:** `surface-container` (#eceeec)
- **Focus Elements:** `surface-container-lowest` (#ffffff) to pull the user’s eye toward data entry points.

### The "Glass & Gradient" Rule
To escape the "flat" government look, employ **Signature Textures**:
- **Hero Transitions:** Use a subtle linear gradient from `primary` (#0d631b) to `primary-container` (#2e7d32) at a 145-degree angle. This provides a "soul" and depth to headers that flat hex codes cannot achieve.
- **Glassmorphism:** For floating navigation or modal overlays, use `surface` with 80% opacity and a `24px` backdrop-blur. This ensures the "environment" (the green-tinted background) bleeds through, softening the interface.

---

## 3. Typography: The Editorial Voice
We utilize a dual-typeface strategy to balance official authority with modern readability.

*   **Display & Headlines (Public Sans):** This is our "Official" voice. It is sturdy, geometric, and trustworthy. Use `display-lg` for high-impact environmental stats and `headline-md` for section starts.
*   **Body & Labels (Inter):** This is our "Functional" voice. Highly legible at small scales, it ensures tax data is unmistakable.

**Editorial Hierarchy Tip:** Use `display-md` in `on-surface-variant` (#40493d) for background context, then overlay `headline-sm` in `primary` for the active focus. This "scale-shifting" creates a premium, magazine-like feel.

---

## 4. Elevation & Depth: Tonal Layering
Traditional drop shadows are too "digital." We use **Ambient Light** and **Tonal Stacking**.

*   **The Layering Principle:** Depth is achieved by placing a `surface-container-lowest` card (Pure White) onto a `surface-container-low` background. The contrast in value creates a "soft lift" without a single pixel of shadow.
*   **Ambient Shadows:** If a floating element (like a FAB or Tooltip) is required, use a shadow with a `32px` blur, `0%` spread, and `6%` opacity. The shadow color should be `on-surface` (#191c1b), never pure black.
*   **The "Ghost Border" Fallback:** If a border is required for accessibility in input fields, use `outline-variant` (#bfcaba) at **20% opacity**. It should be felt, not seen.

---

## 5. Components: Bespoke Materiality

### Buttons
*   **Primary:** A "Forest Gradient" (Primary to Primary-Container). `0.5rem` (lg) roundedness. No border.
*   **Secondary:** `surface-container-highest` background with `on-secondary-container` text. This feels integrated into the page rather than "pasted on."
*   **Tertiary:** Text-only in `primary` (#0d631b), bold weight, with a `0.25rem` bottom margin on hover to simulate a physical lift.

### Input Fields
*   **Styling:** Forgo the "box." Use a `surface-container-low` background with a `0.5rem` top-radius and a `2px` bottom stroke in `primary` that only appears on focus.
*   **Tone:** Use `label-md` for floating labels, always in `on-surface-variant` to maintain a professional distance.

### Cards & Data Lists
*   **No Dividers:** Lists must never use horizontal lines. Separate list items using `12px` of vertical white space or a subtle toggle of `surface` vs `surface-container-low`.
*   **Verification Chips:** Use `primary-fixed` (#a3f69c) with `on-primary-fixed` text for "Verified" states. The low-contrast, high-saturation look signifies modern professional standards.

### Specialized Components: "The Trust Badge"
A custom component for GreenTax Verify: A floating `surface-container-lowest` badge with a `glassmorphism` blur, containing a `primary` leaf icon and `label-sm` text confirming government-backed verification.

---

## 6. Do’s and Don'ts

### Do
*   **DO** use whitespace as a structural element. If a section feels crowded, increase the padding rather than adding a line.
*   **DO** use iconography as a "Signpost." Icons (leaf, government building) should be `primary` color and paired with `headline-sm` typography.
*   **DO** nest containers to show relationship (e.g., a "Tax Form" card inside a "Verification" section).

### Don’t
*   **DON'T** use 100% opaque black for text. Always use `on-surface` (#191c1b) to maintain a soft, high-end feel.
*   **DON'T** use standard Material 2 "Elevated" shadows. They feel dated and "app-like." We want "editorial."
*   **DON'T** use high-contrast borders. If the design "breaks" without a border, the tonal hierarchy isn't strong enough. Adjust your surface tokens instead.