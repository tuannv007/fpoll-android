package com.framgia.fpoll.ui.polledition.editoption;

import com.framgia.fpoll.data.model.poll.Option;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditOptionHandle {
    private EditOptionContract.Presenter mListener;

    public EditOptionHandle(EditOptionContract.Presenter listener) {
        mListener = listener;
    }

    public void clickPickImage(Option optionItem, int position) {
        if (mListener != null) mListener.pickImage(optionItem, position);
    }

    public void clickPickDate(Option optionItem, int position) {
        if (mListener != null) mListener.pickDate(optionItem, position);
    }

    public void clickDeletePoll(Option optionItem, int position) {
        if (mListener != null) mListener.deletePoll(optionItem, position);
    }

    public void clickAugmentPoll(int position) {
        if (mListener != null) mListener.augmentPoll(position);
    }

    public void onDeleteImageClicked(Option option) {
        if (mListener != null) mListener.deleteImage(option);
    }
}
