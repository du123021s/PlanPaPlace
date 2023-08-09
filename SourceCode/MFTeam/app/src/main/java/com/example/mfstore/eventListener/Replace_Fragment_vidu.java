package com.example.mfstore.eventListener;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mfstore.fragment.vidu;


public class Replace_Fragment_vidu implements View.OnClickListener{
    private Context context;

    public Replace_Fragment_vidu(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Replace_Fragment rf = new Replace_Fragment();
        rf.replaceFragment((AppCompatActivity) context, new vidu());
    }

}
