package com.framgia.fpoll.util;

import android.support.annotation.NonNull;

/**
 * Created by Nhahv0902 on 2/28/2017.
 * <></>
 */
public class FeedbackValidation {
    private String mContent;
    private String mName;

    public FeedbackValidation(String content, String name) {
        mContent = content;
        mName = name;
    }

    public void validation(@NonNull Callback callback) {
        if (!validateContent()) {
            callback.onError(FeedbackError.CONTENT);
            return;
        }
        if (!validateName()) {
            callback.onError(FeedbackError.NAME);
            return;
        }
        callback.onSuccess();
    }

    private boolean validateContent() {
        return mContent != null && !mContent.isEmpty();
    }

    private boolean validateName() {
        return mName != null && mName.isEmpty();
    }

    public interface Callback {
        void onSuccess();
        void onError(FeedbackError error);
    }

    public enum FeedbackError {
        CONTENT, NAME
    }
}
