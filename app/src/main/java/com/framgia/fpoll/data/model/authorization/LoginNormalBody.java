package com.framgia.fpoll.data.model.authorization;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <.
 */
public class LoginNormalBody {
    @SerializedName("email")
    private String mEmail;
    @SerializedName("password")
    private String mPassword;

    public LoginNormalBody(String email, String password) {
        mEmail = email;
        mPassword = password;
    }
}
