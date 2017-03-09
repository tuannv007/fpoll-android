package com.framgia.fpoll.ui.pollcreation.infomation;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.ApiRestClient.APIService.pollcreationservice.PollItem;
import com.framgia.fpoll.data.model.PollInformation;
import com.framgia.fpoll.databinding.FragmentCreatePollBinding;
import com.framgia.fpoll.ui.pollcreation.option.OptionPollFragment;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class CreatePollFragment extends Fragment
    implements DatePickerDialog.OnDateSetListener
    , TimePickerDialog.OnTimeSetListener
    , CreationContract.View {
    private FragmentCreatePollBinding mBinding;
    private CreationContract.Presenter mPresenter;
    public final ObservableField<Calendar> mTime = new ObservableField<>(Calendar.getInstance());
    private PollInformation mPollInformation = new PollInformation();
    private Calendar mSavePickCalendar = Calendar.getInstance();

    public static CreatePollFragment newInstance() {
        return new CreatePollFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_create_poll, container, false);
        mPresenter = new CreationPresenter(this, mPollInformation);
        mBinding.setInformation(mPollInformation);
        mBinding.setHandler(new CreatePollActionHandle(mPresenter));
        mBinding.setPresenter((CreationPresenter) mPresenter);
        mBinding.setFragment(this);
        mTime.set(mSavePickCalendar);
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
        } else mTime.notifyChange();
    }

    @Override
    public void showDatePicker() {
        DatePickerDialog dpd = DatePickerDialog.newInstance(
            this,
            mTime.get().get(Calendar.YEAR),
            mTime.get().get(Calendar.MONTH),
            mTime.get().get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getActivity().getFragmentManager(), Constant.Tag.DATE_PICKER_TAG);
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
        timePickerDialog.show(getActivity().getFragmentManager(), Constant.Tag.TIME_PICKER_TAG);
    }

    @Override
    public void nextStep() {
        PollItem pollItem = new PollItem();
        pollItem.setEmail(mPollInformation.getEmail());
        pollItem.setTitle(mPollInformation.getPollTitle());
        pollItem.setName(mPollInformation.getUserName());
        getFragmentManager().beginTransaction()
            .add(R.id.frame_layout, OptionPollFragment.newInstance(pollItem), null)
            .addToBackStack(null)
            .commit();
    }

    @Override
    public void start() {
    }
}
