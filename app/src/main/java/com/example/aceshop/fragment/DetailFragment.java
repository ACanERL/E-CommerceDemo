package com.example.aceshop.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aceshop.R;
import com.example.aceshop.adapter.CustomAdapter;
import com.example.aceshop.model.NetworkData;
import com.example.aceshop.network.GetDataService;
import com.example.aceshop.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailFragment extends Fragment {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;View view;
    Context context;

    ProgressDialog progressDoalog;
    public DetailFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_main,container,false);
        GetDataService service= RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<NetworkData>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<NetworkData>>() {
            @Override
            public void onResponse(Call<List<NetworkData>> call, Response<List<NetworkData>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<NetworkData>> call, Throwable t) {
                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void generateDataList(List<NetworkData> dataList) {
        recyclerView = view.findViewById(R.id.recycler);
        adapter = new CustomAdapter(dataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}