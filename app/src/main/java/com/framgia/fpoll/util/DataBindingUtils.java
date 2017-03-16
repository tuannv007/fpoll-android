package com.framgia.fpoll.util;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareButton;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.databinding.PartialHeadBinding;
import com.framgia.fpoll.ui.authenication.login.LoginType;
import com.framgia.fpoll.ui.history.pollhistory.PollHistoryPresenter;
import com.framgia.fpoll.ui.pollcreation.participant.ParticipantPresenter;
import com.framgia.fpoll.ui.pollcreation.setting.EventSwitchType;
import com.framgia.fpoll.ui.pollcreation.setting.RequireVoteType;
import com.framgia.fpoll.ui.pollcreation.setting.SettingPresenter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;

import static com.framgia.fpoll.util.Constant.TypeSetting.HIDENT_RESULT;
import static com.framgia.fpoll.util.Constant.TypeSetting.TYPE_ADD_OPTION;
import static com.framgia.fpoll.util.Constant.TypeSetting.TYPE_COUNT_VOTE;
import static com.framgia.fpoll.util.Constant.TypeSetting.TYPE_CREATE_PASSWORD;
import static com.framgia.fpoll.util.Constant.TypeSetting.TYPE_EDIT_LINK;
import static com.framgia.fpoll.util.Constant.TypeSetting.TYPE_EDIT_OPTION;
import static com.framgia.fpoll.util.Constant.TypeSetting.TYPE_INPUT_EMAIL;
import static com.framgia.fpoll.util.Constant.TypeSetting.TYPE_INPUT_EMAIL_NAME;
import static com.framgia.fpoll.util.Constant.TypeSetting.TYPE_INPUT_NAME;
import static com.framgia.fpoll.util.Constant.TypeSetting.TYPE_NOT_EQUAL_EMAIL;

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

    @BindingAdapter("bind:cardBackground")
    public static void setCardBackground(CardView view, LoginType typeLogin) {
        if (typeLogin == null) typeLogin = LoginType.GOOGLE;
        switch (typeLogin) {
            case FACEBOOK:
                view.setCardBackgroundColor(
                    ContextCompat.getColor(view.getContext(), R.color.color_indigo_600));
                break;
            case GOOGLE:
                view.setCardBackgroundColor(
                    ContextCompat.getColor(view.getContext(), R.color.color_red_500));
                break;
            case TWITTER:
                view.setCardBackgroundColor(
                    ContextCompat.getColor(view.getContext(), R.color.color_blue));
                break;
            default:
                break;
        }
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
        view.setTouchEnabled(false);
        view.setDescription("");
        view.getAxisLeft().setEnabled(false);
        view.getAxisRight().setEnabled(false);
        view.getXAxis().setDrawGridLines(false);
        view.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        view.getLegend().setEnabled(false);
        view.setDrawGridBackground(false);
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

    @BindingAdapter(value = {"bind:pieData"})
    public static void setPieData(final PieChart pieChart, final PieData pieData) {
        pieChart.setData(pieData);
        pieChart.setTouchEnabled(false);
        pieChart.setDescription("");
        pieChart.setDrawSliceText(false);
    }

    @BindingAdapter({"bind:bindImage", "bind:bindError"})
    public static void bindImage(ImageView view, String url, Drawable error) {
        Glide.with(view.getContext()).load(url).asBitmap().error(error).placeholder(error)
            .centerCrop()
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

    @BindingAdapter({"bind:bindHeader"})
    public static void loadHeader(NavigationView view, User user) {
        PartialHeadBinding binding =
            PartialHeadBinding.inflate(LayoutInflater.from(view.getContext()));
        binding.setUser(user);
        binding.executePendingBindings();
        view.addHeaderView(binding.getRoot());
    }

    @BindingAdapter({"bind:bindAdapter"})
    public static void bindAdapterRecycler(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setNestedScrollingEnabled(false);
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:setErrorEditText"})
    public static void setError(final EditText editText, final String msg) {
        if (TextUtils.isEmpty(editText.getText())) editText.setError(msg);
    }

    @BindingAdapter({"bind:setErrorEmail"})
    public static void setErrorEmail(final EditText editText, final String msg) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editText.getText()).matches()) {
            editText.setError(msg);
        }
    }

    @BindingAdapter({"bind:shareContent"})
    public static void setShareContent(ShareButton shareButton, String token) {
        if (token == null) return;
        String linkPoll = Constant.WebUrl.POLL_URL + token;
        shareButton.setShareContent(new ShareLinkContent.Builder()
            .setContentUrl(Uri.parse(linkPoll))
            .build());
    }

    @BindingAdapter({"bind:objectId"})
    public static void setObjectId(LikeView likeView, String token) {
        if (token == null) return;
        String linkPoll = Constant.WebUrl.POLL_URL + token;
        likeView.setLikeViewStyle(LikeView.Style.BUTTON);
        likeView.setObjectIdAndType(linkPoll, LikeView.ObjectType.PAGE);
    }

    @BindingAdapter({"bind:numAnswer"})
    public static void setNumAnswer(Spinner view, boolean multiple) {
        int type = multiple ? Constant.ConstantApi.KEY_MULTI_CHOOSE :
            Constant.ConstantApi.KEY_SINGER_CHOOSE;
        view.setSelection(type);
    }

    @BindingAdapter({"bind:valueSetting"})
    public static void setValueSetting(TextView view, int values) {
        switch (values) {
            case TYPE_INPUT_EMAIL:
                view.setText(view.getContext().getString(R.string.title_input_email));
                break;
            case TYPE_INPUT_NAME:
                view.setText(view.getContext().getString(R.string.title_input_name));
                break;
            case TYPE_ADD_OPTION:
                view.setText(view.getContext().getString(R.string.title_input_add_option));
                break;
            case TYPE_COUNT_VOTE:
                view.setText(view.getContext().getString(R.string.title_input_count_vote));
                break;
            case TYPE_EDIT_LINK:
                view.setText(view.getContext().getString(R.string.title_input_edit_link));
                break;
            case TYPE_INPUT_EMAIL_NAME:
                view.setText(view.getContext().getString(R.string.title_input_email_name));
                break;
            case TYPE_NOT_EQUAL_EMAIL:
                view.setText(view.getContext().getString(R.string.title_input_not_equal_email));
                break;
            case HIDENT_RESULT:
                view.setText(view.getContext().getString(R.string.title_input_hident_result_vote));
                break;
            case TYPE_CREATE_PASSWORD:
                view.setText(view.getContext().getString(R.string.title_input_create_password));
                break;
            case TYPE_EDIT_OPTION:
                view.setText(view.getContext().getString(R.string.title_input_edit_option));
                break;
            default:
                view.setText(view.getContext().getString(R.string.title_no_setting));
                break;
        }
    }
}
