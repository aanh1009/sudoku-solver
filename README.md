This project implements a stack-based **depth-first search (DFS)** Sudoku solver in Java. It includes a GUI for real-time visualization and supports random puzzle generation, file-based loading, and constraint-based heuristics to optimize solving speed.

> Built as part of the Data Structures & Algorithms course at Colby College.

---

## 🚀 Features

- 🧠 **DFS Backtracking Algorithm** with stack-based state management  
- 🧩 **Constraint checking** (row, column, 3x3 grid) for valid number placement  
- 🎨 **GUI Visualization** using `LandscapeDisplay` and `Graphics` for step-by-step solving  
- 🗂️ **Multiple board loading options**: random generation or file input  
- 📊 **Exploration support**: measures impact of initial clues on solution time and success rate  

---

## 🗂️ Project Structure

```bash
.
├── Sudoku.java             # Main solver class with visualization & algorithm
├── Board.java              # Manages 9x9 grid of Cell objects
├── Cell.java               # Represents individual Sudoku cell
├── LandscapeDisplay.java   # GUI class for drawing the board
├── board1.txt              # Example board input file
├── SudokuTests.java        # Unit tests
└── README.md               # This file
```

---

## 🧪 How It Works

### 1. DFS with Backtracking
The solver uses an explicit `Stack<Cell>` to explore paths and backtrack on dead ends:
- Finds the next empty cell
- Tries values 1 through 9
- Backtracks if no valid values are left
- Repeats until solved

### 2. Constraint Checking
Validates:
- Unique values in rows and columns
- Unique values in 3x3 grids

### 3. Visualization
- 🔵 Locked values (blue), 🔴 Guessed values (red)
- "✅ Hurray!" or "❌ No Solution!" messages on solve attempt
- Adjustable solving speed with delay parameter

---

## 🧑‍💻 Usage

### Compile
```bash
javac *.java
```

### Run with Random Board
```bash
java Sudoku
```

### Run with File Input
```bash
java Sudoku board1.txt
```

### Run Tests
```bash
javac SudokuTests.java && java SudokuTests
```

---

## 📊 Exploration: Clue Count vs Solve Time

Run multiple boards with increasing locked values to test:
- ✅ Solvability rate
- ⏱️ Time taken to solve
- 🔁 Number of backtracking steps
