package com.sudoku.solver.board;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.TreeSet;
import java.util.Vector;

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
    private Cell[][] board;
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
    public Grid() {
        board = new Cell[BOARD_ROWS][BOARD_COLUMNS];
        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLUMNS; col++) {
                board[row][col] = new Cell();
            }
        }
        width = (BOARD_COLUMNS * CELL_SIZE) + (INNER_SQUARE_SIZE + 1) * INNER_SQUARE_BORDER;
        height = (BOARD_ROWS * CELL_SIZE) + (INNER_SQUARE_SIZE + 1) * INNER_SQUARE_BORDER;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void checkValid() { //make into bool
        TreeSet validNums = new TreeSet();

        //Clear cells
        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLUMNS; col++) {
                board[row][col].setValid(true);
            }
        }

        //Vaidate rows
        for (int col = 0; col < BOARD_ROWS; col++) {
            for (int row = 0; row < BOARD_COLUMNS; row++) {
                if (board[row][col].getValue() != 0 && !validNums.add(board[row][col].getValue())) {
                    for (int i = 0; i < board[col].length; i++) {
                        if(board[i][col].getValue() == board[row][col].getValue()) {
                            board[i][col].setValid(false);
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
                if (board[row][col].getValue() != 0 && !validNums.add(board[row][col].getValue())) {
                    for (int i = 0; i < board[row].length; i++) {
                        if(board[row][i].getValue() == board[row][col].getValue()) {
                            board[row][i].setValid(false);
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
                if (board[innerRow][innerCol].getValue() != 0
                        && !validNums.add(board[innerRow][innerCol].getValue())) {
                    for (int i = 0; i < board[innerSquare].length; i++) {
                        int checkRow = INNER_SQUARE_SIZE * (innerOffset) + i % INNER_SQUARE_SIZE;
                        int checkCol = (innerSquare - (innerOffset)) + i / INNER_SQUARE_SIZE;
                        if(board[checkRow][checkCol].getValue() == board[innerRow][innerCol].getValue()) {
                            board[checkRow][checkCol].setValid(false);
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

    public void writeToString() {
        String boardString = "";
        for (int col = BOARD_COLUMNS - 1; col >= 0; col--) {
            for (int row = 0; row < BOARD_ROWS; row++) {
                boardString += board[row][col].getValue();
            }
        }
        System.out.println(boardString);
    }

    //public void readFromString(String boardString) {
    public void readFromString() {
        //boardString = boardString.replace(".","0");
        try {
            String boardString = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
            if (boardString.length() == BOARD_ROWS * BOARD_COLUMNS && boardString.matches("[0-9]+")) {
                for (int row = 0; row < BOARD_ROWS; row++) {
                    for (int col = 0; col < BOARD_COLUMNS; col++) {
                        board[row][col].clearCornerMarks();
                        board[row][col].setValue(0);
                        board[row][col].setValue(Character.getNumericValue(boardString.charAt(row + ((BOARD_COLUMNS - 1) - col) * BOARD_COLUMNS)));
                    }
                }
            }
        }
        catch (UnsupportedFlavorException | IOException e) {

        }
    }

    public void updateCell(int row, int col, int mark) {
        //Analyze rows
        for (int checkRow = 0; checkRow < BOARD_ROWS; checkRow++) {
             board[checkRow][col].removeCornerMark(mark);
        }

        //Analyze columns
        for (int checkCol = 0; checkCol < BOARD_COLUMNS; checkCol++) {
            board[row][checkCol].removeCornerMark(mark);
        }

        //Analyze inner squares
        for (int checkInner = 0; checkInner < INNER_CELLS; checkInner++) {
            int checkRow = (row - row % INNER_SQUARE_SIZE) + checkInner % INNER_SQUARE_SIZE;
            int checkCol = (col - col % INNER_SQUARE_SIZE) + checkInner / INNER_SQUARE_SIZE;
            board[checkRow][checkCol].removeCornerMark(mark);
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
}
