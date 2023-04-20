package com.example.app_android_weather.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_android_weather.Dao.UserDao;
import com.example.app_android_weather.Model.User;
import com.example.app_android_weather.R;

public class SignUpActivity extends AppCompatActivity {
    UserDao userDao;
    EditText txtusername, txtname, txtemail, txtphonenumber, txtpass, txtcheckpass;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        txtusername = findViewById(R.id.txtusername);
        txtname = findViewById(R.id.txtname);
        txtemail = findViewById(R.id.txtemail);
        txtphonenumber = findViewById(R.id.txtphonenumber);
        txtpass = findViewById(R.id.txtpass);
        txtcheckpass = findViewById(R.id.txtcheckpass);
        btnSignup = findViewById(R.id.btnSignup);
    }
    public void register(View view){
        boolean ok = false;
        if(txtusername.getText().toString().isEmpty() || txtname.getText().toString().isEmpty() || txtemail.getText().toString().isEmpty() || txtphonenumber.getText().toString().isEmpty() || txtpass.getText().toString().isEmpty() || txtcheckpass.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Information cannot be left empty!!!", Toast.LENGTH_SHORT).show();
        }else{
            if(txtpass.getText().toString().compareTo(txtcheckpass.getText().toString())!=0){
                Toast.makeText(getApplicationContext(), "Password does not match!!!", Toast.LENGTH_SHORT).show();
            }else{
                userDao = new UserDao(SignUpActivity.this);
                User u = new User(txtusername.getText().toString(), txtpass.getText().toString(), txtname.getText().toString(), txtemail.getText().toString(), txtphonenumber.getText().toString());
                try {
                    if (UserDao.insertUser(u)>0){
                        Toast.makeText(getApplicationContext(), "Register success!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Register Fail!!!", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Log.e("Error", ex.toString());
                }
            }
        }
    }
}
