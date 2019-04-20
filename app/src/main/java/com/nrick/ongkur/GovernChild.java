package com.nrick.ongkur;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class GovernChild extends AppCompatActivity {

    private Button logOut, joinGroup;
    private TextView skillName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govern_child);

        final GraphView graph = (GraphView) findViewById(R.id.graph);
        logOut = findViewById(R.id.logOut);
        joinGroup = findViewById(R.id.joinCommunity);
        skillName = findViewById(R.id.skillName);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("SKILLS").child("IMAGE_PROCESSING");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(FirebaseAuth.getInstance().getUid())){
                    skillName.setText("ছবি দেখে যাচাই করা");
                    joinGroup.setVisibility(View.VISIBLE);

                    joinGroup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(GovernChild.this, ImageProcessingCommunity.class));
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("GAME_1").child(FirebaseAuth.getInstance().getUid());
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Integer> arrayList = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    arrayList.add(Integer.parseInt((String) postSnapshot.getValue()));
                }

                if(arrayList.size()==1){
                    arrayList.add(arrayList.get(0));
                    arrayList.add(arrayList.get(0));
                    arrayList.add(arrayList.get(0));
                    arrayList.add(arrayList.get(0));
                    arrayList.add(arrayList.get(0));
                }else if(arrayList.size()==2){
                    arrayList.add(arrayList.get(1));
                    arrayList.add(arrayList.get(1));
                    arrayList.add(arrayList.get(1));
                    arrayList.add(arrayList.get(1));
                }else if(arrayList.size()==3){
                    arrayList.add(arrayList.get(2));
                    arrayList.add(arrayList.get(2));
                    arrayList.add(arrayList.get(2));
                }else if(arrayList.size()==4){
                    arrayList.add(arrayList.get(3));
                    arrayList.add(arrayList.get(3));
                }else if(arrayList.size()==5){
                    arrayList.add(arrayList.get(4));
                }

                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                        new DataPoint(0, 0),
                        new DataPoint(1, -1*arrayList.get(0)),
                        new DataPoint(2, -1*arrayList.get(1)),
                        new DataPoint(3, -1*arrayList.get(2)),
                        new DataPoint(4, -1*arrayList.get(3)),
                        new DataPoint(5, -1*arrayList.get(4)),
                        new DataPoint(6, -1*arrayList.get(5))
                });
                graph.addSeries(series);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(GovernChild.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
