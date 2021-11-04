package com.sudoku.solver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sudoku.solver.board.Cell;
import com.sudoku.solver.board.Grid;

public class SudokuRenderer extends ShapeRenderer {
    SpriteBatch batch;
    BitmapFont font;

    public SudokuRenderer() {
        super();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    public void drawGrid(Grid grid, int cellSize) {
        //Draw cell
        Cell[][] puzzleGrid = grid.getPuzzleGrid();
        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[i].length; j++) {
                this.setColor((puzzleGrid[i][j].isValid()) ? puzzleGrid[i][j].getColoring() : Color.RED);
                this.rect(grid.getCoord(i, cellSize), grid.getCoord(j, cellSize), cellSize, cellSize);
            }
        }

        drawFocusOutline(grid, cellSize, 7);

        this.end();

        //https://github.com/libgdx/libgdx/wiki/Gdx-freetype
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Lato-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (cellSize * 3) / 8;
        BitmapFont fontSmall = generator.generateFont(parameter); // font size 12 pixels
        parameter.size *= 2;
        BitmapFont fontLarge = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();

        //Draw Text
        batch.begin();

        //https://stackoverflow.com/questions/14271570/libgdx-is-there-an-easy-way-to-center-text-on-each-axis-on-a-button
        GlyphLayout layout = new GlyphLayout();
        float fontX;
        float fontY;

        fontLarge.setColor(Color.BLUE);
        //fontLarge.getData().setScale(2);

        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[i].length; j++) {
                this.setColor(puzzleGrid[i][j].getColoring());
                if (puzzleGrid[i][j].getValue() != 0) {
                    layout.setText(fontLarge, Integer.toString(puzzleGrid[i][j].getValue()));
                    fontX = grid.getCoord(i, cellSize) + (cellSize - layout.width) / 2;
                    fontY = grid.getCoord(j, cellSize) + (cellSize + layout.height) / 2;
                    fontLarge.draw(batch, layout, fontX, fontY);
                }
            }
        }
        batch.end();
    }

    public void drawFocusOutline(Grid grid, int cellSize, int weight) { //weight should always be odd.
        int offset = (weight / 2 + 1);
        this.setColor(Color.TEAL);

        Cell[][] puzzleGrid = grid.getPuzzleGrid();
        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[i].length; j++) {
                if(puzzleGrid[i][j].isFocused()) {
                    if (!((j + 1) < puzzleGrid[i].length && puzzleGrid[i][j + 1].isFocused())) {
                        this.rect(grid.getCoord(i, cellSize) - offset, grid.getCoord(j, cellSize) + cellSize + 1 - offset, cellSize + offset * 2, weight); //Top Border
                    }
                    if (!((i + 1) < puzzleGrid.length && puzzleGrid[i + 1][j].isFocused())) {
                        this.rect(grid.getCoord(i, cellSize) + cellSize + 1 - offset, grid.getCoord(j, cellSize) - offset, weight, cellSize + offset * 2); //Right Border
                    }
                    if (!((j - 1) >= 0 && puzzleGrid[i][j - 1].isFocused())) {
                        this.rect(grid.getCoord(i, cellSize) - offset, grid.getCoord(j, cellSize) - offset, cellSize + offset * 2, weight); //Bottom Border
                    }
                    if (!((i - 1) >= 0 && puzzleGrid[i - 1][j].isFocused())) {
                        this.rect(grid.getCoord(i, cellSize) - offset, grid.getCoord(j, cellSize) - offset, weight, cellSize + offset * 2); //Left Border
                    }
                }
            }
        }
    }
}
