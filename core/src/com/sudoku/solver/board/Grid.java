package com.sudoku.solver.board;


import com.badlogic.gdx.graphics.Color;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.TreeSet;

public class Grid {
    Cell[][] puzzleGrid;

    public Grid() {
        puzzleGrid = new Cell[9][9];
        for (int i = 0; i < puzzleGrid.length; i++) {
            for (int j = 0; j < puzzleGrid[i].length; j++) {
                puzzleGrid[i][j] = new Cell();
            }
        }
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
                if(puzzleGrid[row][col].getValue() != 0 && !validNums.add(puzzleGrid[row][col].getValue())) {
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
                if(puzzleGrid[row][col].getValue() != 0 && !validNums.add(puzzleGrid[row][col].getValue())) {
                    for (int i = 0; i < puzzleGrid[row].length; i++) {
                        puzzleGrid[row][i].setValid(false);
                    }
                    break;
                }
            }
            validNums.clear();
        }

        //Validate 3x3 blocks
        for (int block = 0; block < puzzleGrid.length; block++) {
            for (int cell = 0; cell < puzzleGrid[block].length; cell++) {
                if(puzzleGrid[3 * (block % 3) + cell % 3][(block - (block % 3)) + cell / 3].getValue() != 0 && !validNums.add(puzzleGrid[3 * (block % 3) + cell % 3][(block - (block % 3)) + cell / 3].getValue())) {
                    for (int i = 0; i < puzzleGrid[block].length; i++) {
                        puzzleGrid[3 * (block % 3) + i % 3][(block - (block % 3)) + i / 3].setValid(false);
                    }
                    break;
                }
            }
            validNums.clear();
        }
    }
}
