package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.newsapp.model.Article;
import com.example.newsapp.model.Headlines;
import com.example.newsapp.retrofitUtils.APINews;
import com.example.newsapp.retrofitUtils.RetrofitClient;
import com.example.newsapp.rvUtils.NewsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsActivity extends AppCompatActivity {

    RecyclerView rv;
    NewsAdapter na;
    LinearLayoutManager llm;

    String pais = "US";
    static final String API_KEY = "45dde31099fb4edfb5fb6b622b80bff2";

    static final String CLAVE_URL = "URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rvNoticias);

        // pais = getCountry();

        cosumirWS();

    }

    private void cosumirWS() {

        Retrofit r = RetrofitClient.getClient(APINews.BASE_URL);
        APINews ars = r.create(APINews.class);
        Call<Headlines> call = ars.getHeadlines(pais, API_KEY);
        // Log.i("ANTES_ENQUEUE", "Antes del Enqueue" );

        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "Error" + response.code());
                } else {
                    Log.i("onResponse", "Exito" + response.code());
                    Headlines r = (Headlines) response.body();

                    ArrayList<Article> listaNoticias = r.getArticles();

                    configurarRecyclerView(listaNoticias);

                    /*na = new NewsAdapter(r.getArticles());

                    // listaMonumentos.get(rvCds.getChildAdapterPosition(v)).getTitle());

                    na.setListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(NewsActivity.this, NewsDetailledActivity.class);
                            i.putExtra(CLAVE_URL, );
                            startActivity(i);
                        }
                    });



                    llm = new LinearLayoutManager(getApplicationContext());

                    rv.setLayoutManager(llm);
                    rv.hasFixedSize();
                    rv.setAdapter(na);*/
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Log.e("OnFailure", "Error" + t.getMessage());
            }
        });

    }

    private void configurarRecyclerView(final ArrayList<Article> listaNoticias) {
        rv = findViewById(R.id.rvNoticias);

        na = new NewsAdapter(listaNoticias);
        na.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewsActivity.this, NewsDetailledActivity.class);
                i.putExtra(CLAVE_URL, listaNoticias.get(rv.getChildAdapterPosition(v)).getUrl());
                startActivity(i);
            }
        });
        llm = new LinearLayoutManager(this);

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setAdapter(na);
    }

}