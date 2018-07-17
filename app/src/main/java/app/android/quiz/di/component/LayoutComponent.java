package app.android.quiz.di.component;

import app.android.quiz.di.module.LayoutModule;
import app.android.quiz.di.module.LinearModule;
import app.android.quiz.view.activities.home.fragment.layout.FragmentLayout;
import dagger.Subcomponent;
import app.android.quiz.di.scope.FragmentScope;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@FragmentScope
@Subcomponent(

        modules = {
                LayoutModule.class
        }
)
public interface LayoutComponent {
    FragmentLayout inject(FragmentLayout fragmentLayout);
    LinearComponent plus(LinearModule linearModule);
}


