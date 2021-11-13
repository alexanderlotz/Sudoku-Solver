package com.sudoku.solver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.sudoku.solver.board.Cell;
import com.sudoku.solver.board.Grid;
import java.util.ArrayList;

import com.sudoku.solver.SudokuProperties.SudokuValues;
import com.sudoku.solver.strategies.StrategyTester;

public class InputHandler {
    /**
     *
     */
    private Grid puzzle;
    /**
     *
     */
    private boolean mouseHeld;
    /**
     *
     */
    private ArrayList<Cell> setCells;
    /**
     *
     */
    private OrthographicCamera camera;

    /**
     *
     * @param puzzle
     * @param camera
     */
    public InputHandler(Grid puzzle, OrthographicCamera camera) {
        this.puzzle = puzzle;
        mouseHeld = false;
        setCells = new ArrayList<>();
        this.camera = camera;
    }

    /**
     *
     */
    public void process() {
        checkMouseInput();
        checkValueInput();
        checkKeyboardInput();
        puzzle.checkValid();
    }

    /**
     *
     */
    public void checkMouseInput() { //FIX MAPPING OF X AND Y in cells
        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX(); //condense into input handler
            int y = Gdx.input.getY();
            Vector3 worldCoord = camera.unproject(new Vector3(x, y, 0f));

            //x = (int) ((worldCoord.x - worldCoord.x % 41) / 41);
            x = Grid.getGridCoord((int) worldCoord.x);
            //y = (int) ((worldCoord.y - worldCoord.y % 41) / 41);
            y = Grid.getGridCoord((int) worldCoord.y);

            if (!(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)
                    || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT))
                    && !mouseHeld) {
                for (int i = 0; i < puzzle.getBoard().length; i++) {
                    for (int j = 0; j < puzzle.getBoard()[i].length; j++) {
                        puzzle.getBoard()[i][j].setFocused(false);
                    }
                }
            }


            if (!setCells.contains(puzzle.getBoard()[x][y])) {
                setCells.add(puzzle.getBoard()[x][y]);
                if ((Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)
                        || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT))) {
                    puzzle.getBoard()[x][y].setFocused(!puzzle.getBoard()[x][y].isFocused());
                } else {
                    puzzle.getBoard()[x][y].setFocused(true);
                }
            }
            mouseHeld = true;
        } else {
            setCells.clear();
            mouseHeld = false;
        }
    }

    /**
     *
     */
    public void checkValueInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)
                || Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)
                || Gdx.input.isKeyJustPressed(Input.Keys.FORWARD_DEL)) {
            for (int i = 0; i < puzzle.getBoard().length; i++) {
                for (int j = 0; j < puzzle.getBoard()[i].length; j++) {
                    if (puzzle.getBoard()[i][j].isFocused()) {
                        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                            puzzle.getBoard()[i][j].clearCornerMarks();
                        } else {
                            puzzle.getBoard()[i][j].setValue(SudokuValues.NONE.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_1)) {
            for (int i = 0; i < puzzle.getBoard().length; i++) {
                for (int j = 0; j < puzzle.getBoard()[i].length; j++) {
                    if (puzzle.getBoard()[i][j].isFocused()) {
                        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                            puzzle.getBoard()[i][j].toggleCornerMark(SudokuValues.ONE.ordinal());
                        } else {
                            puzzle.getBoard()[i][j].setValue(SudokuValues.ONE.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_2)) {
            for (int i = 0; i < puzzle.getBoard().length; i++) {
                for (int j = 0; j < puzzle.getBoard()[i].length; j++) {
                    if (puzzle.getBoard()[i][j].isFocused()) {
                        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                            puzzle.getBoard()[i][j].toggleCornerMark(SudokuValues.TWO.ordinal());
                        } else {
                            puzzle.getBoard()[i][j].setValue(SudokuValues.TWO.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_3)) {
            for (int i = 0; i < puzzle.getBoard().length; i++) {
                for (int j = 0; j < puzzle.getBoard()[i].length; j++) {
                    if (puzzle.getBoard()[i][j].isFocused()) {
                        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                            puzzle.getBoard()[i][j].toggleCornerMark(SudokuValues.THREE.ordinal());
                        } else {
                            puzzle.getBoard()[i][j].setValue(SudokuValues.THREE.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_4)) {
            for (int i = 0; i < puzzle.getBoard().length; i++) {
                for (int j = 0; j < puzzle.getBoard()[i].length; j++) {
                    if (puzzle.getBoard()[i][j].isFocused()) {
                        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                            puzzle.getBoard()[i][j].toggleCornerMark(SudokuValues.FOUR.ordinal());
                        } else {
                            puzzle.getBoard()[i][j].setValue(SudokuValues.FOUR.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_5)) {
            for (int i = 0; i < puzzle.getBoard().length; i++) {
                for (int j = 0; j < puzzle.getBoard()[i].length; j++) {
                    if (puzzle.getBoard()[i][j].isFocused()) {
                        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                            puzzle.getBoard()[i][j].toggleCornerMark(SudokuValues.FIVE.ordinal());
                        } else {
                            puzzle.getBoard()[i][j].setValue(SudokuValues.FIVE.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_6)) {
            for (int i = 0; i < puzzle.getBoard().length; i++) {
                for (int j = 0; j < puzzle.getBoard()[i].length; j++) {
                    if (puzzle.getBoard()[i][j].isFocused()) {
                        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                            puzzle.getBoard()[i][j].toggleCornerMark(SudokuValues.SIX.ordinal());
                        } else {
                            puzzle.getBoard()[i][j].setValue(SudokuValues.SIX.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_7)) {
            for (int i = 0; i < puzzle.getBoard().length; i++) {
                for (int j = 0; j < puzzle.getBoard()[i].length; j++) {
                    if (puzzle.getBoard()[i][j].isFocused()) {
                        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                            puzzle.getBoard()[i][j].toggleCornerMark(SudokuValues.SEVEN.ordinal());
                        } else {
                            puzzle.getBoard()[i][j].setValue(SudokuValues.SEVEN.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_8)) {
            for (int i = 0; i < puzzle.getBoard().length; i++) {
                for (int j = 0; j < puzzle.getBoard()[i].length; j++) {
                    if (puzzle.getBoard()[i][j].isFocused()) {
                        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                            puzzle.getBoard()[i][j].toggleCornerMark(SudokuValues.EIGHT.ordinal());
                        } else {
                            puzzle.getBoard()[i][j].setValue(SudokuValues.EIGHT.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_9)) {
            for (int i = 0; i < puzzle.getBoard().length; i++) {
                for (int j = 0; j < puzzle.getBoard()[i].length; j++) {
                    if (puzzle.getBoard()[i][j].isFocused()) {
                        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                            puzzle.getBoard()[i][j].toggleCornerMark(SudokuValues.NINE.ordinal());
                        } else {
                            puzzle.getBoard()[i][j].setValue(SudokuValues.NINE.ordinal());
                        }
                    }
                }
            }
        }
    }


    /**
     *
     */
    public void checkKeyboardInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            StrategyTester.solve(puzzle);
        }
    }
}
