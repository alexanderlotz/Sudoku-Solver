package com.sudoku.solver.board;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.TreeSet;

import static com.sudoku.solver.SudokuProperties.BOARD_COLUMNS;
import static com.sudoku.solver.SudokuProperties.BOARD_ROWS;
import static com.sudoku.solver.SudokuProperties.CELL_SIZE;
import static com.sudoku.solver.SudokuProperties.INNER_CELLS;
import static com.sudoku.solver.SudokuProperties.INNER_SQUARES;
import static com.sudoku.solver.SudokuProperties.INNER_SQUARE_BORDER;
import static com.sudoku.solver.SudokuProperties.INNER_SQUARE_SIZE;

public class Grid {
    /**
     *
     */
    private Cell[][] puzzle;
    /**
     *
     */
    private int width;
    /**
     *
     */
    private int height;
    /**
     *
     */
    private boolean markMode;

    /**
     *
     */
    public Grid() {
        puzzle = new Cell[BOARD_ROWS][BOARD_COLUMNS];
        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLUMNS; col++) {
                puzzle[row][col] = new Cell();
            }
        }
        width = (BOARD_COLUMNS * CELL_SIZE) + (INNER_SQUARE_SIZE + 1) * INNER_SQUARE_BORDER;
        height = (BOARD_ROWS * CELL_SIZE) + (INNER_SQUARE_SIZE + 1) * INNER_SQUARE_BORDER;
        markMode = false;
    }

    public Cell[][] getPuzzle() {
        return puzzle;
    }

    public void checkValid() { //make into bool
        TreeSet validNums = new TreeSet();

        //Clear cells
        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLUMNS; col++) {
                puzzle[row][col].setValid(true);
            }
        }

        //Vaidate rows
        for (int col = 0; col < BOARD_ROWS; col++) {
            for (int row = 0; row < BOARD_COLUMNS; row++) {
                if (puzzle[row][col].getValue() != 0 && !validNums.add(puzzle[row][col].getValue())) {
                    for (int i = 0; i < puzzle[col].length; i++) {
                        if (puzzle[i][col].getValue() == puzzle[row][col].getValue()) {
                            puzzle[i][col].setValid(false);
                        }
                    }
                    break;
                }
            }
            validNums.clear();
        }

        //Vaidate columns
        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLUMNS; col++) {
                if (puzzle[row][col].getValue() != 0 && !validNums.add(puzzle[row][col].getValue())) {
                    for (int i = 0; i < puzzle[row].length; i++) {
                        if (puzzle[row][i].getValue() == puzzle[row][col].getValue()) {
                            puzzle[row][i].setValid(false);
                        }
                    }
                    break;
                }
            }
            validNums.clear();
        }

        //Validate 3x3 blocks
        for (int innerSquare = 0; innerSquare < INNER_SQUARES; innerSquare++) {
            for (int cell = 0; cell < INNER_CELLS; cell++) {
                int innerOffset = innerSquare % INNER_SQUARE_SIZE;
                int innerRow = INNER_SQUARE_SIZE * (innerOffset) + cell % INNER_SQUARE_SIZE;
                int innerCol = (innerSquare - (innerOffset)) + cell / INNER_SQUARE_SIZE;
                if (puzzle[innerRow][innerCol].getValue() != 0
                        && !validNums.add(puzzle[innerRow][innerCol].getValue())) {
                    for (int i = 0; i < puzzle[innerSquare].length; i++) {
                        int checkRow = INNER_SQUARE_SIZE * (innerOffset) + i % INNER_SQUARE_SIZE;
                        int checkCol = (innerSquare - (innerOffset)) + i / INNER_SQUARE_SIZE;
                        if (puzzle[checkRow][checkCol].getValue() == puzzle[innerRow][innerCol].getValue()) {
                            puzzle[checkRow][checkCol].setValid(false);
                        }
                    }
                    break;
                }
            }
            validNums.clear();
        }
    }

    public static float getScreenCoord(int index) {
        return index * (CELL_SIZE + 1) + (index / INNER_SQUARE_SIZE) * INNER_SQUARE_BORDER;
    }

    public static int getGridCoord(int screenPos) { // FIX LATER
        return screenPos / (CELL_SIZE + 1);
    }

    /**
     *
     * @return TODO
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return TODO
     */
    public int getHeight() {
        return height;
    }

    public boolean isMarkMode() {
        return markMode;
    }

    public void setMarkMode(boolean markMode) {
        this.markMode = markMode;
    }

    public void writeToString() {
        String boardString = "";
        for (int col = BOARD_COLUMNS - 1; col >= 0; col--) {
            for (int row = 0; row < BOARD_ROWS; row++) {
                boardString += puzzle[row][col].getValue();
            }
        }
        StringSelection selection = new StringSelection(boardString);
        Toolkit.getDefaultToolkit()
                .getSystemClipboard().setContents(selection, selection);
    }

    public void reset() {
        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLUMNS; col++) {
                puzzle[row][col].clearCornerMarks();
                puzzle[row][col].setValue(0);
            }
        }
    }
    //public void readFromString(String boardString) {
    public void readFromString() {
        //boardString = boardString.replace(".","0");
        try {
            String boardString = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
            if (boardString.length() == BOARD_ROWS * BOARD_COLUMNS && boardString.matches("[0-9]+")) {
                reset();
                for (int row = 0; row < BOARD_ROWS; row++) {
                    for (int col = 0; col < BOARD_COLUMNS; col++) {
                        puzzle[row][col].setValue(Character.getNumericValue(boardString
                                .charAt(row + ((BOARD_COLUMNS - 1) - col) * BOARD_COLUMNS)));
                    }
                }
            }
        } catch (UnsupportedFlavorException | IOException e) {

        }
    }

    public void updateCell(int row, int col, int mark) {
        //Analyze rows
        for (int checkRow = 0; checkRow < BOARD_ROWS; checkRow++) {
             puzzle[checkRow][col].removeCornerMark(mark);
        }

        //Analyze columns
        for (int checkCol = 0; checkCol < BOARD_COLUMNS; checkCol++) {
            puzzle[row][checkCol].removeCornerMark(mark);
        }

        //Analyze inner squares
        for (int checkInner = 0; checkInner < INNER_CELLS; checkInner++) {
            int checkRow = (row - row % INNER_SQUARE_SIZE) + checkInner % INNER_SQUARE_SIZE;
            int checkCol = (col - col % INNER_SQUARE_SIZE) + checkInner / INNER_SQUARE_SIZE;
            puzzle[checkRow][checkCol].removeCornerMark(mark);
        }
    }

    public static Point enforceBounds(Point unboundedVector) {
        Point boundedVector = unboundedVector;
        if (boundedVector.x >= BOARD_COLUMNS) {
            boundedVector.x = BOARD_COLUMNS - 1;
        } else if (boundedVector.x < 0) {
            boundedVector.x = 0;
        }
        if (boundedVector.y >= BOARD_ROWS) {
            boundedVector.y = BOARD_ROWS - 1;
        } else if (boundedVector.y < 0) {
            boundedVector.y = 0;
        }
        return boundedVector;
    }

    public void clearFocus() {
        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLUMNS; col++) {
                puzzle[row][col].setFocused(false);
            }
        }
    }
}
