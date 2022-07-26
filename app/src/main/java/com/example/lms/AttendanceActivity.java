package com.example.lms;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class AttendanceActivity extends AppCompatActivity {
    String attendance = " ";
    String val1 = " ";
    Button btn;



    String welcomename = " ";
    String val = " ";
    TextView name;

    String rollno = " ";
    String val2 = " ";
    TextView regno1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        name = findViewById(R.id.named);
        btn = findViewById(R.id.Attedancebutton);


        //return btn;



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            val = extras.getString("reg");
            System.out.println(val);

        }

        regno1 = findViewById(R.id.regnoC);



        showw s= new showw();
        s.execute("");

//        showreg a = new showreg();
//        a.execute("");
//
//       showattendance atn = new showattendance();
//        atn.execute("");





    }

    public class showw extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings) {


            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.0.35:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();


                String query = "SELECT name FROM `registration` WHERE regno=" + "'" + val + "'" + ";";
                System.out.println("val="+val);
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


                String query = "SELECT attendance FROM `coursesattendance` WHERE regno=" + "'" + val + "'" + ";";
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
                        attendance = g[i];


                    }

                }
            }
            catch (Exception e) {
                System.out.println("TD Cant Connect To Database: " + e);
            }

            int xa=Integer.parseInt(attendance);
            System.out.println(xa);
            if(xa<70)
            {
                // change color
                btn.setBackgroundColor (Color.rgb(246,11,11));// red color
//                Toast.makeText(AttendanceActivity.this, "You're Attendance is Not Satisfactory", Toast.LENGTH_SHORT).show();
            }
            else
            {
                // default state
                btn.setBackgroundColor (Color.rgb(0,255,17)); // default color
//                Toast.makeText(AttendanceActivity.this, "Your Attendance is Satisfactory", Toast.LENGTH_SHORT).show();
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
            attendance=attendance.toUpperCase();
            btn.setText(attendance);
        }
    }

//    public class showreg extends AsyncTask<String,String,String>
//    {
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//
//
//
//            return "";
//
//
//        }
//    }
//
//    public class showattendance extends AsyncTask<String,String,String>
//    {
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//
//
//            return "";
//
//
//        }
//    }

}
