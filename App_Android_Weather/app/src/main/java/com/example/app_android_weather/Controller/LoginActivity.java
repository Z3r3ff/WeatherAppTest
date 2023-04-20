package com.example.app_android_weather.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_android_weather.Dao.UserDao;
import com.example.app_android_weather.Model.User;
import com.example.app_android_weather.R;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    TextView txt_signup;
    CheckBox chkRememberPass;
    Button btn_login;
    String strUsername, strPass;
    UserDao ud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btn_login = findViewById(R.id.btn_login);

        ud = new UserDao(LoginActivity.this);
    }

    public void checkLogin(View v){
        strUsername = edUsername.getText().toString();
        strPass = edPassword.getText().toString();
        if(strUsername.isEmpty() || strPass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Username and password cannot be left blank!!!", Toast.LENGTH_SHORT).show();
        }else{
            boolean ok = false;
            List<User> li = UserDao.getAllUser();
            for (User u: li){
                if(strUsername.equalsIgnoreCase(u.getUsername()) && strPass.equalsIgnoreCase(u.getPassword())){
                    Toast.makeText(getApplicationContext(),"Login successfull!!!",Toast.LENGTH_SHORT).show();
                    ok=true;
                    finish();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
            if(ok==false){
                Toast.makeText(getApplicationContext(),"Username and password are incorrect!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void signup(View view){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

}
