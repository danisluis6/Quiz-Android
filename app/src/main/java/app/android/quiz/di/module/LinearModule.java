package app.android.quiz.di.module;

import android.content.Context;

import app.android.quiz.di.scope.FragmentScope;
import app.android.quiz.service.JsonData;
import app.android.quiz.view.activities.home.HomeActivity;
import app.android.quiz.view.fragments.linear.FragmentLinear;
import app.android.quiz.view.fragments.linear.LinearModel;
import app.android.quiz.view.fragments.linear.LinearPresenter;
import app.android.quiz.view.fragments.linear.LinearPresenterImpl;
import app.android.quiz.view.fragments.linear.LinearView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class LinearModule {

    private FragmentLinear mFragment;
    private HomeActivity mActivity;
    private LinearView mView;

    public LinearModule(HomeActivity homeActivity, FragmentLinear fragmentLinear, LinearView view) {
        mFragment = fragmentLinear;
        mActivity = homeActivity;
        mView = view;
    }

    @Provides
    @FragmentScope
    LinearPresenter provideLinearPresenter(Context context, HomeActivity activity, LinearModel homeModel, JsonData jsonData) {
        return new LinearPresenterImpl(context, activity, mView, homeModel, jsonData);
    }
}
