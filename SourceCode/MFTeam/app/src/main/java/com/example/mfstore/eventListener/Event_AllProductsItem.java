package com.example.mfstore.eventListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mfstore.R;
import com.example.mfstore.model.AllProductModels;
import com.example.mfstore.model.ExclusivesModels;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class Event_AllProductsItem extends RecyclerView.Adapter<Event_AllProductsItem.ViewHolder> {
    List<AllProductModels> listAllproducts;

    public Event_AllProductsItem(List<AllProductModels> list) {
        this.listAllproducts = list;
    }

    @NonNull
    @Override
    public Event_AllProductsItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.alls_product_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Event_AllProductsItem.ViewHolder holder, int position) {
        holder.roundedImageView.setImageResource(listAllproducts.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return listAllproducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView roundedImageView;
        public ViewHolder(@NonNull View itemview){
            super(itemview);
            roundedImageView = itemview.findViewById(R.id.round_img_allproducts);
        }
    }
}
