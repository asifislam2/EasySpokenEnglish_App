package com.tanjilislam.easyspokenenglisg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Begineer_Level extends AppCompatActivity {

    ProgressBar progressbar;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap = new HashMap<>();
    RecyclerView recyclerView_bigeneer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begineer_level);

        recyclerView_bigeneer = findViewById(R.id.recyclerView_bigeneer);
        progressbar = findViewById(R.id.progressbar);

        progressbar.setVisibility(View.VISIBLE);
        String URL = "https://travelerincanada.com/beginnerlevel.json";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressbar.setVisibility(View.GONE);

                try {

                    for (int x=0; x<response.length(); x++){

                        JSONObject jsonObject = response.getJSONObject(x);
                        String ID = jsonObject.getString("id");
                        String TITLE = jsonObject.getString("title");
                        String DESCRIPTION = jsonObject.getString("description");

                        hashMap = new HashMap<>();
                        hashMap.put("id", ID);
                        hashMap.put("title", TITLE);
                        hashMap.put("desc", DESCRIPTION);
                        arrayList.add(hashMap);

                        BAdapter adapter = new BAdapter();
                        recyclerView_bigeneer.setAdapter(adapter);
                        recyclerView_bigeneer.setLayoutManager( new LinearLayoutManager(Begineer_Level.this));


                    }


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(Begineer_Level.this);
        requestQueue.add(jsonArrayRequest);

    }

    public class BAdapter extends RecyclerView.Adapter <BAdapter.BHolder>{

        public class BHolder extends RecyclerView.ViewHolder{

            TextView id_display,title_display;
            CardView item_click;
            public BHolder(@NonNull View itemView) {
                super(itemView);

                title_display = itemView.findViewById(R.id.title_display);
                id_display = itemView.findViewById(R.id.id_display);
                item_click = itemView.findViewById(R.id.item_click);
            }
        }

        @NonNull
        @Override
        public BHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item, parent, false);
            return new BHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BHolder holder, int position) {

            HashMap<String, String> hashMap = arrayList.get(position);
            String ID = hashMap.get("id");
            String TITLE = hashMap.get("title");
            String DESCRIPTION = hashMap.get("desc");

            holder.id_display.setText(ID);
            holder.title_display.setText(TITLE);

            holder.item_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Load_Data.DISPLAY_TITLE = TITLE;
                    Load_Data.DISPLAY_CONTENT = DESCRIPTION;
                    Intent intent = new Intent(Begineer_Level.this, Load_Data.class);
                    startActivity(intent);

                    Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibs.vibrate(60);

                }
            });

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }


    }
}