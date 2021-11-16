package com.sudoku.solver;

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
    public static final String BOARD_BG_COLOR = "3C3C3C";
    /**
     *
     */
    public static final String DEFAULT_CELL_COLOR = "F5F5F5";
    /**
     *
     */
    public static final String DEFAULT_ERROR_COLOR = "FF5A5F";
    /**
     *
     */
    public static final String DEFAULT_OUTLINE_COLOR = "087E8B";
    /**
     *
     */
    public static final int OUTLINE_WEIGHT = 5;
    /**
     *
     */
    public static final int HI_RES_FONT_SIZE = 100;
    /**
     *
     */
    public static final String DEFAULT_FONT_COLOR = "087E8B";
    /**
     *
     */
    public static final float SMALL_FONT_SCALE = 0.15f;
    /**
     *
     */
    public static final float LARGE_FONT_SCALE = 0.4f;
    /**
     *
     */
    public static final String GUI_FONT_COLOR = "087E8B";
    /**
     *
     */
    public static final float GUI_FONT_SCALE = 0.25f;
    /**
     *
     */
    public static final String GUI_BG_COLOR = "087E8B";
    /**
     *
     */
    public static final float GUI_BORDER = 5.5f;
    /**
     *
     */
    public static final float BUTTON_HEIGHT = 40;
    /**
     *
     */
    public static final float BUTTON_BORDER_WEIGHT = 3;
    /**
     *
     */
    public static final String BUTTON_COLOR = "F9A03F";
    /**
     *
     */
    public static final String BUTTON_BORDER_COLOR = "3C3C3C";
    /**
     *
     */
    public static final String BUTTON_CLICK_COLOR = "F5F5F5";

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
