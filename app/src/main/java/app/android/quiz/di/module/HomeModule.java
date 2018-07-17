package app.android.quiz.di.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import app.android.quiz.di.scope.ActivityScope;
import app.android.quiz.service.JsonData;
import app.android.quiz.view.activities.home.HomeActivity;
import app.android.quiz.view.activities.home.HomeView;
import app.android.quiz.view.activities.home.adapter.PaperAdapter;
import app.android.quiz.view.activities.home.fragment.layout.FragmentLayout;
import app.android.quiz.view.dialog.DialogLoading;
import dagger.Module;
import dagger.Provides;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class HomeModule {

    private HomeActivity mHomeActivity;
    private HomeView mHomeView;

    public HomeModule(HomeActivity homeActivity, HomeView homeView) {
        this.mHomeActivity = homeActivity;
        this.mHomeView = homeView;
    }

    public HomeModule(HomeActivity homeActivity) {
        this.mHomeActivity = homeActivity;
    }

    @Provides
    @ActivityScope
    HomeActivity provideHomeActivity() {
        return mHomeActivity;
    }

    /**
     * Show up recyclerView adapter
     * @return FragmentTransaction
     */
    @Provides
    @ActivityScope
    JsonData provideJsonData(Context context) {
        return new JsonData(context);
    }

    @Provides
    @ActivityScope
    DialogLoading provideVGLoadingDialog(HomeActivity homeActivity) {
        return new DialogLoading(homeActivity);
    }

    /**
     * Show up recyclerView adapter
     * @return FragmentTransaction
     */
    @Provides
    @ActivityScope
    FragmentTransaction provideFragmentTransaction() {
        return mHomeActivity.getSupportFragmentManager().beginTransaction();
    }

    @Provides
    @ActivityScope
    FragmentManager provideFragmentManager()  {
        return mHomeActivity.getSupportFragmentManager();
    }

    @Provides
    @ActivityScope
    PaperAdapter provideViewPagerAdapter(Context context, FragmentManager fragmentManager, FragmentLayout fragmentLayout) {
        return new PaperAdapter(context, fragmentManager, fragmentLayout);
    }

    @Provides
    @ActivityScope
    FragmentLayout provideFragmentRecycler() {
        return new FragmentLayout();
    }
}
