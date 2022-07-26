package com.example.lms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class editattendance extends AppCompatActivity {
    private SeekBar sBar;
    int sbval = 0;
    int t=0;
    String subject,rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editattendance);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button confirm = findViewById(R.id.egdone);
        TextView reg = findViewById(R.id.editgtrn);

        TextView rx = findViewById(R.id.rnogt);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            subject = extras.getString("reg");

        }
        sBar = (SeekBar) findViewById(R.id.egseekbar);

        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pval=pval*10;
                reg.setText(pval+"%");
                sbval = pval;
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv=rx.getText().toString();

                shoda s = new shoda();
                s.execute("");

            }
        });
    }

    public class shoda extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {


            try {

                //connection
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.0.35:3306/lms", "max", "max123");
                Statement st = con.createStatement();

                Statement stmt = con.createStatement();

               String x= "UPDATE `coursesattendance` SET  `attendance` = "+"'"+sbval  +"'"+" WHERE regno = "+"'"+rv+"'"+" AND courseid ="+"'"+subject+"'"+";";

                stmt.executeUpdate (x);
                System.out.println("UPDATEDDD SUCESSFULLY "+ rv +" "+ subject);
                t=1;

            } catch (Exception e) {
                System.out.println(e);
            }
            return "";
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(t==1)
            {
                String sa="ATTENDANCE UPDATED";
                Toast.makeText(editattendance.this, sa, Toast.LENGTH_SHORT).show();
                Intent i = new Intent( editattendance.this, TeacherDashboard.class);
                i.putExtra("reg","OK");
                startActivity(i);
            }

        }
        }
}