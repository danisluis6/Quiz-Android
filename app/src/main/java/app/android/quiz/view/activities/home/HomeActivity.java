package app.android.quiz.view.activities.home;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import app.android.quiz.R;
import app.android.quiz.app.Application;
import app.android.quiz.di.module.HomeModule;
import app.android.quiz.service.JsonData;
import app.android.quiz.service.asyntask.DownloadImage;
import app.android.quiz.view.activities.BaseActivity;
import app.android.quiz.view.activities.home.adapter.PaperAdapter;
import butterknife.BindView;
import io.reactivex.disposables.Disposable;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class HomeActivity extends BaseActivity implements HomeView {

    @Inject
    Context mContext;

    @Inject
    FragmentTransaction mFragmentTransaction;

    @Inject
    JsonData mJsonData;

    @Inject
    DownloadImage mDownloadImage;

    @Inject
    PaperAdapter mPaperAdapter;


    @BindView(R.id.pager)
    ViewPager mViewPager;

    private Disposable mDisposable;

    @Override
    public void distributedDaggerComponents() {
        Application.getInstance()
                .getAppComponent()
                .plus(new HomeModule(this, this))
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void initAttributes(Bundle savedInstanceState) {
        super.initAttributes(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().setStatusBarColor(ContextCompat.getColor(HomeActivity.this, R.color.home_statusbar_color));
        }
        mViewPager.setAdapter(mPaperAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(mContext, "Index "+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setDisposable(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    protected void onPause() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        super.onPause();
    }

    public interface HomeInterface {
        void onBackPressOnFragment();
    }

    private static HomeInterface listener;

    public void attachHomeInterface(HomeInterface _interface) {
        listener = _interface;
    }

    @Override
    public void onBackPressed() {
        listener.onBackPressOnFragment();
        super.onBackPressed();
    }
}
