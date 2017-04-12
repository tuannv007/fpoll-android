package com.framgia.fpoll.ui.pollmanage.result;

import android.content.pm.PackageManager;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.databinding.FragmentVoteResultBinding;
import com.framgia.fpoll.ui.pollmanage.ManagePollActivity;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.PermissionsUtil;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;
import java.io.File;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_TOKEN;
import static com.framgia.fpoll.util.Constant.Export.FILE_NAME_SAVED_EXCEL;
import static com.framgia.fpoll.util.Constant.Export.FPOLL_FOLDER_NAME;
import static com.framgia.fpoll.util.Constant.Export.TYPE_EXPORT_PDF;
import static com.framgia.fpoll.util.Constant.RequestCode.PERMISSIONS_REQUEST_WRITE_EXTERNAL;
import static com.framgia.fpoll.util.TimeUtil.getCurentTime;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultVoteFragment extends Fragment implements ResultVoteContract.View {
    private FragmentVoteResultBinding mBinding;
    private ResultVoteContract.Presenter mPresenter;
    private ObservableField<ResultVoteAdapter> mAdapter = new ObservableField<>();
    private String mToken;
    private File mFile;
    private ObservableField<ResultType> mResultType = new ObservableField<>(ResultType.TABLE);
    private DataInfoItem mPoll;
    private BarData mBarData = new BarData();
    private PieData mPieChart = new PieData();

    public static ResultVoteFragment newInstance(String token) {
        ResultVoteFragment resultVoteFragment = new ResultVoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TOKEN, token);
        resultVoteFragment.setArguments(bundle);
        return resultVoteFragment;
    }

    private void getDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        if (bundle.getString(BUNDLE_TOKEN) != null) {
            mToken = bundle.getString(BUNDLE_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mBinding = FragmentVoteResultBinding.inflate(inflater, container, false);
        getDataFromIntent();
        mPresenter =
                new ResultVotePresenter(this, VoteInfoRepository.getInstance(getActivity()), mToken,
                        mFile);
        mBinding.setFragment(this);
        mBinding.setHandler(new ResultActionHandler(mPresenter));
        mBinding.setBarChart(mBarData);
        mBinding.setPieChart(mPieChart);
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        setHasOptionsMenu(true);
        File exportDir = new File(Environment.getExternalStorageDirectory(), FPOLL_FOLDER_NAME);
        if (!exportDir.exists()) exportDir.mkdirs();
        mFile = new File(exportDir, getCurentTime() + FILE_NAME_SAVED_EXCEL);
        mAdapter.set(new ResultVoteAdapter());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.vote_result_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_table:
                mResultType.set(ResultType.TABLE);
                break;
            case R.id.action_pie_chart:
                mResultType.set(ResultType.PIE_CHART);
                break;
            case R.id.action_bar_chart:
                mResultType.set(ResultType.BAR_CHART);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(ResultVoteItem resultVoteList) {
        mAdapter.get().update(resultVoteList.getResults());
    }

    @Override
    public void onError(String message) {
        ActivityUtil.showToast(getActivity(), message);
    }

    @Override
    public void dismissDialog() {
        ((ManagePollActivity) getActivity()).showProgressDialog();
    }

    @Override
    public void showDialog() {
        ((ManagePollActivity) getActivity()).hideProgressDialog();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != PERMISSIONS_REQUEST_WRITE_EXTERNAL) return;
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (mPresenter.getKey() == TYPE_EXPORT_PDF) {
                mPresenter.exportPDF();
            } else {
                mPresenter.exportExcel();
            }
        } else {
            ActivityUtil.showToast(getActivity(), R.string.msg_image_not_choose);
        }
    }

    @Override
    public void updateBarChart(BarData barChart) {
        mBarData = barChart;
        mBinding.setBarChart(barChart);
    }

    @Override
    public void updatePieChart(PieData pieData) {
        mPieChart = pieData;
        mBinding.setPieChart(mPieChart);
    }

    public ObservableField<ResultVoteAdapter> getAdapter() {
        return mAdapter;
    }

    public ObservableField<ResultType> getResultType() {
        return mResultType;
    }
}
