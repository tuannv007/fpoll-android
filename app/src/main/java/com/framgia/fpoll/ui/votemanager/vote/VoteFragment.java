package com.framgia.fpoll.ui.votemanager.vote;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.databinding.FragmentVoteBinding;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.PermissionsUtil;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.graphics.Color.TRANSPARENT;
import static com.framgia.fpoll.util.Constant.RequestCode.IMAGE_PICKER_SELECT;

/**
 * Created by tran.trung.phong on 22/02/2017.
 */
public class VoteFragment extends Fragment implements VoteContract.View {
    private static final String ARGUMENT_VOTE_INFO = "ARGUMENT_VOTE_INFO";
    private FragmentVoteBinding mBinding;
    private ObservableField<VoteAdapter> mAdapter = new ObservableField<>();
    private boolean mIsMultiple;
    private VoteInfoModel mVoteInfoModel;
    private VoteContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;

    public static VoteFragment newInstance(VoteInfoModel voteInfo) {
        VoteFragment voteInformationFragment = new VoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_VOTE_INFO, voteInfo);
        voteInformationFragment.setArguments(bundle);
        return voteInformationFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mVoteInfoModel = getArguments().getParcelable(ARGUMENT_VOTE_INFO);
        mPresenter = new VotePresenter(this, VoteInfoRepository.getInstance(getContext()));
        mAdapter.set(new VoteAdapter(mPresenter, mVoteInfoModel));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_vote, container, false);
        mBinding.setFragment(this);
        mBinding.setPresenter((VotePresenter) mPresenter);
        mBinding.setVoteInfoModel(mVoteInfoModel);
        mIsMultiple = mVoteInfoModel.getVoteInfo().getPoll().isMultiple();
        mBinding.setIsMultiple(mIsMultiple);
        return mBinding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void updateVoteChoice(Option optionModel) {
        optionModel.setChecked(true);
        if (mIsMultiple) return;
        for (Option option : mVoteInfoModel.getOptionModels()) {
            option.setChecked(false);
        }
        if (mPresenter != null) mPresenter.cleanOption();
    }

    @Override
    public void onCheckedChanged(boolean check) {
        if (!check || mIsMultiple) return;
        for (Option option : mVoteInfoModel.getOptionModels()) {
            option.setChecked(false);
        }
    }

    /**
     * @param options : list option user voted
     */
    @Override
    public void onSubmitSuccess(List<Option> options) {
        //TODO submit vote success switch to result tab
        //Update list vote
        mVoteInfoModel.getVoteInfo().getPoll().setOptions(options);
        //Reset check list option model
        List<Option> listOptionModel = new ArrayList<>();
        listOptionModel.addAll(mVoteInfoModel.getOptionModels());
        mVoteInfoModel.setOptionModels(listOptionModel);
        //Notify chart data
        //Notify change list
        mAdapter.get().notifyDataSetChanged();
    }

    @Override
    public void onSubmitFailed(String message) {
        ActivityUtil.showToast(getContext(), message);
    }

    @Override
    public void onNotifyVote() {
        ActivityUtil.showToast(getContext(), getString(R.string.msg_vote_your_option));
    }

    @Override
    public void setLoading(boolean isShow) {
        if (!isShow && mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            return;
        }
        mProgressDialog = ProgressDialog.show(getContext(), null, null, true, false);
        if (mProgressDialog.getWindow() != null)
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
        mProgressDialog.setContentView(new ProgressBar(getContext()));
    }

    @Override
    public void showGallery() {
        if (PermissionsUtil.isAllowPermissions(getActivity())) {
            Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, IMAGE_PICKER_SELECT);
        }
    }

    @Override
    public void updateAdditionOptionSuccess() {
        mAdapter.get().notifyDataSetChanged();
    }

    @Override
    public void showVoteRequirement(int msg) {
        ActivityUtil.showToast(getContext(), getString(msg));
    }

    @Override
    public void resetChoiceBox() {
        //TODO reset choice box
    }

    public ObservableField<VoteAdapter> getAdapter() {
        return mAdapter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != IMAGE_PICKER_SELECT || resultCode != RESULT_OK || data == null) return;
        Uri selectedImage = data.getData();
        if (selectedImage == null) return;
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver()
            .query(selectedImage, filePathColumn, null, null, null);
        if (cursor == null) return;
        cursor.moveToFirst();
        mPresenter.setImageOption(cursor.getString(cursor.getColumnIndex(filePathColumn[0])));
        cursor.close();
    }
}
