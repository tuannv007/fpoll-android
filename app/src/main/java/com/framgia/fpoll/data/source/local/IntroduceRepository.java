package com.framgia.fpoll.data.source.local;

import com.framgia.fpoll.data.model.IntroduceItem;
import com.framgia.fpoll.data.source.local.introduce.IntroduceLocalDataSource;

import java.util.List;

/**
 * Created by tuanbg on 2/23/17.
 */
public class IntroduceRepository implements IntroduceDataSource {
    private static IntroduceRepository sIntroduceRepository;
    private IntroduceDataSource mIntroduceRemoteDataSource;

    public static IntroduceRepository getInstance() {
        if (sIntroduceRepository == null) {
            sIntroduceRepository = new IntroduceRepository(IntroduceLocalDataSource.getInstance());
        }
        return sIntroduceRepository;
    }

    public IntroduceRepository(IntroduceLocalDataSource introduceLocalDataSource) {
        mIntroduceRemoteDataSource = introduceLocalDataSource;
    }

    @Override
    public void getData(final GetCallback callback) {
        mIntroduceRemoteDataSource.getData(new GetCallback<IntroduceItem>() {
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
