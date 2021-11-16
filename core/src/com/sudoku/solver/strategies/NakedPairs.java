package com.sudoku.solver.strategies;

import com.sudoku.solver.board.Cell;
import com.sudoku.solver.board.Grid;

import java.util.ArrayList;
import java.util.Set;

import static com.sudoku.solver.SudokuProperties.BOARD_COLUMNS;
import static com.sudoku.solver.SudokuProperties.BOARD_ROWS;
import static com.sudoku.solver.SudokuProperties.INNER_CELLS;
import static com.sudoku.solver.SudokuProperties.INNER_SQUARE_SIZE;

public class NakedPairs {
    public static boolean parse(Grid puzzle, int row, int col) {
        if (puzzle.getPuzzle()[row][col].getCornerMarks().size() == 2) {
            return parseNakedPair(puzzle, row, col);
        } else if (puzzle.getPuzzle()[row][col].getCornerMarks().size() == 3) {
            return parseNakedTriple(puzzle, row, col);
        } else if (puzzle.getPuzzle()[row][col].getCornerMarks().size() == 4) {
            return parseNakedQuad(puzzle, row, col);
        } else {
            return false;
        }
    }

    public static boolean parseNakedPair(Grid puzzle, int row, int col) {
        Cell checkedCell = puzzle.getPuzzle()[row][col];
        Set<Integer> cellMarkings = checkedCell.getCornerMarks();
        boolean matched;
        ArrayList<Cell> candidates = new ArrayList<>();
        candidates.add(checkedCell);

        //Analyze rows
        for (int checkRow = 0; checkRow < BOARD_ROWS; checkRow++) {
            if (checkRow != row) {
                if (puzzle.getPuzzle()[checkRow][col].getCornerMarks().size() == 2) {
                    matched = true;
                    for (int mark : cellMarkings) {
                        //if ()
                    }
                }
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

        return false;
    }

    public static boolean parseNakedTriple(Grid puzzle, int row, int col) {
        Cell checkedCell = puzzle.getPuzzle()[row][col];
        Set<Integer> cellMarkings = checkedCell.getCornerMarks();
        boolean matched;
        return false;
    }

    public static boolean parseNakedQuad(Grid puzzle, int row, int col) {
        Cell checkedCell = puzzle.getPuzzle()[row][col];
        Set<Integer> cellMarkings = checkedCell.getCornerMarks();
        boolean matched;
        return false;
    }
}
