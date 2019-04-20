package com.nrick.ongkur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_success);

        Intent intent = getIntent();
        int point = intent.getIntExtra("CNT", 1);

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("USERS").child(FirebaseAuth.getInstance().getUid());

        mRef.child("CURRENT_POINT").setValue(String.valueOf(point));

        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        mRef = FirebaseDatabase.getInstance().getReference().child("GAME_1").child(FirebaseAuth.getInstance().getUid());
        mRef.child(ts).setValue(String.valueOf(point));
    }
}
