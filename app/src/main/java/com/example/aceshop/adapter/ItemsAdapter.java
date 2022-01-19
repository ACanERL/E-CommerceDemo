package com.example.aceshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aceshop.fragment.DetailFragment;
import com.example.aceshop.R;
import com.example.aceshop.databinding.ItemrowLayoutBinding;
import com.example.aceshop.model.Items;

import java.util.ArrayList;

public class ItemsAdapter  extends RecyclerView.Adapter<ItemsAdapter.ItemHolder> {
    ArrayList<Items>itemsArrayList;
    Context context;

    public ItemsAdapter(ArrayList<Items> itemsArrayList) {
        this.itemsArrayList = itemsArrayList;
    }

    @NonNull
    @Override
    public ItemsAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemrowLayoutBinding itemrowLayoutBinding=ItemrowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
     return new ItemHolder(itemrowLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itembinding.itemImage.setImageResource(itemsArrayList.get(position).getItemimage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().popBackStack();
                Fragment fragment=new DetailFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }
    public class ItemHolder extends RecyclerView.ViewHolder{
        private ItemrowLayoutBinding itembinding;
        public ItemHolder(@NonNull ItemrowLayoutBinding binding) {
            super(binding.getRoot());
            this.itembinding=binding;
        }
    }
}
