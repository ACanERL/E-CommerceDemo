package com.example.aceshop.network;

import com.example.aceshop.model.NetworkData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    //end point data
    @GET("/photos")
    Call<List<NetworkData>> getAllPhotos();
}
