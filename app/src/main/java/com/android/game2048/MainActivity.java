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

import com.android.game2048.utils.SPData;
import love.smartalk.game2048.widget.Love2048Layout;

public class MainActivity extends AppCompatActivity implements Love2048Layout.OnLove2048Listener {

    TextView tvScore;
    TextView tvBestScore;
    Love2048Layout llGameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        tvScore = findViewById(R.id.tv_score);
        tvBestScore = findViewById(R.id.tv_best_score);
        llGameView = findViewById(R.id.ll_game_view);
        findViewById(R.id.tv_restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llGameView.restart();
            }
        });

        llGameView.setLove2048Listener(this);
        tvBestScore.setText("最高分: " + SPData.getBestScore());
    }

    @Override
    public void onScoreChanged(int score) {
        if (SPData.getBestScore() < score) {
            tvBestScore.setText("最高分: " + score);
            SPData.saveBestScore(score);
        }

        String scoreStr = "当前分数: " + score;
        tvScore.setText(scoreStr);
    }

    @Override
    public void onGameOver() {
        showGameOver();
    }

    private void showGameOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("2048");
        builder.setMessage("游戏结束是否重新开始? ");
        builder.setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                llGameView.restart();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
