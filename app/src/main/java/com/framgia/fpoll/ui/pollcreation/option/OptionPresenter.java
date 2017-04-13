package com.framgia.fpoll.ui.pollcreation.option;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Option;

import static com.framgia.fpoll.ui.pollcreation.option.OptionPollFragment.DEFAULT_OPTION;

/**
 * Created by framgia on 22/02/2017.
 */
public class OptionPresenter implements OptionPollContract.Presenter {
    private static final long DELAY_VIEW_TIME = 700;
    private OptionPollContract.View mView;
    private PollItem mPollItem;

    public OptionPresenter(OptionPollContract.View view, PollItem pollItem) {
        mView = view;
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
    public void checkNextUi(@NonNull final OptionPollFragment.OnCheckOptionListenner listenner) {
        boolean isDeleteEmptyOption = false;
        for (int i = mPollItem.getOptions().size() - 1; i >= 0; i--) {
            Option item = mPollItem.getOptions().get(i);
            if (isEmptyOption(item)) {
                mPollItem.getOptions().remove(i);
                isDeleteEmptyOption = true;
            }
        }

        if (mPollItem.getOptions().size() == 0) {
            for (int i = 0; i < DEFAULT_OPTION; i++) {
                mPollItem.getOptions().add(new Option());
            }
            mView.showOptionError();
            return;
        }

        if (isDeleteEmptyOption) {
            mView.notifyData();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listenner.onSuccessful();
                }
            }, DELAY_VIEW_TIME);
        } else {
            listenner.onSuccessful();
        }
    }

    public boolean isEmptyOption(Option item) {
        return TextUtils.isEmpty(item.getName())
                && TextUtils.isEmpty(item.getImage())
                && TextUtils.isEmpty(item.getDate());
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
