package com.sudoku.solver;

import com.badlogic.gdx.graphics.Color;

public final class SudokuProperties {
    /**
     *
     */
    public static final int CAMERA_BORDER = 20;
    /**
     *
     */
    public static final int CELL_SIZE = 40;
    /**
     *
     */
    public static final int TOTAL_VALUES = 9;
    /**
     *
     */
    public static final int INNER_SQUARE_SIZE = 3;
    /**
     *
     */
    public static final int INNER_SQUARE_BORDER = 3;
    /**
     *
     */
    public static final int INNER_SQUARES = 9;
    /**
     *
     */
    public static final int INNER_CELLS = 9;
    /**
     *
     */
    public static final int BOARD_ROWS = 9;
    /**
     *
     */
    public static final int BOARD_COLUMNS = 9;
    /**
     *
     */
    public static final Color DEFAULT_CELL_COLOR = Color.WHITE;
    /**
     *
     */
    public static final Color DEFAULT_ERROR_COLOR = Color.RED;
    /**
     *
     */
    public static final Color DEFAULT_OUTLINE_COLOR = Color.TEAL;
    /**
     *
     */
    public static final int OUTLINE_WEIGHT = 7;
    /**
     *
     */
    public static final int HI_RES_FONT_SIZE = 500;
    /**
     *
     */
    public static final Color DEFAULT_FONT_COLOR = Color.BLUE;
    /**
     *
     */
    public static final float SMALL_FONT_SCALE = 0.04f;
    /**
     *
     */
    public static final float LARGE_FONT_SCALE = 0.08f;

    public enum SudokuValues {
        /**
         *
         */
        NONE, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;
    }
}
