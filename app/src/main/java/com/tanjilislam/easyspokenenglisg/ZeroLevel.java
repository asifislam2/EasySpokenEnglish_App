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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class ZeroLevel extends AppCompatActivity {

    ProgressBar progressbar;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap = new HashMap<>();
    RecyclerView recyclerView_zeroLevel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zero_level);

        recyclerView_zeroLevel = findViewById(R.id.recyclerView_zeroLevel);
        progressbar = findViewById(R.id.progressbar);

        progressbar.setVisibility(View.VISIBLE);
        String DATA_URL= "https://travelerincanada.com/zerolevel.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, DATA_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressbar.setVisibility(View.GONE);
                for (int i=0; i<response.length(); i++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String ID = jsonObject.getString("id");
                        String ITEM_TITLE = jsonObject.getString("title");
                        String ITEM_CONTENT = jsonObject.getString("description");

                        hashMap = new HashMap<>();
                        hashMap.put("id", ID);
                        hashMap.put("item_title", ITEM_TITLE);
                        hashMap.put("item_content", ITEM_CONTENT);
                        arrayList.add(hashMap);

                        ZAdapter adapter = new ZAdapter();
                        recyclerView_zeroLevel.setAdapter(adapter);
                        recyclerView_zeroLevel.setLayoutManager( new LinearLayoutManager(ZeroLevel.this));

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(ZeroLevel.this);
        requestQueue.add(jsonArrayRequest);


    }

    public class ZAdapter extends RecyclerView.Adapter<ZAdapter.ZHolder>{

        public class ZHolder extends RecyclerView.ViewHolder{

            TextView id_display,title_display;
            CardView item_click;
            public ZHolder(@NonNull View itemView) {
                super(itemView);

                id_display = itemView.findViewById(R.id.id_display);
                title_display = itemView.findViewById(R.id.title_display);
                item_click = itemView.findViewById(R.id.item_click);

            }
        }

        @NonNull
        @Override
        public ZHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item, parent, false);
            return new ZHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ZHolder holder, int position) {


            HashMap<String, String> hashMap = arrayList.get(position);
            String ID = hashMap.get("id");
            String ITEM_TITLE = hashMap.get("item_title");
            String ITEM_CONTENT = hashMap.get("item_content");

            holder.title_display.setText(ITEM_TITLE);
            holder.id_display.setText(ID);
            holder.item_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Load_Data.DISPLAY_TITLE = ITEM_TITLE;
                    Load_Data.DISPLAY_CONTENT = ITEM_CONTENT;
                    Intent intent = new Intent(ZeroLevel.this, Load_Data.class);
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