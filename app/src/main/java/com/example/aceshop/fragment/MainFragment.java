package com.example.aceshop.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aceshop.R;
import com.example.aceshop.adapter.ItemsAdapter;
import com.example.aceshop.model.Items;

import java.util.ArrayList;


public class MainFragment extends Fragment {
    ArrayList<Items> itemList;
    Context context;

    public MainFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        itemList=new ArrayList<>();
        Items itemsElek=new Items("Elektronik",R.drawable.elektronik);
        Items itemsbil=new Items("Bilgisayar",R.drawable.bilgisayar);
        Items itemsbebek=new Items("Bebek",R.drawable.bebek);
        Items itemsev=new Items("Ev Yaşam",R.drawable.evyasam);
        Items itemsyasam=new Items("Kişisel Bakım",R.drawable.kisiselbakim);
        Items itemskitap=new Items("Kitap",R.drawable.kitap);
        Items itemsmoda=new Items("Moda",R.drawable.mode);
        Items itemsofis=new Items("Ofis",R.drawable.ofis);
        Items itemsoyuncak=new Items("Oyuncak",R.drawable.oyuncak);
        Items itemspor=new Items("Spor",R.drawable.spor);
        Items itemstel=new Items("Telefon",R.drawable.telefon);
        Items itemstv=new Items("Tv",R.drawable.tv);
        Items itemsyapi=new Items("Yapı Malzeme",R.drawable.yapi);


        itemList.add(itemsElek);
        itemList.add(itemsbil);
        itemList.add(itemsbebek);
        itemList.add(itemsev);
        itemList.add(itemsyasam);
        itemList.add(itemskitap);
        itemList.add(itemsmoda);
        itemList.add(itemsofis);
        itemList.add(itemsoyuncak);
        itemList.add(itemspor);
        itemList.add(itemstel);
        itemList.add(itemstv);
        itemList.add(itemsyapi);;


        View view=inflater.inflate(R.layout.fragment_main,container,false);
        RecyclerView recyclerView=view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        ItemsAdapter adapter=new ItemsAdapter(itemList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}