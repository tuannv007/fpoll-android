package com.framgia.fpoll.ui.polledition.editinformation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.databinding.FragmentEditInforBinding;
import com.framgia.fpoll.util.Constant;
import com.framgia.fpoll.util.SharePreferenceUtil;
import com.framgia.fpoll.util.TimeUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import java.util.Calendar;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;
import static com.framgia.fpoll.util.Constant.Tag.DATE_PICKER_TAG;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditInforFragment extends Fragment
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener,
        EditInforContract.View {
    private FragmentEditInforBinding mBinding;
    private EditInforContract.Presenter mPresenter;
    private PollItem mPoll;
    private Calendar mCalendar = Calendar.getInstance();

    public static EditInforFragment newInstance(PollItem pollItem) {
        EditInforFragment editInforFragment = new EditInforFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, pollItem);
        editInforFragment.setArguments(bundle);
        return editInforFragment;
    }

    private void getDataFromActivity() {
        Bundle bundle = getArguments();
        if (bundle == null || bundle.getParcelable(BUNDLE_POLL_ITEM) == null) return;
        mPoll = bundle.getParcelable(BUNDLE_POLL_ITEM);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mBinding = FragmentEditInforBinding.inflate(inflater, container, false);
        getDataFromActivity();
        mPresenter =
                new EditInforPresenter(this, mPoll, SharePreferenceUtil.getIntances(getActivity()));
        mBinding.setInformation(mPoll);
        mBinding.setHandler(new EditInforHandle(mPresenter));
        mBinding.setPresenter((EditInforPresenter) mPresenter);
        mBinding.setFragment(this);
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
        DatePickerDialog datePicker =
                DatePickerDialog.newInstance(this, mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show(getActivity().getFragmentManager(), DATE_PICKER_TAG);
    }

    @Override
    public void bindError() {
        mBinding.setMsgError(getString(R.string.msg_content_error));
        mBinding.setMsgErrorEmail(getString(R.string.msg_email_invalidate));
    }

    @Override
    public void showTimePicker() {
        TimePickerDialog timePicker =
                TimePickerDialog.newInstance(this, mCalendar.get(Calendar.HOUR_OF_DAY),
                        mCalendar.get(Calendar.MINUTE), mCalendar.get(Calendar.SECOND), true);
        timePicker.show(getActivity().getFragmentManager(), Constant.Tag.TIME_PICKER_TAG);
    }

    @Override
    public void start() {
    }

    public boolean checkNextUI() {
        bindError();
        return !(TextUtils.isEmpty(mPoll.getUser().getUsername())
                || TextUtils.isEmpty(mPoll.getUser().getEmail())
                || TextUtils.isEmpty(mPoll.getTitle())
                || !android.util.Patterns.EMAIL_ADDRESS.matcher(mPoll.getUser().getEmail())
                .matches());
    }
}
