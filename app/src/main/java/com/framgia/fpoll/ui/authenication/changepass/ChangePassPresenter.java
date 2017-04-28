package com.framgia.fpoll.ui.authenication.changepass;

import android.text.TextUtils;
import com.framgia.fpoll.data.source.remote.login.LoginDataSource;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.util.ActivityUtil;
import com.google.gson.Gson;
import java.io.IOException;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tuanbg on 4/24/17.
 */

public class ChangePassPresenter implements ChangePassContract.Presenter {
    private ChangePassContract.View mView;
    private LoginDataSource mDataSource;
    private CompositeSubscription mCompositeSubscription;

    public ChangePassPresenter(ChangePassContract.View view, LoginDataSource dataSource) {
        mView = view;
        mDataSource = dataSource;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void changePassword(String currentPassword, String newPassword,
            String newPasswordConfirmation) {
        if (!validateData(currentPassword, newPassword, newPasswordConfirmation)) {
            return;
        }

        Subscription subscription =
                mDataSource.changePassword(currentPassword, newPassword, newPasswordConfirmation)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                mView.showProgressDialog();
                            }
                        })
                        .subscribe(new Subscriber<Boolean>() {
                            @Override
                            public void onCompleted() {
                                mView.hideProgressDialog();
                            }

                            @Override
                            public void onError(Throwable e) {
                                mView.hideProgressDialog();
                                handleError(e);
                            }

                            @Override
                            public void onNext(Boolean isSuccess) {
                                if (isSuccess) {
                                    mView.onChangePasswordSuccess();
                                    mView.dismissView();
                                }
                                mView.hideProgressDialog();
                            }
                        });

        mCompositeSubscription.add(subscription);
    }

    private void handleError(Throwable e) {
        if (e == null) return;
        HttpException httpException = ((HttpException) e);

        if (httpException == null
                || httpException.response() == null
                || httpException.response().errorBody() == null) {
            mView.onChangePasswordError(e.getMessage());
            return;
        }

        try {
            String responseItem = httpException.response().errorBody().string();

            ResponseItem response = new Gson().fromJson(responseItem, ResponseItem.class);
            if (response != null) {
                mView.onChangePasswordError(ActivityUtil.byString(response.getMessage()));
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public boolean validateData(String currentPassword, String newPassword,
            String newPasswordConfirmation) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(currentPassword)) {
            isValidate = false;
            mView.onCurrentPasswordEmpty();
        }

        if (TextUtils.isEmpty(newPassword)) {
            isValidate = false;
            mView.onNewPasswordEmpty();
        }

        if (TextUtils.isEmpty(newPasswordConfirmation)) {
            isValidate = false;
            mView.onNewPasswordConfirmationEmpty();
        }

        if (newPasswordConfirmation != null
                && newPasswordConfirmation != null
                && !newPassword.equals(newPasswordConfirmation)) {
            isValidate = false;
            mView.onNewPassNotSame();
        }

        return isValidate;
    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.clear();
    }
}
