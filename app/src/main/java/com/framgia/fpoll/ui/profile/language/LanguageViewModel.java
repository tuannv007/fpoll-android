package com.framgia.fpoll.ui.profile.language;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import com.framgia.fpoll.R;
import com.framgia.fpoll.ui.mainstart.NewMainActivity;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;
import com.framgia.fpoll.util.LanguageUtil;
import com.framgia.fpoll.util.SharePreferenceUtil;

/**
 * Created by tuanbg on 5/10/17.
 */

public class LanguageViewModel implements LanguageContract.ViewModel {
    private LanguageContract.Presenter mPresenter;
    private AppCompatActivity mActivity;
    private Context mContext;
    private SharePreferenceUtil mPreference;
    private DialogFragment mDialogFragment;
    private static final int NO_ANIMATION = 0;

    public LanguageViewModel(DialogFragment dialogFragment, AppCompatActivity activity,
            SharePreferenceUtil sharePrefer) {
        mActivity = activity;
        mContext = dialogFragment.getContext();
        mDialogFragment = dialogFragment;
        mPreference = sharePrefer;
    }

    public AppCompatActivity getActivity() {
        return mActivity;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setPresenter(LanguageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void changeLang(String lang) {
        mPresenter.changeLanguage(lang);
        LanguageUtil.changeLang(lang, mActivity);
        mActivity.finish();
        mActivity.overridePendingTransition(NO_ANIMATION, NO_ANIMATION);
        Intent intent = new Intent(mContext, NewMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        mContext.startActivity(intent);
    }

    public void showConfirmDialog(final String lang) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mContext).setCancelable(true)
                .setTitle(R.string.title_change_language)
                .setMessage(R.string.msg_change_language)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        saveLanguageSharePre(lang);
                        changeLang(lang);
                    }
                })
                .setNegativeButton(android.R.string.no, null);
        alertBuilder.show();
    }

    private void saveLanguageSharePre(String lang) {
        mPreference.savePositionLanguage(lang);
    }

    public void changeLanguage() {
        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
        DialogFragment languageDialog = LanguageDialogFragment.newInstance();
        languageDialog.show(transaction, Constant.TYPE_DIALOG_FRAGMENT);
    }

    public void dismissDialog() {
        if (mDialogFragment != null) mDialogFragment.dismiss();
    }

    @Override
    public void changeLangStatus(String s) {
        ActivityUtil.showToast(mContext, s);
    }
}
