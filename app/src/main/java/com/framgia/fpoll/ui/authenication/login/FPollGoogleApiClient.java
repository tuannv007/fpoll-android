package com.framgia.fpoll.ui.authenication.login;

import android.content.Context;
import android.os.AsyncTask;
import com.framgia.fpoll.R;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.IOException;
import java.lang.ref.WeakReference;

import static com.framgia.fpoll.util.Constant.WebUrl.DATA_SCOPE;

/**
 * Created by Nhahv0902 on 2/20/2017.
 * <></>
 */
public class FPollGoogleApiClient {
    private static FPollGoogleApiClient mInstance;
    private WeakReference<Context> mReference;
    private GoogleApiClient mGoogleApiClient;

    private FPollGoogleApiClient(Context context) {
        mReference = new WeakReference<>(context);
        initGoogle();
    }

    public static FPollGoogleApiClient getInstance(Context context) {
        if (mInstance == null) mInstance = new FPollGoogleApiClient(context);
        return mInstance;
    }

    public void initGoogle() {
        Context context = mReference.get();
        if (context == null) return;
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(
                        context.getString(R.string.server_client_id)).requestEmail().build();
        mGoogleApiClient =
                new GoogleApiClient.Builder(context).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        mGoogleApiClient.connect();
    }

    public void requestToken(String email, CallBack callBack) {
        new GetGoogleTokenAsync(callBack).execute(email);
    }

    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    public interface CallBack {
        void onGetTokenSuccess(String token);

        void onGetTokenFail();
    }

    public class GetGoogleTokenAsync extends AsyncTask<String, Void, String> {
        private CallBack mCallBack;

        public GetGoogleTokenAsync(CallBack callBack) {
            mCallBack = callBack;
        }

        @Override
        protected String doInBackground(String... strs) {
            Context context = mReference.get();
            try {
                String email = strs[0];
                return GoogleAuthUtil.getToken(context, email, DATA_SCOPE);
            } catch (IOException | GoogleAuthException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String token) {
            super.onPostExecute(token);
            if (mCallBack == null) return;
            if (token != null) {
                mCallBack.onGetTokenSuccess(token);
            } else {
                mCallBack.onGetTokenFail();
            }
        }
    }
}
