package com.example.user.oumobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class finAid extends AppCompatActivity {

    /**
     * Initializes activity.
     *
     * @param savedInstanceState current state to be saved and passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_aid);

        TextView name1 = (TextView) findViewById(R.id.aName1);
        TextView name2 = (TextView) findViewById(R.id.aName2);

        TextView amount1 = (TextView) findViewById(R.id.aAmount1);
        TextView amount2 = (TextView) findViewById(R.id.aAmount1);
/*
        name1.setText();
        name2.setText();

        amount1.setText();
        amount2.setText(); */
    }
}
