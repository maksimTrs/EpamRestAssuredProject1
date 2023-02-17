package com.spotify.oauth2.api;

public enum StatusCode {

    CODE_200(200, ""),
    CODE_201(201, ""),
    CODE_400(400, "Missing required field: name"),
    CODE_401(401, "Invalid access token");

    public final int CODE;
    public final String MSG;

    StatusCode(int CODE, String MSG) {
        this.CODE = CODE;
        this.MSG = MSG;
    }

/*    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }*/
}
