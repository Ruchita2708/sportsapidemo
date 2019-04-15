package com.atmiya.admin.sportapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
ListView lv;
Retrofit rf;
Call<Sport> call;
List<Article> article;
Api api;
String[] title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=findViewById(R.id.lv);
        rf=new Retrofit.Builder().baseUrl(api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        api=rf.create(Api.class);
        call=api.getInfo("bbc-sport","7cd87b9152ba43afac584eb53e94e2d6");
        call.enqueue(new Callback<Sport>() {
            @Override
            public void onResponse(Call<Sport> call, Response<Sport> response) {
                article=response.body().getArticles();
                title=new String[article.size()];
                for(int i=0;i<article.size();i++)
                {
                    title[i]=article.get(i).getTitle();
                }
                lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,title));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent in=new Intent(getApplicationContext(),SportDetailActivity.class);
                        in.putExtra("author",article.get(i).getAuthor());
                        in.putExtra("title",article.get(i).getTitle());
                        in.putExtra("description",article.get(i).getDescription());
                        in.putExtra("content",article.get(i).getContent());
                        in.putExtra("urlToImage",article.get(i).getUrlToImage());
                        in.putExtra("publishedAt",article.get(i).getPublishedAt());
                        startActivity(in);
                    }
                });
            }

            @Override
            public void onFailure(Call<Sport> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
