package com.framgia.fpoll.ui.authenication.login;

import android.app.Activity;

import com.framgia.fpoll.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import java.lang.ref.WeakReference;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Nhahv0902 on 2/21/2017.
 * <></>
 */
public class FPollTwitterAuthClient {
    private static FPollTwitterAuthClient mInstance;
    private WeakReference<Activity> mReference;
    private TwitterAuthClient mAuthClient;

    public static FPollTwitterAuthClient getInstance(Activity activity) {
        if (mInstance == null) mInstance = new FPollTwitterAuthClient(activity);
        return mInstance;
    }

    private FPollTwitterAuthClient(Activity activity) {
        mReference = new WeakReference<>(activity);
        TwitterAuthConfig authConfig =
            new TwitterAuthConfig(activity.getString(R.string.TWITTER_KEY),
                activity.getString(R.string.TWITTER_SECRET));
        Fabric.with(activity, new Twitter(authConfig));
        mAuthClient = new TwitterAuthClient();
    }

    public void loginTwitter(final Callback callback) {
        if (callback == null) return;
        Activity activity = mReference.get();
        if (activity == null) return;
        mAuthClient
            .authorize(activity, new com.twitter.sdk.android.core.Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    if (result == null || result.data == null ||
                        result.data.getAuthToken() == null) {
                        return;
                    }
                    callback.loginTwitterSuccess(result.data.getAuthToken());
                }

                @Override
                public void failure(TwitterException exception) {
                    callback.loginTwitterError(exception);
                }
            });
    }

    public TwitterAuthClient getAuthClient() {
        return mAuthClient;
    }

    public interface Callback {
        void loginTwitterSuccess(TwitterAuthToken token);
        void loginTwitterError(TwitterException exception);
    }
}
