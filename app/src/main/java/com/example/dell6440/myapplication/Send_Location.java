package com.example.dell6440.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Send_Location extends AppCompatActivity {

    Button logout, send;
    String Pick, Drop, Load, Commodity, Weight, Required_date;
    EditText pick, drop, load, commodity, weight, required_date;
    String URL_ORDER = "ADSD";
    //  UserSessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send__location);
        logout = (Button) findViewById(R.id.logout);
        //
        send = (Button) findViewById(R.id.send);
        pick = (EditText) findViewById(R.id.pick_location);
        drop = (EditText) findViewById(R.id.drop_location);
        load = (EditText) findViewById(R.id.load_type);
        commodity = (EditText) findViewById(R.id.commodity);
        weight = (EditText) findViewById(R.id.weight);
        required_date = (EditText) findViewById(R.id.Required_Date);

//        session = new UserSessionManager(getApplicationContext());

        //  if(session.checkLogin())
        //    finish();


       /* logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


    //              session.logoutUser();
      //          finish();

                //Intent i = new Intent(Send_Location.this, MainActivity.class);
                //startActivity(i);
            }
        });



*/
       send.setOnClickListener(new View.OnClickListener() {
                  @Override
                public void onClick(View v) {
                  order_place();
            }
        });
        }


    public void order_place() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ApiEndPoints.Place_Order, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //   Toast.makeText(Inbox.this, " " + response, Toast.LENGTH_SHORT).show();


                Toast.makeText(Send_Location.this, "ABC " + response, Toast.LENGTH_SHORT).show();


//                Intent i = new Intent(Inbox.this, Dashboard.class);
                // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //   startActivity(i);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //  Toast.makeText(Inbox.this, " " + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                // String email_1 = Email;
                Pick = pick.getText().toString();
                Drop = drop.getText().toString();
                Commodity = commodity.getText().toString();
                Weight = weight.getText().toString();
                Load = load.getText().toString();
                Required_date = required_date.getText().toString();

                params.put("CustomerId", String.valueOf(MainActivity.CustomerId));
                params.put("StatusId", "1");
                params.put("PickupLocation", Pick);
                params.put("DropoffLocation", Drop);
                params.put("PickupLatitude", "24.9203508");
                params.put("PickupLongitude", "67.1009618");
                params.put("DropoffLatitude", "24.823889");
                params.put("DropoffLongitude", "67.0333673");
                params.put("Commodity", Commodity);
                params.put("Weight", Weight);
                params.put("LoadType", Load);
                params.put("ContactNumber", Required_date);
                params.put("UserId", String.valueOf(MainActivity.CustomerId));
                return params;

            }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                // headers.put("Content-Type", "application/json");
                headers.put("Authorization", "bearer " + MainActivity.token);
                //    headers.put("Authorization", "bearer FqbzQGy3M01-dNXjDUbBSfp8vB70xBI1E7BG1pKnDVOvKSoYizNsmgXkS8Qh6vxWyGfxXQeb8wcVkyU2KaLj5dsBZX3yHgWpikSrqoYBHc-kM263xWxYNaYnm9Rcmc72io4bAjw9sF67My7gFO2ETSnHCn2BYL9nWlfM6g_LfnHHnQ_WHVW1WQaOFimAI0j0tN3sZcCbBxXKiJ-Q3ShkhmtXw1Bta9E2qxzwKbDyrW6vNsc65jQC3BA_FBuig7an");
                return headers;
            }

        };


        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(stringRequest);


    }
}
