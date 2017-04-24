package com.framgia.fpoll.ui.profile;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.authorization.SocialData;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.login.LoginDataSource;
import com.framgia.fpoll.util.SharePreferenceUtil;
import com.framgia.fpoll.util.UserValidation;

/**
 * Created by framgia on 19/04/2017.
 */

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.ViewModel mViewModel;
    private SharePreferenceUtil mPreference;
    private LoginDataSource mRepository;

    public ProfilePresenter(ProfileContract.ViewModel viewModel, SharePreferenceUtil preference,
            LoginDataSource repository) {
        mViewModel = viewModel;
        mPreference = preference;
        mRepository = repository;
        getUser();
    }

    @Override
    public void getUser() {
        User user = mPreference.getUser();
        if (user != null) {
            mViewModel.onGetUserSuccess(user);
        } else {
            mViewModel.onGetUserFailed();
        }
    }

    @Override
    public void editUser(final User user) {
        if (mRepository == null || user == null) return;
        new UserValidation(user).updateProfileValidate(new UserValidation.CallBack() {
            @Override
            public void onError(UserValidation.Error error) {
                switch (error) {
                    case USER_NAME:
                        mViewModel.onValidateNameError();
                        break;
                    case EMAIL:
                        mViewModel.onValidateEmailError();
                        break;
                    case PASSWORD:
                        mViewModel.onValidatePasswordError();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onValidateSuccess() {
                mViewModel.showProgressDialog();
                mRepository.updateProfile(user, new DataCallback<SocialData>() {
                    @Override
                    public void onSuccess(SocialData data) {
                        if (data != null) {
                            data.getUser().setToken(data.getToken());
                            mPreference.writeUser(data.getUser());
                            mViewModel.onUpdateUserSuccess(data.getUser());
                        } else {
                            mViewModel.onUpdateUserFaile(R.string.msg_update_fail);
                        }
                        mViewModel.hideProgressDialog();
                    }

                    @Override
                    public void onError(String msg) {
                        mViewModel.onUpdateUserFaile(msg);
                        mViewModel.hideProgressDialog();
                    }
                });
            }
        });
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
