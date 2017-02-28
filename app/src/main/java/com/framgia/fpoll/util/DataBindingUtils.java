package com.framgia.fpoll.util;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.framgia.fpoll.R;
import com.framgia.fpoll.ui.pollhistory.PollHistoryPresenter;
import com.framgia.fpoll.ui.pollparticipant.ParticipantPresenter;
import com.framgia.fpoll.ui.pollsetting.EventSwitchType;
import com.framgia.fpoll.ui.pollsetting.RequireVoteType;
import com.framgia.fpoll.ui.pollsetting.SettingPresenter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;

/**
 * Created by Nhahv0902 on 2/9/2017.
 * <></>
 */
public class DataBindingUtils {
    @BindingAdapter({"bind:imageResource"})
    public static void loadImage(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter({"bind:drawableLeftStart"})
    public static void bindDrawableLeft(AppCompatButton view, Drawable drawable) {
        view.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }

    @BindingAdapter({"bind:imagePath"})
    public static void loadImagePath(ImageView view, String path) {
        Glide.with(view.getContext())
            .load(path)
            .placeholder(R.drawable.ic_insert_photo_black_24px)
            .into(view);
    }

    @BindingAdapter({"bind:imageDrawable"})
    public static void loadImageDrawable(ImageView view, int source) {
        Glide.with(view.getContext()).load(source).asBitmap().centerCrop()
            .into(new BitmapImageViewTarget(view) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(view.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    view.setImageDrawable(circularBitmapDrawable);
                }
            });
    }

    @BindingAdapter("bind:background")
    public static void setCardBackground(CardView view, int color) {
        view.setCardBackgroundColor(color);
    }

    @BindingAdapter(value = {"bind:selectedValue", "bind:selectedValueAttrChanged"},
        requireAll = false)
    public static void bindSpinnerData(Spinner spinner, int selectedPosition, final
    InverseBindingListener newTextAttrChanged) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newTextAttrChanged.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (selectedPosition != -1) {
            spinner.setSelection(selectedPosition, true);
        }
    }

    @BindingAdapter({"bind:swipeOnRefresh"})
    public static void setViewPagerAdapter(SwipeRefreshLayout view,
                                           final PollHistoryPresenter presenter) {
        view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getData();
            }
        });
    }

    @BindingAdapter({"bind:adapterViewPager"})
    public static void setViewPagerAdapter(ViewPager view, FragmentPagerAdapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:viewPager"})
    public static void setUpWithViewPager(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView view,
                                        LayoutManageUtil.LayoutManagerFactory layout) {
        view.setLayoutManager(layout.create(view));
    }

    @BindingAdapter({"bind:colorSwipeLayout"})
    public static void setColorSwipeLayout(SwipeRefreshLayout view, int color) {
        view.setColorSchemeColors(color);
    }

    @BindingAdapter({"bind:refreshSwipeLayout"})
    public static void setColorSwipeLayout(SwipeRefreshLayout view, boolean isReFresh) {
        view.setRefreshing(isReFresh);
    }

    @InverseBindingAdapter(attribute = "bind:selectedValue",
        event = "bind:selectedValueAttrChanged")
    public static int captureSelectedValue(Spinner spinner) {
        return spinner.getSelectedItemPosition();
    }

    @BindingAdapter({"bind:setVisibility", "bind:presenter", "bind:eventType"})
    public static void setVisibilityLinkPoll(final View view, SwitchCompat switchCompat,
                                             final SettingPresenter presenter,
                                             final EventSwitchType event) {
        view.setVisibility(switchCompat.isChecked() ? View.VISIBLE : View.GONE);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                view.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                switch (event) {
                    case REQUIRE_VOTE:
                        presenter.onCheckedRequireVote(isChecked);
                        break;
                    case LINK_POLL:
                        presenter.onCheckedLinkPoll(isChecked);
                        break;
                    case VOTING_LIMIT:
                        presenter.onCheckedVotingLimit(isChecked);
                        break;
                    case PASSWORD:
                        presenter.onCheckedSetPassword(isChecked);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @BindingAdapter(value = {"bind:textChange"}, requireAll = false)
    public static void setTextWatcher(final EditText view,
                                      final ParticipantPresenter presenter) {
        TextWatcher values = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.getEmail(s.toString());
            }
        };
        view.addTextChangedListener(values);
    }

    @BindingAdapter({"bind:bindBarChart"})
    public static void setBarChart(BarChart view, BarData data) {
        view.setData(data);
    }

    @BindingAdapter({"bind:showImage"})
    public static void setShowImage(ImageView view, boolean isShow) {
        view.setImageResource(isShow ? R.drawable.ic_eye : R.drawable.ic_eye_blocked);
    }

    @BindingAdapter({"bind:showPassword"})
    public static void setShowImage(EditText view, boolean isShow) {
        view.setTransformationMethod(isShow ? null : new PasswordTransformationMethod());
    }

    @BindingAdapter({"bind:eventRadioGroup"})
    public static void eventRadioGroup(RadioGroup view, final SettingPresenter presenter) {
        view.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_enter_email:
                        presenter.setRequireVote(RequireVoteType.EMAIL);
                        break;
                    case R.id.radio_enter_name_email:
                        presenter.setRequireVote(RequireVoteType.NAME_EMAIL);
                        break;
                    case R.id.radio_enter_name:
                    default:
                        presenter.setRequireVote(RequireVoteType.NAME);
                        break;
                }
            }
        });
    }
}
