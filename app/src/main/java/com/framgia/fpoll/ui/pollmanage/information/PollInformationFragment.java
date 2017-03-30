package com.framgia.fpoll.ui.pollmanage.information;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.databinding.FragmentInformationBinding;
import com.framgia.fpoll.ui.pollmanage.ManagePollActivity;
import com.framgia.fpoll.ui.pollmanage.information.pollsetting.PollSettingDialogFragment;
import com.framgia.fpoll.ui.pollmanage.information.viewoption.PollOptionDialogFragment;
import com.framgia.fpoll.ui.votemanager.LinkVoteActivity;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;
import com.framgia.fpoll.util.TimeUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import static com.framgia.fpoll.util.Constant.ConstantApi.KEY_POLL_INFO;
import static com.framgia.fpoll.util.Constant.POSITION_LINK_INVITE;
import static com.framgia.fpoll.util.Constant.Tag.DATE_PICKER_TAG;
import static com.framgia.fpoll.util.Constant.Tag.TIME_PICKER_TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class PollInformationFragment extends Fragment
    implements PollInformationContract.View, DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private FragmentInformationBinding mBinding;
    private PollInformationContract.Presenter mPresenter;
    private DataInfoItem mPollInfo;
    private Calendar mCalendar = Calendar.getInstance();

    public static PollInformationFragment newInstance(DataInfoItem pollInfo) {
        PollInformationFragment fragment = new PollInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_POLL_INFO, pollInfo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentInformationBinding.inflate(inflater, container, false);
        getData();
        mPresenter = new PollInformationPresenter(this, mPollInfo,
            PollRepository.getInstance(getActivity()));
        mBinding.setHandler(new PollInformationHandler(mPresenter));
        mBinding.setInformation(mPollInfo);
        return mBinding.getRoot();
    }

    public void getData() {
        Bundle bundle = getArguments();
        if (bundle == null || bundle.getParcelable(KEY_POLL_INFO) == null) return;
        mPollInfo = bundle.getParcelable(KEY_POLL_INFO);
    }

    @Override
    public void start() {
    }

    @Override
    public void startUiVoting() {
        String token = mPollInfo.getPoll().getLink().get(POSITION_LINK_INVITE).getToken();
        startActivity(LinkVoteActivity.getTokenIntent(getActivity(), token));
    }

    @Override
    public void showDialogOption() {
        if (mPollInfo == null || mPollInfo.getPoll().getOptions() == null) return;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        DialogFragment optionDialog =
            PollOptionDialogFragment.newInstance(mPollInfo.getPoll().getOptions());
        optionDialog.show(transaction, Constant.TYPE_DIALOG_FRAGMENT);
    }

    @Override
    public void showDialogSetting() {
        if (mPollInfo == null || mPollInfo.getPoll().getSettings() == null) return;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        DialogFragment optionDialog =
            PollSettingDialogFragment.newInstance(mPollInfo.getPoll().getSettings());
        optionDialog.show(transaction, Constant.TYPE_DIALOG_FRAGMENT);
    }

    @Override
    public void showDateTimePicker() {
        if (mCalendar == null) mCalendar = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
            this,
            mCalendar.get(Calendar.YEAR),
            mCalendar.get(Calendar.MONTH),
            mCalendar.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getActivity().getFragmentManager(), DATE_PICKER_TAG);
    }

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
        mPollInfo.getPoll().setDateClose(TimeUtil.convertTimeToString(mCalendar));
    }

    @Override
    public void showProgress() {
        ((ManagePollActivity) getActivity()).showProgressDialog();
    }

    @Override
    public void hideProgress() {
        ((ManagePollActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void saveSuccess(String data) {
        ActivityUtil.showToast(getActivity(), data);
    }

    @Override
    public void onError(String msg) {
        ActivityUtil.showToast(getActivity(), msg);
    }
}
