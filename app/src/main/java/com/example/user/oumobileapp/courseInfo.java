package com.example.user.oumobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class courseInfo extends AppCompatActivity {
    private TextView tvInfo, tvPrereq, tvSummary;
    private Button btnSemester, btnCourse;
    private String semesterText, courseText, singleDescription, singlePrereq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        tvSummary = (TextView) findViewById(R.id.tvSummary);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        tvPrereq = (TextView) findViewById(R.id.tvPrereq);

        btnSemester = (Button) findViewById(R.id.btnSemester);

        btnCourse = (Button) findViewById(R.id.btnCourse);

        btnCourse.setEnabled(false);

        tvSummary.setText("");

    }

    public void chooseSemester (View view){
        AlertDialog.Builder pickSemester = new AlertDialog.Builder(courseInfo.this);
        pickSemester.setTitle("Select Semester");

        final String[] semesters = {"Fall 2018", "Winter 2018"};
        pickSemester.setItems(semesters, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                switch(i){
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

    public void getCourseInfo (View view){
        if (semesterText.contains("WINTER 2018"))
        {
            tvInfo.setText("");
            tvPrereq.setText("");
            AlertDialog.Builder noSemester = new AlertDialog.Builder(courseInfo.this);
            noSemester.setTitle("No Courses");
            noSemester.setMessage("You are not enrolled in any courses for this semester.");
            noSemester.show();
        }

        else if (semesterText.contains("FALL 2018"))
        {
            AlertDialog.Builder newCourse = new AlertDialog.Builder (courseInfo.this);
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
        }}}

