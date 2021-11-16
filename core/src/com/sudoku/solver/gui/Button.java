package com.sudoku.solver.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sudoku.solver.SudokuProperties;
import com.sudoku.solver.board.Grid;

import javax.swing.*;

import static com.sudoku.solver.SudokuProperties.*;
import static com.sudoku.solver.SudokuProperties.BUTTON_BORDER_WEIGHT;

public class Button {
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
    /**
     *
     */
    private String text;
    /**
     *
     */
    private Clickable clickEvent;

    public Button(float x, float y, float width, float height, String text) {
        position = new Vector2(x + GUI_BORDER, y + GUI_BORDER);
        this.width = width - 2 * GUI_BORDER;
        this.height = height;
        this.text = text;
    }

    public void draw(ShapeRenderer screen) {
        screen.setColor(BUTTON_BORDER_COLOR);
        screen.rect(position.x, position.y, width, height);
        screen.setColor(BUTTON_COLOR);
        screen.rect(position.x + BUTTON_BORDER_WEIGHT,
                position.y + BUTTON_BORDER_WEIGHT,
                width - 2 * BUTTON_BORDER_WEIGHT,
                height - 2 * BUTTON_BORDER_WEIGHT);
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

    public String getText() {
        return text;
    }

    public boolean contains(Vector2 pos) {
        return pos.x >= position.x
               && pos.x <= position.x + width
               && pos.y >= position.y
               && pos.y <= position.y + height;
    }

    public void displayText(ShapeRenderer screen, SpriteBatch batch, BitmapFont font) {
        GlyphLayout layout = new GlyphLayout();
        float fontX;
        float fontY;

        layout.setText(font, text);
        fontX = position.x + (width - layout.width) / 2;
        fontY = position.y + (height + layout.height) / 2;
        font.draw(batch, layout, fontX, fontY);
    }

    public void setClickEvent(Clickable clickable) {
        clickEvent = clickable;
    }

    public void click() {
        clickEvent.onClick();

    }
}
