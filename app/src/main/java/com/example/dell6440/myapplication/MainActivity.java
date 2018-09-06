package com.example.dell6440.myapplication;

import android.content.Intent;
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
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btn, btn2;
    EditText email, password;
    UserSessionManager session;

    public static String Email;
    String Password;
    public static String token;
    public static int CustomerId;

    //public static String UserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.place_order);
        btn2 = (Button) findViewById(R.id.button7);
        session = new UserSessionManager(getApplicationContext());

        // Globals globals = new Globals();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
           // login();
                Intent i = new Intent(MainActivity.this, Dashboard_Company.class);
                startActivity(i);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Company_register.class);
                startActivity(i);


            }
        });


    }

    private void login() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ApiEndPoints.LoginURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //   Toast.makeText(Inbox.this, " " + response, Toast.LENGTH_SHORT).show();


                JSONObject jsonObject = null;
                try {

                    jsonObject = new JSONObject(response);
                    token = jsonObject.optString("access_token");

                    Toast.makeText(MainActivity.this, " " + token, Toast.LENGTH_SHORT).show();
                    bearer();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //Intent i = new Intent(MainActivity.this, Dashboard_Company.class);
                //startActivity(i);


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

                Email = email.getText().toString();
                Password = password.getText().toString();
                // String grant = "password";
                params.put("grant_type", "password");
                params.put("username", Email);
                params.put("password", Password);

                return params;

            }
        };


        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(stringRequest);

    }


    public void bearer() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ApiEndPoints.GetDriverDetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                OneSignal.startInit(getApplicationContext())
                        .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                        .unsubscribeWhenNotificationsAreDisabled(true)
                        .init();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);

                    CustomerId = jsonObject.optInt("UserId");
                    Toast.makeText(MainActivity.this, " " + CustomerId, Toast.LENGTH_SHORT).show();

                    JSONObject tags = new JSONObject();
                    try {
                        tags.put("topic", "customer_" + CustomerId);
                        tags.put("topic", "customer");
                    } catch (JSONException e) {

                    }

                    OneSignal.sendTags(tags);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                /*try {

                    JSONObject person = new JSONObject();
                    String DriverId = person.getString("UserId");
                    Toast.makeText(Inbox.this, " " + DriverId, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
*/

                Intent i = new Intent(MainActivity.this, Dashboard_Company.class);
                // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
            // try {
            //   JSONArray jsonArray = response.getJSONArray("");

            // for (int i = 0; i < jsonArray.length(); i++) {
            //   JSONObject employee = jsonArray.getJSONObject(i);


            //  DriverId = employee.getInt("RoleId");
            //  String mail = employee.getString("mail");
            // Toast.makeText(Inbox.this, " " + DriverId, Toast.LENGTH_SHORT).show();
            //}
            //} catch (JSONException e) {
            //  e.printStackTrace();


            //    }
            //   }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, " " + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Username", Email);
                return params;

            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "bearer " + token);
                // params.put("Content-Type",response1);
                return params;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(stringRequest);
    }
}


/*
    RequestParams rp = new RequestParams();
                rp.add("username", email.getText().toString());
                        rp.add("password", password.getText().toString());

                        HttpUtils.post(Constants.ApiEndPoints.LoginURL, rp, new JsonHttpResponseHandler() {
@Override
public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        // If the response is JSONObject instead of expected JSONArray

        try {
        JSONObject serverResp = new JSONObject(response.toString());
        String abc = serverResp.getString("av");


        } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
        }

@Override
public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
        // Pull out the first event on the public timeline

        }
        });*/
