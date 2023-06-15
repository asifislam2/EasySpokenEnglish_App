package com.tanjilislam.easyspokenenglisg;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.animations.Toss;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class Fragment_Home extends Fragment {

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__home, container, false);
        ImageSlider image_slider = view.findViewById(R.id.image_slider);
        CardView aboutme_BTN = view.findViewById(R.id.aboutme_BTN);
        TextView Zerolevel_BTN = view.findViewById(R.id.Zerolevel_BTN);
        CardView beginerr_BTN = view.findViewById(R.id.beginerr_BTN);
        CardView advance_level = view.findViewById(R.id.advance_level);
        CardView ielts_preparition = view.findViewById(R.id.ielts_preparition);

        aboutme_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), About.class);
                startActivity(intent);

            }
        });

        Zerolevel_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(getActivity(), ZeroLevel.class);
               startActivity(intent);

            }
        });

        beginerr_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Begineer_Level.class);
                startActivity(intent);

            }
        });

        advance_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Advance_level.class);
                startActivity(intent);

            }
        });

        ielts_preparition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(getActivity(), Ielts_Preparition.class);
               startActivity(intent);

            }
        });

        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.homebanner_img, null));
        imageList.add(new SlideModel(R.drawable.slider_img, null));
        imageList.add(new SlideModel(R.drawable.slider_img_one, null));
        imageList.add(new SlideModel(R.drawable.homebanner_img, null));
        image_slider.setImageList(imageList, ScaleTypes.CENTER_CROP);




        return view;
    }
}