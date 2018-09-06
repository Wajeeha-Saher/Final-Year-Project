package com.example.dell6440.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Company_register extends AppCompatActivity {
 Button register;
    EditText companyName, CompanyLocation, ContactNo,email,password;
    String URL_Register = "http://112c0e03.ngrok.io/api/account/register";
    String Email,Password,name,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_register);


        register = (Button) findViewById(R.id.place_order);
        companyName = (EditText) findViewById(R.id.C_name);
        CompanyLocation= (EditText) findViewById(R.id.C_location);
        ContactNo = (EditText) findViewById(R.id.contact_no);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  signup();
                   }
                 });


                  }
                  private void signup(){
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Register, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Company_register.this, " "+ response, Toast.LENGTH_SHORT).show();
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Company_register.this, " "+error, Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        String Email = email.getText().toString();
                        String Password = password.getText().toString();
                        String FirstName = companyName.getText().toString();
                        String LastName = CompanyLocation.getText().toString();
                        params.put("Email", Email);
                        params.put("Password", Password);
                        params.put("FirstName", FirstName);
                        params.put("LastName", LastName);
                        return params;
                    }
                };
                RequestQueue rq = Volley.newRequestQueue(this);
                rq.add(stringRequest);
    }
}

