package com.framgia.fpoll.ui.polledition.editoption;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.data.model.OptionItem;

import java.util.List;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditOptionAdapter extends RecyclerView.Adapter<EditOptionAdapter.EditOptionHolder> {
    private EditOptionContract.Presenter mPresenter;
    private List mListOption;

    public EditOptionAdapter(EditOptionContract.Presenter presenter, List listOption) {
        mPresenter = presenter;
        mListOption = listOption;
    }

    public void update(List<OptionItem> optionItems) {
        mListOption.clear();
        mListOption.addAll(optionItems);
        notifyDataSetChanged();
    }

    @Override
    public EditOptionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(EditOptionHolder holder, int position) {
        // TODO: 17/03/2017  
    }

    @Override
    public int getItemCount() {
        return mListOption == null ? 0 : mListOption.size();
    }

    public class EditOptionHolder extends RecyclerView.ViewHolder {
        public EditOptionHolder(View itemView) {
            super(itemView);
        }
    }
}
