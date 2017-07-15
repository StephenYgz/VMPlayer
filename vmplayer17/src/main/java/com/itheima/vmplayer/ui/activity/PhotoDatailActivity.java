package com.itheima.vmplayer.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.itheima.vmplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

public class PhotoDatailActivity extends AppCompatActivity {

    @BindView(R.id.activity_photo_datail)
    PhotoView activityPhotoDatail;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_datail);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra("url");

        Glide.with(this).load(url).into(activityPhotoDatail);

    }
}
