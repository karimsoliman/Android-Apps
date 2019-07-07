package com.example.android.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int a_points = 0;
    int b_points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increaseAThree(View V){
        a_points = a_points + 3;
        displayForTeamA(a_points);

    }

    public void increaseATwo(View V){
        a_points = a_points + 2;
        displayForTeamA(a_points);
    }

    public void increaseAOne(View V){
        a_points = a_points + 1;
        displayForTeamA(a_points);
    }

    public void displayForTeamA(int n){
        TextView A_points = (TextView) findViewById(R.id.team_A_score);
        A_points.setText("" + n);
    }

    public void displayForTeamB(int n){
        TextView B_points = (TextView) findViewById(R.id.team_B_score);
        B_points.setText("" + n);
    }

    public void increaseBThree(View V){
        b_points = b_points + 3;
        displayForTeamB(b_points);
    }

    public void increaseBTwo(View V){
        b_points = b_points + 2;
        displayForTeamB(b_points);
    }

    public void increaseBOne(View V){
        b_points = b_points + 1;
        displayForTeamB(b_points);
    }

    public void reset(View V){
        a_points = 0;
        b_points = 0;
        displayForTeamA(a_points);
        displayForTeamB(b_points);
    }

}
