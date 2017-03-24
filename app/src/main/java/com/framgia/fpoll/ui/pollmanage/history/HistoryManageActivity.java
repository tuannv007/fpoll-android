package com.framgia.fpoll.ui.pollmanage.history;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.databinding.ActivityHistoryManageBinding;

public class HistoryManageActivity extends AppCompatActivity implements HistoryManageContract.View {
    private ActivityHistoryManageBinding mBinding;
    private HistoryManageContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_history_manage);
        mPresenter = new HistoryManagePresenter(this, ManagerRepository.getInstance(this));
    }

    @Override
    public void start() {
    }
}
