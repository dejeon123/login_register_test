package com.example.version7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private static final String TAG = Register.class.getSimpleName();
    EditText mEmail;
    EditText mPassword;
    Button mSubmitLogin;
    TextView mRegister;
    //SessionManager session;
    EditText mUserName;
    TextView mInfo;
    private int counter =5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserName = (EditText) findViewById(R.id.submit_username);
        mPassword = (EditText) findViewById(R.id.submit_password);
        mSubmitLogin = (Button) findViewById(R.id.submit_login);
        mRegister = (TextView) findViewById(R.id.register1);
        mInfo = (TextView) findViewById(R.id.tvinfo);

        mInfo.setText("No of attempts remaining: 5" );

        mUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    Log.i(ChatMain.TAG, "onTextChanged: ABLED");
                } else {
                    Log.i(ChatMain.TAG, "onTextChanged: DISABLED");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

//        session = new SessionManager(getApplicationContext());
//
//        if (session.isLoggedIn()) {
//            // User is already logged in. Take him to main activity
//            Intent intent = new Intent(Login.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });


        mSubmitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUserName.getText().toString();
                String password = mPassword.getText().toString();

                // Check for empty data in the form
                if (username.trim().length() > 0 && password.trim().length() > 0) {
                    // login user
//                    Intent intent = new Intent(Login.this,
//                            MainActivity.class);
//                    startActivity(intent);
                    checkLogin1(username, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });



    }

    private void checkLogin1(final String username, final String password) {

        if (username.equals("dejeon3455cj")){
            Profile.setUser_name(username);
            Profile.setName("dejeon Battick");
            Profile.setEmail("dejeon@gmail.com");
            Profile.setPassword(password);
            Profile.setPeer("NO");
            Profile.setRole("Tutor");
            Profile.setCategory("Computer Science");
            Profile.setPhoto(R.drawable.deji);
            Intent intent = new Intent(Login.this,
                    MainActivity.class);
            startActivity(intent);

        }else if (username.equals(Profile.getUser_name()) && password.equals(Profile.getPassword())){
            // login user
                    Intent intent = new Intent(Login.this,
                            MainActivity.class);
                    startActivity(intent);

        }else{

            counter--;
            mInfo.setText("No of attempts remaining: " + String.valueOf(counter));
            if (counter == 0){
                mSubmitLogin.setEnabled(false);
            }
            Toast.makeText(getApplicationContext(),
                    "User name Password do not match!", Toast.LENGTH_LONG)
                    .show();
//            Intent intent = new Intent(Login.this,
//                    MainActivity.class);
//            startActivity(intent);

        }


    }

    private void checkLogin(final String username, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        //to check
        String url = Constants.URL_LOGIN + username + "/" + password+"/login/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());



                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.optBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session

                        //change to email
                        Profile.setName(username);
                        Profile.setEmail((String) jObj.get("login_email"));
                        //session.setLogin(true);
                        // Launch main activity
                        Intent intent = new Intent(Login.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error");
                        Toast.makeText(getApplicationContext(),
                                "Error: " +errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    mSubmitLogin.setVisibility(View.VISIBLE);
//                    Intent intent = new Intent(Login.this,
//                            Main2Activity.class);
//                    startActivity(intent);
                    Toast.makeText(Login.this,"Error: Invalid Credentials Try Again ", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
