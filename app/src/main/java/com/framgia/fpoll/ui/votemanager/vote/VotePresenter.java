package com.framgia.fpoll.ui.votemanager.vote;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.model.poll.ParticipantVotes;
import com.framgia.fpoll.data.model.poll.Poll;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.networking.api.VoteInfoAPI;
import com.framgia.fpoll.ui.votemanager.itemmodel.AdditionOption;
import com.framgia.fpoll.ui.votemanager.itemmodel.OptionModel;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.util.PatternsCompat.EMAIL_ADDRESS;
import static com.framgia.fpoll.util.Constant.TypeEditPoll.TYPE_EDIT_OPTION;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public class VotePresenter implements VoteContract.Presenter {
    private VoteContract.View mView;
    private VoteInfoRepository mVoteInfoRepository;
    private ObservableField<String> mName = new ObservableField<>();
    private ObservableField<String> mEmail = new ObservableField<>();
    private ObservableField<AdditionOption> mAdditionOption = new ObservableField<>();
    private boolean mIsNewOptionAdded;

    public VotePresenter(VoteContract.View view, VoteInfoRepository voteInfoRepository) {
        mView = view;
        mVoteInfoRepository = voteInfoRepository;
        mView.start();
        mName.set("");
        mEmail.set("");
        mAdditionOption.set(new AdditionOption("", ""));
    }

    @Override
    public void voteOption(OptionModel optionModel) {
        mView.updateVoteChoice(optionModel);
        mAdditionOption.set(new AdditionOption("", ""));
    }

    @Override
    public void submitVote(final VoteInfoModel voteInfoModel) {
        if (!validateInput(voteInfoModel)) return;
        final int idPoll = voteInfoModel.getVoteInfo().getPoll().getId();
        //Add list option user vote
        List<Option> options = new ArrayList<>();
        for (int i = 0; i < voteInfoModel.getOptionModels().size(); i++) {
            if (voteInfoModel.getOptionModels().get(i).isChecked()) {
                options.add(voteInfoModel.getOptionModels().get(i).getOption());
            }
        }
        //Check if addition option is available
        //Update new option list then vote poll
        if (!TextUtils.isEmpty(mAdditionOption.get().getOptionText())) {
            mIsNewOptionAdded = true;
            updateNewOption(idPoll, voteInfoModel, options);
            return;
        }
        if (options.size() == 0) mView.onNotifyVote();
        else {
            VoteInfoAPI.OptionsBody optionsBody =
                new VoteInfoAPI.OptionsBody(mName.get(), mEmail.get(), idPoll, options);
            votePoll(optionsBody, voteInfoModel);
        }
    }

    @Override
    public void openGallery() {
        mView.showGallery();
    }

    @Override
    public void setImageOption(String imagePath) {
        mAdditionOption.get().setImagePath(imagePath);
    }

    @Override
    public void updateAdditionOption(VoteInfoModel voteInfoModel) {
        /**
         * TODO If vote is single choice, when user click editText add new option, reset choice box
         */
    }

    private boolean validateInput(VoteInfoModel voteInfoModel) {
        //Check vote setting
        if (voteInfoModel.isNameRequired() && mName.get().isEmpty()) {
            mView.showVoteRequirement(R.string.msg_name_required);
            return false;
        } else if (voteInfoModel.isEmailRequired() && mEmail.get().isEmpty()) {
            mView.showVoteRequirement(R.string.msg_email_required);
            return false;
        } else if (voteInfoModel.isEmailAndNameRequired()
            && (mEmail.get().isEmpty() || mName.get().isEmpty())) {
            mView.showVoteRequirement(R.string.msg_name_and_email_required);
            return false;
        }
        //Check email input
        if (!mEmail.get().isEmpty() && !EMAIL_ADDRESS.matcher(mEmail.get()).matches()) {
            mView.showVoteRequirement(R.string.msg_email_invalidate);
            return false;
        }
        return true;
    }

    private void updateNewOption(final int idPoll, final VoteInfoModel voteInfoModel,
                                 final List<Option> options) {
        String newOptionText = mAdditionOption.get().getOptionText();
        String newOptionImage = mAdditionOption.get().getImagePath();
        VoteInfoAPI.NewOptionBody newOptionBody =
            new VoteInfoAPI.NewOptionBody(
                String.valueOf(TYPE_EDIT_OPTION), newOptionText, newOptionImage);
        mView.setLoading(true);
        mVoteInfoRepository.updateNewOption(idPoll, newOptionBody,
            new DataCallback<Poll>() {
                @Override
                public void onSuccess(Poll data) {
                    mView.setLoading(false);
                    if (data == null) return;
                    /**
                     *  Hien tai data Poll tra ve mang option chua co' mang user vote va
                     *  participant vote nen chua the cap nhat UI
                     */
                    voteInfoModel.getVoteInfo().setPoll(data);
                    List<OptionModel> list = new ArrayList<>();
                    for (int i = 0; i < data.getOptions().size(); i++) {
                        list.add(new OptionModel(data.getOptions().get(i), false));
                    }
                    /**
                     * Option Model hien tai k co so luong user vote va participant vote
                     */
                    voteInfoModel.setOptionModels(list);
                    //Checked new option to list option then submit vote
                    options.add(voteInfoModel.getOptionModels().get(0).getOption());
                    VoteInfoAPI.OptionsBody optionsBody =
                        new VoteInfoAPI.OptionsBody(mName.get(), mEmail.get(), idPoll, options);
                    votePoll(optionsBody, voteInfoModel);
                }

                @Override
                public void onError(String msg) {
                    mView.setLoading(false);
                }
            });
    }

    private void votePoll(final VoteInfoAPI.OptionsBody optionBody,
                          final VoteInfoModel voteInfoModel) {
        mView.setLoading(true);
        mVoteInfoRepository.votePoll(optionBody, new DataCallback<ParticipantVotes>() {
            @Override
            public void onSuccess(ParticipantVotes data) {
                List<Option> updatedList = getListVoteUpdated(data, optionBody.getListOptions());
                //Get current list options then update new vote in [user] and [participant]
                List<Option> currentOptions = voteInfoModel.getVoteInfo().getPoll().getOptions();
                for (int i = 0; i < currentOptions.size(); i++) {
                    for (int j = 0; j < updatedList.size(); j++) {
                        if (currentOptions.get(i).getId() ==
                            updatedList.get(j).getId()) {
                            currentOptions.set(i, updatedList.get(j));
                        }
                    }
                }
                mView.onSubmitSuccess(currentOptions);
                mView.setLoading(false);
                //If new option is added , update UI
                if (mIsNewOptionAdded) mView.updateAdditionOptionSuccess();
                mIsNewOptionAdded = false;
            }

            @Override
            public void onError(String msg) {
                mView.onSubmitFailed(msg);
                mView.setLoading(false);
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

    public ObservableField<String> getName() {
        return mName;
    }

    public ObservableField<String> getEmail() {
        return mEmail;
    }

    public AdditionOption getAdditionOption() {
        return mAdditionOption.get();
    }
}
