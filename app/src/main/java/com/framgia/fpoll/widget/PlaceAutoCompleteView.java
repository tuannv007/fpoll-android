package com.framgia.fpoll.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 22/03/2017.
 */
public class PlaceAutoCompleteView extends AppCompatAutoCompleteTextView {
    private static final int MAX_HINT = 5;
    private GoogleApiClient mGoogleApiClient;

    public PlaceAutoCompleteView(Context context) {
        super(context);
    }

    public PlaceAutoCompleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpContent(context);
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        mGoogleApiClient = googleApiClient;
    }

    public void setUpContent(final Context context) {
        final List items = new ArrayList();
        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, items);
        setAdapter(arrayAdapter);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mGoogleApiClient == null) return;
                if (TextUtils.isEmpty(charSequence)) return;
                PendingResult<AutocompletePredictionBuffer> placeResult =
                        Places.GeoDataApi.getAutocompletePredictions(mGoogleApiClient,
                                charSequence.toString(), null, null);
                placeResult.setResultCallback(new ResultCallback<AutocompletePredictionBuffer>() {
                    @Override
                    public void onResult(
                            @NonNull AutocompletePredictionBuffer autocompletePredictions) {
                        if (!autocompletePredictions.getStatus().isSuccess()) return;
                        items.clear();
                        int length = Math.min(autocompletePredictions.getCount(), MAX_HINT);
                        autocompletePredictions.getCount();
                        for (int i = 0; i < length; i++) {
                            items.add(autocompletePredictions.get(i).getFullText(null));
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context,
                                android.R.layout.simple_dropdown_item_1line, items);
                        setAdapter(arrayAdapter);
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}
