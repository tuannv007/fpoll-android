package com.framgia.fpoll.ui.pollcreation.option;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.databinding.FragmentPageOptionBinding;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.PermissionsUtil;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;
import static com.framgia.fpoll.util.Constant.RequestCode.IMAGE_PICKER_SELECT;
import static com.framgia.fpoll.util.Constant.RequestCode.PERMISSIONS_REQUEST_WRITE_EXTERNAL;

/**
 * Created by nhahv on 22/02/2017.
 * <
 */
public class OptionPollFragment extends Fragment implements OptionPollContract.View {
    private static final int UNSELECTED_POSITION = -1;
    private FragmentPageOptionBinding mBinding;
    private OptionPollContract.Presenter mPresenter;
    private ObservableField<OptionAdapter> mAdapter = new ObservableField<>();
    private int mPosition = UNSELECTED_POSITION;
    private PollItem mPoll = new PollItem();

    public static OptionPollFragment newInstance(PollItem pollItem) {
        OptionPollFragment optionPollFragment = new OptionPollFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, pollItem);
        optionPollFragment.setArguments(bundle);
        return optionPollFragment;
    }

    private void getDataFromActivity() {
        Bundle bundle = getArguments();
        if (bundle == null || bundle.getParcelable(BUNDLE_POLL_ITEM) == null) return;
        mPoll = bundle.getParcelable(BUNDLE_POLL_ITEM);
        if (mPoll == null) mPoll = new PollItem();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentPageOptionBinding.inflate(inflater, container, false);
        getDataFromActivity();
        mPresenter = new OptionPresenter(this, mPoll, mPoll.getOptions());
        mBinding.setHandler(new OptionHandler(mPresenter));
        mBinding.setPresenter((OptionPresenter) mPresenter);
        mBinding.setFragment(this);
        mAdapter.set(new OptionAdapter(mPresenter, mPoll.getOptions()));
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        if (mPoll.getOptions() != null && mPoll.getOptions().size() == 0) {
            mPoll.getOptions().add(new Option());
        }
    }

    @Override
    public void openGallery(Option optionItem, int position) {
        mPosition = position;
        if (PermissionsUtil.isAllowPermissions(getActivity())) {
            pickImage();
        }
    }

    @Override
    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE_PICKER_SELECT);
    }

    @Override
    public void showError() {
        ActivityUtil.showToast(getActivity(), R.string.msg_option_blank);
    }

    @Override
    public void deletePoll(int position) {
        mPoll.getOptions().remove(position);
        mAdapter.get().update(mPoll.getOptions());
    }

    @Override
    public void augmentPoll() {
        mPoll.getOptions().add(new Option());
        mAdapter.get().update(mPoll.getOptions());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != PERMISSIONS_REQUEST_WRITE_EXTERNAL) return;
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        } else ActivityUtil.showToast(getActivity(), R.string.msg_image_not_choose);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICKER_SELECT && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver()
                .query(selectedImage, filePathColumn, null, null, null);
            if (cursor == null) return;
            cursor.moveToFirst();
            String url = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            if (mPosition != UNSELECTED_POSITION) {
                mPoll.getOptions().get(mPosition).setImage(url);
                mAdapter.get().update(mPoll.getOptions());
            }
            cursor.close();
        }
    }

    public ObservableField<OptionAdapter> getAdapter() {
        return mAdapter;
    }

    public boolean checkNextUI() {
        List<Option> listOptionReal = new ArrayList<>();
        for (Option item : mPoll.getOptions()) {
            if (item.getName() != null || item.getImage() != null) {
                listOptionReal.add(item);
            }
        }
        if (listOptionReal.size() == 0) {
            ActivityUtil.showToast(getApplicationContext(), R.string.msg_option_blank);
            return false;
        }
        mPoll.setOptions(listOptionReal);
        return true;
    }
}
