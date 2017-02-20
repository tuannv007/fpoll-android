package com.framgia.fpoll.ui.authenication.register;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.User;
import com.framgia.fpoll.databinding.FragmentRegisterBinding;
import com.framgia.fpoll.util.Constant;
import com.framgia.fpoll.util.UserValidation;

import static android.app.Activity.RESULT_OK;

/**
 * Created by tuanbg on 2/9/17.
 */
public class RegisterFragment extends Fragment implements RegisterContract.View {
    private FragmentRegisterBinding mBinding;
    private RegisterContract.Presenter mPresenter;
    private User mUser = new User();
    private String mUrlAvatar;

    public static RegisterFragment getInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        mPresenter = new RegisterPresenter(this, mUser);
        mBinding.setHandler(new RegisterItemActionHandle(mPresenter));
        mBinding.setPresenter((RegisterPresenter) mPresenter);
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
        mUser.setEmail("toandoan.coder@gmail.com");
        mUser.setUsername("Toan Doan");
        mUser.setPassword("123456");
        mUser.setConfirmPassword("123456");
        mUser.setGender(1);
    }

    @Override
    public void chooseImage() {
        Intent intent = new Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, Constant.IMAGE_PICKER_SELECT);
    }

    @Override
    public void onValidateError(UserValidation.Error error) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.IMAGE_PICKER_SELECT && resultCode == RESULT_OK &&
            null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = this.getActivity().getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            mUrlAvatar = cursor.getString(columnIndex);
            mPresenter.setUserUrlImage(mUrlAvatar);
            cursor.close();
        }
    }
}
