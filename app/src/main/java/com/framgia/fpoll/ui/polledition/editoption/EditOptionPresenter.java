package com.framgia.fpoll.ui.polledition.editoption;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.networking.api.PollEditionApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditOptionPresenter implements EditOptionContract.Presenter {
    private EditOptionContract.View mView;
    private PollItem mPollItem;
    private PollRepository mRepository;

    public EditOptionPresenter(EditOptionContract.View view, PollItem pollItem,
                               PollRepository repository) {
        mView = view;
        mPollItem = pollItem;
        mRepository = repository;
        mView.start();
    }

    @Override
    public void nextStep() {
        if (mView == null) return;
        List listOptionReal = new ArrayList();
        for (Option optionItem : mPollItem.getOptions()) {
            if (optionItem.getName() != null) listOptionReal.add(optionItem);
        }
        if (listOptionReal.size() == 0) {
            mView.showError();
            return;
        }
        mPollItem.setOptions(listOptionReal);
        saveOption();
    }

    @Override
    public void saveOption() {
        mView.showDialog();
        mRepository.editPoll(PollEditionApi.TYPE_EDIT_OPTION, mPollItem,
            new DataCallback<DataInfoItem>() {
                @Override
                public void onSuccess(DataInfoItem data) {
                    mView.hideDialog();
                    mView.showMessage(R.string.update_sucess);
                }

                @Override
                public void onError(String msg) {
                    mView.hideDialog();
                    mView.showMessage(msg);
                }
            });
    }

    @Override
    public void back() {
        if (mView != null) mView.back();
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
