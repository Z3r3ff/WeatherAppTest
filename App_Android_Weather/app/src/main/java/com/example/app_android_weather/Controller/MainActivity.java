package com.example.app_android_weather.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_android_weather.Adapter.WeatherRVAdapter;
import com.example.app_android_weather.Model.WeatherRVModal;
import com.example.app_android_weather.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtTemperature, txtWet, txtCloud, txtWind, txtSunrise, txtSundown, txtCity, txtSky, txtDaytime;
    EditText edtTimKiem;
    ImageView imgWeather;
    Button btnForecast;
    private ArrayList<WeatherRVModal> weatherRVModalArrayList;
    private WeatherRVAdapter weatherRVAdapter;
    private RecyclerView weatherRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mapping();
    }

    public void mapping(){
        txtTemperature = findViewById(R.id.txtTemperature);
        txtDaytime = findViewById(R.id.txtDaytime);
        txtWet = findViewById(R.id.txtWet);
        txtCloud = findViewById(R.id.txtCloud);
        txtWind = findViewById(R.id.txtWind);
        txtSunrise = findViewById(R.id.txtSunrise);
        txtSundown = findViewById(R.id.txtSundown);
        imgWeather = findViewById(R.id.imgWeather);
        btnForecast  =findViewById(R.id.btnForecast);
        txtSky = findViewById(R.id.txtSky);
        txtCity = findViewById(R.id.txtCity);

        weatherRV = findViewById(R.id.idRvWeather);
        edtTimKiem = findViewById(R.id.edtTimKiem);
        weatherRVModalArrayList = new ArrayList<>();
        weatherRVAdapter = new WeatherRVAdapter(this, weatherRVModalArrayList);
        weatherRV.setAdapter(weatherRVAdapter);
        if (edtTimKiem.getText().toString().isEmpty()){
            getWeatherInfo("Ha Noi");
        }

        btnForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String city = txtCity.getText().toString();
                Intent intent = new Intent(MainActivity.this, ForecastActivity.class);
//                intent.putExtra("cityName", city);
                intent.putExtra("cityName", "Ha Noi");
                startActivity(intent);
            }
        });
    }

    private void getWeatherInfo(String cityName){
        String url = "http://api.weatherapi.com/v1/forecast.json?key=ffba3f2df5d34d6ea95112718231604&q="+cityName+"&days=1&aqi=no&alerts=no";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                weatherRVModalArrayList.clear();
                try {
                    JSONObject jsonObjectLocation = response.getJSONObject("location");
                    String name = jsonObjectLocation.getString("name");
                    String country = jsonObjectLocation.getString("country"); // ten quoc gia
                    String city = name + ", " + country;
                    txtCity.setText(city);

                    JSONObject jsonObjectWeather = response.getJSONObject("current");
                    String temp = jsonObjectWeather.getString("temp_c");
                    String humi = jsonObjectWeather.getString("humidity");
                    String cloud = jsonObjectWeather.getString("cloud");
                    String wind = jsonObjectWeather.getString("wind_kph");
                    txtTemperature.setText(temp + " °C");
                    txtWet.setText(humi + " %");
                    txtWind.setText(wind + " km/h");
                    txtCloud.setText(cloud + " %");
                    String icon = jsonObjectWeather.getJSONObject("condition").getString("icon");
                    Picasso.get().load("http:".concat(icon)).into(imgWeather);
                    String status = jsonObjectWeather.getJSONObject("condition").getString("text");
                    txtSky.setText(status);

                    JSONObject forecastObj = response.getJSONObject("forecast");
                    JSONObject forecastDays = forecastObj.getJSONArray("forecastday").getJSONObject(0);
                    String day = forecastDays.getString("date");
                    txtDaytime.setText(day);
                    String sunrise = forecastDays.getJSONObject("astro").getString("sunrise");
                    String sunset = forecastDays.getJSONObject("astro").getString("sunset");
                    txtSunrise.setText(sunrise);
                    txtSundown.setText(sunset);

                    JSONArray hourArray = forecastDays.getJSONArray("hour");
                    for (int i =0; i < hourArray.length(); i++){
                        JSONObject hourObj = hourArray.getJSONObject(i);
                        String time = hourObj.getString("time");
                        String temper = hourObj.getString("temp_c");
                        String img = hourObj.getJSONObject("condition").getString("icon");
                        String windy = hourObj.getString("wind_kph");
                        weatherRVModalArrayList.add(new WeatherRVModal(time,temper,img,windy));
                    }
                    weatherRVAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Please Enter City Name", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}