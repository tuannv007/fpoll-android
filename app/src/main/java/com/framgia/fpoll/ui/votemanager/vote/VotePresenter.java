package com.framgia.fpoll.ui.votemanager.vote;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.model.poll.ParticipantVotes;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.networking.api.VoteInfoAPI;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;
import com.framgia.fpoll.util.SharePreferenceUtil;
import java.util.ArrayList;
import java.util.List;

import static android.util.Patterns.EMAIL_ADDRESS;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public class VotePresenter implements VoteContract.Presenter {
    private VoteContract.View mView;
    private VoteInfoRepository mVoteInfoRepository;
    private ObservableField<Option> mOption = new ObservableField<>(new Option());
    private ObservableBoolean mIsLogin = new ObservableBoolean();
    private User mUser;
    private boolean mIsMultiple;

    public VotePresenter(VoteContract.View view, VoteInfoRepository voteInfoRepository,
            SharePreferenceUtil preference, boolean isMultiple) {
        mView = view;
        mVoteInfoRepository = voteInfoRepository;
        mIsMultiple = isMultiple;
        mIsLogin.set(preference.isLogin());
        mUser = preference.getUser() != null ? preference.getUser() : new User();
        mView.start();
    }

    @Override
    public void voteOption(Option optionModel) {
        mView.updateVoteChoice(optionModel);
    }

    @Override
    public void submitVote(final VoteInfoModel voteInfoModel) {
        if (!validateInput(voteInfoModel)) return;
        final int idPoll = voteInfoModel.getVoteInfo().getPoll().getId();
        //Add list option user vote
        List<Option> options = new ArrayList<>();
        for (Option option : voteInfoModel.getOptionModels()) {
            if (option.isChecked()) options.add(option);
        }
        if (options.size() == 0) {
            mView.onNotifyVote();
        } else {
            VoteInfoAPI.OptionsBody optionsBody =
                    new VoteInfoAPI.OptionsBody(mUser.getUsername(), mUser.getEmail(), idPoll,
                            options, mOption.get().getName(), mOption.get().getImage());
            votePoll(optionsBody, voteInfoModel);
        }
    }

    public void clickEdit(Option option) {
        // TODO: 4/7/2017  Edit option when vote
    }

    @Override
    public void openGallery() {
        mView.showGallery();
    }

    @Override
    public void setImageOption(String imagePath) {
        mOption.get().setImage(imagePath);
    }

    public void onCheckedChanged(boolean check) {
        mView.onCheckedChanged(check);
    }

    @Override
    public void updateAdditionOption(VoteInfoModel voteInfoModel) {
        /**
         * TODO If vote is single choice, when user click editText add new option, reset choice box
         */
    }

    private boolean validateInput(VoteInfoModel voteInfoModel) {
        //Check vote setting
        if (voteInfoModel.isNameRequired() && mUser.getUsername().isEmpty()) {
            mView.showVoteRequirement(R.string.msg_name_required);
            return false;
        } else if (voteInfoModel.isEmailRequired() && mUser.getEmail().isEmpty()) {
            mView.showVoteRequirement(R.string.msg_email_required);
            return false;
        } else if (voteInfoModel.isEmailAndNameRequired() && (mUser.getEmail().isEmpty()
                || mUser.getUsername().isEmpty())) {
            mView.showVoteRequirement(R.string.msg_name_and_email_required);
            return false;
        }
        //Check email input
        if (!mUser.getEmail().isEmpty() && !EMAIL_ADDRESS.matcher(mUser.getEmail()).matches()) {
            mView.showVoteRequirement(R.string.msg_email_invalidate);
            return false;
        }
        return true;
    }

    private void votePoll(final VoteInfoAPI.OptionsBody optionBody,
            final VoteInfoModel voteInfoModel) {
        mView.showDialog();
        mVoteInfoRepository.votePoll(optionBody, new DataCallback<ParticipantVotes>() {
            @Override
            public void onSuccess(ParticipantVotes data) {
                List<Option> updatedList = getListVoteUpdated(data, optionBody.getListOptions());
                //Get current list options then update new vote in [user] and [participant]
                List<Option> currentOptions = voteInfoModel.getVoteInfo().getPoll().getOptions();
                for (int i = 0; i < currentOptions.size(); i++) {
                    for (int j = 0; j < updatedList.size(); j++) {
                        if (currentOptions.get(i).getId() == updatedList.get(j).getId()) {
                            currentOptions.set(i, updatedList.get(j));
                        }
                    }
                }
                mView.onSubmitSuccess(currentOptions);
                mView.dismissDialog();
            }

            @Override
            public void onError(String msg) {
                mView.onSubmitFailed(msg);
                mView.dismissDialog();
            }
        });
    }

    private List<Option> getListVoteUpdated(ParticipantVotes participant, List<Option> options) {
        //Update list vote
        for (int i = 0; i < options.size(); i++) {
            //Check user votes or participant votes
            if (participant.getUserId() != null) {
                options.get(i).getVotes().add(participant);
            } else {
                options.get(i).getParticipantVotes().add(participant);
            }
        }
        return options;
    }

    @Override
    public void cleanOption() {
        mOption.set(new Option());
    }

    public void onDeleteImageClicked() {
        mOption.get().setImage(null);
    }

    public ObservableBoolean getIsLogin() {
        return mIsLogin;
    }

    public User getUser() {
        return mUser;
    }

    public ObservableField<Option> getOption() {
        return mOption;
    }
}
