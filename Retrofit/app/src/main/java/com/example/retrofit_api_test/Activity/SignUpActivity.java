package com.example.retrofit_api_test.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofit_api_test.Api.ApiService;
import com.example.retrofit_api_test.Model.SignUp;
import com.example.retrofit_api_test.R;
import com.example.retrofit_api_test.Respone.LoginRespone;
import com.example.retrofit_api_test.Respone.SignUpRespone;
import com.example.retrofit_api_test.Respone.UserData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText tvEmail;
    private EditText tvPwd;
    private EditText tvComfpwd;
    private Button btnsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        tvEmail = findViewById(R.id.textview_email_signup);
        tvPwd = findViewById(R.id.textview_pwd_signup);
        tvComfpwd = findViewById(R.id.textview_cfpwd_signup);
        btnsignup = findViewById(R.id.btn_signup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = tvEmail.getText().toString().trim();
                String password = tvPwd.getText().toString().trim();
                String cfpassword = tvComfpwd.getText().toString().trim();
                if(email.isEmpty()){
                    tvEmail.setError("Email required !");
                    tvEmail.requestFocus();
                    return;
                }
                if(password.isEmpty()) {
                    tvPwd.setError("Password required !");
                    tvPwd.requestFocus();
                    return;
                }
                if(cfpassword.isEmpty()) {
                    tvComfpwd.setError("Please conform password !");
                    tvComfpwd.requestFocus();
                    return;
                }
                if(password.equals(cfpassword)){
                    SignUp();
                }
                else {
                    tvPwd.setText("");
                    tvComfpwd.setText("");
                    tvPwd.requestFocus();
                    Toast.makeText(SignUpActivity.this, "Passwords does not match !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void SignUp(){
        String email = tvEmail.getText().toString().trim();
        String password = tvPwd.getText().toString().trim();
        String cfpassword = tvComfpwd.getText().toString().trim();
        SignUp signUp = new SignUp(email, password);
        ApiService.apiservice.signUp(signUp).enqueue(new Callback<SignUpRespone>() {
            @Override
            public void onResponse(Call<SignUpRespone> call, Response<SignUpRespone> response) {
                String s = null;
                if(response.isSuccessful()){
                    SignUpRespone signUpsuccess = response.body();
                    Toast.makeText(SignUpActivity.this, signUpsuccess.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    try {
                        s = response.errorBody().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            Toast.makeText(SignUpActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<SignUpRespone> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Call API false" ,Toast.LENGTH_LONG).show();
            }
        });
    }
}
