package com.framgia.fpoll.ui.mainstart;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.ActivityNewMainBinding;
import com.framgia.fpoll.ui.base.BaseActivity;

/**
 * NewMain Screen.
 */
public class NewMainActivity extends BaseActivity {

    private NewMainContract.ViewModel mViewModel;

    public static Intent getInstance(Context context) {
        return new Intent(context, NewMainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new NewMainViewModel(this);

        NewMainContract.Presenter presenter = new NewMainPresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityNewMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_new_main);
        binding.setViewModel((NewMainViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
