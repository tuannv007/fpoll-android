package com.framgia.fpoll.ui.feedback;

import android.databinding.ObservableField;

import com.framgia.fpoll.R;
import com.framgia.fpoll.util.FeedbackValidation;

/**
 * Created by Nhahv0902 on 2/28/2017.
 * <></>
 */
public class FeedbackPresenter implements FeedbackContract.Presenter {
    private final FeedbackContract.View mView;
    private ObservableField<String> mContent = new ObservableField<>();
    private ObservableField<String> mName = new ObservableField<>();
    private FeedbackType mFeedbackType;

    public FeedbackPresenter(FeedbackContract.View view) {
        mView = view;
        mFeedbackType = FeedbackType.INTERFACE;
        mView.start();
    }

    @Override
    public void sendFeedback() {
        new FeedbackValidation(mContent.get(), mName.get())
            .validation(new FeedbackValidation.Callback
                () {
                @Override
                public void onSuccess() {
                    // TODO: 2/28/2017 handler api send feedback
                    mView.sendFeedbackSuccess();
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
                        default:
                            break;
                    }
                }
            });
    }

    @Override
    public void setFeedbackType(FeedbackType type) {
        mFeedbackType = type;
    }

    public ObservableField<String> getContent() {
        return mContent;
    }

    public ObservableField<String> getName() {
        return mName;
    }
}
