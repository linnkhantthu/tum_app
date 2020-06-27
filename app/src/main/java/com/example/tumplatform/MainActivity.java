package com.example.tumplatform;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<Posts> posts=new ArrayList<>();
    ArrayList<Comments> comments = new ArrayList<>();
    private postsAdapter postsAdapter;
    private RecyclerView posts_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        posts_recyclerview=(RecyclerView)findViewById(R.id.posts_recyclerview);
        posts_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        getResponse();

    }

    private void getResponse() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://infinite-anchorage-45437.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api requestInteface=retrofit.create(Api.class);
        Call<List<Posts>> call= requestInteface.getPosts();
        Call<List<Comments>> callComments = requestInteface.getComments();
        callComments.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                comments = new ArrayList<>(response.body());
                postsAdapter = new postsAdapter(MainActivity.this, posts, comments);
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {

            }
        });
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                posts = new ArrayList<>(response.body());
                postsAdapter = new postsAdapter(MainActivity.this, posts, comments);
                posts_recyclerview.setAdapter(postsAdapter);
                Toast.makeText(MainActivity.this,"Post retrieved successfully",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Post not retrieved",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
