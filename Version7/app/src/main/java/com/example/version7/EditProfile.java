package com.example.version7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class EditProfile extends Fragment {

    private EditText mName,mUserName,mPassword,mConfirmPassword,mEmail;
    private Spinner mRole,mPeer,mCategory;
    private Button mConfirm;
    private String selectedRole;
    private String selectedpeer;
    private String selectedcategory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.edit_profile, container, false);

        mName = (EditText) v.findViewById(R.id.edit_name);
        mUserName = (EditText) v.findViewById(R.id.edit_user_name);
        mEmail = (EditText) v.findViewById(R.id.edit_email_address);
        mPassword = (EditText) v.findViewById(R.id.edit_password);
        mConfirmPassword = (EditText) v.findViewById(R.id.edit_confirm_password);
        mRole = (Spinner) v.findViewById(R.id.edit_role);
        mPeer = (Spinner) v.findViewById(R.id.edit_peer_review);
        mCategory = (Spinner) v.findViewById(R.id.edit_categoty);
        mConfirm = (Button) v.findViewById(R.id.confirm);

        mName.setText(Profile.getName());
        mUserName.setText(Profile.getUser_name());
        mEmail.setText(Profile.getEmail());


        //something to set the password;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.role, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.peer, android.R.layout.simple_spinner_item);
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

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategory.setAdapter(adapter2);
        mCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Choose a Category")){
                    //do nothing
                }
                else {
                    String category = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(adapterView.getContext(), category, Toast.LENGTH_SHORT).show();
                    selectedcategory = category;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
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
                Fragment fragment = new FragmentProfile();

                if (!user_name.isEmpty() && !name.isEmpty() && !email.isEmpty() && !password.isEmpty() && password.equals(conPassword)) {
                    //if everything is okay
                    Profile.setName(name);
                    Profile.setUser_name(user_name);
                    Profile.setEmail(email);
                    Profile.setRole(role);
                    Profile.setPeer(peer);
                    Profile.setCategory(category);

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.commit();


                } else if (!password.equals(conPassword)) {
                    Toast.makeText(getContext(),
                            "Passwords do not match!", Toast.LENGTH_LONG)
                            .show();
                    mPassword.setBackgroundColor(getResources().getColor(R.color.error));
                    mConfirmPassword.setBackgroundColor(getResources().getColor(R.color.error));
                } else {
                    Toast.makeText(getContext(),
                            "Please enter missing details!", Toast.LENGTH_LONG)
                            .show();
                }


            }
        });


        return v;
    }

}
