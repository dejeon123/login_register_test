package com.example.version7;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SearAPI {

    @GET("v2.0/search/landscape/edushare/")
    Call<List<FileItem>>getFileData();
}
