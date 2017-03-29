package com.framgia.fpoll.ui.introduction;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.IntroduceItem;
import com.framgia.fpoll.databinding.ItemIntroduceAppBinding;
import java.util.List;

/**
 * Created by framgia on 29/03/2017.
 */
public class ViewPageAdapterAuto extends PagerAdapter {
    private List<IntroduceItem> mListItem;
    private LayoutInflater mLayoutInflater;

    public ViewPageAdapterAuto(Context context, List list) {
        mListItem = list;
        mLayoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mListItem == null ? 0 : mListItem.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ItemIntroduceAppBinding binding =
                DataBindingUtil.inflate(mLayoutInflater, R.layout.item_introduce_app, container,
                        false);
        binding.setItem(mListItem.get(position));
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
