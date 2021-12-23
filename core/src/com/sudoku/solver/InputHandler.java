package com.sudoku.solver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sudoku.solver.board.Cell;
import com.sudoku.solver.board.Grid;

import java.awt.Point;
import java.util.ArrayList;

import com.sudoku.solver.SudokuProperties.SudokuValues;
import com.sudoku.solver.gui.Button;
import com.sudoku.solver.gui.GUI;

import static com.sudoku.solver.SudokuProperties.BOARD_COLUMNS;

public class InputHandler {
    /**
     *
     */
    private Grid puzzle;
    /**
     *
     */
    private boolean mouseHeld;
    /**
     *
     */
    private ArrayList<Cell> setCells;
    /**
     *
     */
    private OrthographicCamera camera;
    /**
     *
     */
    private Point primeFocus;
    /**
     *
     */
    private GUI gui;

    /**
     *
     * @param puzzle
     * @param camera
     * @param gui
     */
    public InputHandler(Grid puzzle, OrthographicCamera camera, GUI gui) {
        this.puzzle = puzzle;
        mouseHeld = false;
        setCells = new ArrayList<>();
        this.camera = camera;
        primeFocus = new Point(0, 0);
        this.gui = gui;
    }

    /**
     *
     */
    public void process() {
        checkMouseInput();
        checkValueInput();
        checkKeyboardInput();
        puzzle.checkValid();
    }

    /**
     *
     */
    public void checkMouseInput() { //FIX MAPPING OF X AND Y in cells
        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX(); //condense into input handler
            int y = Gdx.input.getY();
            Vector3 worldCoord = camera.unproject(new Vector3(x, y, 0f));

            x = Grid.getGridCoord((int) worldCoord.x);
            y = Grid.getGridCoord((int) worldCoord.y);
            if (x >= BOARD_COLUMNS) {
                puzzle.clearFocus();
                if (!mouseHeld) {
                    for (Button button : gui.getButtonList()) {
                        if (button.contains(new Vector2(worldCoord.x, worldCoord.y))) {
                            button.click();
                        }
                    }
                }
            } else {
                primeFocus = Grid.enforceBounds(new Point(x, y));

                if (!(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)
                        || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT))
                        && !mouseHeld) {
                    puzzle.clearFocus();
                }


