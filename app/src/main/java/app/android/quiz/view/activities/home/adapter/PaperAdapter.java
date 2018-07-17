package app.android.quiz.view.activities.home.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import app.android.quiz.R;
import app.android.quiz.view.activities.home.fragment.layout.FragmentLayout;

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
        }
        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.tab_1);
            default:
                return null;
        }
    }
}