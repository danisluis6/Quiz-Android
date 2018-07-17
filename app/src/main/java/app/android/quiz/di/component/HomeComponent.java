package app.android.quiz.di.component;

import dagger.Subcomponent;
import app.android.quiz.di.module.LayoutModule;
import app.android.quiz.di.module.HomeModule;
import app.android.quiz.di.scope.ActivityScope;
import app.android.quiz.view.activities.home.HomeActivity;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@ActivityScope
@Subcomponent(

        modules = {
                HomeModule.class
        }
)
public interface HomeComponent {
    HomeActivity inject(HomeActivity homeActivity);
    LayoutComponent plus(LayoutModule layoutModule);
}


