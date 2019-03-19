package com.example.sheetstoroomdatabase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    MainActivity mainActivity;
    List<Sheet1> listLiveData;

    public DataAdapter(MainActivity mainActivity, List<Sheet1> listLiveData) {
        this.mainActivity = mainActivity;
        this.listLiveData = listLiveData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(listLiveData.get(i).getSTUDENTNAME());
        viewHolder.address.setText(listLiveData.get(i).getADDRESS());
        viewHolder.aadhar.setText(listLiveData.get(i).getAADHARNO());

        Picasso.with(mainActivity).load("https://docs.google.com/uc?id="+listLiveData.get(i).getIMAGE())
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        if (listLiveData != null) {
            return listLiveData.size();
        } else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, address,aadhar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            aadhar = itemView.findViewById(R.id.aadhar);
        }
    }
}
