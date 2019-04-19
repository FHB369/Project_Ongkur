package com.nrick.ongkur;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class KURI extends AppCompatActivity {

    private Button feed, play, govern, reward;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuri);



        final Button feed = findViewById(R.id.feed);
        Button play = findViewById(R.id.play);
        Button govern = findViewById(R.id.govern);
        Button reward = findViewById(R.id.reward);


        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KURI.this, com.nrick.ongkur.feed.class);
                startActivity(intent);
            }
        });

        govern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



}
