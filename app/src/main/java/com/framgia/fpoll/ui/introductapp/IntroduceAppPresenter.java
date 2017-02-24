package com.framgia.fpoll.ui.introductapp;

import com.framgia.fpoll.data.model.IntroduceItem;
import com.framgia.fpoll.data.source.local.DataSource;
import com.framgia.fpoll.data.source.local.introduce.IntroduceLocalDataSource;

import java.util.List;

/**
 * Created by tuanbg on 2/22/17.
 */
public class IntroduceAppPresenter implements IntroduceAppContract.Presenter {
    private IntroduceAppContract.View mView;
    private IntroduceLocalDataSource mIntroduceRepository;

    public IntroduceAppPresenter(IntroduceAppContract.View view,
                                 IntroduceLocalDataSource introduceRepository) {
        this.mView = view;
        this.mIntroduceRepository = introduceRepository;
    }

    public void getData() {
        mIntroduceRepository.getData(new DataSource.GetCallback<IntroduceItem>() {
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
    public void openFacebook() {
        mView.openFaceBook();
    }

    @Override
    public void openGitHub() {
        mView.openGitHub();
    }

    @Override
    public void openLikeDin() {
        mView.openLikeDin();
    }
}
