package com.example.version7;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentSearch extends Fragment implements FilterDialog.FilterDialogListener {


    private static final String SEARCH_URL ="http://192.168.3.126/edushare/api/v1.0/search/p";
   // private RecyclerView recyclerView;
   // private FileSearchAdapter adapter;
    private List<FileItem> fileItemList;
    private String filterCategory = "";
    private ListView pdfListView;
    private SearchView searchView;
    ImageView mFilter;
//    private TextView textView;
    private Button download;
    private List<String> downloaded;
    TextView tv;
    private String [] pdfFiles;
    private String [] author;



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        mFilter = (ImageView) v.findViewById(R.id.filter);
        mFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFilterDialog();
            }
        });





        pdfListView = (ListView)v.findViewById(R.id.pdfList);

        pdfFiles = new String[]{"Green Chemistry","chemistry with electronic","chemical lifecycle","characterisation of chemicals in food",
                "Database Design","Database", "Gant Chart","Knowledge sharing","Pdq Inventory", "SDD Template","Monographs in Computer Science",
                "Fundamentals of software engineering", "Knowledge Management in Software Engineering", "Software Engineering Economics",
                "The biology of interleukin", "Quantitative Conservation Biology","Biology Data Book","Cell Adhesion in Vascular Biology",
                "Essentials of geology","geology of the arabian peninsula","Geology of Nevada"};
        author = new String[] {"Zhiyong Wang","james B.Foresman","Hans-Jorg Althaus, Roland Hischier","A.G Renwick", "Sunil Choenni",
                "Jose Caldeira Duarte","PDQ Team", "Wu Liming", "PDQ Team", "DBMSS",  "David Gries","Carlo Ghezzi", "Ioana Rus",
                "Barry W. Boehm", "T. Kishimoto", "William F. Morris", "Philip L. Altman","Rodger P. McEver", "Fredrick K.Lutgens",
                "R. W. Powers","John H. Stewart"};

        //Profile.setCategory("");
        tv = (TextView) v.findViewById(R.id.todelete);
        if (filterCategory.equals("Geology")){
            pdfFiles = new String[]{"dfg"};
            author = new  String[]{"fg"};

        }
        else if (FileItem.getDocument_reviews() >= 2 && Profile.getCategory().equals("Computer Science")){
            pdfFiles = new String[]{"project document","Database Design","Database", "Gant Chart","Knowledge sharing","Pdq Inventory", "SDD Template","Monographs in Computer Science",
                    "Fundamentals of software engineering", "Knowledge Management in Software Engineering", "Software Engineering Economics","The biology of interleukin", "Quantitative Conservation Biology","Biology Data Book","Cell Adhesion in Vascular Biology",
                    "Essentials of geology","geology of the arabian peninsula","Geology of Nevada","Green Chemistry","chemistry with electronic","chemical lifecycle","characterisation of chemicals in food",
            };
            author = new String[] {"Dejeon Battick","Fredrick K.Lutgens", "R. W. Powers","John H. Stewart","Zhiyong Wang","james B.Foresman","Hans-Jorg Althaus", "Roland Hischier","A.G Renwick", "Sunil Choenni",
                    "Jose Caldeira Duarte","PDQ Team", "Wu Liming", "PDQ Team", "DBMSS",  "David Gries","Carlo Ghezzi", "Ioana Rus",
                    "Barry W. Boehm","T. Kishimoto", "William F. Morris", "Philip L. Altman","Rodger P. McEver"};



        }else if (Profile.getCategory().equals("Biology")){

            pdfFiles = new String[]{"The biology of interleukin", "Quantitative Conservation Biology","Biology Data Book","Cell Adhesion in Vascular Biology",
                    "Essentials of geology","geology of the arabian peninsula","Geology of Nevada","Green Chemistry","chemistry with electronic","chemical lifecycle","characterisation of chemicals in food",
                    "Database Design","Database", "Gant Chart","Knowledge sharing","Pdq Inventory", "SDD Template","Monographs in Computer Science",
                    "Fundamentals of software engineering", "Knowledge Management in Software Engineering", "Software Engineering Economics"};
            author = new String[] { "T. Kishimoto", "William F. Morris", "Philip L. Altman","Rodger P. McEver", "Fredrick K.Lutgens",
                    "R. W. Powers","John H. Stewart","Zhiyong Wang","james B.Foresman","Hans-Jorg Althaus, Roland Hischier","A.G Renwick", "Sunil Choenni",
                    "Jose Caldeira Duarte","PDQ Team", "Wu Liming", "PDQ Team", "DBMSS",  "David Gries","Carlo Ghezzi", "Ioana Rus",
                    "Barry W. Boehm"};

        }else if (Profile.getCategory().equals("Computer Science")){
            pdfFiles = new String[]{"Database Design","Database", "Gant Chart","Knowledge sharing","Pdq Inventory", "SDD Template","Monographs in Computer Science",
                    "Fundamentals of software engineering", "Knowledge Management in Software Engineering", "Software Engineering Economics","The biology of interleukin", "Quantitative Conservation Biology","Biology Data Book","Cell Adhesion in Vascular Biology",
                    "Essentials of geology","geology of the arabian peninsula","Geology of Nevada","Green Chemistry","chemistry with electronic","chemical lifecycle","characterisation of chemicals in food",
                    };
            author = new String[] { "Fredrick K.Lutgens", "R. W. Powers","John H. Stewart","Zhiyong Wang","james B.Foresman","Hans-Jorg Althaus", "Roland Hischier","A.G Renwick", "Sunil Choenni",
                    "Jose Caldeira Duarte","PDQ Team", "Wu Liming", "PDQ Team", "DBMSS",  "David Gries","Carlo Ghezzi", "Ioana Rus",
                    "Barry W. Boehm","T. Kishimoto", "William F. Morris", "Philip L. Altman","Rodger P. McEver"};
        }else if (Profile.getCategory().equals("Geology")){
            pdfFiles = new String[]{"Essentials of geology","geology of the arabian peninsula","Geology of Nevada","Green Chemistry","chemistry with electronic","chemical lifecycle","characterisation of chemicals in food",
                    "Database Design","Database", "Gant Chart","Knowledge sharing","Pdq Inventory", "SDD Template","Monographs in Computer Science",
                    "Fundamentals of software engineering", "Knowledge Management in Software Engineering", "Software Engineering Economics",
                    "The biology of interleukin", "Quantitative Conservation Biology","Biology Data Book","Cell Adhesion in Vascular Biology"};
            author = new String[] { "Fredrick K.Lutgens",
                    "R. W. Powers","John H. Stewart","Zhiyong Wang","james B.Foresman","Hans-Jorg Althaus, Roland Hischier","A.G Renwick", "Sunil Choenni",
                    "Jose Caldeira Duarte","PDQ Team", "Wu Liming", "PDQ Team", "DBMSS",  "David Gries","Carlo Ghezzi", "Ioana Rus",
                    "Barry W. Boehm", "T. Kishimoto", "William F. Morris", "Philip L. Altman","Rodger P. McEver"};

        }


        final MyAdapter myAdapter = new MyAdapter(getActivity(),pdfFiles,author);

        pdfListView.setAdapter(myAdapter);


        pdfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = pdfListView.getItemAtPosition(i).toString();

                Intent intent = new Intent(getActivity(),PdfOpener.class);
                intent.putExtra("pdfFileName",item);
                startActivity(intent);

            }
        });

        searchView = (SearchView) v.findViewById(R.id.fil_searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                myAdapter.notifyDataSetChanged();
                return false;
            }
        });

        return v;

    }

    private void openFilterDialog() {
        FilterDialog filterDialog = new FilterDialog();
        filterDialog.setTargetFragment(FragmentSearch.this,1);
        filterDialog.show(getFragmentManager(),"Filter Search");

    }

    @Override
    public void filterApply(String category) {
        //changes to be made to the search view
        filterCategory = category;
        tv.setText(category);

    }


    static class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String pdfFiles[];
        String author[];

        public MyAdapter(Context context,String pdfFiles[],String author[]) {
            super(context, R.layout.row, R.id.tv_article_name, pdfFiles);
            this.context = context;
            this.pdfFiles = pdfFiles;
            this.author = author;


        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            final TextView myTitle = row.findViewById(R.id.tv_article_name);
            TextView myDescription = row.findViewById(R.id.tv_article_author);

            // now set our resources on views
            myTitle.setText(pdfFiles[position]);
            myDescription.setText(author[position]);




            return row;

        }
    }
    }



