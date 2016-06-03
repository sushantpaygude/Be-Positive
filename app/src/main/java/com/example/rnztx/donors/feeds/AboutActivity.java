package com.example.rnztx.donors.feeds;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.rnztx.donors.R;
import com.squareup.picasso.Picasso;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ImageView roh_obj = (ImageView)findViewById(R.id.rohit_image);
        ImageView prats_obj = (ImageView)findViewById(R.id.image_pratik);
        ImageView sush_obj = (ImageView)findViewById(R.id.image_sushant);
        ImageView dev_obj = (ImageView)findViewById(R.id.image_devender);


        Picasso.with(this).load(R.drawable.roh).into(roh_obj);
        Picasso.with(this).load(R.drawable.prats).into(prats_obj);
        Picasso.with(this).load(R.drawable.sus).into(sush_obj);
        Picasso.with(this).load(R.drawable.dev).into(dev_obj);





    }
}
