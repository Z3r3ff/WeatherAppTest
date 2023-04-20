package com.example.app_android_weather.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.app_android_weather.Database.DatabaseHelper;
import com.example.app_android_weather.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "User";
    public static final String SQL_User = "CREATE TABLE User(idUser INTEGER primary key AUTOINCREMENT, username text, password text, name text, phoneNumber text, email text);";
    public static final String TAG = "UserDao";

    public UserDao(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // insert
    public static int insertUser(User u){
        ContentValues values = new ContentValues();
        values.put("username", u.getUsername());
        values.put("password", u.getPassword());
        values.put("name", u.getName());
        values.put("phoneNumber", u.getPhonenumber());
        values.put("email", u.getEmail());
        try {
            if(db.insert(TABLE_NAME, null, values)==-1){
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    // getAll
    public static List<User> getAllUser(){
        List<User> li = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            User u = new User();
            u.setId(c.getInt(0));
            u.setUsername(c.getString(1));
            u.setPassword(c.getString(2));
            u.setName(c.getString(3));
            u.setPhonenumber(c.getString(4));
            u.setEmail(c.getString(5));
            li.add(u);
            Log.d("//====", u.toString());
            c.moveToNext();
        }
        c.close();
        return li;
    }

    // get by id
    public User getUserById(int idUser){
        User u = null;
        String selection = "idUser=?";
        String[] selectionArgs = {String.valueOf(idUser)};
        Cursor c = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        Log.d("getUserById", "===>" + c.getCount());
        c.moveToFirst();
        while (c.isAfterLast()==false){
            u = new User();
            u.setId(c.getInt(0));
            u.setUsername(c.getString(1));
            u.setPassword(c.getString(2));
            u.setName(c.getString(3));
            u.setPhonenumber(c.getString(4));
            u.setEmail(c.getString(5));
            break;
        }
        c.close();
        return u;
    }

    // update
    public int updatePass(User u){
        ContentValues values = new ContentValues();
        values.put("username", u.getUsername());
        values.put("password", u.getPassword());
        int result = db.update(TABLE_NAME, values, "username=?", new String[]{u.getUsername()});
        if(result == 0){
            return -1;
        }
        return 1;
    }
}
