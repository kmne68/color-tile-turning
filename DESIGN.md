# Color Tile Turning - Master Game Design Document

**Version**: 1.0 (Consolidated 2026-06-11)  
**Status**: Guiding document for MVP and future iterations  
**Repo**: https://github.com/kmne68/color-tile-turning

## 1. Game Overview
- **Working Title**: Color Tile Turning
- **Genre**: Turn-based strategy / resource management with spatial territory elements
- **Players**: 2 (human vs human; AI opponent planned)
- **Board**: Square grid, configurable size (default 20x20 for testing; range 10x10–100x100)
- **Core Mechanic**: Players expend points each turn to adjust RGB values of tiles toward their target color (Player 1: Black [0,0,0] subtracts; Player 2: White [255,255,255] adds). Capture/lock tiles for point bonuses and control.
- **Objective**: Maximize the number of captured (locked) tiles by game end.
- **Win Condition**: Most captured tiles.
- **Theme/Visuals**: Abstract, dynamic RGB-colored grid. Tiles update visually in real-time.

## 2. Core Rules & Mechanics
### Board & Tiles
- Tiles initialize with random RGB (0-255 per channel). Consider minimum distance from targets for balance.
- **Capture**: Tile locks when RGB exactly matches player's target. Locked tiles cannot be changed (except via future rules like Surround & Surrender).

### Points System (Start of Turn)
- Base: **500 points**
- +1 per captured tile owned
- +1 per contiguous captured tile (orthogonal adjacency: shared sides only)
- Must spend all points in the turn (no carry-over)
- Distribute points freely across R/G/B on one or more non-locked tiles

### Turn Flow
1. Select tile (mouse click)
2. View current RGB
3. Input R/G/B deltas (text fields)
4. Apply changes (clamped [0,255])
5. Auto-lock on exact target match
6. Repeat until points exhausted

### Game End (Configurable)
- Default: Rounds ≈ total tiles / 2 (scales with grid size)
- Options: Fixed number of rounds, all tiles captured, or sudden death modes

### Adjacency & Bonuses
- Contiguous = sharing a side (not diagonal — testable)
- Full contiguous bonus implementation needed (flood-fill recommended)

## 3. Interface Requirements (JavaFX)
- Clickable grid with live RGB background colors
- Player stats panel (captured count, points remaining, target color, turn indicator)
- RGB input controls (text fields + submit/apply)
- Hover tooltips (current RGB, points to capture)
- Clean, responsive layout

## 4. Technical Specifications
- **Stack**: Java (JDK 11+), Maven, JavaFX, NetBeans IDE
- **Core Classes** (already in repo):
  - `Tile.java`
  - `Player.java`
  - `Board.java`
  - `Game.java`
  - `Main.java` (JavaFX entry)
- **Principles**:
  - DRY, Single Responsibility (Curly’s Law)
  - Encapsulation, clear naming, comments
  - Extensible for AI, new modes, etc.

## 5. TODO List & Future Features
### MVP Priorities
- Complete contiguous bonus logic
- Full JavaFX GUI (clickable tiles, inputs, stats)
- Configurable grid size / rounds
- Basic playtesting & balance

### High Priority
- Player-selectable target colors
- AI opponent (subclass Player)
- Surround & Surrender rule (8-directional encirclement)
- Sudden death bomb tile (configurable behavior)
- Capture the Flag mode
- Save/load game states

### Additional Ideas
- One-click capture helpers
- Visual effects / animations
- Performance optimizations for large grids
- Online multiplayer (future)

## 6. Balance & Playtesting Notes
- Monitor snowballing from large clusters
- Test point economy (500 base works well)
- Ensure initial random distribution is fair
- Orthogonal vs diagonal adjacency impact

---

**This document is living** — edit as we iterate. All prior Word doc, Drive files, and chat history have been incorporated.
