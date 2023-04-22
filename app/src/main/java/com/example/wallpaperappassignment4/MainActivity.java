package com.example.wallpaperappassignment4;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView mtrending,mcars,mbus,mtrain,mnature;
    EditText editText;
    ImageButton search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.recyclerView);
        mtrending=findViewById(R.id.trending);
        mcars=findViewById(R.id.car);
        mbus=findViewById(R.id.bus);
        mtrain=findViewById(R.id.train);
        mnature=findViewById(R.id.nature);
        editText=findViewById(R.id.searchtext);
        search=findViewById(R.id.searchbutton);

        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new Adapter(getApplicationContext(),modelClassList);
        recyclerView.setAdapter(adapter);
        findPhotos();

        mtrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPhotos();
            }
        });

        mcars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="cars";
                getSearchImage(query);
            }
        });

        mtrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="train";
                getSearchImage(query);
            }
        });

        mbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="bus";
                getSearchImage(query);
            }
        });

        mnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="nature";
                getSearchImage(query);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query=editText.getText().toString().trim().toLowerCase();
                if(query.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Something",Toast.LENGTH_SHORT).show();
                } else {
                    getSearchImage(query);
                }
            }
        });


    }

    private void getSearchImage(String query) {
        API.getAPIInterface().getSearchImage(query, 1, 80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if (response.isSuccessful()) {
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "Not Able To Find Images", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }

    private void findPhotos() {
        modelClassList.clear();
        API.getAPIInterface().getImages(1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if (response.isSuccessful()) {
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "Not Able To Find Images", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }
}