package com.example.retrofit_api_test.Model;

public class SignUp {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignUp(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
