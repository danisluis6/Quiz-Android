package app.android.quiz.di.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import java.util.Stack;

import app.android.quiz.R;
import app.android.quiz.di.scope.FragmentScope;
import app.android.quiz.other.utilitize.FragmentStack;
import app.android.quiz.other.utilitize.FragmentUtils;
import app.android.quiz.view.activities.home.HomeActivity;
import app.android.quiz.view.activities.home.fragment.layout.FragmentLayout;
import app.android.quiz.view.activities.home.fragment.layout.LayoutView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class LayoutModule {

    private FragmentLayout mFragment;
    private HomeActivity mActivity;
    private LayoutView mView;

    public LayoutModule(HomeActivity homeActivity, FragmentLayout fragmentLayout, LayoutView view) {
        mFragment = fragmentLayout;
        mActivity = homeActivity;
        mView = view;
    }

    public LayoutModule(HomeActivity homeActivity, FragmentLayout fragmentLayout) {
        mFragment = fragmentLayout;
        mActivity = homeActivity;
    }

    /**
     * Show up recyclerView adapter
     *
     * @return FragmentTransaction
     */
    @Provides
    @FragmentScope
    FragmentManager provideFragmentTransaction() {
        return mFragment.getChildFragmentManager();
    }

    @Provides
    @FragmentScope
    Stack<FragmentStack> provideStack() {
        return new Stack<>();
    }

    @Provides
    @FragmentScope
    FragmentUtils provideFragmentUtils(Context context, Stack<FragmentStack> mCurrentFrgStack) {
        return new FragmentUtils(mFragment, mCurrentFrgStack, R.id.fragment_layout_container);
    }
}
