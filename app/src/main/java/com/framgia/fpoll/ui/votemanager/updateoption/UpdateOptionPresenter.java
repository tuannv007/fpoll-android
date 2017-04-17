package com.framgia.fpoll.ui.votemanager.updateoption;

import android.support.annotation.NonNull;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.model.poll.Poll;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;

/**
 * Created by nhahv on 4/17/2017.
 * <></>
 */

public class UpdateOptionPresenter implements UpdateOptionContract.Presenter {

    private final UpdateOptionContract.View mView;
    private final VoteInfoRepository mRepository;

    public UpdateOptionPresenter(@NonNull UpdateOptionContract.View view,
            VoteInfoRepository instance) {
        mView = view;
        mRepository = instance;
        mView.start();
    }

    public void onPickImage(Option option) {
        if (mView != null) mView.pickImageFromGallery(option);
    }

    public void onDeleteImage(Option option) {
        if (mView != null) mView.onDeleteImage();
    }

    public void onDeleteTitle(Option option) {
        option.setName(null);
        if (mView != null) mView.updateOption(option);
    }

    public void onPickDate(Option option) {
        if (mView != null) mView.showPickDate(option);
    }

    public void onDeleteDate(Option option) {
        option.setDate(null);
        if (mView != null) mView.updateOption(option);
    }

    public void onUpdateOption(Option option) {
        if (mRepository == null || mView == null) return;
        mView.showProgress();
        mRepository.updateOption(option.getPollId(), option.getId(), option.getName(),
                option.getDate(), option.getImage(), new DataCallback<Poll>() {

                    @Override
                    public void onSuccess(Poll data) {
                        mView.showMessage(R.string.update_success);
                        mView.hideProgress();
                        mView.onDismiss();
                        mView.updateUIVote();
                    }

                    @Override
                    public void onError(String msg) {
                        mView.showMessage(msg);
                        mView.hideProgress();
                    }
                });
    }
}
