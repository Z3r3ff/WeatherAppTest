package com.example.app_android_weather.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.app_android_weather.Dao.UserDao;
import com.example.app_android_weather.Model.User;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    List<User> arrUser;
    public Activity context;
    public LayoutInflater inflater;
    UserDao ud;

    public UserAdapter(Activity context, List<User> arrUser){
        super();
        this.context = context;
        this.arrUser = arrUser;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ud = new UserDao(context);
    }

    @Override
    public int getCount() {
        return arrUser.size();
    }

    @Override
    public Object getItem(int i) {
        return arrUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
