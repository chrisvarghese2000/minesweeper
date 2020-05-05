package com.example.minesweeper.util;

import android.util.Log;

public class GridMaker {

    public static int[][] getGrid(final int height, final int width, int numMines) {
        int[][] grid = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = 0;
            }
        }
        grid = setMines(grid, height, width, numMines);
        grid = gridMath(grid, height, width);
        return grid;
    }

    private static int[][] setMines(int[][] grid, final int height, final int width, int numMines) {
        int minesLeft = numMines;
        while (minesLeft > 0) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            Log.e("setMines", x + " " + y);
            if (grid[x][y] != -1) {
                grid[x][y] = -1;
                minesLeft--;
            }
        }
        return grid;
    }

    private static int[][] gridMath(int[][] grid, final int height, final int width) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (grid[x][y] == -1) {
                    continue;
                }
                grid[x][y] = calcGridNumber(grid, height, width, x, y);
            }
        }
        return grid;
    }

    //calculates number of bombs in surrounding 8 tiles
    private static int calcGridNumber(int[][] grid, final int height, final int width, int x, int y) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (isMine(grid, height, width, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    //called by calcgridnumber, main purpose is to handles coordinates on edge of grid
    public static boolean isMine(int[][] grid, final int height, final int width, int x, int y) {
        if (x >= 0 && y >= 0 && x < width && y < height) {
            if (grid[x][y] == -1) {
                return true;
            }
        }
        return false;
    }
}

