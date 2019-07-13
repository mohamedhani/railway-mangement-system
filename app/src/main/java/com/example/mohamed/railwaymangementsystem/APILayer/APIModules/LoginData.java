package com.example.mohamed.railwaymangementsystem.APILayer.APIModules;

/**
 * Created by mohamed on 7/4/2019.
 */

public class LoginData {
    String email, password;

    public LoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }

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
}
