package com.sudoku.solver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.sudoku.solver.board.Cell;
import com.sudoku.solver.board.Grid;

import static com.sudoku.solver.SudokuProperties.CELL_SIZE;
import static com.sudoku.solver.SudokuProperties.DEFAULT_ERROR_COLOR;
import static com.sudoku.solver.SudokuProperties.DEFAULT_OUTLINE_COLOR;
import static com.sudoku.solver.SudokuProperties.OUTLINE_WEIGHT;

public class SudokuRenderer extends ShapeRenderer {
    /**
     *
     */
    private SpriteBatch batch;
    /**
     *
     */
    private BitmapFont font;
    /**
     *
     */
    private OrthographicCamera camera;
    /**
     *
     */
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    /**
     *
     */
    private BitmapFont fontSmall;
    /**
     *
     */
    private BitmapFont fontLarge;

    public SudokuRenderer(OrthographicCamera camera) {
        super();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        font = new BitmapFont();
        this.camera = camera;
        this.setProjectionMatrix(camera.combined);

        //Memory Leak here
        //https://github.com/libgdx/libgdx/wiki/Gdx-freetype
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Lato-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.kerning = false;


        parameter.size = SudokuProperties.HI_RES_FONT_SIZE;
        fontSmall = generator.generateFont(parameter);
        fontLarge = generator.generateFont(parameter);
        generator.dispose();
        fontSmall.setColor(SudokuProperties.DEFAULT_FONT_COLOR);
        fontSmall.getData().setScale(SudokuProperties.SMALL_FONT_SCALE);
        fontLarge.setColor(SudokuProperties.DEFAULT_FONT_COLOR);
        fontLarge.getData().setScale(SudokuProperties.LARGE_FONT_SCALE);
    }
//FIX TEXT RENDERING
    public void drawGrid(Grid grid, int cellSize) {
        //Draw cell
        Cell[][] puzzleGrid = grid.getPuzzleGrid();
        for (int row = 0; row < puzzleGrid.length; row++) {
            for (int col = 0; col < puzzleGrid[row].length; col++) {
                this.setColor((puzzleGrid[row][col].isValid())
                        ? puzzleGrid[row][col].getColoring()
                        : DEFAULT_ERROR_COLOR);
//                this.rect(worldCoord.x, worldCoord.y, cellSize, cellSize);
                this.rect(grid.getScreenCoord(row), grid.getScreenCoord(col), CELL_SIZE, CELL_SIZE);
            }
        }

        drawFocusOutline(grid, cellSize, OUTLINE_WEIGHT);

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

        GlyphLayout layout = new GlyphLayout();
        float fontX;
        float fontY;


        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[i].length; j++) {
                this.setColor(puzzleGrid[i][j].getColoring());
                if (puzzleGrid[i][j].getValue() != 0) {
                    layout.setText(fontLarge, Integer.toString(puzzleGrid[i][j].getValue()));
                    fontX = grid.getScreenCoord(i) + (cellSize - layout.width) / 2;
                    fontY = grid.getScreenCoord(j) + (cellSize + layout.height) / 2;
                    Vector3 screenCoord = camera.unproject(new Vector3(fontX, fontY, 0f));
//                    fontLarge.draw(batch, layout, screenCoord.x, screenCoord.y);
                    fontLarge.draw(batch, layout, fontX, fontY);
                }
            }
        }
        batch.end();

        //fontSmall.dispose();
        //fontLarge.dispose();
    }

    public void drawFocusOutline(Grid grid, int cellSize, int weight) { //weight should always be odd.
        int offset = (weight / 2 + 1);
        this.setColor(DEFAULT_OUTLINE_COLOR);

        Cell[][] puzzleGrid = grid.getPuzzleGrid();
        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[i].length; j++) {
                if (puzzleGrid[i][j].isFocused()) {
                    if (!((j + 1) < puzzleGrid[i].length && puzzleGrid[i][j + 1].isFocused())) {
                        this.rect(grid.getScreenCoord(i) - offset,
                                  grid.getScreenCoord(j) + cellSize + 1 - offset,
                                  cellSize + offset * 2, weight); //Top Border
                    }
                    if (!((i + 1) < puzzleGrid.length && puzzleGrid[i + 1][j].isFocused())) {
                        this.rect(grid.getScreenCoord(i) + cellSize + 1 - offset,
                                  grid.getScreenCoord(j) - offset, weight,
                                  cellSize + offset * 2); //Right Border
                    }
                    if (!((j - 1) >= 0 && puzzleGrid[i][j - 1].isFocused())) {
                        this.rect(grid.getScreenCoord(i) - offset,
                                  grid.getScreenCoord(j) - offset,
                                  cellSize + offset * 2, weight); //Bottom Border
                    }
                    if (!((i - 1) >= 0 && puzzleGrid[i - 1][j].isFocused())) {
                        this.rect(grid.getScreenCoord(i) - offset,
                                  grid.getScreenCoord(j) - offset,
                                  weight, cellSize + offset * 2); //Left Border
                    }
                }
            }
        }
    }
}
