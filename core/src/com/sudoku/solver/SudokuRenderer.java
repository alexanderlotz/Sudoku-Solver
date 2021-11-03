package com.sudoku.solver;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sudoku.solver.board.Cell;
import com.sudoku.solver.board.Grid;

public class SudokuRenderer extends ShapeRenderer {
    public SudokuRenderer() {
        super();
    }

    public void drawGrid(Grid grid, int cellSize) {
        Cell[][] puzzleGrid = grid.getPuzzleGrid();
        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[i].length; j++) {
                this.setColor(puzzleGrid[i][j].getColoring());
                this.rect(i * (cellSize + 1), j * (cellSize + 1), cellSize, cellSize);
            }
        }
    }
}
