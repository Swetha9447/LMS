package com.example.lms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class DashboardSTDActivity extends AppCompatActivity {

    String welcomename = " ";
    String val = " ";
    TextView name;

    String rollno = " ";
    String val2 = " ";
    TextView regno1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_s_t_d);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        LinearLayout attendbtn =  (LinearLayout) findViewById(R.id.attendanceWidg);
        LinearLayout assignmentbtn =  (LinearLayout) findViewById(R.id.assignmentWidg);
        LinearLayout gradesbtn =  (LinearLayout) findViewById(R.id.gradesWidg);
        LinearLayout notificationsbtn =  (LinearLayout) findViewById(R.id.notificationsWidg);
        LinearLayout medissuebtn =  (LinearLayout) findViewById(R.id.medicalissueWidg);
        LinearLayout registerbtn = (LinearLayout) findViewById(R.id.registerWidg);





    attendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show a toast message.
                startActivity(new Intent(DashboardSTDActivity.this,AttendanceActivity.class ));
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show a toast message.
                startActivity(new Intent(DashboardSTDActivity.this,CourseRegActivity.class ));
            }
        });


        assignmentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show a toast message.
                startActivity(new Intent(DashboardSTDActivity.this,AssignmentActivity.class ));
            }
        });


        notificationsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show a toast message.
                Intent i = new Intent(DashboardSTDActivity.this, MedissueActivity.class);
                i.putExtra("reg", val);
                startActivity(i);
            }
        });


        medissuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show a toast message.

                startActivity(new Intent(DashboardSTDActivity.this,MedissueActivity.class ));
            }
        });

        gradesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show a toast message.
                startActivity(new Intent(DashboardSTDActivity.this,GradesActivity.class ));
            }
        });

        name = findViewById(R.id.STDname);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            val = extras.getString("reg");
            System.out.println(val);

        }

        regno1 = findViewById(R.id.STDreg);

        Bundle regextras = getIntent().getExtras();
        if (regextras != null) {
            val2 = extras.getString("reg");

        }




        showz s= new showz();
        s.execute("");




        attendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( DashboardSTDActivity.this, AttendanceActivity.class);
                i.putExtra("reg",val);
                startActivity(i);


            }
        });

        
        assignmentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( DashboardSTDActivity.this, AssignmentActivity.class);
                i.putExtra("reg",val);
                startActivity(i);


            }
        });

        gradesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( DashboardSTDActivity.this, GradesActivity.class);
                i.putExtra("reg",val);
                startActivity(i);


            }
        });

        notificationsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( DashboardSTDActivity.this, NotifActivity.class);
                i.putExtra("reg",val);
                startActivity(i);


            }
        });

        medissuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( DashboardSTDActivity.this, MedissueActivity.class);
                i.putExtra("reg",val);
                startActivity(i);


            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( DashboardSTDActivity.this, CourseRegActivity.class);
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


                String query = "SELECT regno FROM `registration` WHERE regno=" + "'" + val2 + "'" + ";";
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
            regno1.setText(val);
        }
    }

}
