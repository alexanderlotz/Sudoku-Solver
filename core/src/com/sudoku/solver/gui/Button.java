package com.sudoku.solver.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.sudoku.solver.SudokuProperties.BUTTON_BORDER_COLOR;
import static com.sudoku.solver.SudokuProperties.BUTTON_BORDER_WEIGHT;
import static com.sudoku.solver.SudokuProperties.BUTTON_CLICK_COLOR;
import static com.sudoku.solver.SudokuProperties.BUTTON_COLOR;
import static com.sudoku.solver.SudokuProperties.GUI_BORDER;

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
    private boolean focused;
    /**
     *
     */
    private Clickable clickEvent;

    public Button(float x, float y, float width, float height, String text) {
        focused = false;
        position = new Vector2(x + GUI_BORDER, y + GUI_BORDER);
        this.width = width - 2 * GUI_BORDER;
        this.height = height;
        this.text = text;
    }

    public void draw(ShapeRenderer screen) {
        screen.setColor(focused ? Color.valueOf(BUTTON_CLICK_COLOR)
                                : Color.valueOf(BUTTON_BORDER_COLOR));
        screen.rect(position.x, position.y, width, height);
        screen.setColor(Color.valueOf(BUTTON_COLOR));
        float border = focused ? 2 * BUTTON_BORDER_WEIGHT : BUTTON_BORDER_WEIGHT;
        screen.rect(position.x + border,
                position.y + border,
                width - 2 * border,
                height - 2 * border);
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

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
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
