package com.sudoku.solver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sudoku.solver.board.Grid;
import com.sudoku.solver.gui.Button;
import com.sudoku.solver.gui.GUI;
import com.sudoku.solver.gui.SectionBreak;
import com.sudoku.solver.strategies.StrategyTester;

import static com.sudoku.solver.SudokuProperties.BOARD_BG_COLOR;
import static com.sudoku.solver.SudokuProperties.BUTTON_HEIGHT;
import static com.sudoku.solver.SudokuProperties.CAMERA_BORDER;
import static com.sudoku.solver.SudokuProperties.GUI_BORDER;

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
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.valueOf(BOARD_BG_COLOR));

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

        Button loadButton = new Button(gui.getPosition().x,
                quitButton.getPosition().y + BUTTON_HEIGHT,
                gui.getWidth(), BUTTON_HEIGHT, "Load");

        Button saveButton = new Button(gui.getPosition().x,
                loadButton.getPosition().y + BUTTON_HEIGHT,
                gui.getWidth(), BUTTON_HEIGHT, "Save");

        SectionBreak stateManagerBreak = new SectionBreak(gui.getPosition().x,
                                                          saveButton.getPosition().y + BUTTON_HEIGHT,
                                                          gui.getWidth());

        Button markButton = new Button(gui.getPosition().x,
                stateManagerBreak.getPosition().y + GUI_BORDER,
                gui.getWidth(), BUTTON_HEIGHT, "Mark");

        Button solveButton = new Button(gui.getPosition().x,
                markButton.getPosition().y + BUTTON_HEIGHT,
                gui.getWidth(), BUTTON_HEIGHT, "Solve");

        Button resetButton = new Button(gui.getPosition().x,
                solveButton.getPosition().y + BUTTON_HEIGHT,
                gui.getWidth(), BUTTON_HEIGHT, "Reset");

        SectionBreak boardControlBreak = new SectionBreak(gui.getPosition().x,
                                                          resetButton.getPosition().y + BUTTON_HEIGHT,
                                                          gui.getWidth());

        Button pencilButton = new Button(gui.getPosition().x,
                boardControlBreak.getPosition().y + GUI_BORDER,
                gui.getWidth(), BUTTON_HEIGHT, "Pencil");

        Button valueButton = new Button(gui.getPosition().x,
                pencilButton.getPosition().y + BUTTON_HEIGHT,
                gui.getWidth(), BUTTON_HEIGHT, "Values");
        valueButton.setFocused(true);

        quitButton.setClickEvent(() -> Gdx.app.exit());
        saveButton.setClickEvent(() -> puzzle.writeToString());
        loadButton.setClickEvent(() -> puzzle.readFromString());
        markButton.setClickEvent(() -> StrategyTester.setCellMarks(puzzle));
        solveButton.setClickEvent(() -> StrategyTester.solve(puzzle));
        resetButton.setClickEvent(() -> puzzle.reset());
        valueButton.setClickEvent(() -> {
            valueButton.setFocused(true);
            pencilButton.setFocused(false);
            puzzle.setMarkMode(false);
        });
        pencilButton.setClickEvent(() -> {
            pencilButton.setFocused(true);
            valueButton.setFocused(false);
            puzzle.setMarkMode(true);
        });

        gui.addButton(quitButton);
        gui.addButton(loadButton);
        gui.addButton(saveButton);
        gui.addSectionBreak(stateManagerBreak);
        gui.addButton(markButton);
        gui.addButton(solveButton);
        gui.addButton(resetButton);
        gui.addButton(valueButton);
        gui.addSectionBreak(boardControlBreak);
        gui.addButton(pencilButton);

    }
}
