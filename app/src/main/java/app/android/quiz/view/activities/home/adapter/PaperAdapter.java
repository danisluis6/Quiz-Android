package app.android.quiz.view.activities.home.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import app.android.quiz.R;
import app.android.quiz.view.activities.home.fragment.layout.FragmentLayout;
import app.android.quiz.view.activities.home.loading.FragmentLoading;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class PaperAdapter extends FragmentStatePagerAdapter {

    private FragmentLayout mFragmentLayout;
    private Context mContext;

    public PaperAdapter(Context context, FragmentManager fm, FragmentLayout fragmentLayout) {
        super(fm);
        mFragmentLayout = fragmentLayout;
        mContext = context;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return mFragmentLayout;
            }
            case 1: {
                return new FragmentLoading();
            }
            case 2: {
                return new FragmentLoading();
            }
            case 3: {
                return new FragmentLoading();
            }
            case 4: {
                return new FragmentLoading();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.tab_1);
            case 1:
                return mContext.getResources().getString(R.string.tab_2);
            case 2:
                return mContext.getResources().getString(R.string.tab_3);
            case 3:
                return mContext.getResources().getString(R.string.tab_4);
            case 4:
                return mContext.getResources().getString(R.string.tab_5);
            default:
                return null;
        }
    }
}