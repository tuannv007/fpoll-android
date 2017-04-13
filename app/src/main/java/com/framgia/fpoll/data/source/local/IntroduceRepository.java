package com.framgia.fpoll.data.source.local;

import android.content.Context;
import com.framgia.fpoll.data.model.IntroduceItem;
import com.framgia.fpoll.data.source.local.introduce.IntroduceLocalDataSource;
import java.util.List;

/**
 * Created by tuanbg on 2/23/17.
 */
public class IntroduceRepository implements IntroduceDataSource {
    private static IntroduceRepository sIntroduceRepository;
    private IntroduceDataSource mLocalDataSource;

    public IntroduceRepository(IntroduceLocalDataSource introduceDataSource) {
        mLocalDataSource = introduceDataSource;
    }

    public static IntroduceRepository getInstance(Context context) {
        if (sIntroduceRepository == null) {
            sIntroduceRepository =
                    new IntroduceRepository(IntroduceLocalDataSource.getInstance(context));
        }
        return sIntroduceRepository;
    }

    @Override
    public void getData(final GetCallback callback) {
        mLocalDataSource.getData(new GetCallback<IntroduceItem>() {
            @Override
            public void onLoaded(List<IntroduceItem> data) {
                callback.onLoaded(data);
            }

            @Override
            public void onNotAvailable() {
                callback.onNotAvailable();
            }
        });
    }
}
