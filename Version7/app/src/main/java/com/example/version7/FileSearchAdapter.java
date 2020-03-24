package com.example.version7;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FileSearchAdapter extends RecyclerView.Adapter<FileSearchAdapter.FileViewHolder> {


    private FileItem[] listItems;
    private Context context;

    public FileSearchAdapter(FileItem[] listItems,Context context){
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.file_item,parent,false);

        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, final int position) {

        final FileItem listItem = listItems[position];

        holder.mTitle.setText(listItem.getDocument_title());
        holder.mAuthor.setText(listItem.getDocument_authors());
        //holder.mDate.setText(listItem.getDocument_reviews());
        //holder.mInfo.setText(listItem.getDocument_file());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,PdfOpener.class);
                intent.putExtra("title",position);
                context.startActivity(intent);


                Toast.makeText(view.getContext(),"click on item: "+listItem.getDocument_title(),Toast.LENGTH_LONG).show();
            }
        });

        //        pdfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = pdfListView.getItemAtPosition(i).toString();
//
//                Intent intent = new Intent(getActivity(),PdfOpener.class);
//                intent.putExtra("pdfFileName",item);
//                startActivity(intent);
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return listItems.length;
    }

    public class FileViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout linearLayout;
        private TextView mTitle, mAuthor, mDate, mInfo;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.tv_article_name);
            mAuthor = (TextView) itemView.findViewById(R.id.tv_article_author);
           // mDate = (TextView) itemView.findViewById(R.id.tv_article_date);
            //mInfo = (TextView) itemView.findViewById(R.id.tv_article_info);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.LinearLayout);
        }
    }
}
