package com.example.version7;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class UploadDialog extends AppCompatDialogFragment {

    TextView mScore, mReviews, mStatus;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.view_upload_dialog, null);

        builder.setView(view)
                .setTitle(" My Uploads")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        mScore = (TextView) view.findViewById(R.id.re_score);
        mReviews = (TextView) view.findViewById(R.id.num_of_reviews);
        mStatus = (TextView) view.findViewById(R.id.status);

        mStatus.setText("Document has been reviewed: "+ FileItem.getDocument_reviews() +" times" + " With a total score of: "+ FileItem.getDocument_score() );
        String score = String.valueOf(FileItem.getDocument_score());
        mScore.setText(score);
        String reviews = String.valueOf(FileItem.getDocument_reviews());
        mReviews.setText(reviews);


    return builder.create() ;
    }

    public interface  uploadDialogListener{
        void uploadApply(String score, String Remarks);
        // do what ever with what i got from the review dialog
        //create a new pdf review file

    }
}
