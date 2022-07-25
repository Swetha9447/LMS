package com.example.lms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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


public class LoginActivity extends AppCompatActivity {
    String g[]=new String[2];
    String regnod,passwd;
    public String authregno;
int auth=0,x=0,y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //full screen
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView regno=findViewById(R.id.loginreg);
        TextView passw=findViewById(R.id.loginpass);

        TextView btn = findViewById(R.id.Signup2);
        Button login= findViewById(R.id.loginbtnx);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling async function to connect to database
                regnod=regno.getText().toString().toLowerCase();
                passwd=passw.getText().toString().toLowerCase();
                System.out.println("CLICKED");
            show s=new show();
            s.execute("");

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 startActivity(new Intent(LoginActivity.this,RegisterActivity.class ));
            }
        });

    }

public class show extends AsyncTask<String,String,String>
{

    @Override
    protected String doInBackground(String... strings) {


        try {

        //connection
            Class.forName("com.mysql.jdbc.Driver");

            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://192.168.0.35:3306/lms","max","max123");
            Statement st = con.createStatement();

            Statement stmt = con.createStatement();

            //stmt.executeUpdate (insertion)
            String query= "SELECT regno,password FROM `registration` WHERE regno="+"'"+regnod+"'"+ ";";
            System.out.println("CHECKED");

            ResultSet rs=stmt.executeQuery(query);
            ResultSet rs1=rs;
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

            int columnsNumber = rsmd.getColumnCount();
            String gx[]=new String[40];
            int o=0;
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(" ");//prints columns name
                    String columnValue = rs.getString(i);

                    //System.out.print(rsmd.getColumnName(i) );
                    String columnValue1 = rs1.getString(i);

                    //System.out.print(columnValue1 );

                    gx[o]=columnValue1;
                    o++;
                }


            }
            System.out.println("\n");

            for(int i=0; i< g.length ; i++)
            {

                     if(gx[i].equals(passwd))
                    {

                        auth=2;
                    }
            }

            if(auth==2)
            {

                authregno=regnod;


                //Toast Welcome
                String value="Hello world";


//                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                Log.i("mylog","login Success  ");
                System.out.println("Login Success");
            }
            else
            {

//                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                Log.i("mylog","Login Failed Invalid Credentials ");
                System.out.println("Login failed");
            }


        }
        catch(Exception e){

            //TOAST INVALID DATA

            System.out.println("Invalid Data:  "+e.toString());

        }

        return "0";
    }

    @Override

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (auth == 2) {
            Intent i = new Intent(LoginActivity.this, DashboardSTDActivity.class);
            i.putExtra("reg", authregno);
            startActivity(i);
            auth=0;
        }
    }
}

}