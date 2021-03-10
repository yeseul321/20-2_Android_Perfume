package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>implements Filterable {

    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }

   private ArrayList<perfume> filteredList; // 정렬된 향수 리스트
   private ArrayList<perfume> unFilteredList; // 정렬이 안된 향수 리스트
   private Context context;

   ArrayList<perfume> resultList;
    public CustomAdapter(ArrayList<perfume> arrayList, Context context) {
        this.unFilteredList = arrayList;
        this.filteredList=arrayList;
        this.context=context;
    }

    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        // arraylist에 firebase 데이터(향수 이미지)를 가져와서 adapter에 전송

        Glide.with(holder.itemView)
                .load(filteredList.get(position).getPhoto())
                .into(holder.pf_profile);

        holder.pf_name.setText(filteredList.get(position).getName());
        holder.pf_estimating.setText("별점 "+String.valueOf(filteredList.get(position).getEstimating()));
        holder.pf_brand.setText(filteredList.get(position).getBrand());
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (filteredList != null ? filteredList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
       ImageView pf_profile;
        TextView pf_brand;
        TextView pf_estimating;
        TextView pf_name;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView); // itemView 상속
            //상속 받은 itemView 를 통해 객체를 얻음

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = unFilteredList.indexOf(filteredList.get(getAdapterPosition()));

                    if(pos!=RecyclerView.NO_POSITION){
                        if(mListener!=null){
                            mListener.onItemClick(v,pos);
                        }
                    }

                }
            });

            this.pf_profile = itemView.findViewById(R.id.pf_photo);
            this.pf_name = itemView.findViewById(R.id.pf_name);
            this.pf_brand = itemView.findViewById(R.id.pf_brand);
            this.pf_estimating = itemView.findViewById(R.id.pf_estimating);

        }

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()) {
                    filteredList = unFilteredList; //처음에는 정렬이 안되있는 상태
                } else {
                    ArrayList<perfume> filteringList = new ArrayList<>();
                    for(perfume item : unFilteredList) {
                        if(item.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteringList.add(item);
                        }
                    }
                    filteredList = filteringList; //이름순으로 정렬한 리스트를 넣음
                    resultList=filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<perfume>)results.values;
                notifyDataSetChanged();
            }
        };
    }

}


