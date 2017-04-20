package com.framgia.fpoll.ui.feedback;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.feedback.FeedbackRepository;
import com.framgia.fpoll.util.FeedbackValidation;
import com.framgia.fpoll.util.SharePreferenceUtil;

/**
 * Created by Nhahv0902 on 2/28/2017.
 * <></>
 */
public class FeedbackPresenter implements FeedbackContract.Presenter {
    private final FeedbackContract.View mView;
    private ObservableField<String> mContent = new ObservableField<>();
    private ObservableField<String> mName = new ObservableField<>();
    private ObservableField<String> mEmail = new ObservableField<>();
    private FeedbackRepository mRepository;
    private ObservableBoolean mIsLogin = new ObservableBoolean(false);

    public FeedbackPresenter(FeedbackContract.View view, FeedbackRepository repository,
            SharePreferenceUtil sharePreferenceUtil) {
        mView = view;
        mRepository = repository;
        mView.start();
        initData(sharePreferenceUtil);
    }

    private void initData(SharePreferenceUtil sharePreferenceUtil) {
        User user = sharePreferenceUtil.getUser();
        if (user != null && !TextUtils.isEmpty(user.getEmail()) &&
                !TextUtils.isEmpty(user.getUsername())) {
            mName.set(user.getUsername());
            mEmail.set(user.getEmail());
            mIsLogin.set(true);
        }
    }

    @Override
    public void sendFeedback() {
        if (mView == null) return;
        new FeedbackValidation(mContent.get(), mName.get(), mEmail.get()).validation(
                new FeedbackValidation.Callback() {
                    @Override
                    public void onSuccess() {
                        mView.showProgressDialog();
                        mRepository.sendFeedback(mName.get(), mEmail.get(), mContent.get(),
                                new DataCallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        mView.sendFeedbackSuccess();
                                        mView.hideProgressDialog();
                                    }

                                    @Override
                                    public void onError(String message) {
                                        mView.showMessage(R.string.msg_send_feedback_error);
                                        mView.hideProgressDialog();
                                    }
                                });
                    }

                    @Override
                    public void onError(FeedbackValidation.FeedbackError error) {
                        switch (error) {
                            case CONTENT:
                                mView.showMessage(R.string.msg_content_error);
                                break;
                            case NAME:
                                mView.showMessage(R.string.msg_name_error);
                                break;
                            case EMAIL:
                                mView.showMessage(R.string.msg_email_invalidate);
                                break;
                            default:
                                break;
                        }
                    }
                });
    }

    public ObservableField<String> getContent() {
        return mContent;
    }

    public ObservableField<String> getName() {
        return mName;
    }

    public ObservableField<String> getEmail() {
        return mEmail;
    }

    public ObservableBoolean getIsLogin() {
        return mIsLogin;
    }
}
