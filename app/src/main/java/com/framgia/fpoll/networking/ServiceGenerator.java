package com.framgia.fpoll.networking;

import android.content.Context;
import com.framgia.fpoll.FPollApplication;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.util.SharePreferenceUtil;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.framgia.fpoll.util.Constant.ConstantApi.BASE_URL;
import static com.framgia.fpoll.util.Constant.TIME_OUT_SERVER;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by tuanbg on 3/2/17.
 */
public class ServiceGenerator {
    private static OkHttpClient.Builder sHttpClient;
    private static Retrofit.Builder sBuilder = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static HttpLoggingInterceptor sLoggingInterceptor = new HttpLoggingInterceptor();

    public static <S> S createService(Class<S> serviceClass) {
        if (sHttpClient == null) {
            sHttpClient = new OkHttpClient.Builder();
            sHttpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Accept", "application/json")
                            .method(original.method(), original.body());
                    String token = getToken();
                    if (token != null) {
                        requestBuilder.header("Authorization", "Bearer " + token);
                    }
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
            sLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            sHttpClient.connectTimeout(TIME_OUT_SERVER, SECONDS);
            sHttpClient.addNetworkInterceptor(sLoggingInterceptor);
        }

        OkHttpClient client = sHttpClient.build();
        Retrofit retrofit = sBuilder.client(client).build();
        return retrofit.create(serviceClass);
    }

    private static String getToken() {
        Context context = FPollApplication.getContext();
        if (context == null) return null;
        SharePreferenceUtil sharePreferenceUtil = SharePreferenceUtil.getIntances(context);
        User user = sharePreferenceUtil.getUser();
        return user != null ? user.getToken() : null;
    }
}
