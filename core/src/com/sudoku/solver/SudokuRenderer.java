package com.sudoku.solver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sudoku.solver.board.Grid;
import com.sudoku.solver.gui.GUI;

import static com.sudoku.solver.SudokuProperties.*;

public class SudokuRenderer extends ShapeRenderer {
    /**
     *
     */
    private SpriteBatch batch;
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
    /**
     *
     */
    private BitmapFont guiFont;
    /**
     *
     */
    private Grid puzzle;

    public SudokuRenderer(OrthographicCamera camera, Grid puzzle) {
        super();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        this.camera = camera;
        this.setProjectionMatrix(camera.combined);
        this.puzzle = puzzle;

        //Memory Leak here
        //https://github.com/libgdx/libgdx/wiki/Gdx-freetype
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Montserrat-Bold.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //parameter.kerning = false;


        parameter.size = SudokuProperties.HI_RES_FONT_SIZE;
        fontSmall = generator.generateFont(parameter);
        fontLarge = generator.generateFont(parameter);
        guiFont = generator.generateFont(parameter);
        generator.dispose();
        fontSmall.setColor(DEFAULT_FONT_COLOR);
        fontSmall.getData().setScale(SMALL_FONT_SCALE);
        fontLarge.setColor(DEFAULT_FONT_COLOR);
        fontLarge.getData().setScale(LARGE_FONT_SCALE);
        guiFont.setColor(GUI_FONT_COLOR);
        guiFont.getData().setScale(GUI_FONT_SCALE);
    }
//FIX TEXT RENDERING
    public void drawGrid(GUI gui) {
        //Draw cell
        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLUMNS; col++) {
                this.setColor((puzzle.getPuzzle()[row][col].isValid())
                        ? puzzle.getPuzzle()[row][col].getColoring()
                        : DEFAULT_ERROR_COLOR);
//                this.rect(worldCoord.x, worldCoord.y, CELL_SIZE, CELL_SIZE);
                this.rect(Grid.getScreenCoord(row), Grid.getScreenCoord(col), CELL_SIZE, CELL_SIZE);
            }
        }

        gui.drawLayout(this);

        drawFocusOutline();

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



        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLUMNS; col++) {
                this.setColor(puzzle.getPuzzle()[row][col].getColoring());
                if (puzzle.getPuzzle()[row][col].getValue() != 0) {
                    drawValue(row, col);
                } else {
                    drawMarkings(row, col);
                }
            }
        }
