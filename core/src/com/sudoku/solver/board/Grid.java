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
    private Cell[][] puzzleGrid;
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
        puzzleGrid = new Cell[BOARD_ROWS][BOARD_COLUMNS];
        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[i].length; j++) {
                puzzleGrid[i][j] = new Cell();
            }
        }
        width = (BOARD_COLUMNS * CELL_SIZE) + (INNER_SQUARE_SIZE + 1) * INNER_SQUARE_BORDER;
        height = (BOARD_ROWS * CELL_SIZE) + (INNER_SQUARE_SIZE + 1) * INNER_SQUARE_BORDER;
    }

    public Cell[][] getPuzzleGrid() {
        return puzzleGrid;
    }

    public void checkValid() { //make into bool
        TreeSet validNums = new TreeSet();

        //Clear cells
        for (int r = 0; r < puzzleGrid.length; r++) {
            for (int c = 0; c < puzzleGrid[r].length; c++) {
                puzzleGrid[r][c].setValid(true);
            }
        }

        //Vaidate rows
        for (int col = 0; col < puzzleGrid.length; col++) {
            for (int row = 0; row < puzzleGrid[col].length; row++) {
                if (puzzleGrid[row][col].getValue() != 0 && !validNums.add(puzzleGrid[row][col].getValue())) {
                    for (int i = 0; i < puzzleGrid[col].length; i++) {
                        puzzleGrid[i][col].setValid(false);
                    }
                    break;
                }
            }
            validNums.clear();
        }

        //Vaidate columns
        for (int row = 0; row < puzzleGrid.length; row++) {
            for (int col = 0; col < puzzleGrid[row].length; col++) {
                if (puzzleGrid[row][col].getValue() != 0 && !validNums.add(puzzleGrid[row][col].getValue())) {
                    for (int i = 0; i < puzzleGrid[row].length; i++) {
                        puzzleGrid[row][i].setValid(false);
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

                if (puzzleGrid[INNER_SQUARE_SIZE * (innerOffset) + cell % INNER_SQUARE_SIZE]
                             [(innerSquare - (innerOffset)) + cell / INNER_SQUARE_SIZE]
                             .getValue() != 0
                          && !validNums.add(puzzleGrid[INNER_SQUARE_SIZE * (innerOffset) + cell % INNER_SQUARE_SIZE]
                                                      [(innerSquare - (innerOffset)) + cell / INNER_SQUARE_SIZE]
                                                      .getValue())) {
                    for (int i = 0; i < puzzleGrid[innerSquare].length; i++) {
                        puzzleGrid[INNER_SQUARE_SIZE * (innerOffset) + i % INNER_SQUARE_SIZE]
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
