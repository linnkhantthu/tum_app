package com.example.tumplatform;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;

public class postsAdapter extends RecyclerView.Adapter<postsAdapter.ViewHolder> {

    private ArrayList<Posts> posts=new ArrayList<>();
    private ArrayList<Comments> comments = new ArrayList<>();
    private Context context;


    public postsAdapter(Context context, ArrayList<Posts> posts, ArrayList<Comments> comments) {
        this.posts = posts;
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public postsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.posts_list_item,viewGroup,false);
        return new postsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull postsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.username.setText(posts.get(i).getAuthor().getUsername());
        try {
            viewHolder.date.setText(posts.get(i).getDate_posted());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewHolder.title.setText(posts.get(i).getTitle());
        viewHolder.content.setText(posts.get(i).getContent());
        int numberOfComments = 0;
        for (int j=0; j<comments.size();j++){
            if (comments.get(j).getPost_id()==posts.get(i).getId()){
                numberOfComments += 1;
            }
        }
        //numberOfComments + "Comments"
        viewHolder.comment.setText(numberOfComments + " Comments");
        Picasso.get().load("https://infinite-anchorage-45437.herokuapp.com/static/profile_pics/" + posts.get(i).getAuthor().getImage_file()).into(viewHolder.car_image);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView car_image;
        private TextView title, content, username, date, comment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            car_image = (ImageView)itemView.findViewById(R.id.user_image);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView)itemView.findViewById(R.id.content);
            username = (TextView)itemView.findViewById(R.id.username);
            date = (TextView)itemView.findViewById(R.id.date);
            comment = (TextView)itemView.findViewById(R.id.comment);
        }
    }
}
