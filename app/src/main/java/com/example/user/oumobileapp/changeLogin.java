package com.example.user.oumobileapp;

import android.app.AlertDialog;
import android.content.Context;
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
 * Created by kyles on 3/6/2018.
 */

public class changeLogin extends AsyncTask<String, Void, String> {


    Context context;
    AlertDialog alertDialog;

    changeLogin(Context ctx) {
        context = ctx;
    }

    /**
     * Gets app connected to the database and logged in.
     *
     * @param params parameter array for user email and password.
     * @return whether or not app is connected to database
     */
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://www.secs.oakland.edu/~ksmith/loginChange.php";
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
     * Performs pre-execute tasks
     */
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Change Status");


    }


    /**
     * Output result to user
     *
     * @param result result to be presented to user
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
