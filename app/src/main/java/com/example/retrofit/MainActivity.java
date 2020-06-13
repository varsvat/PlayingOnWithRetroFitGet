package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerAdapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ApiService apiService;
    private List<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        apiService = ApiClient.getApiClient().create(ApiService.class);

        Call<List<Model>> call = apiService.getPosts();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (!response.isSuccessful()) {
                    Log.d("Error: " , "Error code for the failure is" +response.code());
                    return;
                }

                list = response.body();
                recyclerAdapter = new RecyclerAdapter(list);
                recyclerView.setAdapter(recyclerAdapter);

            }
            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Log.d("Error: " , t.getMessage());
            }
        });
    }
}