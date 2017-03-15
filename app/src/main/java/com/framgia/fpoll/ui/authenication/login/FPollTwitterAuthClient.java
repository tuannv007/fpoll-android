package com.framgia.fpoll.ui.authenication.login;

import android.app.Activity;

import com.framgia.fpoll.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Nhahv0902 on 2/21/2017.
 * <></>
 */
public class FPollTwitterAuthClient {
    private Activity mActivity;
    private TwitterAuthClient mAuthClient;

    public FPollTwitterAuthClient(Activity activity) {
        mActivity = activity;
        TwitterAuthConfig authConfig =
            new TwitterAuthConfig(activity.getString(R.string.TWITTER_KEY),
                activity.getString(R.string.TWITTER_SECRET));
        Fabric.with(activity, new Twitter(authConfig));
    }

    public void initTwitter() {
        mAuthClient = new TwitterAuthClient();
    }

    public void loginTwitter(final Callback callback) {
        if (callback == null) return;
        mAuthClient
            .authorize(mActivity, new com.twitter.sdk.android.core.Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    callback.loginTwitterSuccess(result.data.getAuthToken().token);
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
        void loginTwitterSuccess(String token);
        void loginTwitterError(TwitterException exception);
    }
}
