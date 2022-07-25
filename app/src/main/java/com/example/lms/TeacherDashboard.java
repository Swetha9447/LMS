package com.example.lms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class TeacherDashboard extends AppCompatActivity {

    String welcomename = "";
    String val = "",subject;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        name = findViewById(R.id.dtname);
        Button showall=findViewById(R.id.dtshowall);
        Button editat=findViewById(R.id.dteditattendence);
        Button editgt=findViewById(R.id.dteditgrades);
        Button ha=findViewById(R.id.dtsendnoti);
        Button asi=findViewById(R.id.dtassign);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            val = extras.getString("reg");

        }

        showz s=new showz();
        s.execute("");
        asi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent wwx= new Intent( TeacherDashboard.this, uploadassign.class);
                wwx.putExtra("rega",subject);
                startActivity(wwx);


            }
        });
        ha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent wwx= new Intent( TeacherDashboard.this, notifyhigher.class);
                wwx.putExtra("rega","OK");
                startActivity(wwx);


            }
        });

        editgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ww = new Intent( TeacherDashboard.this, gradeschange.class);
                ww.putExtra("rega",subject);
                startActivity(ww);


            }
        });

        editat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( TeacherDashboard.this, editattendance.class);
                i.putExtra("reg",subject);
                startActivity(i);


            }
        });



        showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( TeacherDashboard.this, studentsdetails.class);
                i.putExtra("reg",val);
                startActivity(i);


            }
        });




}

    public class showz extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.0.35:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();


                String query = "SELECT courseteaching FROM `teacherregistration` WHERE regnot=" + "'" + val + "'" + ";";
                ResultSet rs = stmt.executeQuery(query);
                ResultSet rs1 = rs;
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

                int columnsNumber = rsmd.getColumnCount();
                String g[] = new String[40];
                int o = 0;
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {

                        String columnValue = rs.getString(i);


                        String columnValue1 = rs1.getString(i);

                        //System.out.print(columnValue1 );

                        g[o] = columnValue1;
                        o++;
                    }


                }
                System.out.println("\n");

                for (int i = 0; i < g.length; i++) {
                    if (g[i] != null) {

                        subject=g[i];
                    }

                }
            } catch (Exception e) {
                System.out.println("TD Cant Connect To Database: " + e);
            }

            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.0.35:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();


                String query = "SELECT name FROM `teacherregistration` WHERE regnot=" + "'" + val + "'" + ";";
                ResultSet rs = stmt.executeQuery(query);
                ResultSet rs1 = rs;
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

                int columnsNumber = rsmd.getColumnCount();
                String g[] = new String[40];
                int o = 0;
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {

                        String columnValue = rs.getString(i);


                        String columnValue1 = rs1.getString(i);

                        //System.out.print(columnValue1 );

                        g[o] = columnValue1;
                        o++;
                    }


                }
                System.out.println("\n");

                for (int i = 0; i < g.length; i++) {
                    if (g[i] != null) {
                        System.out.print(g[i]);
                        welcomename = g[i];


                    }

                }
            }
            catch (Exception e) {
                System.out.println("TD Cant Connect To Database: " + e);
            }
            welcomename=welcomename.toUpperCase();
            name.setText(welcomename);
            return "";


        }
    }







}