//    private void loadRecyclerViewData(){
//        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage("Loading Data");
//        progressDialog.show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET,
//                SEARCH_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            //name of the list in shemar server
//                            JSONArray array = jsonObject.getJSONArray("Documents");
//
//                            for (int i =0; i<array.length(); i++){
//                                JSONObject object = array.getJSONObject(i);
//                                FileItem item = new FileItem(
//                                        object.getString("title"),
//                                        object.getString("author"),
//                                        object.getString("date"),
//                                        object.getString("info")
//                                );
//                                fileItemList.add(item);
//                            }
//
//                            Madapter = new FileSearchAdapter(fileItemList,getContext());
//                            recyclerView.setAdapter(Madapter);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//
//                    }
//                });
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        requestQueue.add(stringRequest);
//    }


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://edushare-services.herokuapp.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        SearAPI searAPI = retrofit.create(SearAPI.class);
//
//        Call<List<FileItem>> call = searAPI.getFileData();
//        call.enqueue(new Callback<List<FileItem>>() {
//            @Override
//            public void onResponse(Call<List<FileItem>> call, Response<List<FileItem>> response) {
//
//                if (!response.isSuccessful()){
//                    Toast.makeText(getContext(), "some problem: "+ response.code(), Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                fileItemList = response.body();
//                adapter = new FileSearchAdapter(fileItemList,getContext());
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<FileItem>> call, Throwable t) {
//
//                //textView.setText(t.getMessage());
//            }
//        });


//        final ArrayAdapter<String> adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,pdfFiles){
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                View view =super.getView(position, convertView, parent);
//
//                TextView myText = (TextView) view.findViewById(android.R.id.text1);
//                return view;
//            }
//        };



//        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_search);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        FileSearchAdapter adapter = new FileSearchAdapter(pdfFiles,getContext());
//        recyclerView.setAdapter(adapter);



