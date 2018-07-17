package app.android.quiz.di.module;

import android.content.Context;

import javax.inject.Singleton;

import app.android.quiz.app.Application;
import app.android.quiz.view.fragments.constraint.FragmentConstraint;
import app.android.quiz.view.fragments.coordinate.FragmentCoordinate;
import app.android.quiz.view.fragments.relative.FragmentRelative;
import app.android.quiz.view.fragments.linear.FragmentLinear;
import dagger.Module;
import dagger.Provides;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class FragmentModule {

    private Application mApplication;
    private Context mContext;

    public FragmentModule(Application application, Context context) {
        this.mApplication = application;
        this.mContext = context;
    }

    @Provides
    @Singleton
    FragmentLinear provideFragmentTypeLayout() {
        return new FragmentLinear();
    }

    @Provides
    @Singleton
    FragmentRelative provideFragmentRelative() {
        return new FragmentRelative();
    }

    @Provides
    @Singleton
    FragmentConstraint provideFragmentConstraint() {
        return new FragmentConstraint();
    }

    @Provides
    @Singleton
    FragmentCoordinate provideFragmentCoordinate() {
        return new FragmentCoordinate();
    }
}
