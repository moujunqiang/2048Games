package com.android.game2048;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {


    int[][] card = new int[4][4];
    int current = 0, max = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = 0;
                init();
            }
        });
    }

    public void init() {
        current = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                card[i][j] = 0;
            }
        }
        createRandow();
        initView();
    }

    float mPosX, mPosY, mCurPosX, mCurPosY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mPosX = event.getX();
                mPosY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurPosX = event.getX();
                mCurPosY = event.getY();

                break;
            case MotionEvent.ACTION_UP:
                if (mCurPosY - mPosY > 0
                        && (Math.abs(mCurPosY - mPosY) > 40)) {
                    //向下滑動
                    goDown();

                } else if (mCurPosY - mPosY < 0
                        && (Math.abs(mCurPosY - mPosY) > 40)) {
                    //向上滑动
                    goUp();
                }
                if (mCurPosX - mPosX > 0
                        && (Math.abs(mCurPosX - mPosX) > 40)) {
                    //向左滑動
                    goRight();


                } else if (mCurPosX - mPosX < 0
                        && (Math.abs(mCurPosX - mPosX) > 40)) {
                    //向右滑动
                    goLeft();

                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void initView() {
        TextView[] textView = new TextView[16];
        textView[0] = findViewById(R.id.tv0);
        textView[1] = findViewById(R.id.tv1);
        textView[2] = findViewById(R.id.tv2);
        textView[3] = findViewById(R.id.tv3);
        textView[4] = findViewById(R.id.tv4);
        textView[5] = findViewById(R.id.tv5);
        textView[6] = findViewById(R.id.tv6);
        textView[7] = findViewById(R.id.tv7);
        textView[8] = findViewById(R.id.tv8);
        textView[9] = findViewById(R.id.tv9);
        textView[10] = findViewById(R.id.tv10);
        textView[11] = findViewById(R.id.tv11);
        textView[12] = findViewById(R.id.tv12);
        textView[13] = findViewById(R.id.tv13);
        textView[14] = findViewById(R.id.tv14);
        textView[15] = findViewById(R.id.tv15);
        for (int i = 0; i < 16; i++) {
            textView[i].setTextSize(30);
            textView[i].setWidth(50);
            textView[i].setHeight(30);

            int p = card[i / 4][i % 4];
            if (p == 0) textView[i].setBackgroundColor(0x1416CCFA);
            if (p == 2) textView[i].setBackgroundColor(0x8888FFFF);
            if (p == 4) textView[i].setBackgroundColor(Color.LTGRAY);
            if (p == 8) textView[i].setBackgroundColor(Color.GRAY);
            if (p == 16) textView[i].setBackgroundColor(0x129471FD);
            if (p == 32) textView[i].setBackgroundColor(Color.GREEN);
            if (p == 64) textView[i].setBackgroundColor(Color.RED);
            if (p == 128) textView[i].setBackgroundColor(0x900000FF);
            if (p == 256) textView[i].setBackgroundColor(Color.YELLOW);
            if (p == 512) textView[i].setBackgroundColor(Color.BLUE);
            if (p == 1024) textView[i].setBackgroundColor(Color.CYAN);
            if (p == 2048) textView[i].setBackgroundColor(0x6EF11000);

            if (p != 0) textView[i].setText(String.valueOf(p));
            else textView[i].setText("");
        }

        TextView score = findViewById(R.id.score);
        TextView maxscore = findViewById(R.id.maxscore);
        max = Math.max(max, current);
        score.setText("" + current);
        maxscore.setText("" + max);
    }

    public void goLeft() {
        int jud = 0;
        int i, j, k;
        for (i = 0; i < 4; i++) {
            for (j = 1; j < 4; j++) {
                if (card[i][j] != 0) {
                    for (k = j - 1; k >= 0; k--) {
                        if (card[i][k] == 0) continue;
                        else if (card[i][k] == card[i][j]) {
                            jud = 1;
                            card[i][k] *= 2;
                            current += card[i][k];
                            card[i][j] = -1;
                            break;
                        }//先标记成-1，避免继续合并。
                        else break;
                    }
                }
            }
        }
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if (card[i][j] == -1) card[i][j] = 0;
            }
        }
        for (i = 0; i < 4; i++) {
            for (j = 1; j < 4; j++) {
                if (card[i][j] != 0) {
                    for (k = j - 1; k >= 0; k--) {
                        if (card[i][k] == 0) {
                            jud = 1;
                            card[i][k] = card[i][k + 1];
                            card[i][k + 1] = 0;
                        } else break;
                    }
                }
            }
        }
        if (jud == 1) createRandow();
        initView();
        checkStatus();
    }

    public void goRight() {
        int jud = 0;
        int i, j, k;
        for (i = 0; i < 4; i++) {
            for (j = 2; j >= 0; j--) {
                if (card[i][j] != 0) {
                    for (k = j + 1; k < 4; k++) {
                        if (card[i][k] == 0) continue;
                        else if (card[i][k] == card[i][j]) {
                            jud = 1;
                            card[i][k] *= 2;
                            current += card[i][k];
                            card[i][j] = -1;
                            break;
                        }//先标记成-1，避免继续合并。
                        else break;
                    }
                }
            }
        }
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if (card[i][j] == -1) card[i][j] = 0;
            }
        }
        for (i = 0; i < 4; i++) {
            for (j = 2; j >= 0; j--) {
                if (card[i][j] != 0) {
                    for (k = j + 1; k < 4; k++) {
                        if (card[i][k] == 0) {
                            jud = 1;
                            card[i][k] = card[i][k - 1];
                            card[i][k - 1] = 0;
                        } else break;
                    }
                }
            }
        }
        if (jud == 1) createRandow();
        initView();
        checkStatus();
    }

    public void goUp() {
        int jud = 0;
        int i, j, k;
        for (i = 0; i < 4; i++) {
            for (j = 1; j < 4; j++) {
                if (card[j][i] != 0) {
                    for (k = j - 1; k >= 0; k--) {
                        if (card[k][i] == 0) continue;
                        else if (card[k][i] == card[j][i]) {
                            jud = 1;
                            card[k][i] *= 2;
                            current += card[k][i];
                            card[j][i] = -1;
                            break;
                        }//先标记成-1，避免继续合并。
                        else break;
                    }
                }
            }
        }
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if (card[i][j] == -1) card[i][j] = 0;
            }
        }
        for (i = 0; i < 4; i++) {
            for (j = 1; j < 4; j++) {
                if (card[j][i] != 0) {
                    for (k = j - 1; k >= 0; k--) {
                        if (card[k][i] == 0) {
                            jud = 1;
                            card[k][i] = card[k + 1][i];
                            card[k + 1][i] = 0;
                        } else break;
                    }
                }
            }
        }
        if (jud == 1) createRandow();
        initView();
        checkStatus();
    }

    public void goDown() {
        int jud = 0;
        int i, j, k;
        for (i = 0; i < 4; i++) {
            for (j = 2; j >= 0; j--) {
                if (card[j][i] != 0) {
                    for (k = j + 1; k < 4; k++) {
                        if (card[k][i] == 0) continue;
                        else if (card[k][i] == card[j][i]) {
                            jud = 1;
                            card[k][i] *= 2;
                            current += card[k][i];
                            card[j][i] = -1;
                            break;
                        }//先标记成-1，避免继续合并。
                        else break;
                    }
                }
            }
        }
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if (card[i][j] == -1) card[i][j] = 0;
            }
        }
        for (i = 0; i < 4; i++) {
            for (j = 2; j >= 0; j--) {
                if (card[j][i] != 0) {
                    for (k = j + 1; k < 4; k++) {
                        if (card[k][i] == 0) {
                            jud = 1;
                            card[k][i] = card[k - 1][i];
                            card[k - 1][i] = 0;
                        } else break;
                    }
                }
            }
        }
        if (jud == 1) createRandow();
        initView();
        checkStatus();
    }

    //检测是否可以继续游戏
    public void checkStatus() {
        if (!isValid()) {
            AlertDialog dialog;
            dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("游戏结束")  //设置标题
                    .setMessage("最终得分：" + current)
                    .setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            init();
                            dialog.dismiss();
                        }
                    })
                    .create();  //创建对话框
            dialog.show();  //显示对话框
        }
    }

    public boolean isValid() {
        int i, j;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if (card[i][j] == 0) return true;
            }
        }
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (card[i][j] == card[i + 1][j] || card[i][j] == card[i][j + 1]) return true;
            }
        }
        return false;
    }

    public void createRandow() {
        while (true) {
            Random rand = new Random();
            int p = rand.nextInt(3);
            int st;
            if (p == 0) st = 4;
            else st = 2;
            int x = rand.nextInt(4);
            int y = rand.nextInt(4);
            if (card[x][y] != 0) continue;
            card[x][y] = st;
            break;
        }
    }


}