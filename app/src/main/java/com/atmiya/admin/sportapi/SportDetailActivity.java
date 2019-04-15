package com.atmiya.admin.sportapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SportDetailActivity extends AppCompatActivity {
ImageView iv;
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_detail);

        iv=findViewById(R.id.iv);
        tv=findViewById(R.id.tv);
        String s=getIntent().getStringExtra("author")+"\n"+
                getIntent().getStringExtra("title")+"\n"+
                getIntent().getStringExtra("description")+"\n"+
                getIntent().getStringExtra("content")+"\n"+
                getIntent().getStringExtra("publishedAt");
        String img=getIntent().getStringExtra("urlToImage");
        tv.setText(s);
        Glide.with(getApplicationContext()).load(img).into(iv);
    }
}
