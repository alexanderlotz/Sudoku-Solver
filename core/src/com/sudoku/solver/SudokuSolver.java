package com.sudoku.solver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sudoku.solver.board.Grid;

public class SudokuSolver extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Texture img;
	SudokuRenderer sudokuRenderer;
	Grid grid;
	int[][] sudokuColors;
	int[][] sudokuValues;
	InputHandler inputHandler;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		font = new BitmapFont();
		grid = new Grid();
		sudokuRenderer = new SudokuRenderer();
		sudokuColors = new int[9][9];
		for (int i = 0; i < sudokuColors.length; i++) {
			for (int j = 0; j < sudokuColors[i].length; j++) {
				sudokuColors[i][j] = 1;
			}
		}
		inputHandler = new InputHandler(grid);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);


		//Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//INPUT HANDLING
		inputHandler.process();


		sudokuRenderer.begin(SudokuRenderer.ShapeType.Filled);
		sudokuRenderer.setColor(0, 1, 0, 1);
		//shapeRenderer.circle(200, 100, 75);
		//shapeRenderer.rect(10, 10, 100, 80);
		sudokuRenderer.drawGrid(grid, 40);
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
