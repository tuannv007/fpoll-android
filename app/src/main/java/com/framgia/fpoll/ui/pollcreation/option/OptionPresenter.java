package com.framgia.fpoll.ui.pollcreation.option;

import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Option;

import java.util.List;

/**
 * Created by framgia on 22/02/2017.
 */
public class OptionPresenter implements OptionPollContract.Presenter {
    private OptionPollContract.View mView;
    private PollItem mPollItem;
    private List<Option> mListOption;

    public OptionPresenter(OptionPollContract.View view, PollItem pollItem, List listOption) {
        mView = view;
        mListOption = listOption;
        mPollItem = pollItem;
        mView.start();
    }

    @Override
    public void pickImage(Option optionItem, int position) {
        mView.openGallery(optionItem, position);
    }

    @Override
    public void pickDate(Option optionItem, int position) {
        if (mView != null) mView.datePicker(optionItem, position);
    }

    @Override
    public void deleteDateOfOption(Option option) {
        option.setDate(null);
    }

    @Override
    public void deleteImage(Option option) {
        option.setImage(null);
    }

    @Override
    public void deletePoll(Option optionItem, int position) {
        mView.deletePoll(position);
    }

    @Override
    public void augmentPoll(int position) {
        mView.augmentPoll(position);
    }
}
