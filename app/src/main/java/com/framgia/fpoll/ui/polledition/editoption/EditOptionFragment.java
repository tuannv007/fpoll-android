package com.framgia.fpoll.ui.polledition.editoption;

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
import android.widget.Toast;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.databinding.FragmentEditOptionBinding;
import com.framgia.fpoll.ui.pollcreation.option.OptionPollFragment;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;
import com.framgia.fpoll.util.PermissionsUtil;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.framgia.fpoll.util.Constant.RequestCode.IMAGE_PICKER_SELECT;
import static com.framgia.fpoll.util.Constant.RequestCode.PERMISSIONS_REQUEST_WRITE_EXTERNAL;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditOptionFragment extends Fragment implements EditOptionContract.View {
    private static final int UNSELECTED_POSITION = -1;
    private static final int NUMBER_DEFAULT_OPTION = 4;
    private FragmentEditOptionBinding mBinding;
    private EditOptionContract.Presenter mPresenter;
    private ObservableField<EditOptionAdapter> mAdapter = new ObservableField<>();
    private List<Option> mListOption = new ArrayList<>();
    private int mPosition = UNSELECTED_POSITION;
    private PollItem mPollItem;

    public static OptionPollFragment newInstance(PollItem pollItem) {
        OptionPollFragment optionPollFragment = new OptionPollFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.BundleConstant.BUNDLE_POLL_ITEM, pollItem);
        optionPollFragment.setArguments(bundle);
        return optionPollFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit_option, container, false);
        mPollItem = getArguments().getParcelable(Constant.BundleConstant.BUNDLE_POLL_ITEM);
        mPresenter = new EditOptionPresenter(this, mPollItem, mListOption);
        mBinding.setHandler(new EditOptionHandle(mPresenter));
        mBinding.setPresenter((EditOptionPresenter) mPresenter);
        mBinding.setFragment(this);
        mAdapter.set(new EditOptionAdapter(mPresenter, mListOption));
        return mBinding.getRoot();
    }

    @Override
    public void nextStep() {
    }

    @Override
    public void previousStep() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void start() {
        for (int i = 0; i < NUMBER_DEFAULT_OPTION; i++) {
            mListOption.add(new Option());
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
        Toast.makeText(getContext(), getString(R.string.msg_option_blank), Toast.LENGTH_SHORT)
            .show();
    }

    @Override
    public void deletePoll(int position) {
        mListOption.remove(position);
        mAdapter.get().update(mListOption);
    }

    @Override
    public void augmentPoll() {
        mListOption.add(new Option());
        mAdapter.get().update(mListOption);
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
                mListOption.get(mPosition).setImage(url);
                mAdapter.get().update(mListOption);
            }
            cursor.close();
        }
    }

    public ObservableField<EditOptionAdapter> getAdapter() {
        return mAdapter;
    }
}
