package com.framgia.fpoll.ui.votemanager.updateoption;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.databinding.FragmentUpdateOptionBinding;
import com.framgia.fpoll.ui.votemanager.LinkVoteActivity;
import com.framgia.fpoll.ui.votemanager.vote.VoteFragment;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.PermissionsUtil;
import com.framgia.fpoll.util.TimeUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_EVENT;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_OPTION;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POSITION;
import static com.framgia.fpoll.util.Constant.DataConstant.DATA_IMAGE;
import static com.framgia.fpoll.util.Constant.RequestCode.IMAGE_PICKER_SELECT;
import static com.framgia.fpoll.util.Constant.RequestCode.PERMISSIONS_REQUEST_WRITE_EXTERNAL;
import static com.framgia.fpoll.util.Constant.Tag.DATE_PICKER_TAG;
import static com.framgia.fpoll.util.Constant.Tag.TIME_PICKER_TAG;

public class UpdateOptionFragment extends DialogFragment
        implements UpdateOptionContract.View, DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {
    private Option mOption;
    private List<Option> mOptionTemplate;
    private Calendar mCalendar = Calendar.getInstance();
    private int mPosition;
    private VoteFragment.OnUpdateUI mOnUpdateOptionEvent;

    public static UpdateOptionFragment newInstance(List<Option> options, int position,
            VoteFragment.OnUpdateUI onUpdateUI) {
        UpdateOptionFragment fragment = new UpdateOptionFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(BUNDLE_OPTION, (ArrayList<Option>) options);
        args.putInt(BUNDLE_POSITION, position);
        args.putParcelable(BUNDLE_EVENT, onUpdateUI);
        fragment.setArguments(args);
        return fragment;
    }

    private void getDataFromActivity() {
        if (getArguments() != null) {
            mOptionTemplate = getArguments().getParcelableArrayList(BUNDLE_OPTION);
            mPosition = getArguments().getInt(BUNDLE_POSITION);
            mOnUpdateOptionEvent = getArguments().getParcelable(BUNDLE_EVENT);
            if (mOptionTemplate == null || mOptionTemplate.size() == 0) return;
            try {
                mOption = (Option) mOptionTemplate.get(mPosition).clone();
                ActivityUtil.splitDateOption(mOption);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        FragmentUpdateOptionBinding binding =
                FragmentUpdateOptionBinding.inflate(inflater, container, false);
        getDataFromActivity();
        binding.setFragment(this);
        binding.setPresenter(
                new UpdateOptionPresenter(this, PollRepository.getInstance(getActivity()),
                        mOptionTemplate, mPosition));
        binding.setOption(mOption);
        return binding.getRoot();
    }

    @Override
    public void start() {

    }

    @Override
    public void pickImageFromGallery(Option option) {
        if (PermissionsUtil.isAllowPermissions(getActivity())) {
            Intent intent = new Intent();
            intent.setType(DATA_IMAGE);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, getString(R.string.app_name)),
                    IMAGE_PICKER_SELECT);
        }
    }

    @Override
    public void showPickDate(Option option) {
        if (mCalendar == null) mCalendar = Calendar.getInstance();
        DatePickerDialog datePicker =
                DatePickerDialog.newInstance(this, mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show(getActivity().getFragmentManager(), DATE_PICKER_TAG);
    }

    @Override
    public void updateOption(Option option) {
        if (option != null) mOption = option;
    }

    @Override
    public void updateUIVote() {
        mOptionTemplate.set(mPosition, mOption);
        if (mOnUpdateOptionEvent != null) mOnUpdateOptionEvent.updateOption(mOption, mPosition);
    }

    @Override
    public void onDismiss() {
        if (getDialog() != null && getDialog().isShowing()) getDialog().dismiss();
    }

    @Override
    public void showProgress() {
        ((LinkVoteActivity) getActivity()).showProgressDialog();
    }

    @Override
    public void hideProgress() {
        ((LinkVoteActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, monthOfYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        showTimePicker();
    }

    private void showTimePicker() {
        if (mCalendar == null) mCalendar = Calendar.getInstance();
        TimePickerDialog timePicker =
                TimePickerDialog.newInstance(this, mCalendar.get(Calendar.HOUR_OF_DAY),
                        mCalendar.get(Calendar.MINUTE), mCalendar.get(Calendar.SECOND), true);
        timePicker.show(getActivity().getFragmentManager(), TIME_PICKER_TAG);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mCalendar.set(Calendar.MINUTE, minute);
        mCalendar.set(Calendar.SECOND, second);
        if (mOption != null) mOption.setDate(TimeUtil.timeOptionToString(mCalendar));
    }

    @Override
    public void onDeleteImage() {
        mOption.setImage(null);
    }

    @Override
    public void showMessage(int message) {
        ActivityUtil.showToast(getActivity(), message);
    }

    @Override
    public void showMessage(String message) {
        ActivityUtil.showToast(getActivity(), message);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != PERMISSIONS_REQUEST_WRITE_EXTERNAL) return;
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImageFromGallery(mOption);
        } else {
            ActivityUtil.showToast(getActivity(), R.string.msg_image_not_choose);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICKER_SELECT
                && resultCode == RESULT_OK
                && data != null
                && mOption != null) {
            mOption.setImage(ActivityUtil.getPathFromUri(getActivity(), data.getData()));
        }
    }
}
