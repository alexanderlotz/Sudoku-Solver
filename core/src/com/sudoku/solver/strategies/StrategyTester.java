package com.sudoku.solver.strategies;

import com.sudoku.solver.SudokuProperties;
import com.sudoku.solver.board.Grid;
import com.sudoku.solver.SudokuProperties.SudokuValues;

import static com.sudoku.solver.SudokuProperties.BOARD_COLUMNS;
import static com.sudoku.solver.SudokuProperties.BOARD_ROWS;
import static com.sudoku.solver.SudokuProperties.INNER_CELLS;
import static com.sudoku.solver.SudokuProperties.INNER_SQUARE_SIZE;

public class StrategyTester {
    public StrategyTester() {

    }

    public static void solve(Grid puzzle) { //break and redo marks if strategy found
        //setCellMarks(puzzle);
        /*for (int row = 0; row < SudokuProperties.BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLUMNS; col++) {
                if (puzzle.getBoard()[row][col].getValue() == SudokuValues.NONE.ordinal()) {
                    NHSingles.parse(puzzle, row, col);
                }
            }
        }*/
        NHSingles.parse(puzzle);
    }

    public static void setCellMarks(Grid puzzle) {
        for (int row = 0; row < SudokuProperties.BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLUMNS; col++) {
                if (puzzle.getBoard()[row][col].getValue() == SudokuValues.NONE.ordinal()) {
                    puzzle.getBoard()[row][col].addCornerMarks(new Integer[]{
                            SudokuValues.ONE.ordinal(),
                            SudokuValues.TWO.ordinal(),
                            SudokuValues.THREE.ordinal(),
                            SudokuValues.FOUR.ordinal(),
                            SudokuValues.FIVE.ordinal(),
                            SudokuValues.SIX.ordinal(),
                            SudokuValues.SEVEN.ordinal(),
                            SudokuValues.EIGHT.ordinal(),
                            SudokuValues.NINE.ordinal(),
                    });
                    //Analyze rows
                    for (int checkRow = 0; checkRow < BOARD_ROWS; checkRow++) {
                        if (puzzle.getBoard()[checkRow][col].getValue() != SudokuValues.NONE.ordinal()) {
                            puzzle.getBoard()[row][col].removeCornerMark(puzzle.getBoard()[checkRow][col].getValue());
                        }
                    }
                    //Analyze columns
                    for (int checkCol = 0; checkCol < BOARD_COLUMNS; checkCol++) {
                        if (puzzle.getBoard()[row][checkCol].getValue() != SudokuValues.NONE.ordinal()) {
                            puzzle.getBoard()[row][col].removeCornerMark(puzzle.getBoard()[row][checkCol].getValue());
                        }
                    }
                    //Analyze inner squares
                    for (int checkInner = 0; checkInner < INNER_CELLS; checkInner++) {
                        int checkRow = (row - row % INNER_SQUARE_SIZE) + checkInner % INNER_SQUARE_SIZE;
                        int checkCol = (col - col % INNER_SQUARE_SIZE) + checkInner / INNER_SQUARE_SIZE;
                        if (puzzle.getBoard()[checkRow][checkCol].getValue() != SudokuValues.NONE.ordinal()) {
                            puzzle.getBoard()[row][col]
                                    .removeCornerMark(puzzle.getBoard()[checkRow][checkCol].getValue());
                        }
                    }
                }
            }
        }
    }
}
