package com.example.lms;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class MedissueActivity extends AppCompatActivity {
    String de,regnog;
    ArrayList<String> depression = new ArrayList<>();
    ArrayList<String> anxiety = new ArrayList<>();
    ArrayList<String> periods = new ArrayList<>();
    ArrayList<String> headache = new ArrayList<>();
    TextView mi;

    String welcomename ;
    String val ;
    TextView namea;

    String rollno ;
    String val2 ;
    TextView regnox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medissue);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        depression.add("If you’re up for exercise, consider a walk around the block");
        depression.add("Know that today isn’t indicative of tomorrow");
        depression.add("Do the opposite of what the ‘depression voice’ suggests");
        depression.add("All goals are worthy of recognition, and all successes are worthy of celebration");

        anxiety.add("Develop a routine so that you're physically active most days of the week. Exercise is a powerful stress reducer");
        anxiety.add("Alcohol substances can cause or worsen anxiety. If you can't quit on your own, see your health care provider or find a support group to help you. ");
        anxiety.add("Quit smoking, and cut back or quit drinking caffeinated beverages ");
        anxiety.add("Use stress management and relaxation techniques");

        periods.add("Heating pads can help reduce common period symptoms, such as pain and cramping ");
        periods.add("The body needs energy to restore the blood that it loses during a heavy menstrual flow. ");
        periods.add("No research has shown that dietary changes alone can reduce heavy menstrual bleeding ");
        periods.add("A person with a heavy period is losing a lot of blood, and with it, a lot of iron. ");

        headache.add("Inadequate hydration may lead you to develop a headache.");
        headache.add("Magnesium is an important mineral necessary for countless functions in the body");
        headache.add("Sleep deprivation can be detrimental to your health in many ways, and may even cause headaches in some people.");
        headache.add("Using a cold compress may help reduce your headache symptoms.");

         namea=findViewById(R.id.namemi);

        regnox=findViewById(R.id.regno1);
        mi=findViewById(R.id.displayhelp);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            regnog = extras.getString("reg");

        }
        sh f=new sh();
        f.execute("");
    }

    public class sh extends AsyncTask<String, String, String> {

        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            namea.setText(welcomename);

            regnox.setText(regnog);
            Random r=new Random();
            int rand= r.nextInt(4);
            if (de.toLowerCase().equals("prolonged periods"))
            {
                mi.setText(periods.get(rand));
            }
            else if (de.toLowerCase().equals("anxiety"))
            {
                mi.setText(anxiety.get(rand));
            }
            else if (de.toLowerCase().equals("depression"))
            {
                mi.setText(depression.get(rand));
            }
            else if (de.toLowerCase().equals("severe headaches"))
            {
                mi.setText(headache.get(rand));
            }
        }
        @Override

        protected String doInBackground(String... strings) {


            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.0.35:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();


                String query = "SELECT name FROM `registration` WHERE regno=" + "'" + regnog + "'" + ";";
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


                String query = "SELECT regno FROM `registration` WHERE regno=" + "'" + regnog + "'" + ";";
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

            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.0.35:3306/lms", "max", "max123");
                Statement stmt = con.createStatement();


                String query = "SELECT medical_issue FROM `registration` WHERE regno= '"+regnog+"';";
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
                        System.out.println("GOT NOTHING "+ g[i]);
                        de=(g[i]);

                    }

                }
            } catch (Exception e) {
                System.out.println("TD Cant Connect To Database: " + e);
            }
            return "";
        }



    }
}