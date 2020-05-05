package com.example.minesweeper.view.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.minesweeper.GameEngine;

public class Grid extends GridView {
    public Grid(Context context, AttributeSet aSet) {
        super(context, aSet);
        setNumColumns(GameEngine.WIDTH);
        setAdapter(new GridAdapter());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return GameEngine.WIDTH * GameEngine.HEIGHT;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            return GameEngine.getInstance().getCellAt(position);
        }
    }
}
