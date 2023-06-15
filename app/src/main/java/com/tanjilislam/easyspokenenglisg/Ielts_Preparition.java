package com.tanjilislam.easyspokenenglisg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import soup.neumorphism.NeumorphCardView;

public class Ielts_Preparition extends AppCompatActivity {

    ProgressBar progressbar;
    RecyclerView recyclerView_ielts;

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ielts_preparition);

        recyclerView_ielts = findViewById(R.id.recyclerView_ielts);

        IIAdapter iiAdapter = new IIAdapter();
        recyclerView_ielts.setAdapter(iiAdapter);
        recyclerView_ielts.setLayoutManager( new LinearLayoutManager(Ielts_Preparition.this));


        myhasmap();

    }

    public class IIAdapter extends RecyclerView.Adapter<IIAdapter.IIHolder>{

        public class IIHolder extends RecyclerView.ViewHolder{

            TextView Tv_Display_home,display_number;
            NeumorphCardView click_button;
            public IIHolder(@NonNull View itemView) {
                super(itemView);

                Tv_Display_home = itemView.findViewById(R.id.Tv_Display_home);
                display_number = itemView.findViewById(R.id.display_number);
                click_button = itemView.findViewById(R.id.click_button);

            }
        }

        @NonNull
        @Override
        public IIHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_one, parent,false);

            return new IIHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull IIHolder holder, int position) {

            HashMap<String, String> hashMap = arrayList.get(position);
            String TITLE = hashMap.get("title");
            String POSITION = hashMap.get("position");
            String KEY = hashMap.get("key");

            holder.display_number.setText(POSITION);
            holder.Tv_Display_home.setText(TITLE);

            holder.click_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (KEY.contains("recentPost")){

                        Intent intent = new Intent(Ielts_Preparition.this, Recent_post.class);
                        startActivity(intent);
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(60);


                    } else if (KEY.contains("Reading_Part")) {


                        Intent intent = new Intent(Ielts_Preparition.this, Reading.class);
                        startActivity(intent);
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(60);


                    } else if (KEY.contains("Writing_Part")) {

                        Intent intent = new Intent(Ielts_Preparition.this, Writing.class);
                        startActivity(intent);
                        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibs.vibrate(60);

                    }


                }

            });

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

    }

    public void myhasmap(){

        hashMap = new HashMap<>();
        hashMap.put("key", "recentPost");
        hashMap.put("position", "1");
        hashMap.put("title", "Recent Post");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("key", "Reading_Part");
        hashMap.put("position", "2");
        hashMap.put("title", "Reading Part");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("key", "Writing_Part");
        hashMap.put("position", "3");
        hashMap.put("title", "Writing Part");
        arrayList.add(hashMap);


    }
}