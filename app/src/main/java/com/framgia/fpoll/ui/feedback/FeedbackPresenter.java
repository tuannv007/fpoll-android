package com.framgia.fpoll.ui.feedback;

import android.databinding.ObservableField;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.ApiRestClient.APIService.ResponseItem;
import com.framgia.fpoll.data.source.remote.feedback.FeedbackDataSource;
import com.framgia.fpoll.data.source.remote.feedback.FeedbackRepository;
import com.framgia.fpoll.util.FeedbackValidation;

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

    public FeedbackPresenter(FeedbackContract.View view, FeedbackRepository repository) {
        mView = view;
        mRepository = repository;
        mView.start();
    }

    @Override
    public void sendFeedback() {
        new FeedbackValidation(mContent.get(), mName.get(), mEmail.get())
            .validation(new FeedbackValidation.Callback() {
                @Override
                public void onSuccess() {
                    mRepository.sendFeedback(mName.get(), mEmail.get(), mContent.get(),
                        new FeedbackDataSource.Callback() {
                            @Override
                            public void onSuccess(ResponseItem<String> data) {
                                mView.sendFeedbackSuccess();
                            }

                            @Override
                            public void onError(String message) {
                                mView.showMessage(R.string.msg_send_feedback_error);
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
}
