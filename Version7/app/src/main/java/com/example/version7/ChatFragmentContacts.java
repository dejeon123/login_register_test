package com.example.version7;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChatFragmentContacts extends Fragment {

    private Button mContact;
    LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contactlist, container, false);

        linearLayout = (LinearLayout) v.findViewById(R.id.layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),
                        ChatMain.class);
                startActivity(intent);
            }
        });
/*
        mContact = (Button) v.findViewById(R.id.trial);
        mContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),
                        MainActivity.class);
                startActivity(intent);
            }
        });
*/

        return v;
    }


}
