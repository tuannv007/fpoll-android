package com.framgia.fpoll.ui.votemanager.updateoption;

import android.support.annotation.NonNull;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhahv on 4/17/2017.
 * <></>
 */

public class UpdateOptionPresenter implements UpdateOptionContract.Presenter {

    private final UpdateOptionContract.View mView;
    private final int mPosition;
    private PollRepository mRepository;
    private final List<Option> mOptions;

    public UpdateOptionPresenter(@NonNull UpdateOptionContract.View view, PollRepository instance,
            List<Option> options, int position) {
        mView = view;
        mRepository = instance;
        mOptions = options;
        mPosition = position;
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
        List<Option> options = new ArrayList<>();
        options.addAll(mOptions);
        mView.showProgress();
        options.set(mPosition, option);
        mRepository.updateOption(option.getPollId(), options, new DataCallback<PollItem>() {
            @Override
            public void onSuccess(PollItem data) {
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
