package com.example.version7;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class FragmentProfile extends Fragment {
    TextView mName, mEmail, mCategory;
    LinearLayout mMyUpload, mlogOut, mView_chat, mView_favorites, mView_download, mUpload,mEditProfile, mReview;
    ImageView profilepic;
    SessionManager sessionManager;
    //ArrayList<Profile> person = new ArrayList();


    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_profile, container,false);


        mName = (TextView) v.findViewById(R.id.name);
        mEmail = (TextView) v.findViewById(R.id.email);
        mCategory = (TextView) v.findViewById(R.id.category);
        mView_favorites = (LinearLayout) v.findViewById(R.id.view_favorites);
        mView_chat = (LinearLayout) v.findViewById(R.id.view_chat);
        mView_download = (LinearLayout) v.findViewById(R.id.view_download);
        profilepic = (ImageView) v.findViewById(R.id.imageView);
        mEditProfile =(LinearLayout) v.findViewById(R.id.editProfile);
        mReview = (LinearLayout) v.findViewById(R.id.layout_review);
        mUpload = (LinearLayout) v.findViewById(R.id.upload_doc);
        mMyUpload = (LinearLayout) v.findViewById(R.id.view_my_Uploads);

        sessionManager = new SessionManager(getContext());


//
//        Profile.setPassword("1234");
//        Profile.setRole("Student");
//        Profile.setPeer("YES");
//        Profile.setName("dejeon");
//        Profile.setEmail("dejii");
//        Profile.setUser_name("dejeon");
//        Profile.setCategory("Computer Science");

        if (Profile.getPeer().equals("NO")){
            mReview.setVisibility(View.GONE);
        }

        mMyUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new MyUploads();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });

        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Upload();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });

        mReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Review();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();

            }
        });



        profilepic.setImageResource(R.drawable.deji);


        //sessionManager = new SessionManager(getContext());

        mlogOut = (LinearLayout)v.findViewById(R.id.log_out);
        mlogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        // Displaying the user details on the screen
        mName.setText(Profile.getName());
        mEmail.setText(Profile.getEmail());
        mCategory.setText(Profile.getCategory());

        mView_favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new Favorites();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });

        mView_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Downloaded();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();

            }
        });

        mView_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(getActivity(), Chat.class);
                startActivity(chatIntent);
            }
        });

        mEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new EditProfile();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });


        return v ;

    }

    private void logoutUser() {
        //sessionManager.setLogin(false);

        // Launching the login activity
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
    }


}