//        SpriteBatch guiBatch = new SpriteBatch();
//        guiBatch.setProjectionMatrix(camera.combined);
        gui.drawText(this, batch, guiFont);
        batch.end();
        //fontSmall.dispose();
        //fontLarge.dispose();
    }

    public void drawFocusOutline() { //weight should always be odd.
        int offset = (OUTLINE_WEIGHT / 2 + 1);
        this.setColor(DEFAULT_OUTLINE_COLOR);

        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLUMNS; col++) {
                if (puzzle.getPuzzle()[row][col].isFocused()) {
                    if (!((col + 1) < BOARD_COLUMNS && puzzle.getPuzzle()[row][col + 1].isFocused())) {
                        this.rect(Grid.getScreenCoord(row) - offset,
                                Grid.getScreenCoord(col) + CELL_SIZE + 1 - offset,
                                CELL_SIZE + offset * 2, OUTLINE_WEIGHT); //Top Border
                    }
                    if (!((row + 1) < BOARD_COLUMNS && puzzle.getPuzzle()[row + 1][col].isFocused())) {
                        this.rect(Grid.getScreenCoord(row) + CELL_SIZE + 1 - offset,
                                Grid.getScreenCoord(col) - offset,
                                OUTLINE_WEIGHT, CELL_SIZE + offset * 2); //Right Border
                    }
                    if (!((col - 1) >= 0 && puzzle.getPuzzle()[row][col - 1].isFocused())) {
                        this.rect(Grid.getScreenCoord(row) - offset,
                                Grid.getScreenCoord(col) - offset,
                                CELL_SIZE + offset * 2, OUTLINE_WEIGHT); //Bottom Border
                    }
                    if (!((row - 1) >= 0 && puzzle.getPuzzle()[row - 1][col].isFocused())) {
                        this.rect(Grid.getScreenCoord(row) - offset,
                                Grid.getScreenCoord(col) - offset,
                                OUTLINE_WEIGHT, CELL_SIZE + offset * 2); //Left Border
                    }
                }
            }
        }
    }

    public void drawValue(int row, int col) {
        GlyphLayout layout = new GlyphLayout();
        float fontX;
        float fontY;

        layout.setText(fontLarge, Integer.toString(puzzle.getPuzzle()[row][col].getValue()));
        fontX = Grid.getScreenCoord(row) + (CELL_SIZE - layout.width) / 2;
        fontY = Grid.getScreenCoord(col) + (CELL_SIZE + layout.height) / 2;
        fontLarge.draw(batch, layout, fontX, fontY);
    }

    public void drawMarkings(int row, int col) {
        int[] markings = puzzle.getPuzzle()[row][col].getCornerMarkArray();
        int markingIndex = 0;
        GlyphLayout layout = new GlyphLayout();
        float fontX = 0;
        float fontY = 0;
        float sideLength = CELL_SIZE / NUM_SUB_CELLS;

        while (markingIndex < markings.length) {
            layout.setText(fontSmall, Integer.toString(markings[markingIndex]));
            switch (MarkingPosition.values()[markingIndex]) {
                case TOP_LEFT:
                    fontX = Grid.getScreenCoord(row) + (0 * sideLength) + (sideLength - layout.width) / 2;
                    fontY = Grid.getScreenCoord(col) + (2 * sideLength) + (sideLength + layout.height) / 2;
                    break;
                case TOP_RIGHT:
                    fontX = Grid.getScreenCoord(row) + (2 * sideLength) + (sideLength - layout.width) / 2;
                    fontY = Grid.getScreenCoord(col) + (2 * sideLength) + (sideLength + layout.height) / 2;
                    break;
                case BOTTOM_LEFT:
                    fontX = Grid.getScreenCoord(row) + (0 * sideLength) + (sideLength - layout.width) / 2;
                    fontY = Grid.getScreenCoord(col) + (0 * sideLength) + (sideLength + layout.height) / 2;
                    break;
                case BOTTOM_RIGHT:
                    fontX = Grid.getScreenCoord(row) + (2 * sideLength) + (sideLength - layout.width) / 2;
                    fontY = Grid.getScreenCoord(col) + (0 * sideLength) + (sideLength + layout.height) / 2;
                    break;
                case TOP_MIDDLE:
                    fontX = Grid.getScreenCoord(row) + (1 * sideLength) + (sideLength - layout.width) / 2;
                    fontY = Grid.getScreenCoord(col) + (2 * sideLength) + (sideLength + layout.height) / 2;
                    break;
                case BOTTOM_MIDDLE:
                    fontX = Grid.getScreenCoord(row) + (1 * sideLength) + (sideLength - layout.width) / 2;
                    fontY = Grid.getScreenCoord(col) + (0 * sideLength) + (sideLength + layout.height) / 2;
                    break;
                case MIDDLE_LEFT:
                    fontX = Grid.getScreenCoord(row) + (0 * sideLength) + (sideLength - layout.width) / 2;
                    fontY = Grid.getScreenCoord(col) + (1 * sideLength) + (sideLength + layout.height) / 2;
                    break;
                case MIDDLE_RIGHT:
                    fontX = Grid.getScreenCoord(row) + (2 * sideLength) + (sideLength - layout.width) / 2;
                    fontY = Grid.getScreenCoord(col) + (1 * sideLength) + (sideLength + layout.height) / 2;
                    break;
                case CENTER:
                    fontX = Grid.getScreenCoord(row) + (1 * sideLength) + (sideLength - layout.width) / 2;
                    fontY = Grid.getScreenCoord(col) + (1 * sideLength) + (sideLength + layout.height) / 2;
                    break;
                default:
                    //markingIndex++;
            }
            fontSmall.draw(batch, layout, fontX, fontY);
            markingIndex++;
        }
    }
}
