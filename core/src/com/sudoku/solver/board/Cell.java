package com.sudoku.solver.board;

import com.badlogic.gdx.graphics.Color;

public class Cell {
    int[] centerMarks;
    int[] cornerMarks;
    Color coloring;
    int value;
    boolean valid;
    boolean focused;

    public Cell() {
        centerMarks = new int[9];
        cornerMarks = new int[9];
        coloring = Color.WHITE;
        value = 0;
        valid = true;
        focused = false;
    }

    public int[] getCenterMarks() {
        return centerMarks;
    }

    public void setCenterMarks(int mark) {
        centerMarks[mark] = (centerMarks[mark] == 1) ? 0 : 1;
    }

    public int[] getCornerMarks() {
        return cornerMarks;
    }

    public void setCornerMarks(int mark) {
        cornerMarks[mark] = (cornerMarks[mark] == 1) ? 0 : 1;
    }

    public Color getColoring() {
        return coloring;
    }

    public void setColoring(Color coloring) { //reassess
        this.coloring = coloring;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) { //reassess
        this.value = (this.value == value) ? 0 : value;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }
}
