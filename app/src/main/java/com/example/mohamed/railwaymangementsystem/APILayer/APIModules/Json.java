package com.example.mohamed.railwaymangementsystem.APILayer.APIModules;

/**
 * Created by mohamed on 7/6/2019.
 */

public class Json
{
    String jwt;

    public Json(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
