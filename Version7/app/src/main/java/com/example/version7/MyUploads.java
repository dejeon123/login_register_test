package com.example.version7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyUploads extends Fragment implements UploadDialog.uploadDialogListener {

    private ListView myUploadList;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_uploads, container, false);

        myUploadList = (ListView) v.findViewById(R.id.my_upload_list);

        if (Profile.getUser_name().equals("dejeon")) {
            myUploadList.setVisibility(View.VISIBLE);
        }

        String[] myUploadListName ={"project document"};
        String[] reviewListCategory ={""};
        String[] reviewListAuthors ={"Dejeon Battick"};
        String[] reviewListDate ={"08/03/2020"};

        final Review.MyAdapter myAdapter = new Review.MyAdapter(getActivity(),myUploadListName,reviewListAuthors,reviewListDate);

        myUploadList.setAdapter(myAdapter);

        myUploadList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // call a dialog to view it
                myUploadDialog();

            }
        });


        return v;
    }

    private void myUploadDialog() {

        UploadDialog uploadDialog = new UploadDialog();
        uploadDialog.show(getFragmentManager(),"My Upload");
    }


    @Override
    public void uploadApply(String score, String Remarks) {

    }
}
