package app.android.quiz.di.component;

import app.android.quiz.di.module.LinearModule;
import app.android.quiz.di.scope.FragmentScope;
import app.android.quiz.view.fragments.linear.FragmentLinear;
import dagger.Subcomponent;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@FragmentScope
@Subcomponent(

        modules = {
                LinearModule.class
        }
)
public interface LinearComponent {
    FragmentLinear inject(FragmentLinear fragmentLinear);
}


