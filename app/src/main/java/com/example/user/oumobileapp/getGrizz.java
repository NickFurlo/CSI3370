package com.example.user.oumobileapp;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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

/**
 * Created by kyles on 3/20/2018.
 */

public class getGrizz extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;

    getGrizz(Context ctx) {
        context = ctx;
    }

    /**
     * connects user to database via username and password.
     *
     * @param params parameters for PHP server connection.
     * @return whether or not user is connected to the database.
     */
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://www.secs.oakland.edu/~ksmith/getGrizz.php";
        if (type.equals("login")) {
            try {
                String useremail = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("useremail", "UTF-8") + "=" + URLEncoder.encode(useremail, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
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
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Set visual activity data.
     */
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Grizz ID");
    }

    /**
     * Sets dialog to display grizz ID
     *
     * @param result Users grizz ID
     */
    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    /**
     * Updates app progress
     *
     * @param values values to be updated
     */
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
