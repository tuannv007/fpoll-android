package com.framgia.fpoll.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framgia.fpoll.R;
import com.tokenautocomplete.TokenCompleteTextView;

public class ChipsContactView extends TokenCompleteTextView<String> {
    public ChipsContactView(Context context) {
        super(context);
    }

    public ChipsContactView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChipsContactView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected View getViewForObject(final String contact) {
        LayoutInflater inflater =
            (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View viewChild =
            inflater.inflate(R.layout.partial_chips_view, (ViewGroup) getParent(), false);
        final TextView textView = (TextView) viewChild.findViewById(R.id.text_email);
        textView.setText(contact);
        viewChild.findViewById(R.id.ic_clear).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setSelected(true);
            }
        });
        return viewChild;
    }

    @Override
    protected String defaultObject(String completionText) {
        return completionText;
    }
}
