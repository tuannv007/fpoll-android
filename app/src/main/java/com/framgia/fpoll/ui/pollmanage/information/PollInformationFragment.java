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
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.databinding.FragmentInformationBinding;
import com.framgia.fpoll.ui.pollmanage.ManagePollActivity;
import com.framgia.fpoll.ui.pollmanage.information.pollsetting.PollSettingDialogFragment;
import com.framgia.fpoll.ui.pollmanage.information.viewoption.PollOptionDialogFragment;
import com.framgia.fpoll.ui.votemanager.LinkVoteActivity;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;
import com.framgia.fpoll.util.SharePreferenceUtil;
import com.framgia.fpoll.util.TimeUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import java.util.Calendar;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_TOKEN;
import static com.framgia.fpoll.util.Constant.POSITION_LINK_INVITE;
import static com.framgia.fpoll.util.Constant.Tag.DATE_PICKER_TAG;
import static com.framgia.fpoll.util.Constant.Tag.TIME_PICKER_TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class PollInformationFragment extends Fragment
        implements PollInformationContract.View, DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {
    private Calendar mCalendar = Calendar.getInstance();
    private String mToken;
    private DataInfoItem mPoll;
    private FragmentInformationBinding mBinding;

    public static PollInformationFragment newInstance(String token) {
        PollInformationFragment fragment = new PollInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TOKEN, token);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void getDataFromActivity() {
        Bundle bundle = getArguments();
        if (bundle.getString(BUNDLE_TOKEN) != null) {
            mToken = bundle.getString(BUNDLE_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mBinding = FragmentInformationBinding.inflate(inflater, container, false);
        getDataFromActivity();
        PollInformationContract.Presenter presenter =
                new PollInformationPresenter(this, PollRepository.getInstance(getActivity()),
                        ManagerRepository.getInstance(getActivity()),
                        SharePreferenceUtil.getIntances(getActivity()), mToken);
        mBinding.setHandler(new PollInformationHandler(presenter));
        return mBinding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void startUiVoting() {
        if (mPoll == null) return;
        String token = mPoll.getPoll().getLink().get(POSITION_LINK_INVITE).getToken();
        startActivity(LinkVoteActivity.getTokenIntent(getActivity(), token));
    }

    @Override
    public void showDialogOption() {
        if (mPoll == null || mPoll.getPoll().getOptions() == null) return;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        DialogFragment optionDialog =
                PollOptionDialogFragment.newInstance(mPoll.getPoll().getOptions());
        optionDialog.show(transaction, Constant.TYPE_DIALOG_FRAGMENT);
    }

    @Override
    public void showDialogSetting() {
        if (mPoll == null || mPoll.getPoll().getSettings() == null) return;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        DialogFragment optionDialog =
                PollSettingDialogFragment.newInstance(mPoll.getPoll().getSettings());
        optionDialog.show(transaction, Constant.TYPE_DIALOG_FRAGMENT);
    }

    @Override
    public void showDateTimePicker() {
        if (mCalendar == null) mCalendar = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(this, mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        dpd.show(getActivity().getFragmentManager(), DATE_PICKER_TAG);
    }

    public void showTimePicker() {
        if (mCalendar == null) mCalendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog =
                TimePickerDialog.newInstance(this, mCalendar.get(Calendar.HOUR_OF_DAY),
                        mCalendar.get(Calendar.MINUTE), mCalendar.get(Calendar.SECOND), true);
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
        mPoll.getPoll().setDateClose(TimeUtil.convertTimeToString(mCalendar));
    }

    @Override
    public void onGetPollSuccessful(DataInfoItem data) {
        mPoll = data;
        mBinding.setInformation(mPoll);
    }

    @Override
    public void onGetPollFailed(String message) {
        ActivityUtil.showToast(getActivity(), message);
    }

    public DataInfoItem getPoll() {
        return mPoll;
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
