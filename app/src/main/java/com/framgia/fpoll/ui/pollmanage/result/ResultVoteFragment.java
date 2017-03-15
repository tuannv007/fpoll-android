package com.framgia.fpoll.ui.pollmanage.result;

import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.source.remote.resultvote.ResultVoteDataRepository;
import com.framgia.fpoll.databinding.FragmentVoteResultBinding;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;
import com.framgia.fpoll.util.PermissionsUtil;
import com.framgia.fpoll.widget.FPollProgressDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.util.Constant.ConstantApi.KEY_TOKEN;
import static com.framgia.fpoll.util.Constant.Export.FILE_NAME_SAVED_EXCEL;
import static com.framgia.fpoll.util.Constant.Export.FPOLL_FOLDER_NAME;
import static com.framgia.fpoll.util.TimeUtil.getCurentTime;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultVoteFragment extends Fragment implements ResultVoteContract.View {
    private FragmentVoteResultBinding mBinding;
    private ResultVoteContract.Presenter mPresenter;
    private ObservableField<ResultVoteAdapter> mAdapter = new ObservableField<>();
    private List<ResultVoteItem.Result> mListResultVote = new ArrayList<>();
    private String mToken;
    private FPollProgressDialog mDialog;
    private File mFile;

    public static ResultVoteFragment newInstance(String token) {
        ResultVoteFragment resultVoteFragment = new ResultVoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TOKEN, token);
        resultVoteFragment.setArguments(bundle);
        return resultVoteFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_vote_result, container, false);
        getDataFromIntent();
        start();
        mPresenter =
            new ResultVotePresenter(ResultVoteDataRepository.getInstance(getActivity()),
                this, mListResultVote, mFile);
        mBinding.setFragment(this);
        mBinding.setHandler(new ResultActionHandler(mPresenter));
        mPresenter.getAllData(mToken);
        return mBinding.getRoot();
    }

    @Override
    public void onSuccess(ResponseItem<ResultVoteItem> resultVoteList) {
        mListResultVote.addAll(resultVoteList.getData().getResults());
        mAdapter.set(new ResultVoteAdapter(mListResultVote));
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) mDialog.dismiss();
    }

    @Override
    public void showDialog() {
        if (mDialog == null) mDialog = new FPollProgressDialog(getActivity());
        mDialog.show();
    }

    @Override
    public void exportError() {
        ActivityUtil.showToast(getActivity(), getString(R.string.msg_export_error));
    }

    @Override
    public void exportSuccess(String path) {
        ActivityUtil.showToast(getActivity(), getString(R.string.msg_export_success) + path);
    }

    @Override
    public boolean isAllowPermissions() {
        return PermissionsUtil.isAllowPermissions(getActivity());
    }

    @Override
    public void start() {
        File exportDir =
            new File(Environment.getExternalStorageDirectory(), FPOLL_FOLDER_NAME);
        if (!exportDir.exists()) exportDir.mkdirs();
        mFile = new File(exportDir, getCurentTime() + FILE_NAME_SAVED_EXCEL);
    }

    public void getDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        mToken = bundle.getString(KEY_TOKEN);
    }

    public ObservableField<ResultVoteAdapter> getAdapter() {
        return mAdapter;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == Constant.RequestCode.PERMISSIONS_REQUEST_WRITE_EXTERNAL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (mPresenter.getKey() == Constant.Export.TYPE_EXPORT_PDF) mPresenter.exportPDF();
                else mPresenter.exportExcel();
            } else {
                ActivityUtil.showToast(getActivity(), R.string.msg_image_not_choose);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
