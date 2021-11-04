package com.sudoku.solver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.sudoku.solver.board.Cell;
import com.sudoku.solver.board.Grid;

import java.util.ArrayList;

public class InputHandler {
    Grid grid;
    boolean mouseHeld;
    ArrayList<Cell> setCells;

    public InputHandler(Grid grid) {
        this.grid = grid;
        mouseHeld = false;
        setCells = new ArrayList<>();
    }

    public void process() {
        checkMouseInput();
        checkValueInput();
        grid.checkValid();
    }

    public void checkMouseInput() {
        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX(); //condense into input handler
            x = (x - x % 41) / 41;
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();
            y = (y - y % 41) / 41;

            if (!(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)
                    || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT))
                    && !mouseHeld) {
                for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                    for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                        grid.getPuzzleGrid()[i][j].setFocused(false);
                    }
                }
            }

            if(!setCells.contains(grid.getPuzzleGrid()[x][y])) {
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

    public void checkValueInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)
                || Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)
                || Gdx.input.isKeyJustPressed(Input.Keys.FORWARD_DEL)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(0);
                    }
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_1)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(1);
                    }
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_2)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(2);
                    }
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_3)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(3);
                    }
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_4)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(4);
                    }
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_5)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(5);
                    }
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_6)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(6);
                    }
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_7)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(7);
                    }
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_8)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(8);
                    }
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_9)) {
            for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
                for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
                    if (grid.getPuzzleGrid()[i][j].isFocused()) {
                        grid.getPuzzleGrid()[i][j].setValue(9);
                    }
                }
            }
        }
    }
}
