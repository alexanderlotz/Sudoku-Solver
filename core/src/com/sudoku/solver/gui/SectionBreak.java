package com.sudoku.solver.gui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.sudoku.solver.SudokuProperties.BOARD_BG_COLOR;
import static com.sudoku.solver.SudokuProperties.GUI_BORDER;

public class SectionBreak {
    /**
     *
     */
    private Vector2 position;
    /**
     *
     */
    private float width;
    /**
     *
     */
    private float height;

    public SectionBreak(float x, float y, float width) {
        position = new Vector2(x, y + GUI_BORDER);
        this.width = width;
        height = GUI_BORDER;
    }

    public void draw(ShapeRenderer screen) {
        screen.setColor(BOARD_BG_COLOR);
        screen.rect(position.x, position.y, width, height);
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
