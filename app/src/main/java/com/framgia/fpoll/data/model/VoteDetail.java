package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.fpoll.data.model.poll.Option;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv0902 on 4/4/2017.
 * <></>
 */
public class VoteDetail {
    @SerializedName("results")
    private List<Result> mResults = new ArrayList<>();

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

    public class Result extends BaseObservable {
        @SerializedName("name")
        private String mName;
        @SerializedName("image")
        private String mImage;
        @SerializedName("voters")
        private Option mVoters;

        @Bindable
        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
            notifyPropertyChanged(BR.name);
        }

        @Bindable
        public String getImage() {
            return mImage;
        }

        public void setImage(String image) {
            mImage = image;
            notifyPropertyChanged(BR.image);
        }

        @Bindable
        public Option getVoters() {
            return mVoters;
        }

        public void setVoters(Option voters) {
            mVoters = voters;
            notifyPropertyChanged(BR.voters);
        }
    }
}
