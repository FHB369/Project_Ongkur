package com.nrick.ongkur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CatagoryChooser extends AppCompatActivity {

    private Button catagory1, catagory2, catagory3;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory_chooser);

        catagory1 = findViewById(R.id.cat1);
        catagory2 = findViewById(R.id.cat2);
        catagory3 = findViewById(R.id.cat3);

        catagory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCatagory(1);
            }
        });

        catagory2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCatagory(2);
            }
        });

        catagory3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCatagory(3);
            }
        });
    }

    private void saveCatagory(int i) {
        String catagory = null;
        if(i == 1){
            catagory = "KURI";
        }else if(i == 2){
            catagory = "NOBIN";
        }else if(i==3){
            catagory = "PUSHPO";
        }

        mAuth = FirebaseAuth.getInstance();
        String UID = mAuth.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("USERS").child(UID);
        mDatabase.child("CURRENT_CATAGORY").setValue(catagory);

        Intent intent = new Intent(CatagoryChooser.this, Home.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left, R.anim.right_to_left_exit);
        finish();
    }
}
