package com.framgia.fpoll.ui.introduction;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.IntroduceItem;
import com.framgia.fpoll.data.source.local.IntroduceRepository;
import com.framgia.fpoll.databinding.ActivityIntroduceBinding;
import com.framgia.fpoll.ui.authenication.activity.AuthenticationActivity;
import com.framgia.fpoll.ui.main.MainActivity;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.SharePreferenceUtil;
import java.util.List;

public class IntroduceActivity extends AppCompatActivity implements IntroduceAppContract.View {
    private static final int FIRST_POSITION = 0;
    public static final String EXTRA_OPEN_FROM_MAIN = "EXTRA_OPEN_FROM_MAIN";
    private List<IntroduceItem> mItems;
    private ObservableField<ViewPageAdapterAuto> mAdapter = new ObservableField<>();
    private ObservableField<String> mCurrentAction = new ObservableField<>();
    private ObservableField<String> mCurrentTitle = new ObservableField<>();
    private IntroduceAppContract.Presenter mPresenter;
    private boolean mIsOpenFromMain;

    public static Intent getInstance(Context context, boolean isOpenFromMain) {
        Intent intent = new Intent(context, IntroduceActivity.class);
        intent.putExtra(EXTRA_OPEN_FROM_MAIN, isOpenFromMain);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityIntroduceBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_introduce);
        start();
        binding.setHandler(new IntroduceHandlerAction(mPresenter));
        binding.setActivity(this);
    }

    @Override
    public void start() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            mIsOpenFromMain = intent.getExtras().getBoolean(EXTRA_OPEN_FROM_MAIN);
        }
        mPresenter = new IntroduceAppPresenter(this, IntroduceRepository.getInstance(this),
                SharePreferenceUtil.getIntances(this), mIsOpenFromMain);
        mPresenter.start();
    }

    @Override
    public void updateIntroduceView(List<IntroduceItem> list) {
        mItems = list;
        mAdapter.set(new ViewPageAdapterAuto(this, mItems));
        mCurrentAction.set(getResources().getString(R.string.action_next));
        mCurrentTitle.set(mItems.get(FIRST_POSITION).getTitle());
    }

    @Override
    public void updateIntroduceError() {
        ActivityUtil.showToast(this, R.string.msg_not_load_item);
    }

    @Override
    public void updatePageTitle(int pagePosition) {
        mCurrentAction.set(
                pagePosition != mItems.size() - 1 ? getResources().getString(R.string.action_next)
                        : getResources().getString(R.string.action_finish));
        mCurrentTitle.set(mItems.get(pagePosition).getTitle());
    }

    public void nextAction(ViewPager viewPager) {
        int currentItem = viewPager.getCurrentItem();
        currentItem++;
        if (currentItem != mItems.size()) {
            viewPager.setCurrentItem(currentItem);
            return;
        }
        mPresenter.onFinnishTutorial();
    }

    @Override
    public void startAuthenicationActivity() {
        startActivity(AuthenticationActivity.getAuthenticationIntent(this, false));
    }

    @Override
    public void startMainActivity() {
        startActivity(MainActivity.getInstance(this));
    }

    @Override
    public void finnish() {
        finish();
    }

    public ObservableField<String> getCurrentAction() {
        return mCurrentAction;
    }

    public ObservableField<String> getCurrentTitle() {
        return mCurrentTitle;
    }

    public ObservableField<ViewPageAdapterAuto> getAdapter() {
        return mAdapter;
    }
}
