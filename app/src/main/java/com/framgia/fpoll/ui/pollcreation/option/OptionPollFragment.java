package com.framgia.fpoll.ui.pollcreation.option;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.ObservableField;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.databinding.FragmentPageOptionBinding;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.PermissionsUtil;
import com.framgia.fpoll.util.TimeUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;
import static com.framgia.fpoll.util.Constant.RequestCode.IMAGE_PICKER_SELECT;
import static com.framgia.fpoll.util.Constant.RequestCode.PERMISSIONS_REQUEST_WRITE_EXTERNAL;
import static com.framgia.fpoll.util.Constant.Tag.DATE_PICKER_TAG;
import static com.framgia.fpoll.util.Constant.Tag.TIME_PICKER_TAG;

/**
 * Created by nhahv on 22/02/2017.
 * <
 */
public class OptionPollFragment extends Fragment
    implements OptionPollContract.View, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private static final int DEFAULT_OPTION = 4;
    private static final long DELAY_VIEW_TIME = 700;
    private FragmentPageOptionBinding mBinding;
    private OptionPollContract.Presenter mPresenter;
    private ObservableField<OptionAdapter> mAdapter = new ObservableField<>();
    private PollItem mPoll = new PollItem();
    private Option mOption;
    private Calendar mCalendar = Calendar.getInstance();

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
            for (int i = 0; i < DEFAULT_OPTION; i++) {
                mPoll.getOptions().add(new Option());
            }
        }
    }

    @Override
    public void openGallery(Option optionItem, int position) {
        mOption = optionItem;
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
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, monthOfYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        showTimePicker();
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mCalendar.set(Calendar.MINUTE, minute);
        mCalendar.set(Calendar.SECOND, second);
        if (mOption != null) mOption.setDate(TimeUtil.timeOptionToString(mCalendar));
    }

    @Override
    public void datePicker(Option option, int position) {
        mOption = option;
        if (mCalendar == null) mCalendar = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
            this,
            mCalendar.get(Calendar.YEAR),
            mCalendar.get(Calendar.MONTH),
            mCalendar.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getActivity().getFragmentManager(), DATE_PICKER_TAG);
    }

    private void showTimePicker() {
        if (mCalendar == null) mCalendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
            this,
            mCalendar.get(Calendar.HOUR_OF_DAY),
            mCalendar.get(Calendar.MINUTE),
            mCalendar.get(Calendar.SECOND),
            true
        );
        timePickerDialog.show(getActivity().getFragmentManager(), TIME_PICKER_TAG);
    }

    @Override
    public void showError() {
        ActivityUtil.showToast(getActivity(), R.string.msg_option_blank);
    }

    @Override
    public void deletePoll(int position) {
        if (position == 0 && mAdapter.get().getItemCount() == 1 ||
            position > mAdapter.get().getItemCount() - 1) return;
        mPoll.getOptions().remove(position);
        mAdapter.get().notifyDataSetChanged();
    }

    @Override
    public void augmentPoll(int position) {
        if (position != mAdapter.get().getItemCount() - 1) return;
        mPoll.getOptions().add(new Option());
        mAdapter.get().notifyItemInserted(mAdapter.get().getItemCount() - 1);
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
        if (requestCode == IMAGE_PICKER_SELECT &&
            resultCode == RESULT_OK && data != null && mOption != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver()
                .query(selectedImage, filePathColumn, null, null, null);
            if (cursor == null) return;
            cursor.moveToFirst();
            String url = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            mOption.setImage(url);
            cursor.close();
        }
    }

    public ObservableField<OptionAdapter> getAdapter() {
        return mAdapter;
    }

    public void checkNextUI(@NonNull final OnCheckOptionListenner listtenner) {
        boolean isDeleteEmptyOption = false;
        for (int i = mPoll.getOptions().size() - 1; i >= 0; i--) {
            Option item = mPoll.getOptions().get(i);
            if (item.getName() == null || TextUtils.isEmpty(item.getName()) &&
                item.getImage() == null) {
                mPoll.getOptions().remove(i);
                isDeleteEmptyOption = true;
            }
        }
        if (mPoll.getOptions().size() == 0) {
            for (int i = 0; i < DEFAULT_OPTION; i++) {
                mPoll.getOptions().add(new Option());
            }
            ActivityUtil.showToast(getApplicationContext(), R.string.msg_option_blank);
            return;
        }
        if (isDeleteEmptyOption) {
            mAdapter.get().notifyDataSetChanged();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listtenner.onSuccessful();
                }
            }, DELAY_VIEW_TIME);
        } else {
            listtenner.onSuccessful();
        }
    }

    public interface OnCheckOptionListenner {
        void onSuccessful();
    }
}
