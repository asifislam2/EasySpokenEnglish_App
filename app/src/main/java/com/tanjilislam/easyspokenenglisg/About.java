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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class About extends AppCompatActivity {

    ProgressBar progressbar;
    TextView display_content, aboutAll_SEC;
    ImageView copy,share,rate_us;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        progressbar = findViewById(R.id.progressbar);
        display_content = findViewById(R.id.display_content);
        aboutAll_SEC = findViewById(R.id.aboutAll_SEC);
        copy = findViewById(R.id.copy);
        share = findViewById(R.id.share);
        rate_us = findViewById(R.id.rate_us);


        progressbar.setVisibility(View.VISIBLE);
        String URL = "https://travelerincanada.com/about.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                progressbar.setVisibility(View.GONE);

                try {
                    JSONObject jsonObject = response.getJSONObject(0);

                    String ABOUT_ALL_SEC = jsonObject.getString("aboutAllsec");
                    String ABOUT_CONTENT = jsonObject.getString("aboutContent");

                    aboutAll_SEC.setText(ABOUT_ALL_SEC);
                    display_content.setText(ABOUT_CONTENT);

                    copy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            ClipboardManager clipboardManager = (ClipboardManager)
                                    getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clipData = ClipData.newPlainText("nonsense_data",
                                    display_content.getText().toString());
                            clipboardManager.setPrimaryClip(clipData);

                            Vibrator va = (Vibrator) About.this.getSystemService(Context.VIBRATOR_SERVICE);
                            va.vibrate(100);

                            Toast.makeText(About.this, "Copied", Toast.LENGTH_SHORT).show();

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




                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(About.this);
        requestQueue.add(jsonArrayRequest);





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