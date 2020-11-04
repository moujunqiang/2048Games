package com.android.game2048.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.game2048.MyApp;


/**
 * 保存最高分
 */
public class SPData {
    private static Context context = MyApp.getContext();
    private static final String LOVE_2048 = "love2048";

    public static boolean saveBestScore(int bestScore){
        SharedPreferences preferences = context.getSharedPreferences(LOVE_2048,Context.MODE_PRIVATE);
        return preferences.edit().putInt("bestScore",bestScore).commit();
    }

    public static int getBestScore(){
        SharedPreferences preferences = context.getSharedPreferences(LOVE_2048,Context.MODE_PRIVATE);
        return preferences.getInt("bestScore",0);
    }
}
