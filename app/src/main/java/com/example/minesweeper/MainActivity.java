package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(this, WelcomeActivity.class);
//        startActivity(intent);
        setContentView(R.layout.activity_main);
        GameEngine.getInstance().createGrid(this);
        ImageButton btn = findViewById(R.id.imageButton);
        if(GameEngine.getInstance().gameState.equals(GameEngine.WON)) {
            btn.setImageResource(R.drawable.wink);
        }
        if(GameEngine.getInstance().gameState.equals(GameEngine.LOST)) {
            btn.setImageResource(R.drawable.sad);
        }
        if(GameEngine.getInstance().gameState.equals(GameEngine.PLAYING)) {
            btn.setImageResource(R.drawable.smile2);
        }
    }
}
