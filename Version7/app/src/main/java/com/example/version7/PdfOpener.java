package com.example.version7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfOpener extends AppCompatActivity {

    private ArrayList<String> downloaded;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> fav;
    private static final int MY_PERMISSION_REQUEST_STORAGE =1;
    Button button, favorites,review ;
    PDFView mPdfViewer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_opener);

        listView = (ListView) findViewById(R.id.downloadedlistV);

        downloaded = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(PdfOpener.this, android.R.layout.simple_list_item_1,
                downloaded);

       // listView.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_STORAGE);
            }else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_STORAGE);
            }
        }else {
        }


        button = (Button) findViewById(R.id.btn_download);
        favorites = (Button) findViewById(R.id.btn_fav);
        review = (Button) findViewById(R.id.btn_read);
        review.setVisibility(View.GONE);

        mPdfViewer = (PDFView) findViewById(R.id.pdfViewer);
        final String getItem = getIntent().getStringExtra("pdfFileName");

        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),getItem+" Added to Favourites",Toast.LENGTH_SHORT).show();
            }
        });

        if (getItem.equals("Green Chemistry")){
            mPdfViewer.fromAsset("Green Chemistry.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("Green Chemistry.pdf");
                }
            });
        }
        if (getItem.equals("chemistry with electronic")){
            mPdfViewer.fromAsset("chemistry with electronic.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("chemistry with electronic.pdf");
                }
            });
        }
        if (getItem.equals("chemical lifecycle")){
            mPdfViewer.fromAsset("chemical lifecycle.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("chemical lifecycle.pdf");
                }
            });
        }
        if (getItem.equals("characterisation of chemicals in food")){
            mPdfViewer.fromAsset("characterisation of chemicals in food.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("characterisation of chemicals in food.pdf");
                }
            });
        }

        if (getItem.equals("Database Design")){
            mPdfViewer.fromAsset("choenni.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("choenni.pdf");
                }
            });
        }

        if (getItem.equals("Database")){
            mPdfViewer.fromAsset("database.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("database.pdf");
                }
            });
        }

        if (getItem.equals("Gant Chart")){
            mPdfViewer.fromAsset("gant_chart.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("gant_chart.pdf");
                }
            });
        }

        if (getItem.equals("Knowledge sharing")){
            mPdfViewer.fromAsset("Jatit_Knowledgesharing.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("Jatit_Knowledgesharing.pdf");
                }
            });
        }

        if (getItem.equals("Pdq Inventory")){
            mPdfViewer.fromAsset("PDQ inventory MV.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("PDQ inventory MV.pdf");
                }
            });
        }

        if (getItem.equals("Project Document")){
            mPdfViewer.fromAsset("project document.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("project document.pdf");
                }
            });
        }

        if (getItem.equals("SDD Template")){
            mPdfViewer.fromAsset("sdd_template.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("sdd_template.pdf");
                }
            });
        }

        if (getItem.equals("Monographs in Computer Science")){
            mPdfViewer.fromAsset("Bookmatter_ParsingTechniques.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("Bookmatter_ParsingTechniques.pdf");
                }
            });
        }

        if (getItem.equals("Fundamentals of software engineering")){
            mPdfViewer.fromAsset("Fundamentals of software engineering.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("Fundamentals of software engineering.pdf");
                }
            });
        }

        if (getItem.equals("Knowledge Management in Software Engineering")){
            mPdfViewer.fromAsset("Knowledge Management in Software Engineering.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("Knowledge Management in Software Engineering.pdf");
                }
            });
        }

        if (getItem.equals("Software Engineering Economics")){
            mPdfViewer.fromAsset("Software Engineering Economics.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("Software Engineering Economics.pdf");
                }
            });
        }

        if (getItem.equals("The biology of interleukin")){
            mPdfViewer.fromAsset("The biology of interleukin.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("The biology of interleukin.pdf");
                }
            });
        }

        if (getItem.equals("Quantitative Conservation Biology")){
            mPdfViewer.fromAsset("Quantitative Conservation Biology.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("Quantitative Conservation Biology.pdf");
                }
            });
        }

        if (getItem.equals("Biology Data Book")){
            mPdfViewer.fromAsset("Biology Data Book.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("Biology Data Book.pdf");
                }
            });
        }

        if (getItem.equals("Cell Adhesion in Vascular Biology")){
            mPdfViewer.fromAsset("Cell Adhesion in Vascular Biology.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("Cell Adhesion in Vascular Biology.pdf");
                }
            });
        }

        if (getItem.equals("Essentials of geology")){
            mPdfViewer.fromAsset("Essentials of geology.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("Essentials of geology.pdf");
                }
            });
        }

        if (getItem.equals("geology of the arabian peninsula")){
            mPdfViewer.fromAsset("geology of the arabian peninsula.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("geology of the arabian peninsula.pdf");
                }
            });
        }

        if (getItem.equals("Geology of Nevada")){
            mPdfViewer.fromAsset("Geology of Nevada.pdf").load();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coppyAsset("Geology of Nevada.pdf");
                }
            });
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                    }
                }else {
                    Toast.makeText(this,"no permission granted", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    protected void coppyAsset(String filename){
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/MyFiles";
        File dir = new File(dirPath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(filename);
            File outFile = new File(dirPath,filename);
            out = new FileOutputStream(outFile);
            download(in, out);
            Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show();
            downloaded.add(filename);
            adapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show();
        }finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (out != null){
                try {
                    out.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void download(InputStream in, OutputStream out) throws IOException{
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1){
            out.write(buffer,0,read);
        }
    }
}
