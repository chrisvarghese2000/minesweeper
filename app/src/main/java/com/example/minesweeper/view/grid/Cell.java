package com.example.minesweeper.view.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.minesweeper.GameEngine;
import com.example.minesweeper.R;

public class Cell extends View implements View.OnClickListener {
    private int value;
    private boolean searched;
    public static final int MINE = -1;
    private int x;
    private int y;
    private boolean exploded;

    public Cell(Context context, int setX, int setY) {
        super (context);
        x = setX;
        y = setY;
        exploded = false;
        setOnClickListener(this);
    }

    //ui functions

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View view) {
        GameEngine.getInstance().click(x, y);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawButton(canvas);
    }

    private void drawButton(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.unsearched_button);;
        if (!searched) {
            drawable = ContextCompat.getDrawable(getContext(), R.drawable.unsearched_button);
        } else {
            int toDraw = getValue();
            if (value == -1) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_normal);
            } else if (value == 0) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.sqvalue0);
            } else if (value == 1) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button_num_1);
            } else if (value == 2) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button_num_2);
            } else if (value == 3) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button_num_3);
            } else if (value == 4) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button_num_4);
            } else if (value == 5) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button_num_5);
            } else if (value == 6) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button_num_6);
            } else if (value == 7) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button_num_7);
            } else if (value == 8) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.button_num_8);
            }
        }
        if (exploded) {
            drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_exploded);
        }
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    //math related functions
    public int getValue() {
        return value;
    }

    public void setValue(int setValue) {
        searched = false;
        value = setValue;
    }

    public boolean isSearched() {
        return searched;
    }

    public void search() {
        searched = true;
        invalidate();
    }

    public void explode() {
        exploded = true;
        invalidate();
    }

    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return y;
    }

    public void setPosition(int setX, int setY) {
        x = setX;
        y = setY;
        invalidate();
    }
}
