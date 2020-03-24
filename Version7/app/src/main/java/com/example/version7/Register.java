package com.example.version7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private static final String TAG = Register.class.getSimpleName();
    private EditText mUserName;
    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Spinner mCategory;
    private Button mRegister;
    private TextView mLogin;
    private Spinner mRole;
    private Spinner mPeer;
    private SessionManager session;
    private String selectedRole;
    private String selectedpeer;
    private String selectedcategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUserName = (EditText) findViewById(R.id.user_name);
        mName = (EditText) findViewById(R.id.name);
        mEmail = (EditText) findViewById(R.id.email_address);
        mPassword = (EditText) findViewById(R.id.reg_password);
        mConfirmPassword = (EditText) findViewById(R.id.reg_confirm_password);
        mCategory =(Spinner) findViewById(R.id.rCategoty);
        mRegister = (Button) findViewById(R.id.submit_register);
        mLogin = (TextView) findViewById(R.id.login_page);
        mPeer = (Spinner) findViewById(R.id.peer_review);
        mRole = (Spinner) findViewById(R.id.role);


        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(Register.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Register.this, Login.class);
                startActivity(loginIntent);
                finish();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mRole.setAdapter(adapter);
        mRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String role = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(adapterView.getContext(), role, Toast.LENGTH_SHORT).show();
                    selectedRole = role;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.peer, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mPeer.setAdapter(adapter1);
        mPeer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String peer = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(adapterView.getContext(), peer, Toast.LENGTH_SHORT).show();
                    selectedpeer = peer;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mCategory.setAdapter(adapter2);
        mCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String category = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(adapterView.getContext(), category, Toast.LENGTH_SHORT).show();
                    selectedcategory = category;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mName.getText().toString();
                String user_name = mUserName.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String conPassword = mConfirmPassword.getText().toString();
                String role = selectedRole;
                String peer = selectedpeer;
                String category = selectedcategory;


                if (!user_name.isEmpty() && !name.isEmpty() && !email.isEmpty()
                        && !password.isEmpty() && password.equals(conPassword)
                        && !role.equals("Select Role") && !peer.equals("Peer reviewer?")
                        && !category.equals("Choose a Category")) {
                    registerUser1(user_name, email, password,name, role, peer, category);
                } else if (!password.equals(conPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Passwords do not match!", Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please fill out all fields!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

/*        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mCategory.setAdapter(adapter);
        mCategory.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.preferences, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mPreference.setAdapter(adapter1);
        mPreference.setOnItemSelectedListener(this);*/

    }
    private void registerUser1(final String user_name, final String email,
                             final String password, final String name,
                               final String role, final String peer, final String category) {

        ArrayList<Profile> person= new ArrayList();
        Profile xx = new Profile(name,email,user_name,role,peer,R.drawable.deji,category,password);
//        xx.setUser_name(user_name);
//        xx.setName(name);
//        xx.setEmail(email);
//        xx.setPassword(password);
//        xx.setRole(role);
//        xx.setCategory(category);
//        xx.setPeer(peer);
        //person=(name,email,user_name,role,peer,R.drawable.deji,category,password);
        person.add(xx);

        Intent intent = new Intent(Register.this,
                Login.class);
        startActivity(intent);
        finish();
        Toast.makeText(getApplicationContext(), "registered", Toast.LENGTH_SHORT).show();


    }

    private void registerUser(final String user_name, final String email,
                              final String password, final String name, final String role, final String peer) {

        String regist = Constants.URL_REGISTER + user_name + "/" + password + "/" + email + "/" + role + "/" + peer + "/" + name + "/register/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                regist, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.optBoolean("error");
                    if (!error) {

                        Profile.setName(name);
                        Profile.setUser_name(user_name);
                        Profile.setRole(role);
                        Profile.setPeer(peer);
                        Profile.setEmail((String) jObj.get("email"));
                        session.setLogin(true);
                        // Launch main activity
                        Intent intent = new Intent(Register.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "registered", Toast.LENGTH_SHORT).show();
                    } else {
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: Invalid Credentials Try Again");
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "register");
                params.put("username", user_name);
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("role", role);
                params.put("peer", peer);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}