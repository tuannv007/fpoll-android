package com.framgia.fpoll.ui.polledition.editoption;

import android.text.TextUtils;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.util.ActivityUtil;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditOptionPresenter implements EditOptionContract.Presenter {
    private static final int DEFAULT_OPTION = 4;
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
    public void pickImage(Option optionItem, int position) {
        mView.openGallery(optionItem, position);
    }

    @Override
    public void pickDate(Option optionItem, int position) {
        // TODO: 3/13/2017 pick date
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
        mView.augmentPoll();
    }

    @Override
    public void validateNextUI(EditOptionFragment.OnCheckOptionListener listener) {
        boolean isDeleteEmptyOption = false;
        for (int i = mPollItem.getOptions().size() - 1; i >= 0; i--) {
            Option item = mPollItem.getOptions().get(i);
            if (item.getName() == null
                    || TextUtils.isEmpty(item.getName()) && item.getImage() == null) {
                mPollItem.getOptions().remove(i);
                isDeleteEmptyOption = true;
            }
        }
        if (mPollItem.getOptions().size() == 0) {
            for (int i = 0; i < DEFAULT_OPTION; i++) {
                mPollItem.getOptions().add(new Option());
            }
            ActivityUtil.showToast(getApplicationContext(), R.string.msg_option_blank);
            return;
        }
        if (isDeleteEmptyOption) {
            if (mView != null) mView.updateUI(listener);
        } else {
            listener.onSuccessful();
        }
    }
}
