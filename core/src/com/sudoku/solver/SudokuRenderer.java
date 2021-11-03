package com.sudoku.solver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sudoku.solver.board.Cell;
import com.sudoku.solver.board.Grid;

public class SudokuRenderer extends ShapeRenderer {
    SpriteBatch batch;
    BitmapFont font;
    Texture img;

    public SudokuRenderer() {
        super();
        img = new Texture("badlogic.jpg");
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    public void drawGrid(Grid grid, int cellSize) {
        Cell[][] puzzleGrid = grid.getPuzzleGrid();
        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[i].length; j++) {
                this.setColor(puzzleGrid[i][j].getColoring());
                this.rect(i * (cellSize + 1), j * (cellSize + 1), cellSize, cellSize);
            }
        }
        this.end();

        batch.begin();
        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[i].length; j++) {
                this.setColor(puzzleGrid[i][j].getColoring());
                if (puzzleGrid[i][j].getValue() != 0) {
                    font.draw(batch, Integer.toString(puzzleGrid[i][j].getValue()), i * (cellSize + 1) + cellSize / 2, j * (cellSize + 1) + cellSize / 2);
                }
            }
        }
        batch.end();
    }
}
