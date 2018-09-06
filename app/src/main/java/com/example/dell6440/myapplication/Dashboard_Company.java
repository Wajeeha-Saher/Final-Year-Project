package com.example.dell6440.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard_Company extends AppCompatActivity {
    Button btn1,btn2,logout;
    UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard__company);




        btn1 = (Button) findViewById(R.id.place_order);
        btn2 = (Button) findViewById(R.id.send);
        logout = (Button) findViewById(R.id.logout);
        //session = new UserSessionManager(getApplicationContext());
      //  if(session.checkLogin())
        //    finish();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard_Company.this, Send_Location.class);
                startActivity(i);


            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard_Company.this, review.class);
                startActivity(i);


            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           //     session.logoutUser();
             //   finish();

               // Intent i = new Intent(Dashboard_Company.this, MainActivity.class);
                //startActivity(i);


            }
        });

    }
}
