package com.sudoku.solver.board;

public class Grid {
    Cell[][] puzzleGrid;

    public Grid() {
        puzzleGrid = new Cell[9][9];
        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[i].length; j++) {
                puzzleGrid[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getPuzzleGrid() {
        return puzzleGrid;
    }
}
