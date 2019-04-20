package com.nrick.ongkur;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ImageProcessingCommunity extends AppCompatActivity {

    private TextView name, name2, name3, name4, name5, phone, phone2, phone3, phone4, phone5;
    private CardView card1, card2, card3, card4, card5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_processing_community);

        card1 = findViewById(R.id.imagePro1);
        card2 = findViewById(R.id.imagePro2);
        card3 = findViewById(R.id.imagePro3);
        card4 = findViewById(R.id.imagePro4);
        card5 = findViewById(R.id.imagePro5);

        name = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);
        name4 = findViewById(R.id.name4);
        name5 = findViewById(R.id.name5);

        phone = findViewById(R.id.phone1);
        phone2 = findViewById(R.id.phone2);
        phone3 = findViewById(R.id.phone3);
        phone4 = findViewById(R.id.phone4);
        phone5 = findViewById(R.id.phone5);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("USERS");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<DataSnapshot> snapshotArrayList = new ArrayList<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    snapshotArrayList.add(snapshot);
                }

                String NAME1 = (String) snapshotArrayList.get(0).child("NAME").getValue();
                String NAME2 = (String) snapshotArrayList.get(1).child("NAME").getValue();
                String NAME3 = (String) snapshotArrayList.get(2).child("NAME").getValue();
                String NAME4 = (String) snapshotArrayList.get(3).child("NAME").getValue();
                String NAME5 = (String) snapshotArrayList.get(4).child("NAME").getValue();

                final String PHONE1 = (String) snapshotArrayList.get(0).child("PHONE").getValue();
                final String PHONE2 = (String) snapshotArrayList.get(1).child("PHONE").getValue();
                final String PHONE3 = (String) snapshotArrayList.get(2).child("PHONE").getValue();
                final String PHONE4 = (String) snapshotArrayList.get(3).child("PHONE").getValue();
                final String PHONE5 = (String) snapshotArrayList.get(4).child("PHONE").getValue();

                name.setText(NAME1);
                name2.setText(NAME2);
                name3.setText(NAME3);
                name4.setText(NAME4);
                name5.setText(NAME5);

                phone.setText(PHONE1);
                phone2.setText(PHONE2);
                phone3.setText(PHONE3);
                phone4.setText(PHONE4);
                phone5.setText(PHONE5);

                card1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+PHONE1));
                        startActivity(intent);
                    }
                });

                card2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+PHONE2));
                        startActivity(intent);
                    }
                });

                card3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+PHONE3));
                        startActivity(intent);
                    }
                });

                card4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+PHONE4));
                        startActivity(intent);
                    }
                });

                card5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+PHONE5));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
