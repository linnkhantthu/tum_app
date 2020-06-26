package com.example.tumplatform;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tumplatform.Posts;
import com.example.tumplatform.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Navneet Krishna on 11/05/19.
 */
public class postsAdapter extends RecyclerView.Adapter<postsAdapter.ViewHolder> {

    private ArrayList<Posts> posts=new ArrayList<>();
    private Context context;


    public postsAdapter(Context context, ArrayList<Posts> posts) {
        this.posts=posts;
        this.context=context;
    }

    @NonNull
    @Override
    public postsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.posts_list_item,viewGroup,false);
        return new postsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull postsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(posts.get(i).getTitle());
        viewHolder.content.setText(posts.get(i).getContent());
        //default.jpg
        Picasso.get().load("https://boiling-basin-67311.herokuapp.com/static/profile_pics/" + posts.get(i).getAuthor().getImage_file()).into(viewHolder.car_image);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView car_image;
        private TextView title,content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            car_image=(ImageView)itemView.findViewById(R.id.user_image);
            title=(TextView) itemView.findViewById(R.id.title);
            content=(TextView)itemView.findViewById(R.id.content);
        }
    }
}
