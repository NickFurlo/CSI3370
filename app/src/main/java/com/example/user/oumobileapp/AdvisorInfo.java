package com.example.user.oumobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AdvisorInfo extends AppCompatActivity {

    /**
     * Initializes activity.
     *
     * @param savedInstanceState current state to be saved and passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor_info);

        TextView name2 = (TextView)findViewById(R.id.nameTwoTextvie) ;
        TextView name3 = (TextView)findViewById(R.id.adName3) ;
        TextView name4 = (TextView)findViewById(R.id.adname4) ;

        TextView num2 = (TextView)findViewById(R.id.adNum2) ;
        TextView num3 = (TextView)findViewById(R.id.adnum3) ;
        TextView num4 = (TextView)findViewById(R.id.adnumfour) ;

        TextView email2 = (TextView)findViewById(R.id.advisor2) ;
        TextView email3 = (TextView)findViewById(R.id.advisor3) ;
        TextView email4 = (TextView)findViewById(R.id.textView18) ;


        name2.setText("Tim Johnson");
        name3.setText("Kim Orlando");
        name4.setText("Nate Edwards");

        num2.setText("458-562-4881");
        num3.setText("496-023-0004");
        num4.setText("689-413-2952");

        email2.setText("tj@oakland.edu");
        email3.setText("klando@oakland.edu");
        email4.setText("Nedwards@oakland.edu");
    }

}
