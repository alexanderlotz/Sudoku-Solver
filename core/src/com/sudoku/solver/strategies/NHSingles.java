package com.sudoku.solver.strategies;

import com.sudoku.solver.board.Cell;
import com.sudoku.solver.board.Grid;

import java.util.Set;

import static com.sudoku.solver.SudokuProperties.BOARD_COLUMNS;
import static com.sudoku.solver.SudokuProperties.BOARD_ROWS;
import static com.sudoku.solver.SudokuProperties.INNER_CELLS;
import static com.sudoku.solver.SudokuProperties.INNER_SQUARE_SIZE;

public class NHSingles {
    public static boolean parse(Grid puzzle, int row, int col) {
        Cell checkedCell = puzzle.getBoard()[row][col];
        Set<Integer> cellMarkings = checkedCell.getCornerMarks();
        boolean matched;

        for (int mark : cellMarkings) {
            //Analyze rows
            matched = true;
            for (int checkRow = 0; checkRow < BOARD_ROWS; checkRow++) {
                if (checkRow != row) {
                    //if (containsMark(puzzle.getBoard()[checkRow][col].getCornerMarkArray(), mark)) {
                    if (puzzle.getBoard()[checkRow][col].getCornerMarks().contains(mark)) {
                        matched = false;
                        checkRow = BOARD_ROWS;
                    }
                }
            }
            if (matched) {
                checkedCell.clearCornerMarks();
                checkedCell.setValue(mark);
                return true;
            }

            //Analyze columns
            matched = true;
            for (int checkCol = 0; checkCol < BOARD_COLUMNS; checkCol++) {
                if (checkCol != col) {
                    //if (containsMark(puzzle.getBoard()[row][checkCol].getCornerMarkArray(), mark)) {
                    if (puzzle.getBoard()[row][checkCol].getCornerMarks().contains(mark)) {
                        matched = false;
                        checkCol = BOARD_COLUMNS;
                    }
                }
            }
            if (matched) {
                checkedCell.clearCornerMarks();
                checkedCell.setValue(mark);
                return true;
            }

            //Analyze inner squares
            matched = true;
            for (int checkInner = 0; checkInner < INNER_CELLS; checkInner++) {
                int checkRow = (row - row % INNER_SQUARE_SIZE) + checkInner % INNER_SQUARE_SIZE;
                int checkCol = (col - col % INNER_SQUARE_SIZE) + checkInner / INNER_SQUARE_SIZE;
                if (checkRow != row || checkCol != col) {
                    //if (containsMark(puzzle.getBoard()[checkRow][checkCol].getCornerMarkArray(),mark)) {
                    if (puzzle.getBoard()[checkRow][checkCol].getCornerMarks().contains(mark)) {
                        matched = false;
                        checkInner = INNER_CELLS;
                    }
                }

            }
            if (matched) {
                checkedCell.clearCornerMarks();
                checkedCell.setValue(mark);
                return true;
            }
        }
        return false;
    }

    public static boolean containsMark(int[] markings, int mark) {
        for (int i = 0; i < markings.length; i++) {
            if (markings[i] == mark) {
                return true;
            }
        }
        return false;
    }
}
