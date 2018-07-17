package app.android.quiz.view.fragments.linear;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import butterknife.BindView;
import io.github.kbiakov.codeview.CodeView;
import io.github.kbiakov.codeview.adapters.Options;
import io.github.kbiakov.codeview.highlight.ColorTheme;
import io.github.kbiakov.codeview.highlight.Font;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

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

    @BindView(R.id.codeView)
    CodeView mCodeView;

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
            retrieveData();
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

    public void retrieveData() {
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

        mCodeView.setCode(items.get(0).getA());
        mCodeView.updateOptions(new Function1<Options, Unit>() {
            @Override
            public Unit invoke(Options options) {
                options.withFont(Font.Consolas)
                        .withTheme(ColorTheme.SOLARIZED_LIGHT)
                        .withShadows()
                        .setShortcut(false);
                return null;
            }
        });
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
