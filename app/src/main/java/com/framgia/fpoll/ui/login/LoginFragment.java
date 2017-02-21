package com.framgia.fpoll.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.FragmentLoginBinding;
import com.framgia.fpoll.util.ActivityUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.framgia.fpoll.util.Constant.RequestCode.REQUEST_GOOGLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View {
    private FragmentLoginBinding mBinding;
    private LoginContract.Presenter mPresenter;

    public static LoginFragment getInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        mPresenter = new LoginPresenter(this, new FpollGoogleApiClient(getActivity()));
        mBinding.setPresenter((LoginPresenter) mPresenter);
        mBinding.setHandler(new LoginActionHandler((LoginPresenter) mPresenter));
        mPresenter.initGoogle();
        return mBinding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GOOGLE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            mPresenter.checkLoginGoogleResult(result);
        }
    }

    @Override
    public void loginGoogle(GoogleApiClient googleApiClient) {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQUEST_GOOGLE);
    }

    @Override
    public void loginGoogleSuccess() {
        ActivityUtil.showToast(getActivity(), R.string.msg_login_google_success);
    }

    @Override
    public void loginGoogleError() {
        ActivityUtil.showToast(getActivity(), R.string.msg_login_google_error);
    }
}
