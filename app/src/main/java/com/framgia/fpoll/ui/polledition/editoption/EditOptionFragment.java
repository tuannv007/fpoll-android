package com.framgia.fpoll.ui.polledition.editoption;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.databinding.FragmentEditOptionBinding;
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
 * Created by framgia on 16/03/2017.
 */
public class EditOptionFragment extends Fragment
        implements EditOptionContract.View, DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {
    private static final int UNSELECTED_POSITION = -1;
    private static final int NUMBER_DEFAULT_OPTION = 4;
    private static final long DELAY_VIEW_TIME = 700;
    private ObservableField<EditOptionAdapter> mAdapter = new ObservableField<>();
    private int mPosition = UNSELECTED_POSITION;
    private PollItem mPollItem;
    private Calendar mCalendar = Calendar.getInstance();

    public static EditOptionFragment newInstance(PollItem pollItem) {
        EditOptionFragment editOptionFragment = new EditOptionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, pollItem);
        editOptionFragment.setArguments(bundle);
        return editOptionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentEditOptionBinding binding =
                FragmentEditOptionBinding.inflate(inflater, container, false);
        mPollItem = getArguments().getParcelable(BUNDLE_POLL_ITEM);
        EditOptionContract.Presenter presenter =
                new EditOptionPresenter(this, mPollItem, PollRepository.getInstance(getActivity()));
        binding.setHandler(new EditOptionHandle(presenter));
        binding.setPresenter((EditOptionPresenter) presenter);
        binding.setFragment(this);
        mAdapter.set(new EditOptionAdapter(presenter, mPollItem.getOptions()));
        return binding.getRoot();
    }

    @Override
    public void start() {
        if (mPollItem.getOptions() != null && mPollItem.getOptions().size() == 0) {
            for (int i = 0; i < NUMBER_DEFAULT_OPTION; i++) {
                mPollItem.getOptions().add(new Option());
            }
        }
    }

    @Override
    public void openGallery(Option optionItem, int position) {
        mPosition = position;
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
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int resId) {
        Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deletePoll(int position) {
        mPollItem.getOptions().remove(position);
        mAdapter.get().update(mPollItem.getOptions());
    }

    @Override
    public void augmentPoll(int position) {
        if (position != mAdapter.get().getItemCount() - 1) return;
        mPollItem.getOptions().add(new Option());
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
        if (requestCode == IMAGE_PICKER_SELECT && resultCode == RESULT_OK && data != null) {
            String url = ActivityUtil.getPathFromUri(getActivity(), data.getData());
            if (mPosition != UNSELECTED_POSITION) {
                mPollItem.getOptions().get(mPosition).setImage(url);
                mAdapter.get().update(mPollItem.getOptions());
            }
        }
    }

    public ObservableField<EditOptionAdapter> getAdapter() {
        return mAdapter;
    }

    @Override
    public void updateUI(final OnCheckOptionListener listener) {
        mAdapter.get().notifyDataSetChanged();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onSuccessful();
            }
        }, DELAY_VIEW_TIME);
    }

    public void notifyUI(PollItem poll) {
        mPollItem = poll;
        mAdapter.get().update(mPollItem.getOptions());
    }

    @Override
    public void onPickDate(Option optionItem, int position) {
        mPosition = position;
        if (mCalendar == null) mCalendar = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(this, mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        dpd.show(getActivity().getFragmentManager(), DATE_PICKER_TAG);
    }

    private void showTimePicker() {
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
        if (mPollItem != null && mPollItem.getOptions() != null) {
            mPollItem.getOptions().get(mPosition).setDate(TimeUtil.timeOptionToString(mCalendar));
        }
    }

    public interface OnCheckOptionListener {
        void onSuccessful();
    }
}
