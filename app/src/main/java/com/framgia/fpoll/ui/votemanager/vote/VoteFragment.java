package com.framgia.fpoll.ui.votemanager.vote;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.databinding.FragmentVoteBinding;
import com.framgia.fpoll.ui.votemanager.LinkVoteActivity;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;
import com.framgia.fpoll.ui.votemanager.updateoption.UpdateOptionFragment;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.PermissionsUtil;
import com.framgia.fpoll.util.SharePreferenceUtil;
import com.framgia.fpoll.widget.FPollProgressDialog;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_EVENT;
import static com.framgia.fpoll.util.Constant.RequestCode.IMAGE_PICKER_SELECT;
import static com.framgia.fpoll.util.Constant.RequestCode.PERMISSIONS_REQUEST_WRITE_EXTERNAL;

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
    private FPollProgressDialog mDialog;
    private LinkVoteActivity.EventVote mEventVote;

    public static VoteFragment newInstance(VoteInfoModel voteInfo,
            LinkVoteActivity.EventVote eventVote) {
        VoteFragment voteInformationFragment = new VoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_VOTE_INFO, voteInfo);
        bundle.putParcelable(BUNDLE_EVENT, eventVote);
        voteInformationFragment.setArguments(bundle);
        return voteInformationFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mVoteInfoModel = getArguments().getParcelable(ARGUMENT_VOTE_INFO);
            mEventVote = getArguments().getParcelable(BUNDLE_EVENT);
        }
        if (mVoteInfoModel == null) return;
        mIsMultiple = mVoteInfoModel.getVoteInfo().getPoll().isMultiple();
        mPresenter = new VotePresenter(this, VoteInfoRepository.getInstance(getContext()),
                SharePreferenceUtil.getIntances(getActivity()), mIsMultiple, mEventVote);
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
        mBinding.setIsMultiple(mIsMultiple);
        return mBinding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void updateVoteChoice(Option optionModel) {
        optionModel.setChecked(!optionModel.isChecked());
        if (mIsMultiple) return;
        for (Option option : mVoteInfoModel.getOptionModels()) {
            if (option.getId() != optionModel.getId()) option.setChecked(false);
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

    @Override
    public void showDialog() {
        if (mDialog == null) mDialog = new FPollProgressDialog(getActivity());
        mDialog.show();
    }

    @Override
    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) mDialog.dismiss();
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
        ((LinkVoteActivity) getActivity()).showProgressDialog();
    }

    @Override
    public void showGallery() {
        if (PermissionsUtil.isAllowPermissions(getActivity())) {
            Intent intent = new Intent(Intent.ACTION_PICK,
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

    @Override
    public void showDialogEditOption(Option option) {
        if (getChildFragmentManager() != null) {
            UpdateOptionFragment fragment = UpdateOptionFragment.newInstance(option);
            fragment.show(getChildFragmentManager(), "");
        }
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
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver()
                .query(selectedImage, filePathColumn, null, null, null);
        if (cursor == null || !cursor.moveToFirst()) return;
        String url = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
        cursor.close();
        mPresenter.setImageOption(url);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != PERMISSIONS_REQUEST_WRITE_EXTERNAL) return;
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showGallery();
        } else {
            ActivityUtil.showToast(getActivity(), R.string.msg_image_not_choose);
        }
    }
}
