package com.example.minesweeper;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.constraintlayout.solver.widgets.WidgetContainer;

import com.example.minesweeper.util.GridMaker;
import com.example.minesweeper.util.PrintGridLogCat;
import com.example.minesweeper.view.grid.Cell;

public class GameEngine {
    private static GameEngine instance;

    public static final int WIDTH = 10;
    public static final int HEIGHT = 12;
    public static final int NUM_MINES = 1;
    private Context context;
    private Cell[][] game = new Cell[WIDTH][HEIGHT];
    public String gameState;
    public static final String PLAYING = "P";
    public static final String LOST = "L";
    public static final String WON = "W";

    private GameEngine() {
        //cannot call constructor for this class :(
    }

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public void createGrid(Context setContext) {
        Log.e("GameEngine", "createGrid");
        context = setContext;
        int[][] gridAsArray = GridMaker.getGrid(HEIGHT, WIDTH, NUM_MINES);
        PrintGridLogCat.print(gridAsArray);
        setGame(context, gridAsArray);
        gameState = PLAYING;
    }

    private void setGame(Context context, int[][] grid) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (game[i][j] == null) {
                    game[i][j] = new Cell(context, i, j);
                }
                game[i][j].setPosition(i, j);
                game[i][j].setValue(grid[i][j]);
                game[i][j].invalidate();
            }
        }
    }

    public View getCellAt(int position) {
        int x = position % WIDTH;
        int y = (position) / WIDTH;
        return game[x][y];
        /**
        int count = 0;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (count == position) {
                    return game[i][j];
                }
                count++;
            }
        }
        return game[0][0];
         **/
    }

    public void click(int x, int y) {
        if (!gameState.equals(PLAYING)) {
            reset();
        } else {
            if (game[x][y].getValue() == -1) {
                //game[x][y].explode();
                gameLost();
                return;
            }
            checkEmpty(x, y);
            game[x][y].search();
            checkAround(x, y);
            checkForWin();
        }
    }

    private void checkAround(int x, int y) {
        checkEmpty(x + 1, y);
        checkEmpty(x, y - 1);
        checkEmpty(x - 1, y);
        checkEmpty(x, y + 1);
    }

    private void checkEmpty(int x, int y) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) {
            return;
        } else if (!game[x][y].isSearched() && game[x][y].getValue() == 0) {
            game[x][y].search();
            ripple(x, y);
        }
    }

    private void ripple(int x, int y) {
        rippleClick(x + 1, y);
        rippleClick(x + 1, y - 1);
        rippleClick(x, y - 1);
        rippleClick(x - 1, y - 1);
        rippleClick(x - 1, y);
        rippleClick(x - 1, y + 1);
        rippleClick(x, y + 1);
        rippleClick(x + 1, y + 1);
    }

    private void rippleClick(int x, int y) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) {
            return;
        } else if (!game[x][y].isSearched() && game[x][y].getValue() == 0) {
            game[x][y].search();
            ripple(x, y);
        } else if (!game[x][y].isSearched()) {
            game[x][y].search();
        }
    }

    public void gameLost() {
        Toast.makeText(context, "Game Lost", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (!(game[i][j].isSearched()) && game[i][j].getValue() == -1) {
                    game[i][j].search();
                }
            }
        }
        gameState = LOST;
        //reset();
    }

    public void checkForWin() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (!(game[i][j].isSearched()) && game[i][j].getValue() != -1) {
                    return;
                }
            }
        }
        Toast.makeText(context, "Game Won", Toast.LENGTH_SHORT).show();
        //reset();
        gameState = WON;
    }

    public void reset() {
        int[][] gridAsArray = GridMaker.getGrid(HEIGHT, WIDTH, NUM_MINES);
        PrintGridLogCat.print(gridAsArray);
        setGame(context, gridAsArray);
        gameState = PLAYING;
    }
}
