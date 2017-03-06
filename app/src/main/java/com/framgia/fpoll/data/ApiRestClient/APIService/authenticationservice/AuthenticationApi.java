package com.framgia.fpoll.data.ApiRestClient.APIService.authenticationservice;

import com.framgia.fpoll.data.ApiRestClient.APIService.ResponseItem;
import com.framgia.fpoll.data.model.LoginNormalBody;
import com.framgia.fpoll.data.model.LoginNormalData;
import com.framgia.fpoll.data.model.SocialData;
import com.framgia.fpoll.data.model.User;
import com.framgia.fpoll.util.Constant;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by tuanbg on 3/2/17.
 */
public class AuthenticationApi {
    private static String KEY_AVATAR = "avatar";

    public interface RegisterService {
        @POST("api/v1/register")
        @Multipart
        Call<ResponseItem<User>> registerUser(
            @Part("email") RequestBody email,
            @Part("name") RequestBody name,
            @Part("password") RequestBody password,
            @Part("password_confirmation")
                RequestBody passwordConfirmation,
            @Part("gender") RequestBody gender,
            @Part MultipartBody.Part file);
    }

    public interface LoginService {
        @GET("api/v1/loginSocial")
        Call<ResponseItem<SocialData>> loginSocial(
            @Query("provider") String provider,
            @Query("token") String token);
        @POST("api/v1/login")
        Call<ResponseItem<LoginNormalData>> loginNormal(@Body LoginNormalBody user);
    }

    public static MultipartBody.Part getAvatar(User user) {
        if (user.getAvatar() != null) {
            File file = new File(user.getAvatar());
            return MultipartBody.Part.createFormData(KEY_AVATAR,
                file.getName(), RequestBody.create(MediaType.parse(Constant.TYPE_IMAGE), file));
        }
        return null;
    }
}
