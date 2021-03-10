package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private ArrayList<review> arrayList;
    private Context context;

    public ReviewAdapter(ArrayList<review> arrayList, Context context){
        this.arrayList = arrayList;
        this.context=context;
    }
    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_review, parent, false);
        ReviewViewHolder holder = new ReviewViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.pf_userId.setText(arrayList.get(position).getUserId());
        holder.pf_review.setText(arrayList.get(position).getReview_text());
        holder.pf_stars.setText("별점 "+String.valueOf(arrayList.get(position).getReview_stars()));
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView pf_userId;
        TextView pf_review;
        TextView pf_stars;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView); // itemView 상속
            //상속 받은 itemView 를 통해 객체를 얻음
            this.pf_userId = itemView.findViewById(R.id.pf_userId);
            this.pf_review = itemView.findViewById(R.id.pf_review);
            this.pf_stars = itemView.findViewById(R.id.pf_stars);

        }
    }
}
