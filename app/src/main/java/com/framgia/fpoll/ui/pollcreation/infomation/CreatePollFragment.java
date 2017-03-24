package com.framgia.fpoll.ui.pollcreation.infomation;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.databinding.FragmentCreatePollBinding;
import com.framgia.fpoll.ui.pollcreation.option.OptionPollFragment;
import com.framgia.fpoll.util.ActivityUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;
import static com.framgia.fpoll.util.Constant.Tag.DATE_PICKER_TAG;
import static com.framgia.fpoll.util.Constant.Tag.TIME_PICKER_TAG;

public class CreatePollFragment extends Fragment implements DatePickerDialog.OnDateSetListener
    , TimePickerDialog.OnTimeSetListener
    , CreationContract.View
    , GoogleApiClient.OnConnectionFailedListener {
    private FragmentCreatePollBinding mBinding;
    private CreationContract.Presenter mPresenter;
    public final ObservableField<Calendar> mTime = new ObservableField<>();
    private PollItem mPoll;
    private Calendar mSavePickCalendar = Calendar.getInstance();
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
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_create_poll, container, false);
        getDataFromActivity();
        mPresenter = new CreationPresenter(this, mPoll);
        mBinding.setInformation(mPoll);
        mBinding.setHandler(new CreatePollActionHandle(mPresenter));
        mBinding.setPresenter((CreationPresenter) mPresenter);
        mBinding.setFragment(this);
        mTime.set(mSavePickCalendar);
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
            .addApi(Places.GEO_DATA_API)
            .enableAutoManage(getActivity(), this)
            .build();
        mBinding.editLocation.setGoogleApiClient(mGoogleApiClient);
        return mBinding.getRoot();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mSavePickCalendar.set(Calendar.YEAR, year);
        mSavePickCalendar.set(Calendar.MONTH, monthOfYear);
        mSavePickCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        showTimePicker();
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        mSavePickCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mSavePickCalendar.set(Calendar.MINUTE, minute);
        mSavePickCalendar.set(Calendar.SECOND, second);
        if (mSavePickCalendar.before(Calendar.getInstance())) {
            ActivityUtil.showToast(getContext(), R.string.msg_date_error);
        } else {
            mTime.set(mSavePickCalendar);
            mTime.notifyChange();
        }
    }

    @Override
    public void showDatePicker() {
        if (mTime.get() == null) mTime.set(Calendar.getInstance());
        DatePickerDialog dpd = DatePickerDialog.newInstance(
            this,
            mTime.get().get(Calendar.YEAR),
            mTime.get().get(Calendar.MONTH),
            mTime.get().get(Calendar.DAY_OF_MONTH)
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
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
            this,
            mTime.get().get(Calendar.HOUR_OF_DAY),
            mTime.get().get(Calendar.MINUTE),
            mTime.get().get(Calendar.SECOND),
            true
        );
        timePickerDialog.show(getActivity().getFragmentManager(), TIME_PICKER_TAG);
    }

    @Override
    public void nextStep() {
        mPoll.setDateClose(mBinding.edtChooseTime.getText().toString());
        getFragmentManager().beginTransaction()
            .add(R.id.frame_layout, OptionPollFragment.newInstance(mPoll), null)
            .addToBackStack(null)
            .commit();
    }

    @Override
    public void start() {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
