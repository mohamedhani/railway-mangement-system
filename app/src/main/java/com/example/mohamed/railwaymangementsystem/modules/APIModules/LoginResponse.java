package com.example.mohamed.railwaymangementsystem.modules.APIModules;

/**
 * Created by mohamed on 7/3/2019.
 */

public class LoginResponse {
    String message;
    String token;

    public LoginResponse(String message, String jwt) {
        this.message = message;
        this.token = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

