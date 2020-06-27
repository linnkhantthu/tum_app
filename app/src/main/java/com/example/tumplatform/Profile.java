package com.example.tumplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile extends AppCompatActivity {

    private RecyclerView profile_recyclerview;
    ArrayList<author> author=new ArrayList<>();
    private profileAdapter profileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //TextView user_id_d = (TextView) findViewById(R.id.user_id);
        //user_id_d.setText("UserID: " + user_id);

        profile_recyclerview=(RecyclerView)findViewById(R.id.profile_recyclerview);
        profile_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        getResponse();
    }
    private void getResponse() {
        Intent intent = getIntent();
        int user_id = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("user_id")));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://infinite-anchorage-45437.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api requestInteface=retrofit.create(Api.class);
        Call<List<author>> call= requestInteface.getProfiles(user_id);
        call.enqueue(new Callback<List<author>>() {
            @Override
            public void onResponse(Call<List<author>> call, Response<List<author>> response) {
                author = new ArrayList<>(response.body());
                profileAdapter = new profileAdapter(Profile.this, author);
                profile_recyclerview.setAdapter(profileAdapter);
                Toast.makeText(Profile.this,"User retrieved successfully",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<author>> call, Throwable t) {
                Toast.makeText(Profile.this,"Post not retrieved",Toast.LENGTH_SHORT).show();
            }
        });
    }
}