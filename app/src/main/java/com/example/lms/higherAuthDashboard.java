package com.example.lms;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class higherAuthDashboard extends AppCompatActivity {
    ArrayList<String> sturegno = new ArrayList<>();
    ArrayList<String> end = new ArrayList<>();
    ArrayList<String> details = new ArrayList<>();
    ListView myListView;
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> arrayAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_auth_dashboard);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        myListView = findViewById(R.id.notilist);
        shoza x=new shoza();
        x.execute("");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, end)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View row = super.getView(position, convertView, parent);
                int xa=position;
                if(xa%2!=0)
                {
                    // do something change color
               //     row.setBackgroundColor (Color.rgb(255,82,82)); // some color
                }
                else
                {
                    // default state
                    row.setBackgroundColor (Color.rgb(79,195,247)); // default coloe
                }
                return row;
            }
        };


    }

    public class shoza extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {


            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.0.35:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();


                String query = "SELECT details FROM `notifications` ";
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
                        System.out.println(g[i]);
                        details.add(g[i]);

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


                String query = "SELECT regno FROM `notifications` ";
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

                    sturegno.add(g[i]);

                    }

                }
            } catch (Exception e) {
                System.out.println("TD Cant Connect To Database: " + e);
            }
            return "";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            int xa= details.size();
                for (int i = 0; i < xa; i++) {

                    end.add(sturegno.get(i));
                    end.add(details.get(i));

                }
            myListView.setAdapter(arrayAdapter);


           //


        }
    }

        }