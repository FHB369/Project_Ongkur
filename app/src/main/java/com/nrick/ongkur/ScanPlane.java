package com.nrick.ongkur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ScanPlane extends AppCompatActivity {

    private ImageView image1,image2,image3, image4, image5, image6;
     static int c = 0;
    private Button select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_plane);

        final ImageView image1 = findViewById(R.id.image1);
        final ImageView image2 = findViewById(R.id.image2);
        final ImageView image3 = findViewById(R.id.image3);
        final ImageView image4 = findViewById(R.id.image4);
        final ImageView image5 = findViewById(R.id.image5);
        final ImageView image6 = findViewById(R.id.image6);
        final Button select = findViewById(R.id.select);

        c=0;

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image1.setVisibility(View.INVISIBLE);
                c++;
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               image2.setVisibility(View.INVISIBLE);
                c--;
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image3.setVisibility(View.INVISIBLE);
                c++;
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image4.setVisibility(View.INVISIBLE);
                c++;
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image5.setVisibility(View.INVISIBLE);
                c++;
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image6.setVisibility(View.INVISIBLE);
                c--;
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanPlane.this, ResultPlane.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
