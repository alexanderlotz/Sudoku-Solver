package com.sudoku.solver.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.sudoku.solver.SudokuProperties.*;
import static com.sudoku.solver.SudokuProperties.BUTTON_BORDER_WEIGHT;

public class SectionBreak {/**
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
