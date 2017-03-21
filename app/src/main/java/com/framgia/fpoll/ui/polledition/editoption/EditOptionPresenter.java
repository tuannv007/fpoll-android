package com.framgia.fpoll.ui.polledition.editoption;

import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Option;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditOptionPresenter implements EditOptionContract.Presenter {
    private EditOptionContract.View mView;
    private PollItem mPollItem;
    private List<Option> mListOption;

    public EditOptionPresenter(EditOptionContract.View view, PollItem pollItem, List listOption) {
        mView = view;
        mListOption = listOption;
        mPollItem = pollItem;
        mView.start();
    }

    @Override
    public void nextStep() {
        if (mView == null) return;
        List listOptionReal = new ArrayList();
        for (Option optionItem : mListOption) {
            if (optionItem.getName() != null) listOptionReal.add(optionItem);
        }
        if (listOptionReal.size() == 0) {
            mView.showError();
            return;
        }
        mPollItem.setOptions(listOptionReal);
        mView.nextStep();
    }

    @Override
    public void previousStep() {
        if (mView != null) mView.previousStep();
    }

    @Override
    public void pickImage(Option optionItem, int position) {
        mView.openGallery(optionItem, position);
    }

    @Override
    public void pickDate(Option optionItem, int position) {
        // TODO: 3/13/2017 pick date
    }

    @Override
    public void deletePoll(Option optionItem, int position) {
        mView.deletePoll(position);
    }

    @Override
    public void augmentPoll() {
        mView.augmentPoll();
    }
}
