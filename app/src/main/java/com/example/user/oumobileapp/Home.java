package com.example.user.oumobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jonathan Lacoursiere on 3/6/2018.
 */


public class Home extends Activity {

    /**
     * Initializes buttons and sets which intent to open upon being clicked.
     *
     * @param savedInstanceState
     */
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        //The buttons to get to the their respective use cases
        Button goFinAid = (Button) findViewById(R.id.goFinAidBtn);
        goFinAid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent goFin = new Intent(getApplicationContext(), finAid.class);
                startActivity(goFin);
            }
        });

        Button goAdv = (Button) findViewById(R.id.goAdvisingBtn);
        goAdv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent goAdv = new Intent(getApplicationContext(), advSelect.class);
                startActivity(goAdv);
            }
        });
    }

    /**
     * Open grades activity upon clicking grades button
     *
     * @param view current view
     */
    public void openActivity(View view) {
        String btnText;
        btnText = ((Button) view).getText().toString();
        if (btnText.equals("Grades")) {
            Intent i = new Intent(this, Grades.class);
            startActivity(i);
        }
    }

    /**
     * Open bus activity upon clicking bus tracker button
     *
     * @param view current view
     */
    public void openActivityBus(View view) {
        Intent i = new Intent(this, bus.class);
        startActivity(i);
    }

    /**
     * Opesn courseinfo activity
     *
     * @param view current view
     */
    public void openActivityCourseInfo(View view) {
        Intent i = new Intent(this, courseInfo.class);
        startActivity(i);
    }

    /**
     * queried PHP server for grizz ID of current authenticated user.
     *
     * @param view current view
     */
    public void getGrizzID(View view) {
        String username = "tester@tester.com";
        String password = "shouldnt_need";
        String type = "login";

        getGrizz getGrizz = new getGrizz(this);
        getGrizz.execute(type, username, password);

    }
}
