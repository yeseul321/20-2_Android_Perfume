package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> {

   private ArrayList<perfume> recommendList;
   private Context context;

    public RecommendAdapter(ArrayList<perfume> recommendList, Context context){
        this.context=context;
        this.recommendList =recommendList;
    }
    @NonNull
    @Override
    public RecommendAdapter.RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        RecommendViewHolder holder = new RecommendViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendAdapter.RecommendViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(recommendList.get(position).getPhoto())
                .into(holder.pf_profile);
        holder.pf_name.setText(recommendList.get(position).getName());
        holder.pf_estimating.setText("별점 "+String.valueOf(recommendList.get(position).getEstimating()));
        holder.pf_brand.setText(recommendList.get(position).getBrand());

    }

    @Override
    public int getItemCount() {
        return (recommendList != null ? recommendList.size() : 0);
    }


    public class RecommendViewHolder extends RecyclerView.ViewHolder {

        ImageView pf_profile;
        TextView pf_brand;
        TextView pf_estimating;
        TextView pf_name;
        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            this.pf_profile = itemView.findViewById(R.id.pf_photo);
            this.pf_name = itemView.findViewById(R.id.pf_name);
            this.pf_brand = itemView.findViewById(R.id.pf_brand);
            this.pf_estimating = itemView.findViewById(R.id.pf_estimating);

        }
    }
}
