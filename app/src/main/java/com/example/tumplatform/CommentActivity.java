package com.example.tumplatform;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentActivity extends AppCompatActivity {

    ArrayList<Comments> comments = new ArrayList<>();
    private commentsAdapter commentsAdapter;
    private RecyclerView comments_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        comments_recyclerview=(RecyclerView)findViewById(R.id.comments_recyclerview);
        comments_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        getResponse();

    }

    private void getResponse() {
        Intent intent = getIntent();
        int post_id = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("post_id")));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://infinite-anchorage-45437.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api requestInteface=retrofit.create(Api.class);
        Call<List<Comments>> call = requestInteface.getCommentsByPostId(post_id);
        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                comments = new ArrayList<>(response.body());
                commentsAdapter = new commentsAdapter(CommentActivity.this, comments);
                comments_recyclerview.setAdapter(commentsAdapter);
                Toast.makeText(CommentActivity.this,"Comments retrieved successfully",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                Toast.makeText(CommentActivity.this,"Post not retrieved",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
