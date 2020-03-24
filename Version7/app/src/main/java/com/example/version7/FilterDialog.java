package com.example.version7;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class FilterDialog extends AppCompatDialogFragment {
    private Spinner mCategory;
    private String selectedCategory;
    private FilterDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.filter, null);

        builder.setView(view)
                .setTitle("Filter dialog")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //something to filter the search
                        String category = selectedCategory;
                        listener.filterApply(category);
                    }
                });


        mCategory = (Spinner) view.findViewById(R.id.filter_category);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.category, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategory.setAdapter(adapter2);
        mCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adapterView.getItemAtPosition(i).equals("Choose a Category")){
                    //do nothing
                }
                else {
                    selectedCategory = adapterView.getItemAtPosition(i).toString();
                    //FileItem.setDocument_reviews(score);
                    Toast.makeText(adapterView.getContext(), "Selected: " + selectedCategory, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (FilterDialogListener)getTargetFragment();
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString()+
                    "Must implement filterDialogListener");
        }
    }

    public interface  FilterDialogListener{
        void filterApply(String category);
        // do what ever with what i got from the review dialog
        //create a new pdf review file

    }
}
