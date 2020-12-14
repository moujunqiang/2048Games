package com.android.game2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class RankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        RecyclerView rvScore = findViewById(R.id.rv_score);
        rvScore.setLayoutManager(new LinearLayoutManager(this));
        ScoreListAdapter adapter = new ScoreListAdapter();
        List<ScoreBean> scoreBeans = new ScoreDao(this).queryTypesAll();
        adapter.setNewsBeans(scoreBeans);
        rvScore.setAdapter(adapter);

    }
}