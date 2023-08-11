package com.example.mfstore.eventListener;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mfstore.fragment.PhotoFragment;
import com.example.mfstore.model.OfferModels;

import java.io.Serializable;
import java.util.List;

public class Replace_Fragment_Product extends FragmentStateAdapter {
    private List<OfferModels> listoffer;

    public Replace_Fragment_Product(@NonNull FragmentActivity fragmentActivity, List<OfferModels> list){
        super(fragmentActivity);
        this.listoffer = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        OfferModels offer_m = listoffer.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_offer", (Serializable) offer_m);

        PhotoFragment photoFragment = new PhotoFragment();
        photoFragment.setArguments(bundle);

        return  photoFragment;
    }

    @Override
    public int getItemCount() {
        if(listoffer!=null){
            return  listoffer.size();
        }
        return 0;
    }
}
