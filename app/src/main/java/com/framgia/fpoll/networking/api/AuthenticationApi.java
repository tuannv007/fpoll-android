package com.framgia.fpoll.networking.api;

import com.framgia.fpoll.data.model.authorization.LoginNormalBody;
import com.framgia.fpoll.data.model.authorization.LoginNormalData;
import com.framgia.fpoll.data.model.authorization.SocialData;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.util.Constant;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by tuanbg on 3/2/17.
 */
public class AuthenticationApi {
    private static String KEY_AVATAR = "avatar";

    public static MultipartBody.Part getAvatar(User user) {
        if (user.getAvatar() != null) {
            File file = new File(user.getAvatar());
            if (!file.exists()) return null;
            return MultipartBody.Part.createFormData(KEY_AVATAR, file.getName(),
                    RequestBody.create(MediaType.parse(Constant.TYPE_IMAGE), file));
        }
        return null;
    }

    public interface RegisterService {
        @POST("api/v1/register")
        @Multipart
        Call<ResponseItem<User>> registerUser(@Part("email") RequestBody email,
                @Part("name") RequestBody name, @Part("password") RequestBody password,
                @Part("password_confirmation") RequestBody passwordConfirmation,
                @Part("gender") RequestBody gender, @Part MultipartBody.Part file);
    }

    public interface LoginService {
        @GET("api/v1/loginSocial")
        Call<ResponseItem<SocialData>> loginSocial(@Query("token") String token,
                @Query("secret") String secret, @Query("provider") String provider);

        @POST("api/v1/login")
        Call<ResponseItem<LoginNormalData>> loginNormal(@Body LoginNormalBody user);

        @POST("api/v1/logout")
        Call<ResponseItem> logout(@Header("Authorization") String token);

        @POST("api/v1/updateProfile")
        @Multipart
        Call<ResponseItem<SocialData>> updateProfile(@Part("name") RequestBody name,
                @Part("email") RequestBody email, @Part("password") RequestBody password,
                @Part("gender") RequestBody gender, @Part("chatwork_id") RequestBody chatWorkId,
                @Part MultipartBody.Part file);

        @POST("api/v1/password/reset")
        @Multipart
        Call<ResponseItem> resetPassword(@Part("email") RequestBody email);

        @GET("/api/v1/getProfile")
        Call<ResponseItem<User>> getProfile(@Header("Authorization") String token);
    }
}
