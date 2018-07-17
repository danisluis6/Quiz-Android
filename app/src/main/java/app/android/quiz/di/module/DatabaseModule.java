package app.android.quiz.di.module;

import android.content.Context;

import javax.inject.Singleton;

import app.android.quiz.app.Application;
import app.android.quiz.service.DisposableManager;
import app.android.quiz.view.fragments.linear.LinearModel;
import app.android.quiz.view.fragments.linear.LinearModelImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class DatabaseModule {

    private Application mApplication;
    private Context mContext;

    public DatabaseModule(Application application, Context context) {
        mApplication = application;
        mContext = context;
    }

    @Singleton
    @Provides
    LinearModel provideLinearModel() {
        return new LinearModelImpl(mContext);
    }

    @Singleton
    @Provides
    DisposableManager provideDisposableManager() {
        return new DisposableManager();
    }
}
