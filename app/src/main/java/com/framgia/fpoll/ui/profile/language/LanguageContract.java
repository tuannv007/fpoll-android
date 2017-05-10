package com.framgia.fpoll.ui.profile.language;

import com.framgia.fpoll.ui.base.BasePresenter;
import com.framgia.fpoll.ui.base.BaseViewModel;

/**
 * Created by tuanbg on 5/10/17.
 */

public interface LanguageContract {
    interface ViewModel extends BaseViewModel<Presenter> {
        void changeLangStatus(String s);
    }

    interface Presenter extends BasePresenter {
        void changeLanguage(String lang);
    }
}
