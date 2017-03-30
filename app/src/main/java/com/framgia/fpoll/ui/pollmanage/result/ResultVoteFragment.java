package com.framgia.fpoll.ui.pollmanage.result;

import android.content.pm.PackageManager;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.ResultItem;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.source.remote.resultvote.ResultVoteDataRepository;
import com.framgia.fpoll.databinding.FragmentVoteResultBinding;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.ui.votemanager.barchart.BarchartFragment;
import com.framgia.fpoll.ui.votemanager.piechartresult.PieChartFragment;
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
import static com.framgia.fpoll.util.Constant.TYPE_DIALOG_FRAGMENT;
import static com.framgia.fpoll.util.TimeUtil.getCurentTime;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultVoteFragment extends Fragment implements ResultVoteContract.View {
    private static final int SHOW_IMAGE_CHART = 1;
    private static final int HIDE_IMAGE_CHART = 0;
    private FragmentVoteResultBinding mBinding;
    private ResultVoteContract.Presenter mPresenter;
    private ObservableField<ResultVoteAdapter> mAdapter = new ObservableField<>();
    private List<ResultVoteItem.Result> mListResultVote = new ArrayList<>();
    private String mToken;
    private FPollProgressDialog mDialog;
    private File mFile;
    private List<ResultItem> mItemList = new ArrayList<>();
    private ObservableInt mVote = new ObservableInt();

    public static ResultVoteFragment newInstance(String token) {
        ResultVoteFragment resultVoteFragment = new ResultVoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TOKEN, token);
        resultVoteFragment.setArguments(bundle);
        return resultVoteFragment;
    }

    public void getDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle == null || bundle.getString(KEY_TOKEN) == null) return;
        mToken = bundle.getString(KEY_TOKEN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentVoteResultBinding.inflate(inflater, container, false);
        getDataFromIntent();
        mPresenter =
            new ResultVotePresenter(ResultVoteDataRepository.getInstance(getActivity()), this,
                mListResultVote, mFile);
        mBinding.setFragment(this);
        mBinding.setHandler(new ResultActionHandler(mPresenter));
        mPresenter.getAllData(mToken);
        mVote.set(HIDE_IMAGE_CHART);
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        setHasOptionsMenu(true);
        File exportDir =
            new File(Environment.getExternalStorageDirectory(), FPOLL_FOLDER_NAME);
        if (!exportDir.exists()) exportDir.mkdirs();
        mFile = new File(exportDir, getCurentTime() + FILE_NAME_SAVED_EXCEL);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (mListResultVote != null && mListResultVote.size() > 0) {
            inflater.inflate(R.menu.vote_result_menu, menu);
            menu.findItem(R.id.action_table).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pie_chart:
                showPieChart();
                break;
            case R.id.action_bar_chart:
                showBartChart();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(ResponseItem<ResultVoteItem> resultVoteList) {
        mListResultVote.addAll(resultVoteList.getData().getResults());
        mAdapter.set(new ResultVoteAdapter(mListResultVote));
        if (resultVoteList.getData() == null) return;
        for (int i = 0; i < resultVoteList.getData().getResults().size(); i++) {
            if (resultVoteList.getData().getResults().get(i).getVoters() > 0) {
                mVote.set(SHOW_IMAGE_CHART);
            }
        }
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

    public void setDataItemChart() {
        mItemList.clear();
        for (int i = 0; i < mListResultVote.size(); i++) {
            mItemList.add(new ResultItem(String.valueOf(i), mListResultVote.get(i).getName(), String
                .valueOf(mListResultVote.get(i).getVoters())));
        }
    }

    @Override
    public void showPieChart() {
        setDataItemChart();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        DialogFragment pieChartFragment =
            PieChartFragment.newInstance(mItemList);
        pieChartFragment.show(transaction, TYPE_DIALOG_FRAGMENT);
    }

    @Override
    public void showBartChart() {
        setDataItemChart();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        DialogFragment bartChartItem =
            BarchartFragment.newInstance(mItemList);
        bartChartItem.show(transaction, TYPE_DIALOG_FRAGMENT);
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

    public ObservableInt getVote() {
        return mVote;
    }
}
