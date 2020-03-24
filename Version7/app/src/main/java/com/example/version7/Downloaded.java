package com.example.version7;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Downloaded extends Fragment {

    ArrayList<String> downloaded;
    ArrayAdapter<String> adapter;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.downloaded, container, false);

        listView = (ListView) v.findViewById(R.id.downloadedlistV);


        String[] pdfFiles = {"Biology Data Book","gant chart", "Knowledge sharing",
                "Software Engineering Economics",};
        String[] author = {"Phillip L. Altman","pdq team","Wu Liming","Barry W. Boehm"};



        final FragmentSearch.MyAdapter myAdapter = new FragmentSearch.MyAdapter(getActivity(),pdfFiles,author);

        listView.setAdapter(myAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = listView.getItemAtPosition(i).toString();

                Intent intent = new Intent(getActivity(),PdfOpener.class);
                intent.putExtra("pdfFileName",item);
                startActivity(intent);

            }
        });

        return v;
    }

}
