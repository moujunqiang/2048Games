package com.android.game2048;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import love.smartalk.game2048.widget.Love2048Layout;

public class MainActivity extends AppCompatActivity {

    Love2048Layout llGameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        llGameView = findViewById(R.id.ll_game_view);
        findViewById(R.id.tv_restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llGameView.restart();
            }
        });

    }


}
