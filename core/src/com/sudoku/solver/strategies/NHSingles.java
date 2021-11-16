package com.sudoku.solver.strategies;

import com.sudoku.solver.board.Cell;
import com.sudoku.solver.board.Grid;

import java.util.Set;

import static com.sudoku.solver.SudokuProperties.BOARD_COLUMNS;
import static com.sudoku.solver.SudokuProperties.BOARD_ROWS;
import static com.sudoku.solver.SudokuProperties.INNER_CELLS;
import static com.sudoku.solver.SudokuProperties.INNER_SQUARE_SIZE;

import com.sudoku.solver.SudokuProperties.SudokuValues;

public class NHSingles {
    public static boolean parse(Grid puzzle) {
        Cell checkedCell;
        Set<Integer> cellMarkings;
        boolean matched;

        for (int row = 0; row < BOARD_ROWS; row++) {
            for (int col = 0; col < BOARD_COLUMNS; col++) {
                checkedCell = puzzle.getPuzzle()[row][col];
                cellMarkings = checkedCell.getCornerMarks();

                if (checkedCell.getValue() == SudokuValues.NONE.ordinal()) {
                    if (checkedCell.getCornerMarks().size() == 1) {
                        int mark = checkedCell.getCornerMarkArray()[0];
                        checkedCell.clearCornerMarks();
                        puzzle.updateCell(row, col, mark);
                        checkedCell.setValue(mark);
                        return true;
                    }
                    for (int mark : cellMarkings) {
                        //Analyze rows
                        matched = true;
                        for (int checkRow = 0; checkRow < BOARD_ROWS; checkRow++) {
                            if (checkRow != row) {
                                if (puzzle.getPuzzle()[checkRow][col].getCornerMarks().contains(mark)) {
                                    matched = false;
                                    checkRow = BOARD_ROWS;
                                }
                            }
                        }
                        if (matched) {
                            checkedCell.clearCornerMarks();
                            puzzle.updateCell(row, col, mark);
                            checkedCell.setValue(mark);
                            return true;
                        }

                        //Analyze columns
                        matched = true;
                        for (int checkCol = 0; checkCol < BOARD_COLUMNS; checkCol++) {
                            if (checkCol != col) {
                                if (puzzle.getPuzzle()[row][checkCol].getCornerMarks().contains(mark)) {
                                    matched = false;
                                    checkCol = BOARD_COLUMNS;
                                }
                            }
                        }
                        if (matched) {
                            checkedCell.clearCornerMarks();
                            puzzle.updateCell(row, col, mark);
                            checkedCell.setValue(mark);
                            return true;
                        }

                        //Analyze inner squares
                        matched = true;
                        for (int checkInner = 0; checkInner < INNER_CELLS; checkInner++) {
                            int checkRow = (row - row % INNER_SQUARE_SIZE) + checkInner % INNER_SQUARE_SIZE;
                            int checkCol = (col - col % INNER_SQUARE_SIZE) + checkInner / INNER_SQUARE_SIZE;
                            if (checkRow != row || checkCol != col) {
                                if (puzzle.getPuzzle()[checkRow][checkCol].getCornerMarks().contains(mark)) {
                                    matched = false;
                                    checkInner = INNER_CELLS;
                                }
                            }

                        }
                        if (matched) {
                            checkedCell.clearCornerMarks();
                            puzzle.updateCell(row, col, mark);
                            checkedCell.setValue(mark);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
