package com.example.lms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class uploadassign extends AppCompatActivity {
String link,subjecta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadassign);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView l=findViewById(R.id.uploadlink);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            subjecta = extras.getString("rega");

        }

        Button i=findViewById(R.id.upload);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                link= l.getText().toString();
                showzq sa=new showzq();
                sa.execute("");


            }
        });


    }
    public class showzq extends AsyncTask<String,String,String> {

        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.0.35:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();

                String query= "INSERT INTO assignments (`subject`, `link`) VALUES ('"+subjecta +"','"+ link+"');";
                stmt.executeUpdate(query);

            } catch (Exception e)
            {
                System.out.println("TD Cant Connect To Database: " + e);
                String sa="CANT UPLOAD DATABASE ERROR "+e;
                Toast.makeText(uploadassign.this, sa, Toast.LENGTH_SHORT).show();

            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String sa="ASSIGNMENT UPLOADED";
            Toast.makeText(uploadassign.this, sa, Toast.LENGTH_SHORT).show();
            Intent iaw = new Intent( uploadassign.this, TeacherDashboard.class);
            iaw.putExtra("reg","OK");
            startActivity(iaw);

        }
    }


    }