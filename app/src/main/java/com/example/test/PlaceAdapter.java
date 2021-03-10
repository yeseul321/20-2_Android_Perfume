package com.example.test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.telephony.CarrierConfigManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>{

    ArrayList<placeList> arrayList;
    Context context;
    public PlaceAdapter(ArrayList<placeList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }


    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_place, parent, false);
        PlaceViewHolder holder = new PlaceViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position)
    {
        final double latitude = arrayList.get(position).getLattitude();
        final double longtitude=arrayList.get(position).getLongtitude();
        holder.place_name.setText(arrayList.get(position).getPlace_name());
        holder.place_address.setText(arrayList.get(position).getPlace_address());

        holder.call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               v.getContext().startActivity(new Intent("android.intent.action.DIAL",Uri.parse("tel:01039943356")));
            }
        });

        holder.alarm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

               final Intent intent = new Intent(v.getContext(),alarmGpsActivity.class);
               intent.putExtra("latitude",latitude);
               intent.putExtra("longtitude",longtitude);
               intent.putExtra("tracking",1);

                new AlertDialog.Builder(v.getContext()) // TestActivity 부분에는 현재 Activity의 이름 입력.
                        .setMessage("도착 알람을 설정하시겠습니까?")     // 제목 부분 (직접 작성)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which){
                               v.getContext().startActivity(intent);

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which){
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() { return (arrayList != null ? arrayList.size() : 0); }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView place_name;
        TextView place_address;
        ImageButton call_button;
        ImageButton alarm_button;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView); // itemView 상속
            this.place_name = itemView.findViewById(R.id.place_name);
            this.place_address=itemView.findViewById(R.id.place_address);
            this.call_button=itemView.findViewById(R.id.callButton);
            this.alarm_button=itemView.findViewById(R.id.alarmButton);

        }
    }
}


