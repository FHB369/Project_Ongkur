package com.nrick.ongkur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SliderOne extends AppCompatActivity {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_one);

        next = findViewById(R.id.button_next_1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SliderOne.this, SliderTwo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slow_fade_in, R.anim.slow_fade_out);
            }
        });
    }
}
