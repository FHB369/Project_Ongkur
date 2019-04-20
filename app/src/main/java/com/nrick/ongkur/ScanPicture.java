package com.nrick.ongkur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScanPicture extends AppCompatActivity {

    private Button startScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_picture);

        startScan = findViewById(R.id.buttonScanPic);

        startScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.linkon.sustAR");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                    finish();
                }
            }
        });
    }
}
