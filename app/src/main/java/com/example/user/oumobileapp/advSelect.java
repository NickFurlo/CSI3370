package com.example.user.oumobileapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class advSelect extends AppCompatActivity {

    /**
     * Initializes buttons, textviews, and clicklisteners.
     *
     * @param savedInstanceState current state to be saved and passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_select);

        //buttons to tp adviser info
        Button goAdvInfo = (Button) findViewById(R.id.adviserInfoBtn);
        goAdvInfo.setOnClickListener(new View.OnClickListener() {

            /**
             * Opens advisor info
             *
             * @param v current view
             */
            @Override
            public void onClick(View v) {

                Intent goAdInfo = new Intent(getApplicationContext(), AdvisorInfo.class);
                startActivity(goAdInfo);
            }
        });


        //https://ou-advisor.oakland.edu/TracWeb40/Default.html
        //for schedule an appointment
        Button goApp = (Button) findViewById(R.id.addAppBtn);
        goApp.setOnClickListener(new View.OnClickListener() {

            /**
             * Opens advising office anointment  website
             *
             * @param v current view
             */
            @Override
            public void onClick(View v) {
                String daWebsite = "https://ou-advisor.oakland.edu/TracWeb40/Default.html";
                Uri webAddress = Uri.parse(daWebsite);

                Intent goToAppointment = new Intent(Intent.ACTION_VIEW, webAddress);
                if (goToAppointment.resolveActivity(getPackageManager()) != null) {
                    startActivity(goToAppointment);
                }
            }
        });
    }
}
