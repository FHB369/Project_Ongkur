package com.nrick.ongkur;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.linkon.game.UnityPlayerActivity;

public class CompanionChooser extends AppCompatActivity {

    private Button leftOption, rightOption, character;
    private boolean isChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companion_chooser);

        isChanged = false;

        leftOption = findViewById(R.id.leftOption);
        rightOption = findViewById(R.id.rightOption);
        character = findViewById(R.id.character);

        leftOption.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(isChanged){
                    character.setBackground(getDrawable(R.drawable.character_1));
                    isChanged = false;
                }else{
                    character.setBackground(getDrawable(R.drawable.character_2));
                    isChanged = true;
                }
            }
        });

        rightOption.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(isChanged){
                    character.setBackground(getDrawable(R.drawable.character_1));
                    isChanged = false;
                }else{
                    character.setBackground(getDrawable(R.drawable.character_2));
                    isChanged = true;
                }
            }
        });

        character.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.linkon.game");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });
    }
}
