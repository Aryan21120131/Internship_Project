package com.example.internship_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        hook();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        isOnline();

        if(isOnline()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.spacexdata.com/v4/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
            Call<List<Post>> call = jsonPlaceholder.getPost();
            call.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    List<Post> postList = response.body();
                    Adapter adapter = new Adapter(getApplicationContext(), postList);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {

                }
            });
        }
        else {
            Toast.makeText(Home.this, "OFFLINE !!!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isOnline() {
        try {
            ConnectivityManager manager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=null;
            if(manager!=null){
                networkInfo=manager.getActiveNetworkInfo();
            }
            return networkInfo!=null && networkInfo.isConnected();
        }catch (Exception e){
            return false;
        }
    }

    private void hook() {
        recyclerView=findViewById(R.id.recycler_view);
    }
}