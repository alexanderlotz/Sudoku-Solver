package com.sudoku.solver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sudoku.solver.board.Grid;

import static com.sudoku.solver.SudokuProperties.CAMERA_BORDER;

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
    private Grid grid;
    /**
     *
     */
    private InputHandler inputHandler;
    /**
     *
     */
    private float aspectRatio;

    @Override
    public void create() {
        grid = new Grid();
        aspectRatio = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        camera = new OrthographicCamera((aspectRatio * grid.getWidth()) + CAMERA_BORDER,
                            grid.getHeight() + CAMERA_BORDER);
        camera.position.set(grid.getWidth() / 2, grid.getHeight() / 2, 0);
        camera.update();

        sudokuRenderer = new SudokuRenderer(camera);
        inputHandler = new InputHandler(grid, camera);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        //INPUT HANDLING
        inputHandler.process();
        camera.update();

        sudokuRenderer.begin(SudokuRenderer.ShapeType.Filled);
        sudokuRenderer.setColor(0, 1, 0, 1);
        sudokuRenderer.drawGrid(grid, SudokuProperties.CELL_SIZE); //MEMORY LEAK
        sudokuRenderer.end();
    }

    @Override
    public void dispose() {
    }
}
