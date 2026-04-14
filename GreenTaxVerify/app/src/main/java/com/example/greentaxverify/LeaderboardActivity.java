package com.example.greentaxverify;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LeaderboardActivity extends AppCompatActivity {

    private MaterialToolbar toolbarLeaderboard;
    private TextView tvLeaderboardTitle;
    private TextView tvLeaderboardSubtitle;
    private TextView tvQuarterLabel;
    private TextView tvRank1Name;
    private TextView tvRank1Score;
    private TextView tvRank2Name;
    private TextView tvRank2Score;
    private TextView tvRank3Name;
    private TextView tvRank3Score;
    private RecyclerView rvLeaderboardList;
    private Button btnViewAllSocieties;
    private BottomNavigationView bottomNavLeaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        toolbarLeaderboard   = findViewById(R.id.toolbar_leaderboard);
        tvLeaderboardTitle   = findViewById(R.id.tv_leaderboard_title);
        tvLeaderboardSubtitle = findViewById(R.id.tv_leaderboard_subtitle);
        tvQuarterLabel       = findViewById(R.id.tv_quarter_label);
        tvRank1Name          = findViewById(R.id.tv_rank1_name);
        tvRank1Score         = findViewById(R.id.tv_rank1_score);
        tvRank2Name          = findViewById(R.id.tv_rank2_name);
        tvRank2Score         = findViewById(R.id.tv_rank2_score);
        tvRank3Name          = findViewById(R.id.tv_rank3_name);
        tvRank3Score         = findViewById(R.id.tv_rank3_score);
        rvLeaderboardList    = findViewById(R.id.rv_leaderboard_list);
        btnViewAllSocieties  = findViewById(R.id.btn_view_all_societies);
        bottomNavLeaderboard = findViewById(R.id.bottom_nav_leaderboard);

        setSupportActionBar(toolbarLeaderboard);

        rvLeaderboardList.setLayoutManager(new LinearLayoutManager(this));
        // TODO: set adapter on rvLeaderboardList

        btnViewAllSocieties.setOnClickListener(v -> {
            // TODO: expand to full society rankings list
        });

        bottomNavLeaderboard.setOnItemSelectedListener(item -> {
            // TODO: handle bottom nav tab changes
            return true;
        });
    }
}
