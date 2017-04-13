package com.framgia.fpoll.data.model.poll;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResultVoteItem extends BaseObservable {
    @SerializedName("results")
    private List<Result> mResults;

    @Bindable
    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        this.mResults = results;
        notifyPropertyChanged(BR.results);
    }

    public class Result extends BaseObservable {
        @SerializedName("name")
        private String mName;
        @SerializedName("image")
        private String mImage;
        @SerializedName("voters")
        private int mVoters;

        @Bindable
        public String getName() {
            return mName;
        }

        public void setName(String name) {
            this.mName = name;
            notifyPropertyChanged(BR.name);
        }

        @Bindable
        public String getImage() {
            return mImage;
        }

        public void setImage(String image) {
            this.mImage = image;
            notifyPropertyChanged(BR.image);
        }

        @Bindable
        public int getVoters() {
            return mVoters;
        }

        public void setVoters(int voters) {
            this.mVoters = voters;
            notifyPropertyChanged(BR.votes);
        }
    }
}
