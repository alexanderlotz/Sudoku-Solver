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
    public static final int NUM_SUB_CELLS = 3;
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
    public static final Color BOARD_BG_COLOR = Color.valueOf("3C3C3C");//Color.TEAL;
    /**
     *
     */
    public static final Color DEFAULT_CELL_COLOR = Color.valueOf("F5F5F5");//Color.WHITE;
    /**
     *
     */
    public static final Color DEFAULT_ERROR_COLOR = Color.valueOf("FF5A5F");//Color.RED;
    /**
     *
     */
    public static final Color DEFAULT_OUTLINE_COLOR = Color.valueOf("087E8B");//Color.TEAL;
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
    public static final Color DEFAULT_FONT_COLOR = Color.valueOf("087E8B");//Color.BLUE;
    /**
     *
     */
    public static final float SMALL_FONT_SCALE = 0.03f;
    /**
     *
     */
    public static final float LARGE_FONT_SCALE = 0.08f;
    /**
     *
     */
    public static final Color GUI_BG_COLOR = Color.valueOf("ECDCB0");//Color.TEAL;

    public enum SudokuValues {
        /**
         *
         */
        NONE, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;
    }

    public enum MarkingPosition {
        /**
         *
         */
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, TOP_MIDDLE, BOTTOM_MIDDLE, MIDDLE_LEFT, MIDDLE_RIGHT, CENTER;
    }

    /*
    //Analyze rows
    for (int checkRow = 0; checkRow < BOARD_ROWS; checkRow++) {
        if (checkRow != row) {
        }
    }

    //Analyze columns
    for (int checkCol = 0; checkCol < BOARD_COLUMNS; checkCol++) {
        if (checkCol != col) {
        }
    }

    //Analyze inner squares
    for (int checkInner = 0; checkInner < INNER_CELLS; checkInner++) {
        int checkRow = (row - row % INNER_SQUARE_SIZE) + checkInner % INNER_SQUARE_SIZE;
        int checkCol = (col - col % INNER_SQUARE_SIZE) + checkInner / INNER_SQUARE_SIZE;
        if (checkRow != row || checkCol != col) {
        }
    }
    */
}
