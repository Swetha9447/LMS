package com.example.lms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TeeacherRegActivity extends AppCompatActivity {

    String named, coursed, passd, regnod;
int a=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teeacher_reg);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView btn = findViewById(R.id.AlreadyReg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeeacherRegActivity.this, "LOGIN", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TeeacherRegActivity.this, TeachLoginActivity.class));
            }
        });

        Button regb = findViewById(R.id.regbtn);
        regb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(RegisterActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();

                TextView name = findViewById(R.id.loginreg);
                named = name.getText().toString().toLowerCase();

                TextView course = findViewById(R.id.InputCourse);
                coursed = course.getText().toString().toLowerCase();

                TextView regno = findViewById(R.id.regNo);
                regnod = regno.getText().toString().toLowerCase();

                TextView password = findViewById(R.id.InputPassword);
                passd = password.getText().toString().toLowerCase();

                show xx=new show();
               xx.execute("");


            }
        });

    }

    public class show extends AsyncTask<String, String, String> {

        protected String doInBackground(String... strings) {
            if (!(named.isEmpty()) && !(coursed.isEmpty()) && !(passd.isEmpty()) && !(regnod.isEmpty())) {
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://192.168.0.35:3306/lms", "max", "max123");
                    String insert = ("INSERT INTO `teacherregistration` (`name`, `regnot`, `courseteaching`, `password`) VALUES (?,?,?,?);");
                    PreparedStatement preparedStmt = con.prepareStatement(insert);
                    preparedStmt.setString(1, named);
                    preparedStmt.setString(2, regnod);
                    preparedStmt.setString(3, coursed);
                    preparedStmt.setString(4, passd);
                    preparedStmt.execute();
                    con.close();
                    // TOAST DISPLAY
//                    String text="REGISTERED SUCESSFULLY";
//                    Toast.makeText(RegisterActivity.this,text , Toast.LENGTH_SHORT).show();
 a=0;
                    Log.i("mylog", "Registered Successfully with Database");


                    return "OK";

                } catch (Exception e) {
                    // TOAST DISPLAY
//                    String text="Cant Connect to Database";
//                    Toast.makeText(RegisterActivity.this,text , Toast.LENGTH_SHORT).show();

                    Log.i("mylog", "CANT CONNECT TO DATABASE: " + e);
                    return "CANT REGISTER : " + e.toString();
                }
            } else {
                // TOAST DISPLAY
//                String text="Please Fill All Fields";
//                Toast.makeText(RegisterActivity.this,text , Toast.LENGTH_SHORT).show();

                Log.i("mylog", "PLEASE FILL ALL FIELDS ");
                return "FAILED TO REGISTER";

            }
        }

        @Override

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(a==0)
            Toast.makeText(TeeacherRegActivity.this, "REGISTERED SUCESSFULLY", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(TeeacherRegActivity.this, TeachLoginActivity.class));
        }
    }
}

