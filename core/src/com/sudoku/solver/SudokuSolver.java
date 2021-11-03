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


		//Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//INPUT HANDLING
		if (Gdx.input.isTouched()) {
			for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
				for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
					grid.getPuzzleGrid()[i][j].setColoring(Color.WHITE);
					grid.getPuzzleGrid()[i][j].setFocused(false);
				}
			}
			int x = Gdx.input.getX(); //condense into input handler
			x = (x - x % 41) / 41;
			int y = Gdx.graphics.getHeight() - Gdx.input.getY();
			y = (y - y % 41) / 41;
			grid.getPuzzleGrid()[x][y].setColoring(Color.RED);
			grid.getPuzzleGrid()[x][y].setFocused(true);

		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)
		   || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)
		   || Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)
		   || Gdx.input.isKeyJustPressed(Input.Keys.FORWARD_DEL)) {
			for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
				for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
					if (grid.getPuzzleGrid()[i][j].isFocused()) {
						grid.getPuzzleGrid()[i][j].setValue(0);
					}
				}
			}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)
				|| Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_1)) {
			for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
				for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
					if (grid.getPuzzleGrid()[i][j].isFocused()) {
						grid.getPuzzleGrid()[i][j].setValue(1);
					}
				}
			}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)
				|| Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_2)) {
			for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
				for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
					if (grid.getPuzzleGrid()[i][j].isFocused()) {
						grid.getPuzzleGrid()[i][j].setValue(2);
					}
				}
			}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)
				|| Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_3)) {
			for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
				for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
					if (grid.getPuzzleGrid()[i][j].isFocused()) {
						grid.getPuzzleGrid()[i][j].setValue(3);
					}
				}
			}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)
				|| Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_4)) {
			for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
				for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
					if (grid.getPuzzleGrid()[i][j].isFocused()) {
						grid.getPuzzleGrid()[i][j].setValue(4);
					}
				}
			}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)
				|| Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_5)) {
			for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
				for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
					if (grid.getPuzzleGrid()[i][j].isFocused()) {
						grid.getPuzzleGrid()[i][j].setValue(5);
					}
				}
			}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)
				|| Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_6)) {
			for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
				for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
					if (grid.getPuzzleGrid()[i][j].isFocused()) {
						grid.getPuzzleGrid()[i][j].setValue(6);
					}
				}
			}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)
				|| Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_7)) {
			for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
				for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
					if (grid.getPuzzleGrid()[i][j].isFocused()) {
						grid.getPuzzleGrid()[i][j].setValue(7);
					}
				}
			}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)
				|| Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_8)) {
			for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
				for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
					if (grid.getPuzzleGrid()[i][j].isFocused()) {
						grid.getPuzzleGrid()[i][j].setValue(8);
					}
				}
			}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)
				|| Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_9)) {
			for (int i = 0; i < grid.getPuzzleGrid().length; i++) {
				for (int j = 0; j < grid.getPuzzleGrid()[i].length; j++) {
					if (grid.getPuzzleGrid()[i][j].isFocused()) {
						grid.getPuzzleGrid()[i][j].setValue(9);
					}
				}
			}
		}



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
