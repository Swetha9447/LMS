package com.example.lms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Random;

public class CourseRegActivity extends AppCompatActivity {


    String welcomename = " ";
    String val = " ";
    TextView name;

    String rollno = " ";
    String val2 = " ";
    TextView regno1;

    String course,attendance="0",grade="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_reg);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Random a1=new Random();
        int xaa=a1.nextInt(4);
        if(xaa==0)
            grade="A";
        else if(xaa==1)
            grade="B";
        else if(xaa==2)
            grade="C";
        else if(xaa==3)
            grade="D";

        Random a=new Random();
        int xa=a.nextInt(90);
        attendance=String.valueOf(xa);
        name = findViewById(R.id.nameD);
        regno1 = findViewById(R.id.regnoC);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            val = extras.getString("reg");
            System.out.println(val);

        }

        showz s= new showz();
        s.execute("");


        Button coursebtn = findViewById(R.id.Regbtn);

        coursebtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Toast.makeText(RegisterActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();

                TextView coursename = findViewById(R.id.courseD);
                course = coursename.getText().toString().toLowerCase();

                showreg aa=new showreg();
                aa.execute("");

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


                String query = "SELECT name FROM `registration` WHERE regno=" + "'" + val + "'" + ";";
                System.out.println("Dashboard val="+val);
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

            try {
                  Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.0.35:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();


                String query = "SELECT regno FROM `registration` WHERE regno=" + "'" + val + "'" + ";";
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
                        rollno = g[i];


                    }

                }
            }
            catch (Exception e) {
                System.out.println("TD Cant Connect To Database: " + e);
            }

            return "";


        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            welcomename=welcomename.toUpperCase();
            name.setText(welcomename);
            rollno=rollno.toUpperCase();
            regno1.setText(rollno);
        }
    }

    public class showreg extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(CourseRegActivity.this, "REGISTRED SUCESSFULLY ", Toast.LENGTH_SHORT).show();
            Intent ia = new Intent( CourseRegActivity.this, DashboardSTDActivity.class);
            ia.putExtra("done","OK");
            startActivity(ia);
        }

        @Override
        protected String doInBackground(String... strings) {

            if( !(val.isEmpty()) && !(course.isEmpty()))
            {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://192.168.0.35:3306/lms", "max", "max123");
                    String insert=("INSERT INTO `coursesattendance` (`regno`, `courseid`, `attendance`, `grade`) VALUES (?,?,?,?);");
                    PreparedStatement preparedStmt = con.prepareStatement(insert);
                    preparedStmt.setString (1, val);
                    preparedStmt.setString (2, course);
                    preparedStmt.setString (3, attendance);
                    preparedStmt.setString(4, grade);
                    preparedStmt.execute();
                    con.close();
                    System.out.println(val);
                    System.out.println(course);
                    System.out.println(attendance);
                    System.out.println(grade);
                    // TOAST
                    // DISPLAY
                    String text="REGISTERED SUCCESSFULLY";
//                    Toast.makeText(CourseRegActivity.this,text , Toast.LENGTH_SHORT).show();

                    Log.i("mylog", "Registered Successfully with Database");
                    //startActivity(new Intent(RegisterActivity.this,LoginActivity.class ));

                    return "OK";

                }
                catch (Exception e) {
                    // TOAST DISPLAY
//                    String text="Cant Connect to Database";
//                    Toast.makeText(RegisterActivity.this,text , Toast.LENGTH_SHORT).show();

                    Log.i("mylog", "CANT CONNECT TO DATABASE: "+e);
                    return "CANT REGISTER : " + e.toString();
                }
            }
            else{
                // TOAST DISPLAY
//                String text="Please Fill All Fields";
//                Toast.makeText(RegisterActivity.this,text , Toast.LENGTH_SHORT).show();

                Log.i("mylog", "PLEASE FILL ALL FIELDS ");
                return "FAILED TO REGISTER";

            }
        }
    }





}