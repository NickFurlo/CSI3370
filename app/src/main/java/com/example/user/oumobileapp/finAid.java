package com.example.user.oumobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class finAid extends AppCompatActivity {

    /**
     * Initializes activity.
     *

     */

    protected String doInBackFA(){
        String finAidURL = "http://www.secs.oakland.edu/~ksmith/aidInfo.php";

        try {
            URL url = new URL(finAidURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_aid);

        TextView name1 = (TextView) findViewById(R.id.aName1);
        TextView name2 = (TextView) findViewById(R.id.aName2);

        TextView amount1 = (TextView) findViewById(R.id.aAmount1);
        TextView amount2 = (TextView) findViewById(R.id.aAmount1);

        String a = doInBackFA();


        name1.setText("Deans Award");

        name2.setText("Geographical Award");

        amount1.setText("5000");
        amount2.setText("2000");
    }
}
