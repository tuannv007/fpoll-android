package com.framgia.fpoll.ui.polledition.editsetting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.databinding.FragmentEditSettingBinding;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;

/**
 * Created by framgia on 17/03/2017.
 */
public class EditSettingFragment extends Fragment implements EditSettingContract.View {
    private PollItem mPoll;

    public static EditSettingFragment newInstance(PollItem pollItem) {
        EditSettingFragment editSettingFragment = new EditSettingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, pollItem);
        editSettingFragment.setArguments(bundle);
        return editSettingFragment;
    }

    private void getDataFromActivity() {
        if (getArguments() != null) {
            mPoll = getArguments().getParcelable(BUNDLE_POLL_ITEM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentEditSettingBinding binding =
                FragmentEditSettingBinding.inflate(inflater, container, false);
        getDataFromActivity();
        EditSettingContract.Presenter presenter = new EditSettingPresenter(this, mPoll);
        binding.setHandler(new EditSettingHandler(presenter));
        binding.setPresenter((EditSettingPresenter) presenter);
        return binding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int resId) {
        Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
    }
}
