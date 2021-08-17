package com.example.internship_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView;
    Button delete,update;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        hook();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        isOnline();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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
                    if(DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData().isEmpty()) {
                        for (int i = 0; i < postList.size(); i++) {
                            UserModel model = new UserModel();
                            model.setName(postList.get(i).getName());
                            model.setAgency(postList.get(i).getAgency());
                            model.setStatus(postList.get(i).getStatus());
                            model.setWikipedia(postList.get(i).getWikipedia());
                            model.setId(postList.get(i).getId());
                            model.setImage(postList.get(i).getImage());
                            DatabaseClass.getDatabase(Home.this).getDao().insertAllData(model);
                            Toast.makeText(Home.this, "Data Saved "+i+"th index", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {

                }
            });
        }
        else {
            OfflineAdapter offlineAdapter=new OfflineAdapter(getApplicationContext(),DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData());
            recyclerView.setAdapter(offlineAdapter);
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
        delete=findViewById(R.id.delete_home);
        update=findViewById(R.id.update_home);
    }
}