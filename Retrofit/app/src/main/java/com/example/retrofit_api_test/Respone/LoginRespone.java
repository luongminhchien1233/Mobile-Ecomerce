package com.example.retrofit_api_test.Respone;

public class LoginRespone {
    private boolean success;
    private UserData data;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginRespone(boolean success, UserData data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }
}
