package com.example.mfstore.eventListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mfstore.R;
import com.example.mfstore.model.OfferModels;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class Event_OfferItem  extends RecyclerView.Adapter<Event_OfferItem.ViewHolder> {

    List<OfferModels> list;

    public Event_OfferItem(List<OfferModels> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.roundedImageView.setImageResource(list.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        RoundedImageView roundedImageView;
            public ViewHolder(@NonNull View itemview){
                super(itemview);
                roundedImageView = itemview.findViewById(R.id.offer_item_id);
            }
    }
}
