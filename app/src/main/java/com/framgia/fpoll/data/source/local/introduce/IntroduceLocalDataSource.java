package com.framgia.fpoll.data.source.local.introduce;

import android.content.Context;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.IntroduceItem;
import com.framgia.fpoll.data.source.local.IntroduceDataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 2/22/17.
 */
public class IntroduceLocalDataSource implements IntroduceDataSource {
    private static IntroduceLocalDataSource sIntroduceLocalDataSource;
    private static Context mContext;

    public IntroduceLocalDataSource(Context context) {
        mContext = context;
    }

    public static IntroduceLocalDataSource getInstance(Context context) {
        if (sIntroduceLocalDataSource == null) {
            sIntroduceLocalDataSource = new IntroduceLocalDataSource(context);
        }
        return sIntroduceLocalDataSource;
    }

    @Override
    public void getData(GetCallback callback) {
        if (callback == null) return;
        List<IntroduceItem> items = new ArrayList<>();
        items.add(new IntroduceItem(mContext.getString(R.string.title_introduce_vote_poll),
                R.drawable.ic_fpoll_vote));
        items.add(new IntroduceItem(mContext.getString(R.string.title_introduce_chart_poll),
                R.drawable.ic_fpoll_chart));
        items.add(new IntroduceItem(mContext.getString(R.string.title_introduce_security),
                R.drawable.ic_fpoll_security));
        items.add(new IntroduceItem(mContext.getString(R.string.title_introduce_export_poll),
                R.drawable.ic_fpoll_export));
        items.add(new IntroduceItem(mContext.getString(R.string.title_introduce_support_poll),
                R.drawable.ic_fpoll_responsive));
        items.add(new IntroduceItem(mContext.getString(R.string.title_introduce_share_poll),
                R.drawable.ic_fpoll_like_share));
        callback.onLoaded(items);
    }
}
