package com.framgia.fpoll.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by framgia on 29/03/2017.
 */
public class ViewPageAutoScroll extends ViewPager {
    private static final int TIME_DELAY = 3000;
    private CountDownTimer mCountDownTimer;

    public ViewPageAutoScroll(Context context) {
        super(context);
    }

    public ViewPageAutoScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpAutoScroll();
    }

    private void setUpAutoScroll() {
        mCountDownTimer = new CountDownTimer(TIME_DELAY, TIME_DELAY) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                int nextItem =
                    getCurrentItem() == (getAdapter().getCount() - 1) ? 0 : getCurrentItem() + 1;
                ViewPageAutoScroll.this.setCurrentItem(nextItem, false);
                if (mCountDownTimer != null) mCountDownTimer.start();
            }
        };
        mCountDownTimer.start();
    }

    public void stopAutoScroll() {
        if (mCountDownTimer != null) mCountDownTimer.cancel();
    }
}
