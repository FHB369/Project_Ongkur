package com.nrick.ongkur;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
            mDatabaseGEN = FirebaseDatabase.getInstance().getReference().child("USERS").child(mAuth.getUid());
            mDatabaseGEN.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String catagory = (String) dataSnapshot.child("CURRENT_CATAGORY").getValue();

                    if(catagory.equals("KURI")){
                        Intent intent = new Intent(MainActivity.this, KURI.class);
                        overridePendingTransition(R.anim.slow_fade_in, R.anim.bottom_to_top);
                        startActivity(intent);
                        finish();
                    }else if(catagory.equals("NOBIN")){
                        Intent intent = new Intent(MainActivity.this, NobinHome.class);
                        overridePendingTransition(R.anim.slow_fade_in, R.anim.bottom_to_top);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }
}
