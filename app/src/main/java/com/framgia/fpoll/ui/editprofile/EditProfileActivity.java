package com.framgia.fpoll.ui.editprofile;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.databinding.ActivityEditProfileBinding;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.PermissionsUtil;

import static com.framgia.fpoll.util.Constant.RequestCode.IMAGE_PICKER_SELECT;
import static com.framgia.fpoll.util.Constant.RequestCode.PERMISSIONS_REQUEST_WRITE_EXTERNAL;

/**
 * Created by tran.trung.phong on 20/02/2017.
 * <></>
 */
public class EditProfileActivity extends AppCompatActivity implements EditProfileContract.View {
    private ActivityEditProfileBinding mBinding;
    private EditProfileContract.Presenter mPresenter;
    private User mUser = new User();

    public static Intent getProfileEditionIntent(Context context) {
        return new Intent(context, EditProfileActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        mPresenter = new EditProfilePresenter(this, mUser);
        mBinding.setPresenter((EditProfilePresenter) mPresenter);
        mBinding.setHandler(new EditProfileHandle(mPresenter));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void chooseImage() {
        if (PermissionsUtil.isAllowPermissions(this)) pickImage();
    }

    @Override
    public void showMessageError(int message) {
        ActivityUtil.showToast(this, message);
    }

    @Override
    public void start() {
        setSupportActionBar(mBinding.layoutToolbar.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title_update_profile);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICKER_SELECT && resultCode == RESULT_OK &&
            null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String urlAvatar = cursor.getString(columnIndex);
            mPresenter.setUserUrlImage(urlAvatar);
            cursor.close();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != PERMISSIONS_REQUEST_WRITE_EXTERNAL) return;
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        } else showMessageError(R.string.msg_image_not_choose);
    }

    public void pickImage() {
        Intent intent = new Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE_PICKER_SELECT);
    }
}
