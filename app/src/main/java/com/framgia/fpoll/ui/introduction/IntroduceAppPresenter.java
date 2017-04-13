package com.framgia.fpoll.ui.introduction;

import com.framgia.fpoll.data.model.IntroduceItem;
import com.framgia.fpoll.data.source.local.IntroduceDataSource;
import com.framgia.fpoll.data.source.local.IntroduceRepository;
import com.framgia.fpoll.util.SharePreferenceUtil;
import java.util.List;

import static com.framgia.fpoll.util.Constant.PreferenceConstant.PREF_SHOW_INTRODUCT;

/**
 * Created by tuanbg on 2/22/17.
 */
public class IntroduceAppPresenter implements IntroduceAppContract.Presenter {
    private IntroduceAppContract.View mView;
    private IntroduceRepository mIntroduceRepository;
    private SharePreferenceUtil mSharePreferenceUtil;
    private boolean mIsOpenFromMain;

    public IntroduceAppPresenter(IntroduceAppContract.View view,
            IntroduceRepository introduceRepository, SharePreferenceUtil sharePreferenceUtil,
            boolean isOpenFromMain) {
        this.mView = view;
        this.mIntroduceRepository = introduceRepository;
        this.mSharePreferenceUtil = sharePreferenceUtil;
        this.mIsOpenFromMain = isOpenFromMain;
    }

    public void getData() {
        mIntroduceRepository.getData(new IntroduceDataSource.GetCallback<IntroduceItem>() {
            @Override
            public void onLoaded(List<IntroduceItem> data) {
                mView.updateIntroduceView(data);
            }

            @Override
            public void onNotAvailable() {
                mView.updateIntroduceError();
            }
        });
    }

    @Override
    public void onPageChange(int pagePosition) {
        mView.updatePageTitle(pagePosition);
    }

    @Override
    public void onFinnishTutorial() {
        if (!mIsOpenFromMain) {
            if (!mSharePreferenceUtil.isLogin()) {
                mView.startAuthenicationActivity();
            } else {
                mView.startMainActivity();
            }
        }
        mView.finnish();
    }

    @Override
    public void start() {
        if (mSharePreferenceUtil.getBoolean(PREF_SHOW_INTRODUCT) && !mIsOpenFromMain) {
            onFinnishTutorial();
            return;
        }
        mSharePreferenceUtil.writePreference(PREF_SHOW_INTRODUCT, true);
        getData();
    }
}
