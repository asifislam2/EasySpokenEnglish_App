package com.tanjilislam.easyspokenenglisg;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Load_Data extends AppCompatActivity {

    TextView display_Title,display_content;
    public static String DISPLAY_TITLE = "";
    public static String DISPLAY_CONTENT = "";

    ImageView copy,share,rate_us;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);

        display_Title = findViewById(R.id.display_Title);
        display_content = findViewById(R.id.display_content);

        display_Title.setText(DISPLAY_TITLE);
        display_content.setText(DISPLAY_CONTENT);

        copy = findViewById(R.id.copy);
        share = findViewById(R.id.share);
        rate_us = findViewById(R.id.rate_us);


        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboardManager = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("nonsense_data",
                        display_content.getText().toString());
                clipboardManager.setPrimaryClip(clipData);

                Vibrator va = (Vibrator) Load_Data.this.getSystemService(Context.VIBRATOR_SERVICE);
                va.vibrate(100);

                Toast.makeText(Load_Data.this, "Copied", Toast.LENGTH_SHORT).show();

            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareTextOnly(display_content.getText().toString());

            }
        });


        rate_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=PackageName")));
            }
        });


    }

    private void shareTextOnly(String titlee) {
        String sharebody = titlee;

        // The value which we will sending through data via
        // other applications is defined
        // via the Intent.ACTION_SEND
        Intent intentt = new Intent(Intent.ACTION_SEND);

        // setting type of data shared as text
        intentt.setType("text/plain");
        intentt.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");

        // Adding the text to share using putExtra
        intentt.putExtra(Intent.EXTRA_TEXT, sharebody);
        startActivity(Intent.createChooser(intentt, "Share Via"));
    }
}