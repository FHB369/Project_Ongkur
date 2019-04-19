package com.nrick.ongkur;

import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.linkon.game.UnityPlayerActivity;

import java.util.Locale;

public class CompanionChooser extends AppCompatActivity  implements TextToSpeech.OnInitListener  {

    private Button leftOption, rightOption, character;
    private boolean isChanged;
    private TextView hiText;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companion_chooser);

        isChanged = false;

        tts = new TextToSpeech(this, this);

        leftOption = findViewById(R.id.leftOption);
        rightOption = findViewById(R.id.rightOption);
        character = findViewById(R.id.character);
        hiText = findViewById(R.id.textView4);

        leftOption.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(isChanged){
                    character.setBackground(getDrawable(R.drawable.character_3));
                    hiText.setText("আমি শিয়াল, আমার বন্ধু হতে চাইলে আমার ছবির উপর ট্যাপ কর");
                    isChanged = false;
                }else{
                    character.setBackground(getDrawable(R.drawable.character_4));
                    hiText.setText("আমি পান্ডা, আমার বন্ধু হতে চাইলে আমার ছবির উপর ট্যাপ কর");
                    isChanged = true;
                }
                speakOut();
            }
        });

        rightOption.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(isChanged){
                    character.setBackground(getDrawable(R.drawable.character_3));
                    hiText.setText("আমি শিয়াল, আমার বন্ধু হতে চাইলে আমার ছবির উপর ট্যাপ কর");
                    isChanged = false;
                    speakOut();
                }else{
                    character.setBackground(getDrawable(R.drawable.character_4));
                    hiText.setText("আমি পান্ডা, আমার বন্ধু হতে চাইলে আমার ছবির উপর ট্যাপ কর");
                    isChanged = true;
                    speakOut();
                }
                speakOut();
            }
        });

        character.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanionChooser.this, KURI.class);
                startActivity(intent);
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
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speakOut() {

        if(isChanged) {
            String text = "আমি সারা, আমার বন্ধু হতে চাইলে আমার ছবির উপর ট্যাপ কর";
            if(text!=null) {
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        }else{
            String text = "আমি পিকু, আমার বন্ধু হতে চাইলে আমার ছবির উপর ট্যাপ কর";
            if(text!=null) {
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    }
}
