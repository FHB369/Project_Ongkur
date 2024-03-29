package com.nrick.ongkur;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class DailyGames extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private Button start, startYourself, tryElse;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_games);

        start = findViewById(R.id.buttonStartTogether);
        startYourself = findViewById(R.id.buttonStartYourself);
        tryElse = findViewById(R.id.buttonTryElse);

        tts = new TextToSpeech(this, this);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Entered");
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.linkon.Plane");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });

        startYourself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DailyGames.this, ScanPlane.class);
                startActivity(intent);
                finish();
            }
        });

        tryElse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DailyGames.this, DailyGames2.class);
                startActivity(intent);
                finish();
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
                speakOut("আজকে আমরা পেপার প্লেন বানাবো, তুমি কি শিখতে চাও?    চলো একসাথে শুরু করি। " +
                        "শেখা শুরু করতে প্রথম বাটনে ট্যাপ করো।" +
                        "যদি নিজেই বানাতে চাও তাহলে দ্বিতীয় বাটনে ট্যাপ করো।" +
                        "অন্য কিছু খেলতে চাইলে তৃতীয় বাটনে ট্যাপ করো। ");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speakOut(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
