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

public class notifyhigher extends AppCompatActivity {
String issue,regino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifyhigher);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView r=findViewById(R.id.regnoti);
        TextView i=findViewById(R.id.issuenoti);
        Button s=findViewById(R.id.sendnoti);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                issue=i.getText().toString();
                regino=r.getText().toString();

                showzxz as=new showzxz();
                as.execute("");






                    }

        });

    }
    public class showzxz extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.0.35:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();


                String query = "INSERT INTO notifications (regno, details) VALUES "+"('"+regino+"','" +issue +"');";
                stmt.executeUpdate(query);

            }
            catch (Exception e) {
                System.out.println("TD Cant Connect To Database: " + e);
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String sa="Notification Sent!";
            Toast.makeText(notifyhigher.this, sa, Toast.LENGTH_SHORT).show();
            Intent ixa = new Intent( notifyhigher.this, TeacherDashboard.class);
            ixa.putExtra("reg","OK");
            startActivity(ixa);
        }
    }

}