package com.nrick.ongkur;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.w3c.dom.Text;

import java.util.Locale;

public class feed extends AppCompatActivity   implements TextToSpeech.OnInitListener {
    Button okay, notok1, notok2;
    private int count = 0;
    ImageView cross;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        okay = findViewById(R.id.okay);
        notok1 = findViewById(R.id.not1);
        notok2 = findViewById(R.id.not2);
        cross = findViewById(R.id.cross);

        tts = new TextToSpeech(this, this);

        speakOut("আমি ক্ষুধার্ত, তুমি কি আমার জন্য একটি খাবার পছন্দ করবে?");


        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(feed.this, FeedSuccess.class);
                intent.putExtra("CNT", count);
                startActivity(intent);
                finish();
            }
        });

        notok1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                count++;
                cross.setImageDrawable(getDrawable(R.drawable.d));
                cross.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cross.setVisibility(View.INVISIBLE);
                    }
                }, 2000);
            }
        });

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        notok2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                count++;
                cross.setImageDrawable(getDrawable(R.drawable.d));
                cross.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cross.setVisibility(View.INVISIBLE);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(new Locale("bn_BD"));

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut("আমি ক্ষুধার্ত, তুমি কি আমার জন্য একটি খাবার পছন্দ করবে?");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speakOut(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
