package com.example.version7;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ReviewFormDialog extends AppCompatDialogFragment {
    private Spinner mScore;
    private String selectedscore;
    private EditText mRemarks;
    private ReviewDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.review_form,null);

        builder.setView(view)
                .setTitle("Review")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Submit Review", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String score = selectedscore;
                        String remarks = mRemarks.getText().toString();
                        String status;
//                        listener.apply(score,remarks);

                        int mscore = Integer.parseInt(selectedscore);
                        mscore = FileItem.getDocument_score() + mscore;
                        FileItem.setDocument_score(mscore);

                        int reviews = FileItem.getDocument_reviews() + 1;
                        FileItem.setDocument_reviews(reviews);

                        Toast.makeText(getContext(),"score: " + FileItem.getDocument_score() + " reviews: " + FileItem.getDocument_reviews() ,Toast.LENGTH_SHORT).show();
                    }
                });


        mScore = (Spinner) view.findViewById(R.id.review_score);
        mRemarks = (EditText) view.findViewById(R.id.review_remarks);

        ArrayAdapter<CharSequence> scoreAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.score, android.R.layout.simple_spinner_item);
        scoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mScore.setAdapter(scoreAdapter);

        mScore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Choose Review Score")){
                    //do nothing
                }
                else {
                    selectedscore = adapterView.getItemAtPosition(i).toString();
                    //FileItem.setDocument_reviews(score);
                    Toast.makeText(adapterView.getContext(), "Selected: " + selectedscore, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return builder.create();
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            listener = (ReviewDialogListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() +
//                    " Must implement ReviewDialogListener");
//        }
//    }

    public interface  ReviewDialogListener{
        void apply(String score, String Remarks);
        // do what ever with what i got from the review dialog
        //create a new pdf review file

    }
}
