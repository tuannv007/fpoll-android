package com.framgia.fpoll.ui.votemanager.barchart;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tran.trung.phong on 27/02/2017.
 */
public class BarcharContract {
    interface View extends BaseView {
        void getData();
    }

    interface Presenter {
        void getBarData();
    }
}
