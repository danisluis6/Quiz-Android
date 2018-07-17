package app.android.quiz.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import app.android.quiz.app.Application;
import app.android.quiz.view.activities.home.loading.FragmentLoading;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class LoadingModule {

    private Application mApplication;
    private Context mContext;

    public LoadingModule(Application application, Context context) {
        this.mApplication = application;
        this.mContext = context;
    }

    @Provides
    @Singleton
    FragmentLoading provideFragmentLoading() {
        return new FragmentLoading();
    }
}
