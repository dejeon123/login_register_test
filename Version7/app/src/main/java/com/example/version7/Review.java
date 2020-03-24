package com.example.version7;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.barteksc.pdfviewer.PDFView;

public class Review extends Fragment {
    private int reviews;
    private int score;
    private String remarks;
    private ListView mReviewList;
    private PDFView reviewpdf;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.review, container, false);

        mReviewList = (ListView) v.findViewById(R.id.review_List);
        reviewpdf = (PDFView) v.findViewById(R.id.review_pdfViewer);


        String[] reviewListName ={"project document"};
        String[] reviewListCategory ={""};
        String[] reviewListAuthors ={"Dejeon Battick"};
        String[] reviewListDate ={"08/03/2020"};


        final MyAdapter myAdapter = new MyAdapter(getActivity(),reviewListName,reviewListAuthors,reviewListDate);

        mReviewList.setAdapter(myAdapter);


        mReviewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = new ReviewPdf();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });


        return v;
    }



    static class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String[] reviewListName;
        String[] reviewListCategory;
        String[] reviewListAuthors;
        String[] reviewListDate ;

        public MyAdapter(Context context,String reviewListName[],String reviewListAuthors[],String reviewListDAte[]) {
            super(context, R.layout.row, R.id.tv_article_name, reviewListName);
            this.context = context;
            this.reviewListName = reviewListName;
            this.reviewListAuthors = reviewListAuthors;
            this.reviewListDate = reviewListDAte;


        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            final TextView myTitle = row.findViewById(R.id.tv_article_name);
            TextView myAuthor = row.findViewById(R.id.tv_article_author);
            TextView mDAte =row.findViewById(R.id.tv_article_date);

            // now set our resources on views
            myTitle.setText(reviewListName[position]);
            myAuthor.setText(reviewListAuthors[position]);
            mDAte.setText(reviewListDate[position]);




            return row;

        }
    }

}
