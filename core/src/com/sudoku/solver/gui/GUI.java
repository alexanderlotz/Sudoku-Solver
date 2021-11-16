package com.sudoku.solver.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import static com.sudoku.solver.SudokuProperties.GUI_BG_COLOR;

public class GUI {
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
    private ArrayList<Button> buttonList;
    /**
     *
     */
    private ArrayList<SectionBreak> sectionBreakList;

    public GUI(float x, float y, float width, float height) {
        buttonList = new ArrayList<>();
        sectionBreakList = new ArrayList<>();
        position = new Vector2(x, y);
        this.width = width;
        this.height = height;
    }

    public void addButton(Button button) {
        buttonList.add(button);
    }

    public void addSectionBreak(SectionBreak sectionBreak) {
        sectionBreakList.add(sectionBreak);
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

    public ArrayList<Button> getButtonList() {
        return buttonList;
    }

    public void drawLayout(ShapeRenderer screen) {
        screen.setColor(GUI_BG_COLOR);
        screen.rect(position.x, position.y, width, height);
        for (Button button : buttonList) {
            button.draw(screen);
        }
        for (SectionBreak sectionBreak : sectionBreakList) {
            sectionBreak.draw(screen);
        }
    }

    public void drawText(ShapeRenderer screen, SpriteBatch textBatch, BitmapFont font) {
        for (Button button : buttonList) {
            button.displayText(screen, textBatch, font);
        }
    }
}
