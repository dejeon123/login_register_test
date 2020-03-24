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
import java.util.Arrays;
import java.util.List;

public class Favorites extends Fragment {
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.favorites, container, false);


        listView = (ListView)v.findViewById(R.id.favList);

        String[] pdfFiles = {"Knowledge sharing","Pdq Inventory","Software Engineering Economics",
                "The biology of interleukin","Biology Data Book","Essentials of geology"};
        String[] author = {"Wu Liming", "PDQ Team","Barry W. Boehm", "T. Kishimoto",
                "William F. Morris","Fredrick K.Lutgens"};



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