package com.example.tumplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        int user_id = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("user_id")));
        TextView user_id_d = (TextView) findViewById(R.id.user_id);
        user_id_d.setText("UserID: " + user_id);
    }
}