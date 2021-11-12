package com.sudoku.solver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.sudoku.solver.board.Cell;
import com.sudoku.solver.board.Grid;

public class SudokuRenderer extends ShapeRenderer {
    SpriteBatch batch;
    BitmapFont font;
    OrthographicCamera camera;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont fontSmall;
    BitmapFont fontLarge;

    public SudokuRenderer() {
        this(new OrthographicCamera(0,0));
    }

    public SudokuRenderer(OrthographicCamera camera) {
        super();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        this.camera = camera;
        this.setProjectionMatrix(camera.combined);

        //Memory Leak here
        //https://github.com/libgdx/libgdx/wiki/Gdx-freetype
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Lato-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.kerning = false;

        parameter.size = (40 * 3) / 10;
        fontSmall = generator.generateFont(parameter); // font size 12 pixels
        //parameter.size *= 3;
        parameter.size = 400;
        fontLarge = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();
        fontLarge.setColor(Color.BLUE);
        fontLarge.getData().setScale(0.09f);
    }
//FIX TEXT RENDERING
    public void drawGrid(Grid grid, int cellSize) {
        //Draw cell
        Cell[][] puzzleGrid = grid.getPuzzleGrid();
        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[i].length; j++) {
                this.setColor((puzzleGrid[i][j].isValid()) ? puzzleGrid[i][j].getColoring() : Color.RED);
//                Vector3 worldCoord = camera.unproject(new Vector3(grid.getCoord(i, cellSize), grid.getCoord(j, cellSize), 0f));
//                this.rect(worldCoord.x, worldCoord.y, cellSize, cellSize);
                this.rect(grid.getCoord(i, cellSize), grid.getCoord(j, cellSize), cellSize, cellSize);
            }
        }

        drawFocusOutline(grid, cellSize, 7);

        this.end();

        //Draw Text
        batch.begin();


        /*FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Lato-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.kerning = false;

        parameter.size = (40 * 3) / 10;
        fontSmall = generator.generateFont(parameter); // font size 12 pixels
        parameter.size *= 3;
        fontLarge = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();
        */
        //https://stackoverflow.com/questions/14271570/libgdx-is-there-an-easy-way-to-center-text-on-each-axis-on-a-button
        GlyphLayout layout = new GlyphLayout();
        float fontX;
        float fontY;


        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[i].length; j++) {
                this.setColor(puzzleGrid[i][j].getColoring());
                if (puzzleGrid[i][j].getValue() != 0) {
                    layout.setText(fontLarge, Integer.toString(puzzleGrid[i][j].getValue()));
                    fontX = grid.getCoord(i, cellSize) + (cellSize - layout.width) / 2;
                    fontY = grid.getCoord(j, cellSize) + (cellSize + layout.height) / 2;
                    Vector3 screenCoord = camera.unproject(new Vector3(fontX, fontY, 0f));
//                    fontLarge.draw(batch, layout, screenCoord.x, screenCoord.y);
                    fontLarge.draw(batch, layout, fontX, fontY);
                }
            }
        }
        batch.end();

        //System.out.println((camera.unproject(new Vector3(grid.getCoord(1, cellSize), 0f, 0f)).x - camera.unproject(new Vector3(grid.getCoord(0, cellSize), 0f, 0f)).x)/cellSize);
        //fontSmall.dispose();
        //fontLarge.dispose();
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
