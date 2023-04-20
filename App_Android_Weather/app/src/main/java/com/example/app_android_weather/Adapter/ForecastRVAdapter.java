package com.example.app_android_weather.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.example.app_android_weather.Model.ForecastRVModal;
import com.example.app_android_weather.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ForecastRVAdapter extends RecyclerView.Adapter<ForecastRVAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ForecastRVModal> forecastRVModalArrayList;

    public ForecastRVAdapter(Context context, ArrayList<ForecastRVModal> forecastRVModalArrayList) {
        this.context = context;
        this.forecastRVModalArrayList = forecastRVModalArrayList;
    }

    @NonNull
    @Override
    public ForecastRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.forecast_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastRVAdapter.ViewHolder holder, int position) {
        ForecastRVModal modal = forecastRVModalArrayList.get(position);
        holder.temperatureTV.setText(modal.getTemperature()+"°C");
        holder.conditionTV.setText(modal.getCondition());
        holder.humidityTV.setText(modal.getHumidity()+"%");
        Picasso.get().load("http:".concat(modal.getIcon())).into(holder.iconIV);
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date t = input.parse(modal.getTime());
            holder.timeTV.setText(output.format(t));
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return forecastRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView temperatureTV, conditionTV, timeTV, humidityTV;
        private ImageView iconIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            temperatureTV = itemView.findViewById(R.id.idTVTemperature);
            conditionTV = itemView.findViewById(R.id.idTVCondition);
            timeTV = itemView.findViewById(R.id.idTVTime);
            humidityTV = itemView.findViewById(R.id.idTVHumi);
            iconIV = itemView.findViewById(R.id.idIVCondition);
        }
    }
}

