package com.example.minesweeper.util;

import android.util.Log;

public class PrintGridLogCat {
    public static void print(int[][] grid) {
        for (int[] i : grid) {
            String toPrint = "\n[";
            for (int j : i) {
                toPrint += j + "] [";
            }
            Log.e("p", toPrint);
        }
    }
}
