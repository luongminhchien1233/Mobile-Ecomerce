package com.example.retrofit_api_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofit_api_test.Api.ApiService;
import com.example.retrofit_api_test.Model.Login;
import com.example.retrofit_api_test.Respone.LoginRespone;
import com.example.retrofit_api_test.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPwd;

    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEmail = findViewById(R.id.txtview_email);
        txtPwd = findViewById(R.id.textview_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPost();
            }
        });
    }

    private void sendPost() {
        String email = txtEmail.getText().toString().trim();
        String password = txtPwd.getText().toString().trim();
        Login login = new Login(email, password);
        ApiService.apiservice.logIn(login).enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                String s = null;
                if(response.isSuccessful()){
                    LoginRespone loginsucess = response.body();
                    Toast.makeText(LoginActivity.this, loginsucess.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    try {
                        s = response.errorBody().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Call API false" ,Toast.LENGTH_LONG).show();
            }
        });
    }

}