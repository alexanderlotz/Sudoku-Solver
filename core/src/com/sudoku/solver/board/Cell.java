package com.sudoku.solver.board;

import com.badlogic.gdx.graphics.Color;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.sudoku.solver.SudokuProperties.DEFAULT_CELL_COLOR;
import static com.sudoku.solver.SudokuProperties.TOTAL_VALUES;

public class Cell {
    /**
     *
     */
    private int[] centerMarks;
    /**
     *
     */
    private Set<Integer> cornerMarks;
    /**
     *
     */
    private Color coloring;
    /**
     *
     */
    private int value;
    /**
     *
     */
    private boolean valid;
    /**
     *
     */
    private boolean focused;

    /**
     *
     */
    public Cell() {
        centerMarks = new int[TOTAL_VALUES];
        cornerMarks = new HashSet<Integer>();
        coloring = DEFAULT_CELL_COLOR;
        value = 0;
        valid = true;
        focused = false;
    }

    /**
     *
     * @return TODO
     */
    public int[] getCenterMarks() {
        return centerMarks;
    }

    /**
     *
     * @param mark
     */
    public void setCenterMarks(int mark) {
        centerMarks[mark] = (centerMarks[mark] == 1) ? 0 : 1;
    }

    /**
     *
     * @return TODO
     */
    public int[] getCornerMarkArray() {
        int[] markingsArray = new int[cornerMarks.size()];
        Iterator markIterator = cornerMarks.iterator();
        for (int i = 0; i < cornerMarks.size(); i++) {
            markingsArray[i] = (int) markIterator.next();
        }
        return markingsArray;
    }

    /**
     *
     * @return TODO
     */
    public Set<Integer> getCornerMarks() {
        return cornerMarks;
    }

    /**
     *
     * @param mark
     */
    public void toggleCornerMark(int mark) {
        if (cornerMarks.contains(mark)) {
            cornerMarks.remove(mark);
        } else {
            cornerMarks.add(mark);
        }
    }

    /**
     *
     * @param mark
     * @return TODO
     */
    public boolean addCornerMark(int mark) {
        return cornerMarks.add(mark);
    }

    /**
     *
     * @param marks
     * @return TODO
     */
    public boolean addCornerMarks(Integer[] marks) {
        return cornerMarks.addAll(Arrays.asList(marks));
    }

    /**
     *
     * @param mark
     * @return TODO
     */
    public boolean removeCornerMark(int mark) {
        return cornerMarks.remove(mark);
    }

    /**
     *
     */
    public void clearCornerMarks() {
        cornerMarks.clear();
    }

    /**
     *
     * @return TODO
     */
    public Color getColoring() {
        return coloring;
    }

    /**
     *
     * @param coloring
     */
    public void setColoring(Color coloring) { //reassess
        this.coloring = coloring;
    }

    /**
     *
     * @return TODO
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(int value) { //reassess
        this.value = (this.value == value) ? 0 : value;
    }

    /**
     *
     * @return TODO
     */
    public boolean isValid() {
        return valid;
    }

    /**
     *
     * @param valid
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     *
     * @return TODO
     */
    public boolean isFocused() {
        return focused;
    }

    /**
     *
     * @param focused
     */
    public void setFocused(boolean focused) {
        this.focused = focused;
    }
}
