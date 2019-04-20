package com.nrick.ongkur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.xml.transform.Templates;


public class ResultPlane extends AppCompatActivity {

    private TextView result;
    private String s = String.valueOf(ScanPlane.c);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_plane);

        result = findViewById(R.id.result);
        result.setText(s);

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("SKILLS").child("IMAGE_PROCESSING");
        mRef.child(FirebaseAuth.getInstance().getUid()).setValue(s);
    }
}
