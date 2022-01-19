package com.example.aceshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aceshop.R;
import com.example.aceshop.model.Items;
import com.example.aceshop.model.NetworkData;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<NetworkData> dataList;
    private Context context;
    int data=0;
    public CustomAdapter(List<NetworkData> dataList) {
        this.dataList=dataList;
    }
    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.recycler_row, parent, false);
        return new CustomViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
        holder.idtx.setText(dataList.get(position).getId().toString());
        holder.txtTitle.setText(dataList.get(position).getTitle());

        Picasso.get()
                .load(dataList.get(position).getThumbnailUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "item positon="+position,Toast.LENGTH_SHORT).show();

                data=data+dataList.get(position).getId();
                System.out.println(data);
            }
        });

    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        TextView txtTitle,idtx;
        private ImageView image;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mView = itemView;
            idtx=mView.findViewById(R.id.idtx);
            txtTitle = mView.findViewById(R.id.titleView);
            image = mView.findViewById(R.id.imageView);
        }
    }

}
