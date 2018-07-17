package app.android.quiz.di.component;

import javax.inject.Singleton;

import app.android.quiz.di.module.FragmentModule;
import dagger.Component;
import app.android.quiz.di.module.AppModule;
import app.android.quiz.di.module.AsyntaskModule;
import app.android.quiz.di.module.DatabaseModule;
import app.android.quiz.di.module.JsoupModule;
import app.android.quiz.di.module.LoadingModule;
import app.android.quiz.di.module.HomeModule;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Singleton
@Component(
        modules = {
                AppModule.class, LoadingModule.class, JsoupModule.class, DatabaseModule.class, AsyntaskModule.class, FragmentModule.class
        }
)
public interface AppComponent {
        HomeComponent plus(HomeModule module);
}
