package com.framgia.fpoll.ui.pollcreation.option;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;
import static com.framgia.fpoll.util.Constant.DataConstant.DATA_IMAGE;
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
    public static final int DEFAULT_OPTION = 4;
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
        mPresenter = new OptionPresenter(this, mPoll);
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
        Intent intent = new Intent();
        intent.setType(DATA_IMAGE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.app_name)),
                IMAGE_PICKER_SELECT);
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
        DatePickerDialog dpd = DatePickerDialog.newInstance(this, mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        dpd.show(getActivity().getFragmentManager(), DATE_PICKER_TAG);
    }

    @Override
    public void notifyData() {
        mAdapter.get().notifyDataSetChanged();
    }

    private void showTimePicker() {
        if (mCalendar == null) mCalendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog =
                TimePickerDialog.newInstance(this, mCalendar.get(Calendar.HOUR_OF_DAY),
                        mCalendar.get(Calendar.MINUTE), mCalendar.get(Calendar.SECOND), true);
        timePickerDialog.show(getActivity().getFragmentManager(), TIME_PICKER_TAG);
    }

    @Override
    public void showOptionError() {
        ActivityUtil.showToast(getActivity(), R.string.msg_option_blank);
    }

    @Override
    public void deletePoll(int position) {
        if (position == 0 && mAdapter.get().getItemCount() == 1
                || position > mAdapter.get().getItemCount() - 1) {
            return;
        }
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

    public ObservableField<OptionAdapter> getAdapter() {
        return mAdapter;
    }

    public void checkNextUI(@NonNull OnCheckOptionListenner listenner) {
        mPresenter.checkNextUi(listenner);
    }

    public interface OnCheckOptionListenner {
        void onSuccessful();
    }
}
