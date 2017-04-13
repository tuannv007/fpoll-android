package com.framgia.fpoll.ui.introduction;

import com.framgia.fpoll.data.model.IntroduceItem;
import com.framgia.fpoll.ui.base.BaseView;
import java.util.List;

/**
 * Created by tuanbg on 2/22/17.
 */
public interface IntroduceAppContract {
    interface View extends BaseView {

        void updateIntroduceView(List<IntroduceItem> list);

        void updateIntroduceError();

        void updatePageTitle(int pagePosition);

        void startAuthenicationActivity();

        void startMainActivity();

        void finnish();
    }

    interface Presenter {

        void onPageChange(int pagePosition);

        void onFinnishTutorial();

        void start();
    }
}
