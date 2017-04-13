package com.framgia.fpoll.ui.pollmanage.information.pollsetting;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Setting;
import com.framgia.fpoll.databinding.ItemViewSettingBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 3/9/17.
 */
public class PollSettingAdapter
        extends RecyclerView.Adapter<PollSettingAdapter.PollSettingViewHOlder> {
    private List<Setting> mListItems = new ArrayList<>();
    private LayoutInflater mInflater;

    public PollSettingAdapter(List<Setting> listItems) {
        mListItems = listItems;
    }

    @Override
    public PollSettingViewHOlder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemViewSettingBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_view_setting, parent, false);
        return new PollSettingViewHOlder(binding);
    }

    @Override
    public void onBindViewHolder(PollSettingViewHOlder holder, int position) {
        Setting item = mListItems.get(position);
        if (item != null) holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mListItems != null ? mListItems.size() : 0;
    }

    public class PollSettingViewHOlder extends RecyclerView.ViewHolder {
        private ItemViewSettingBinding mBinding;

        public PollSettingViewHOlder(ItemViewSettingBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(Setting item) {
            mBinding.setItem(item);
            mBinding.executePendingBindings();
        }
    }
}
