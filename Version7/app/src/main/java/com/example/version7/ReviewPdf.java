package com.example.version7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.github.barteksc.pdfviewer.PDFView;

public class ReviewPdf extends Fragment implements ReviewFormDialog.ReviewDialogListener {
    Button mReview;
    private PDFView reviewpdf;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.review_pdf_view, container, false);

        mReview =(Button) v.findViewById(R.id.btn_review);
        reviewpdf = (PDFView) v.findViewById(R.id.review_pdfViewer);


        reviewpdf.fromAsset("project document.pdf").load();

        mReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewform();
            }
        });


        return v;
    }
    public void reviewform(){
        ReviewFormDialog reviewFormDialog = new ReviewFormDialog();
        reviewFormDialog.show(getFragmentManager(),"Review Dialog");

    }


    @Override
    public void apply(String score, String Remarks) {
        // do what ever with what i got from the review dialog
        //create a new pdf review file
    }
}
