package com.example.user.oumobileapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class courseInfo extends AppCompatActivity {
    private TextView tvInfo, tvPrereq, tvSummary;
    private Button btnSemester, btnCourse;
    private String semesterText, courseText, singleDescription, singlePrereq;


    /**
     * Initializes buttons and textviews.
     *
     * @param savedInstanceState current state to be saved and passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseinfo);

        tvSummary = (TextView) findViewById(R.id.tvSummary);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        tvPrereq = (TextView) findViewById(R.id.tvPrereq);

        btnSemester = (Button) findViewById(R.id.btnSemester);

        btnCourse = (Button) findViewById(R.id.btnCourse);

        btnCourse.setEnabled(false);

        tvSummary.setText("");

    }

    /**
     * Allows user to pick and set a semester to view
     *
     * @param view current view
     */
    public void chooseSemester(View view) {
        AlertDialog.Builder pickSemester = new AlertDialog.Builder(courseInfo.this);
        pickSemester.setTitle("Select Semester");

        final String[] semesters = {"Fall 2018", "Winter 2018"};
        pickSemester.setItems(semesters, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                switch (i) {
                    case 0:
                        semesterText = semesters[i].toUpperCase();

                        btnCourse.setEnabled(true);
                    case 1:
                        semesterText = semesters[i].toUpperCase();

                        btnCourse.setEnabled(true);
                }
            }
        });
        tvInfo.setText("");
        tvPrereq.setText("");
        pickSemester.show();
        tvSummary.setText("");
    }

    /**
     * Displays course info to user based on selected information
     *
     * @param view current view
     */
    public void getCourseInfo(View view) {
        //Call getInfo
        String username = "tester@tester.com";
        String password = "shouldnt_need";
        String type = "login";
        getInfo getInfo = new getInfo(this);
        getInfo.execute(type, username, password);
        /*
        if (semesterText.contains("WINTER 2018"))
        {
            tvInfo.setText("");
            tvInfo.setText("");
            tvPrereq.setText("");
            AlertDialog.Builder noSemester = new AlertDialog.Builder(courseinfo.this);
            noSemester.setTitle("No Courses");
            noSemester.setMessage("You are not enrolled in any courses for this semester.");
            noSemester.show();
        }

        else if (semesterText.contains("FALL 2018"))
        {
            AlertDialog.Builder newCourse = new AlertDialog.Builder (courseinfo.this);
            newCourse.setTitle("Select Course");
            final String[] prereqs = {"Prerequisites: CSI 2300","Prerequisites: CSI 2440","Prerequisites: None"};

            final String[] descriptions = {"A team-oriented project work consisting of a small project to build skills in needs assessment, group problem solving, and written and oral technical presentations.",
                    "Introduces fundamental concepts of system administration for Unix and Windows operating systems. Concepts of operating system such as file system, memory management, processes and service management are discussed in view of System Administration. Script programming is introduced to automate system administration tasks.",
                    "This course introduces students to the global business environment. It focuses on how differences in economic systems, national culture, socio-demographics, and political orientations affect business operations. It also provides an introduction to key business activities."};

            final String[] courses = {"CSI 2999","CSI 2500","MGT 1100"};

            newCourse.setItems(courses, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    switch(i) {
                        case 0:
                            courseText = courses[i].toUpperCase();
                            tvSummary.setText(courseText);

                            singlePrereq = prereqs[i];
                            tvPrereq.setText(singlePrereq);

                            singleDescription = descriptions[i];
                            tvInfo.setText(singleDescription);
                        case 1:
                            courseText = courses[i].toUpperCase();
                            tvSummary.setText(courseText);

                            singlePrereq = prereqs[i];
                            tvPrereq.setText(singlePrereq);

                            singleDescription = descriptions[i];
                            tvInfo.setText(singleDescription);

                        case 2:

                            courseText = courses[i].toUpperCase();
                            tvSummary.setText(courseText);

                            singlePrereq = prereqs[i];
                            tvPrereq.setText(singlePrereq);

                            singleDescription = descriptions[i];
                            tvInfo.setText(singleDescription);}
                }
            });
            tvSummary.setText("");
            tvInfo.setText("");
            tvPrereq.setText("");
            newCourse.show();
        }

        */
    }

    //Datbase connection
    //CAN PROBABLY ONLY HANDLE A SINGLE LINE AS OF 4/2
    public class getInfo extends AsyncTask<String, Void, String> {
        Context context;

        getInfo(Context ctx) {
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
            String login_url = "http://www.secs.oakland.edu/~ksmith/trackBus.php";

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
            return null;
        }


        /**
         * Performs pre-execute tasks
         */
        @Override
        protected void onPreExecute() {
            //PreExecute tasks
        }

        /**
         * Converts PHP output to LatLng variable and create marker
         *
         * @param result outputs result of post execute actions
         */
        @Override
        protected void onPostExecute(String result) {

            final String finalresult = result;

            if (semesterText.contains("WINTER 2018")) {
                tvInfo.setText("");
                tvPrereq.setText("");
                AlertDialog.Builder noSemester = new AlertDialog.Builder(courseInfo.this);
                noSemester.setTitle("No Courses");
                noSemester.setMessage("You are not enrolled in any courses for this semester.");
                noSemester.show();
            } else if (semesterText.contains("FALL 2018")) {
                AlertDialog.Builder newCourse = new AlertDialog.Builder(courseInfo.this);
                newCourse.setTitle("Select Course");
                final String[] prereqs = {"Prerequisites: CSI 2300", "Prerequisites: CSI 2440", "Prerequisites: None"};

                final String[] descriptions = {"A team-oriented project work consisting of a small project to build skills in needs assessment, group problem solving, and written and oral technical presentations.",
                        "Introduces fundamental concepts of system administration for Unix and Windows operating systems. Concepts of operating system such as file system, memory management, processes and service management are discussed in view of System Administration. Script programming is introduced to automate system administration tasks.",
                        "This course introduces students to the global business environment. It focuses on how differences in economic systems, national culture, socio-demographics, and political orientations affect business operations. It also provides an introduction to key business activities."};

                final String[] courses = {"CSI 2999", "CSI 2500", "MGT 1100"};

                newCourse.setItems(courses, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        switch (i) {
                            case 0:
                                courseText = courses[i].toUpperCase();
                                tvSummary.setText(courseText);

                                singlePrereq = prereqs[i];
                                tvPrereq.setText(singlePrereq);

                                singleDescription = descriptions[i];
                                tvInfo.setText(singleDescription);
                            case 1:
                                courseText = courses[i].toUpperCase();
                                tvSummary.setText(courseText);

                                singlePrereq = prereqs[i];
                                tvPrereq.setText(singlePrereq);

                                singleDescription = descriptions[i];
                                tvInfo.setText(singleDescription);

                            case 2:

                                courseText = courses[i].toUpperCase();
                                tvSummary.setText(courseText);

                                singlePrereq = prereqs[i];
                                tvPrereq.setText(singlePrereq);

                                singleDescription = descriptions[i];
                                tvInfo.setText(singleDescription);
                        }
                    }
                });
                tvSummary.setText(finalresult);
                tvInfo.setText("");
                tvPrereq.setText("");
                newCourse.show();
            }

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
}
