package com.sudoku.solver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.sudoku.solver.board.Cell;
import com.sudoku.solver.board.Grid;
import java.util.ArrayList;

import com.sudoku.solver.SudokuProperties.SudokuValues;

public class InputHandler {
    /**
     *
     */
    private Grid grid;
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
     * @param grid
     * @param camera
     */
    public InputHandler(Grid grid, OrthographicCamera camera) {
        this.grid = grid;
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
        grid.checkValid();
    }

    /**
     *
     */
    public void checkMouseInput() { //FIX TRANSLATION
        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX(); //condense into input handler
            int y = Gdx.input.getY();
            Vector3 worldCoord = camera.unproject(new Vector3(x, y, 0f));

            //x = (int) ((worldCoord.x - worldCoord.x % 41) / 41);
            x = grid.getGridCoord((int) worldCoord.x);
            //y = (int) ((worldCoord.y - worldCoord.y % 41) / 41);
            y = grid.getGridCoord((int) worldCoord.y);

            if (!(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)
                    || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT))
                    && !mouseHeld) {
                for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                    for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                        grid.getPuzzleGrid()[i][j].setFocused(false);
                    }
                }
            }


            if (!setCells.contains(grid.getPuzzleGrid()[x][y])) {
                setCells.add(grid.getPuzzleGrid()[x][y]);
                if ((Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)
                        || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT))) {
                    grid.getPuzzleGrid()[x][y].setFocused(!grid.getPuzzleGrid()[x][y].isFocused());
                } else {
                    grid.getPuzzleGrid()[x][y].setFocused(true);
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
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(SudokuValues.NONE.ordinal());
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_1)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(SudokuValues.ONE.ordinal());
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_2)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(SudokuValues.TWO.ordinal());
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_3)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(SudokuValues.THREE.ordinal());
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_4)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(SudokuValues.FOUR.ordinal());
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_5)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(SudokuValues.FIVE.ordinal());
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_6)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(SudokuValues.SIX.ordinal());
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_7)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(SudokuValues.SEVEN.ordinal());
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_8)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(SudokuValues.EIGHT.ordinal());
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_9)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(SudokuValues.NINE.ordinal());
                    }
                }
            }
        }
    }
}
