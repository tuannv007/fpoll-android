package com.framgia.fpoll.ui.authenication.register;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.User;
import com.framgia.fpoll.databinding.FragmentRegisterBinding;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by tuanbg on 2/9/17.
 */
public class RegisterFragment extends Fragment implements RegisterContract.View {
    private FragmentRegisterBinding mBinding;
    private RegisterPresenter mPresenter;
    private String mUsername;
    private String mPassword;
    private String mEmail;
    private String mConfirmPassword;
    private final ObservableField<String> mAvatarPath = new ObservableField<>();
    private ImageView mAvatar;
    private List<String> mListGender = new ArrayList<>();
    private RegisterItemActionHandle mActionHandle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        mPresenter = new RegisterPresenter(this);
        mActionHandle = new RegisterItemActionHandle(mPresenter);
        mBinding.setHandler(mActionHandle);
        mBinding.setFragment(this);
        return mBinding.getRoot();
    }

    public RegisterFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        start();
    }

    @Override
    public void start() {
        mAvatar = mBinding.imageAvatarRegister;
        if (mListGender != null) initSpinnerGender();
    }

    @Override
    public User registerAccount() {
        return new User(mUsername, mEmail, getGender(), mPassword, mAvatarPath.get());
    }

    public void initSpinnerGender() {
        mListGender.add(getResources().getString(R.string.title_male));
        mListGender.add(getResources().getString(R.string.title_female));
        ArrayAdapter<String> adapter =
            new ArrayAdapter<>(getActivity(), android.R.layout
                .simple_spinner_dropdown_item, mListGender);
        mBinding.spinGenderRegister.setAdapter(adapter);
    }

    public int getGender() {
        String gender = mBinding.spinGenderRegister.getSelectedItem().toString().trim();
        return gender.equals(getResources().getString(R.string.title_male)) ? 1 : 0;
    }

    @Override
    public void chooseImage() {
        Intent intent = new Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        getActivity().startActivityForResult(intent, Constant.IMAGE_PICKER_SELECT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.IMAGE_PICKER_SELECT && resultCode == RESULT_OK &&
            null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = this.getActivity().getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            mAvatarPath.set(cursor.getString(columnIndex));
            cursor.close();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean validateForm() {
        mUsername = mBinding.editUsernameRegister.getText().toString();
        mEmail = mBinding.editEmailRegister.getText().toString();
        mPassword = mBinding.editPasswordRegister.getText().toString();
        mConfirmPassword = mBinding.editConfirmPassRegister.getText().toString();
        if (mUsername.trim().isEmpty() || mEmail.trim().isEmpty() || mPassword.trim().isEmpty()) {
            ActivityUtil.showToast(getActivity(), R.string.msg_register_error);
            return false;
        }
        if (mPassword.trim().equals(mConfirmPassword)) {
            ActivityUtil.showToast(getActivity(), R.string.msg_confirm_pass_not_success);
            return false;
        }
        return true;
    }

    public ObservableField<String> getAvatarPath() {
        return mAvatarPath;
    }
}
