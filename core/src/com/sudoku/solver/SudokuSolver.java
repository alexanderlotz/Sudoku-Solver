package com.sudoku.solver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sudoku.solver.board.Grid;

public class SudokuSolver extends ApplicationAdapter {
	private static final int CELL_SIZE = 40;
	SpriteBatch batch;
	BitmapFont font;
	Texture img;
	OrthographicCamera camera;
	SudokuRenderer sudokuRenderer;
	Grid grid;
	int[][] sudokuColors;
	int[][] sudokuValues;
	InputHandler inputHandler;
	float aspectRatio;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		font = new BitmapFont();
		grid = new Grid();
		aspectRatio = (float) Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
		camera = new OrthographicCamera( aspectRatio * ((9 * CELL_SIZE) + 9 + 4), ((9 * CELL_SIZE) + 9 + 4));
		//camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		camera.position.set(((9 * CELL_SIZE) + 9 + 4)/2, ((9 * CELL_SIZE) + 9 + 4)/2, 0);
		camera.update();

		sudokuRenderer = new SudokuRenderer(camera);
		sudokuColors = new int[9][9];
		for (int i = 0; i < sudokuColors.length; i++) {
			for (int j = 0; j < sudokuColors[i].length; j++) {
				sudokuColors[i][j] = 1;
			}
		}
		inputHandler = new InputHandler(grid, camera);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);


		//Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//INPUT HANDLING
		inputHandler.process();
		camera.update();

		sudokuRenderer.begin(SudokuRenderer.ShapeType.Filled);
		sudokuRenderer.setColor(0, 1, 0, 1);
		//shapeRenderer.circle(200, 100, 75);
		//shapeRenderer.rect(10, 10, 100, 80);
		sudokuRenderer.drawGrid(grid, CELL_SIZE); //MEMORY LEAK
		sudokuRenderer.end();

		/*batch.begin();
		batch.draw(img, 0, 0);
		//font.draw(batch, "Happy Coding!", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		//font.draw(batch, "Happier Coding!", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		batch.end();*/
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		img.dispose();
	}
}
