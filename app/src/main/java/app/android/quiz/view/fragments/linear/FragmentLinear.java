package app.android.quiz.view.fragments.linear;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import app.android.quiz.R;
import app.android.quiz.app.Application;
import app.android.quiz.data.storage.database.entities.Linear;
import app.android.quiz.di.module.HomeModule;
import app.android.quiz.di.module.LayoutModule;
import app.android.quiz.di.module.LinearModule;
import app.android.quiz.view.activities.home.HomeActivity;
import app.android.quiz.view.activities.home.fragment.layout.FragmentLayout;
import app.android.quiz.view.activities.home.loading.FragmentLoading;
import app.android.quiz.view.fragments.BaseFragment;
import io.reactivex.disposables.Disposable;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class FragmentLinear extends BaseFragment implements LinearView {

    @Inject
    FragmentLoading mFragmentLoading;

    @Inject
    LinearPresenter mLinearPresenter;

    @Inject
    HomeActivity mActivity;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Disposable mDisposable;

    public FragmentLinear() {
    }

    public void distributedDaggerComponents() {
        Application.getInstance()
                .getAppComponent()
                .plus(new HomeModule((HomeActivity) getActivity()))
                .plus(new LayoutModule((HomeActivity) getActivity(), (FragmentLayout) getParentFragment()))
                .plus(new LinearModule((HomeActivity) getActivity(), this, this))
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_linear, container, false);
        distributedDaggerComponents();
        bindView(view);
        callAPI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mDisposable.isDisposed()) {
            while (getChildFragmentManager().getBackStackEntryCount() > 0) {
                mFragmentManager.popBackStackImmediate();
            }
            retreiveData();
        }
    }

    public void callAPI() {
        mLinearPresenter.getQuestionFromAPI();
        mFragmentManager = getChildFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.fragment_linear_container, mFragmentLoading);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }

    public void retreiveData() {
        mLinearPresenter.getQuestionFromAPI();
        mFragmentManager = getChildFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.fragment_linear_container, mFragmentLoading);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }

    @Override
    public void getDataSuccess(List<Linear> items) {
        if (mFragmentLoading.isShown()) {
            while (getChildFragmentManager().getBackStackEntryCount() > 0) {
                mFragmentManager.popBackStackImmediate();
            }
        }
    }

    @Override
    public void getDataFailure(String message) {

    }

    @Override
    public void setDisposable(Disposable disposable) {
        mDisposable = disposable;
        mActivity.setDisposable(disposable);
    }
}
