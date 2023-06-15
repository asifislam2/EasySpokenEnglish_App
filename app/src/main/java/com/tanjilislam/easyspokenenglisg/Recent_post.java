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

public class Recent_post extends AppCompatActivity {


    ProgressBar progressbar;
    RecyclerView recyclerView_recent_post;

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap = new HashMap<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_post);


        recyclerView_recent_post = findViewById(R.id.recyclerView_recent_post);
        progressbar = findViewById(R.id.progressbar);



        progressbar.setVisibility(View.VISIBLE);

        String URL = "https://travelerincanada.com/recentpost.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressbar.setVisibility(View.GONE);
                try {

                    for (int x=0; x<response.length(); x++){
                        JSONObject jsonObject = response.getJSONObject(x);
                        String ID = jsonObject.getString("id");
                        String TITLE = jsonObject.getString("title");
                        String DESCRIPTION = jsonObject.getString("desc");

                        hashMap = new HashMap<>();
                        hashMap.put("id", ID);
                        hashMap.put("title", TITLE);
                        hashMap.put("des", DESCRIPTION);
                        arrayList.add(hashMap);

                        RRAdapter adapter = new RRAdapter();
                        recyclerView_recent_post.setAdapter(adapter);
                        recyclerView_recent_post.setLayoutManager( new LinearLayoutManager(Recent_post.this));

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

        RequestQueue queue = Volley.newRequestQueue(Recent_post.this);
        queue.add(jsonArrayRequest);


    }

    public class RRAdapter extends RecyclerView.Adapter<RRAdapter.RRHolder>{

        public class RRHolder extends RecyclerView.ViewHolder{

            TextView id_display,title_display;
            CardView item_click;
            public RRHolder(@NonNull View itemView) {
                super(itemView);

                id_display = itemView.findViewById(R.id.id_display);
                title_display = itemView.findViewById(R.id.title_display);
                item_click = itemView.findViewById(R.id.item_click);

            }
        }


        @NonNull
        @Override
        public RRHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item, parent, false);

            return new RRHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RRHolder holder, int position) {

            HashMap<String, String> hashMap = arrayList.get(position);
            String ID = hashMap.get("id");
            String TITLE = hashMap.get("title");
            String DESCRIPTION = hashMap.get("des");

            holder.id_display.setText(ID);
            holder.title_display.setText(TITLE);

            holder.item_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Load_Data.DISPLAY_TITLE = TITLE;
                    Load_Data.DISPLAY_CONTENT = DESCRIPTION;
                    Intent intent = new Intent(Recent_post.this, Load_Data.class);
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