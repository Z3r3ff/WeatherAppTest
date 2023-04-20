package com.example.app_android_weather.Controller;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_android_weather.Adapter.ForecastRVAdapter;
import com.example.app_android_weather.Model.ForecastRVModal;
import com.example.app_android_weather.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ForecastActivity extends AppCompatActivity {

    private RelativeLayout forecastRL;
    private ProgressBar loadingPB;
    private ImageView backForecastIV;
    private RecyclerView forecastDaysRV;
    private ArrayList<ForecastRVModal> forecastRVModalArrayList;
    private ForecastRVAdapter forecastRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecastdays);
        forecastRL = findViewById(R.id.idRLForecast);
        loadingPB = findViewById(R.id.idPBLoading);
        backForecastIV = findViewById(R.id.idIVBackForecast);
        forecastDaysRV = findViewById(R.id.idRVForecastdays);
        forecastRVModalArrayList = new ArrayList<>();
        forecastRVAdapter = new ForecastRVAdapter(this, forecastRVModalArrayList);
        forecastDaysRV.setAdapter(forecastRVAdapter);

        Bundle extras = getIntent().getExtras();
        String city = "";
        if (extras != null) {
            city = extras.getString("cityName");
            getForecastInfo(city);
        }
        else{
            Toast.makeText(this,"Do not receive city name", Toast.LENGTH_SHORT).show();
        }
    }

    public void getForecastInfo(String cityName){
        String url ="http://api.weatherapi.com/v1/forecast.json?key=ffba3f2df5d34d6ea95112718231604&q="+ cityName +"&days=10&aqi=no&alerts=no";
        RequestQueue requestQueue = Volley.newRequestQueue(ForecastActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loadingPB.setVisibility(View.GONE);
                forecastRL.setVisibility(View.VISIBLE);
                forecastRVModalArrayList.clear();

                try {
                    int isDay = response.getJSONObject("current").getInt("is_day");
                    if (isDay==1){
                        Picasso.get().load("https://cdn.dribbble.com/users/925716/screenshots/3333720/attachments/722376/after_noon.png").into(backForecastIV);
                    } else {
                        Picasso.get().load("https://cdn.dribbble.com/users/925716/screenshots/3333720/attachments/722375/night.png").into(backForecastIV);
                    }
                    JSONObject forecastObj = response.getJSONObject("forecast");
                    JSONArray forecastDays = forecastObj.getJSONArray("forecastday");
                    for (int i =0; i < forecastDays.length(); i++){
                        JSONObject dayObj = forecastDays.getJSONObject(i);
                        String time = dayObj.getString("date");
                        String temper = dayObj.getJSONObject("day").getString("avgtemp_c");
                        String icon = dayObj.getJSONObject("day").getJSONObject("condition").getString("icon");
                        String condition = dayObj.getJSONObject("day").getJSONObject("condition").getString("text");
                        String humi = dayObj.getJSONObject("day").getString("avghumidity");
                        forecastRVModalArrayList.add(new ForecastRVModal(temper,icon,condition,time,humi));

                    }
                    forecastRVAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ForecastActivity.this, "Please Enter City Name", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
