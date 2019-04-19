package com.nrick.ongkur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mDatabaseGEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            overridePendingTransition(R.anim.slow_fade_in, R.anim.bottom_to_top);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(MainActivity.this, Home.class);
            overridePendingTransition(R.anim.slow_fade_in, R.anim.bottom_to_top);
            startActivity(intent);
            finish();
        }
    }
}
