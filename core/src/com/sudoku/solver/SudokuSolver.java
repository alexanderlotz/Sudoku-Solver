package com.sudoku.solver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class SudokuSolver extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Texture img;
	ShapeRenderer shapeRenderer;
	int[][] sudokuColors;
	int[][] sudokuValues;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();
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
			for (int i = 0; i < sudokuColors.length; i++) {
				for (int j = 0; j < sudokuColors[i].length; j++) {
					sudokuColors[i][j] = 1;
				}
			}
			int x = Gdx.input.getX();
			x = (x - x % 41) / 41;
			int y = Gdx.graphics.getHeight() - Gdx.input.getY();
			y = (y - y % 41) / 41;
			sudokuColors[x][y] = 0;
		}
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(0, 1, 0, 1);
		//shapeRenderer.circle(200, 100, 75);
		//shapeRenderer.rect(10, 10, 100, 80);
		for (int i = 0; i < sudokuColors.length; i++) {
			for (int j = 0; j < sudokuColors[i].length; j++) {
				shapeRenderer.setColor(1, sudokuColors[i][j], sudokuColors[i][j], 1);
				shapeRenderer.rect(i*41, j*41, 40, 40);
			}
		}
		shapeRenderer.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		img.dispose();
	}
}
