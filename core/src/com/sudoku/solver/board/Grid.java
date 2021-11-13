package com.sudoku.solver.board;

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
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                board[r][c].setValid(true);
            }
        }

        //Vaidate rows
        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[col].length; row++) {
                if (board[row][col].getValue() != 0 && !validNums.add(board[row][col].getValue())) {
                    for (int i = 0; i < board[col].length; i++) {
                        board[i][col].setValid(false);
                    }
                    break;
                }
            }
            validNums.clear();
        }

        //Vaidate columns
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col].getValue() != 0 && !validNums.add(board[row][col].getValue())) {
                    for (int i = 0; i < board[row].length; i++) {
                        board[row][i].setValid(false);
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

                if (board[INNER_SQUARE_SIZE * (innerOffset) + cell % INNER_SQUARE_SIZE]
                             [(innerSquare - (innerOffset)) + cell / INNER_SQUARE_SIZE]
                             .getValue() != 0
                          && !validNums.add(board[INNER_SQUARE_SIZE * (innerOffset) + cell % INNER_SQUARE_SIZE]
                                                      [(innerSquare - (innerOffset)) + cell / INNER_SQUARE_SIZE]
                                                      .getValue())) {
                    for (int i = 0; i < board[innerSquare].length; i++) {
                        board[INNER_SQUARE_SIZE * (innerOffset) + i % INNER_SQUARE_SIZE]
                                  [(innerSquare - (innerOffset)) + i / INNER_SQUARE_SIZE]
                                  .setValid(false);
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
}
