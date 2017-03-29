package com.framgia.fpoll.ui.pollcreation.infomation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.databinding.FragmentCreatePollBinding;
import com.framgia.fpoll.util.TimeUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;
import static com.framgia.fpoll.util.Constant.Tag.DATE_PICKER_TAG;
import static com.framgia.fpoll.util.Constant.Tag.TIME_PICKER_TAG;

public class CreatePollFragment extends Fragment
    implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener,
    CreationContract.View, GoogleApiClient.OnConnectionFailedListener {
    private FragmentCreatePollBinding mBinding;
    private CreationContract.Presenter mPresenter;
    private PollItem mPoll;
    private Calendar mCalendar = Calendar.getInstance();
    private GoogleApiClient mGoogleApiClient;

    public static CreatePollFragment newInstance(PollItem data) {
        CreatePollFragment fragment = new CreatePollFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void getDataFromActivity() {
        Bundle bundle = getArguments();
        if (bundle == null || bundle.getParcelable(BUNDLE_POLL_ITEM) == null) return;
        mPoll = bundle.getParcelable(BUNDLE_POLL_ITEM);
        if (mPoll == null) mPoll = new PollItem();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCreatePollBinding.inflate(inflater, container, false);
        getDataFromActivity();
        mPresenter = new CreationPresenter(this, mPoll);
        mBinding.setInformation(mPoll);
        mBinding.setHandler(new CreatePollActionHandle(mPresenter));
        mBinding.setPresenter((CreationPresenter) mPresenter);
        mBinding.setFragment(this);
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
            .addApi(Places.GEO_DATA_API)
            .enableAutoManage(getActivity(), this)
            .build();
        mBinding.editLocation.setGoogleApiClient(mGoogleApiClient);
        bindError();
        return mBinding.getRoot();
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
        mPoll.setDateClose(TimeUtil.convertTimeToString(mCalendar));
    }

    @Override
    public void showDatePicker() {
        if (mCalendar == null) mCalendar = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
            this,
            mCalendar.get(Calendar.YEAR),
            mCalendar.get(Calendar.MONTH),
            mCalendar.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getActivity().getFragmentManager(), DATE_PICKER_TAG);
    }

    @Override
    public void bindError() {
        mBinding.setMsgError(getString(R.string.msg_content_error));
        mBinding.setMsgErrorEmail(getString(R.string.msg_email_invalidate));
    }

    @Override
    public void showTimePicker() {
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
    public void start() {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }

    public boolean checkNextUI() {
        return !(TextUtils.isEmpty(mPoll.getUser().getUsername()) ||
            TextUtils.isEmpty(mPoll.getUser().getEmail()) ||
            TextUtils.isEmpty(mPoll.getTitle()) ||
            !android.util.Patterns.EMAIL_ADDRESS.matcher(mPoll.getUser().getEmail()).matches());
    }
}
