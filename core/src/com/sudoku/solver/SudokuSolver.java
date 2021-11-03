package com.sudoku.solver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		//batch.draw(img, 0, 0);
		//font.draw(batch, "Happy Coding!", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		//font.draw(batch, "Happier Coding!", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		batch.end();


		//Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.isTouched()) {
			for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
				for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
					grid.getPuzzleGrid()[i][j].setColoring(Color.WHITE);
				}
			}
			int x = Gdx.input.getX(); //condense into input handler
			x = (x - x % 41) / 41;
			int y = Gdx.graphics.getHeight() - Gdx.input.getY();
			y = (y - y % 41) / 41;
			grid.getPuzzleGrid()[x][y].setColoring(Color.RED);
		}
		sudokuRenderer.begin(SudokuRenderer.ShapeType.Filled);
		sudokuRenderer.setColor(0, 1, 0, 1);
		//shapeRenderer.circle(200, 100, 75);
		//shapeRenderer.rect(10, 10, 100, 80);
		sudokuRenderer.drawGrid(grid, 40);
		sudokuRenderer.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		img.dispose();
	}
}
