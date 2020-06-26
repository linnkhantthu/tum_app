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
    private postsAdapter postsAdapter;
    private RecyclerView posts_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posts_recyclerview=(RecyclerView)findViewById(R.id.posts_recyclerview);
        posts_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        getCarsResponse();

    }

    private void getCarsResponse() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://boiling-basin-67311.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api requestInteface=retrofit.create(Api.class);
        Call<List<Posts>> call=requestInteface.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                posts = new ArrayList<>(response.body());
                postsAdapter = new postsAdapter(MainActivity.this, posts);
                posts_recyclerview.setAdapter(postsAdapter);
                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
