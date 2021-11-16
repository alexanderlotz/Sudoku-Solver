package com.sudoku.solver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sudoku.solver.board.Grid;
import com.sudoku.solver.gui.Button;
import com.sudoku.solver.gui.Clickable;
import com.sudoku.solver.gui.GUI;
import com.sudoku.solver.gui.SectionBreak;
import com.sudoku.solver.strategies.StrategyTester;

import static com.sudoku.solver.SudokuProperties.*;
import static com.sudoku.solver.SudokuProperties.BUTTON_HEIGHT;

public class SudokuSolver extends ApplicationAdapter {
    /**
     *
     */
    private OrthographicCamera camera;
    /**
     *
     */
    private SudokuRenderer sudokuRenderer;
    /**
     *
     */
    private Grid puzzle;
    /**
     *
     */
    private InputHandler inputHandler;
    /**
     *
     */
    private float aspectRatio;
    /**
     *
     */
    private GUI gui;

    @Override
    public void create() {
        puzzle = new Grid();
        aspectRatio = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        camera = new OrthographicCamera((aspectRatio * puzzle.getWidth()) + CAMERA_BORDER,
                            puzzle.getHeight() + CAMERA_BORDER);
        camera.position.set(Gdx.graphics.getWidth() - (puzzle.getWidth() + CAMERA_BORDER),
                            puzzle.getHeight() / 2, 0);
        camera.update();

        sudokuRenderer = new SudokuRenderer(camera, puzzle);
        initGUI();

        inputHandler = new InputHandler(puzzle, camera, gui);
//        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
//        style.font = new BitmapFont();
//
//        button = new TextButton("Test", style);
    }

    @Override
    public void render() {
        ScreenUtils.clear(BOARD_BG_COLOR);

        //INPUT HANDLING
        inputHandler.process();
        camera.update();

        sudokuRenderer.begin(SudokuRenderer.ShapeType.Filled);
        sudokuRenderer.setColor(0, 1, 0, 1);
        sudokuRenderer.drawGrid(gui);
        sudokuRenderer.end();
    }

    @Override
    public void dispose() {
    }


    public void initGUI() {
        gui = new GUI(puzzle.getWidth() + CAMERA_BORDER / 2, -1 * CAMERA_BORDER / 2,
                camera.viewportWidth - puzzle.getWidth() - CAMERA_BORDER,
                puzzle.getHeight() + CAMERA_BORDER);

        Button quitButton = new Button(gui.getPosition().x, gui.getPosition().y,
                gui.getWidth(), BUTTON_HEIGHT, "Quit");
        gui.addButton(quitButton);
//        gui.addButton(new Button(gui.getPosition().x + GUI_BORDER, gui.getPosition().y + GUI_BORDER,
//                gui.getWidth() - 2 * GUI_BORDER, BUTTON_HEIGHT, ""));
        quitButton.setClickEvent(() -> System.exit(0));

        Button loadButton = new Button(gui.getPosition().x,
                quitButton.getPosition().y + BUTTON_HEIGHT,
                gui.getWidth(), BUTTON_HEIGHT, "Load");
        gui.addButton(loadButton);
//        gui.addButton(new Button(gui.getPosition().x + GUI_BORDER, gui.getPosition().y + GUI_BORDER,
//                gui.getWidth() - 2 * GUI_BORDER, BUTTON_HEIGHT, ""));
        loadButton.setClickEvent(() -> puzzle.readFromString());

        Button saveButton = new Button(gui.getPosition().x,
                loadButton.getPosition().y + BUTTON_HEIGHT,
                gui.getWidth(), BUTTON_HEIGHT, "Save");
        saveButton.setClickEvent(() -> puzzle.writeToString());
        gui.addButton(saveButton);

        SectionBreak stateManagerBreak = new SectionBreak(gui.getPosition().x, saveButton.getPosition().y + BUTTON_HEIGHT, gui.getWidth());
        gui.addSectionBreak(stateManagerBreak);

        Button markButton = new Button(gui.getPosition().x,
                stateManagerBreak.getPosition().y + GUI_BORDER,
                gui.getWidth(), BUTTON_HEIGHT, "Mark");
        gui.addButton(markButton);
//        gui.addButton(new Button(gui.getPosition().x + GUI_BORDER, gui.getPosition().y + GUI_BORDER,
//                gui.getWidth() - 2 * GUI_BORDER, BUTTON_HEIGHT, ""));
        markButton.setClickEvent(() -> StrategyTester.setCellMarks(puzzle));

        Button solveButton = new Button(gui.getPosition().x,
                markButton.getPosition().y + BUTTON_HEIGHT,
                gui.getWidth(), BUTTON_HEIGHT, "Solve");
        solveButton.setClickEvent(() -> StrategyTester.solve(puzzle));
        gui.addButton(solveButton);

        Button resetButton = new Button(gui.getPosition().x,
                solveButton.getPosition().y + BUTTON_HEIGHT,
                gui.getWidth(), BUTTON_HEIGHT, "Reset");
        resetButton.setClickEvent(() -> puzzle.reset());
        gui.addButton(resetButton);

        SectionBreak boardControlBreak = new SectionBreak(gui.getPosition().x, resetButton.getPosition().y + BUTTON_HEIGHT, gui.getWidth());
        gui.addSectionBreak(boardControlBreak);

        Button pencilButton = new Button(gui.getPosition().x,
                boardControlBreak.getPosition().y + GUI_BORDER,
                gui.getWidth(), BUTTON_HEIGHT, "Pencil");
        pencilButton.setClickEvent(() -> StrategyTester.solve(puzzle));
        gui.addButton(pencilButton);

        Button valueButton = new Button(gui.getPosition().x,
                pencilButton.getPosition().y + BUTTON_HEIGHT,
                gui.getWidth(), BUTTON_HEIGHT, "Values");
        valueButton.setClickEvent(() -> puzzle.reset());
        gui.addButton(valueButton);

    }
}