                if (!setCells.contains(puzzle.getPuzzle()[primeFocus.x][primeFocus.y])) {
                    setCells.add(puzzle.getPuzzle()[primeFocus.x][primeFocus.y]);
                    if ((Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)
                            || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT))) {
                        puzzle.getPuzzle()[primeFocus.x][primeFocus.y]
                                .setFocused(!puzzle.getPuzzle()[primeFocus.x][primeFocus.y].isFocused());
                    } else {
                        puzzle.getPuzzle()[primeFocus.x][primeFocus.y].setFocused(true);
                    }
                }
            }
            mouseHeld = true;
        } else {
            setCells.clear();
            mouseHeld = false;
        }
    }

    /**
     *
     */
    public void checkValueInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)
                || Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)
                || Gdx.input.isKeyJustPressed(Input.Keys.FORWARD_DEL)) {
            for (int i = 0; i < puzzle.getPuzzle().length; i++) {
                for (int j = 0; j < puzzle.getPuzzle()[i].length; j++) {
                    if (puzzle.getPuzzle()[i][j].isFocused()) {
                        if (puzzle.isMarkMode()) {
                            puzzle.getPuzzle()[i][j].clearCornerMarks();
                        } else {
                            puzzle.getPuzzle()[i][j].setValue(SudokuValues.NONE.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_1)) {
            for (int i = 0; i < puzzle.getPuzzle().length; i++) {
                for (int j = 0; j < puzzle.getPuzzle()[i].length; j++) {
                    if (puzzle.getPuzzle()[i][j].isFocused()) {
                        if (puzzle.isMarkMode()) {
                            puzzle.getPuzzle()[i][j].toggleCornerMark(SudokuValues.ONE.ordinal());
                        } else {
                            puzzle.getPuzzle()[i][j].setValue(SudokuValues.ONE.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_2)) {
            for (int i = 0; i < puzzle.getPuzzle().length; i++) {
                for (int j = 0; j < puzzle.getPuzzle()[i].length; j++) {
                    if (puzzle.getPuzzle()[i][j].isFocused()) {
                        if (puzzle.isMarkMode()) {
                            puzzle.getPuzzle()[i][j].toggleCornerMark(SudokuValues.TWO.ordinal());
                        } else {
                            puzzle.getPuzzle()[i][j].setValue(SudokuValues.TWO.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_3)) {
            for (int i = 0; i < puzzle.getPuzzle().length; i++) {
                for (int j = 0; j < puzzle.getPuzzle()[i].length; j++) {
                    if (puzzle.getPuzzle()[i][j].isFocused()) {
                        if (puzzle.isMarkMode()) {
                            puzzle.getPuzzle()[i][j].toggleCornerMark(SudokuValues.THREE.ordinal());
                        } else {
                            puzzle.getPuzzle()[i][j].setValue(SudokuValues.THREE.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_4)) {
            for (int i = 0; i < puzzle.getPuzzle().length; i++) {
                for (int j = 0; j < puzzle.getPuzzle()[i].length; j++) {
                    if (puzzle.getPuzzle()[i][j].isFocused()) {
                        if (puzzle.isMarkMode()) {
                            puzzle.getPuzzle()[i][j].toggleCornerMark(SudokuValues.FOUR.ordinal());
                        } else {
                            puzzle.getPuzzle()[i][j].setValue(SudokuValues.FOUR.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_5)) {
            for (int i = 0; i < puzzle.getPuzzle().length; i++) {
                for (int j = 0; j < puzzle.getPuzzle()[i].length; j++) {
                    if (puzzle.getPuzzle()[i][j].isFocused()) {
                        if (puzzle.isMarkMode()) {
                            puzzle.getPuzzle()[i][j].toggleCornerMark(SudokuValues.FIVE.ordinal());
                        } else {
                            puzzle.getPuzzle()[i][j].setValue(SudokuValues.FIVE.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_6)) {
            for (int i = 0; i < puzzle.getPuzzle().length; i++) {
                for (int j = 0; j < puzzle.getPuzzle()[i].length; j++) {
                    if (puzzle.getPuzzle()[i][j].isFocused()) {
                        if (puzzle.isMarkMode()) {
                            puzzle.getPuzzle()[i][j].toggleCornerMark(SudokuValues.SIX.ordinal());
                        } else {
                            puzzle.getPuzzle()[i][j].setValue(SudokuValues.SIX.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_7)) {
            for (int i = 0; i < puzzle.getPuzzle().length; i++) {
                for (int j = 0; j < puzzle.getPuzzle()[i].length; j++) {
                    if (puzzle.getPuzzle()[i][j].isFocused()) {
                        if (puzzle.isMarkMode()) {
                            puzzle.getPuzzle()[i][j].toggleCornerMark(SudokuValues.SEVEN.ordinal());
                        } else {
                            puzzle.getPuzzle()[i][j].setValue(SudokuValues.SEVEN.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_8)) {
            for (int i = 0; i < puzzle.getPuzzle().length; i++) {
                for (int j = 0; j < puzzle.getPuzzle()[i].length; j++) {
                    if (puzzle.getPuzzle()[i][j].isFocused()) {
                        if (puzzle.isMarkMode()) {
                            puzzle.getPuzzle()[i][j].toggleCornerMark(SudokuValues.EIGHT.ordinal());
                        } else {
                            puzzle.getPuzzle()[i][j].setValue(SudokuValues.EIGHT.ordinal());
                        }
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)
                || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_9)) {
            for (int i = 0; i < puzzle.getPuzzle().length; i++) {
                for (int j = 0; j < puzzle.getPuzzle()[i].length; j++) {
                    if (puzzle.getPuzzle()[i][j].isFocused()) {
                        if (puzzle.isMarkMode()) {
                            puzzle.getPuzzle()[i][j].toggleCornerMark(SudokuValues.NINE.ordinal());
                        } else {
                            puzzle.getPuzzle()[i][j].setValue(SudokuValues.NINE.ordinal());
                        }
                    }
                }
            }
        }
    }


    /**
     *
     */
    public void checkKeyboardInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            primeFocus = Grid.enforceBounds(new Point(primeFocus.x, primeFocus.y + 1));
            keyMove();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            primeFocus = Grid.enforceBounds(new Point(primeFocus.x, primeFocus.y - 1));
            keyMove();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            primeFocus = Grid.enforceBounds(new Point(primeFocus.x - 1, primeFocus.y));
            keyMove();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            primeFocus = Grid.enforceBounds(new Point(primeFocus.x + 1, primeFocus.y));
            keyMove();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    public void keyMove() {
        if (!(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)
                || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT))) {
            puzzle.clearFocus();
        }
        puzzle.getPuzzle()[primeFocus.x][primeFocus.y].setFocused(true);
    }
}

//EASY PUZZLE: 000750008802003500300080691200014000580307900073820105700940006046000750000206409 (NHSingles)

//SUDOKU 7
//VERY EASY: 905000407007540000800010060500830070600000004020056003080020001000073800701000309 (NHSingles)
//VERY EASY: 008032950090060004000051060050070810000000000087020090040610000500090080026580300 (NHSingles)
//VERY EASY: 060029058005006000290807300002003040000080000040600800004502083000300400620940070 (NHSingles)
//VERY EASY: 000040080470030000350208070081300005045000820900002740030401057000090038090050000 (NHSingles)

//EASY: 000902400604830002000060013800000600007000500003000007590020000700019805006704000 (NHSingles)
//EASY: 014000200920000007005700000007801350090000080036205700000004500100000093009000120 (NHSingles)
//EASY: 803260000100008000064070030009000203000000000401000900080040790000600001000037802 (NHSingles)
//EASY: 600000215500006000079000000042060300705201408006050790000000180000300007297000003 (NHSingles)
//EASY: 000800043000097000050300008501670000702105906000043107200004080000720000960008000 (NHSingles)

//MODERATE: 012009080803500409000000000200100976000000000158007002000000000409008603080400590 (NHSingles)

//DIFFICULT: 007000091000004300009108000052300809900000003106009450000401600003200000260000900 (Hidden Quads)

//VERY DIFFICULT: 050000009100300080000097006080731000009804300000952040600480000040009002900000070 (Locked Candidates)
