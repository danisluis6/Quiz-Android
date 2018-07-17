package app.android.quiz.other.utilitize;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

import app.android.quiz.R;
import app.android.quiz.view.fragments.BaseFragment;

public class FragmentUtils {
    private Stack<FragmentStack> mCurrentFrgStack;
    private int mContainerId;
    private Fragment mBaseFragment;

    public FragmentUtils(BaseFragment baseFragment, Stack<FragmentStack> currentFrgStack, @IdRes int containerId) {
        this.mCurrentFrgStack = currentFrgStack;
        this.mContainerId = containerId;
        this.mBaseFragment = baseFragment;
    }

    public void peekFragment() {
        try {
            FragmentStack fragment = mCurrentFrgStack.peek();
            FragmentManager manager = mBaseFragment.getChildFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
            ft.replace(mContainerId, fragment.getFragment(), fragment.getTag());
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException | ArrayIndexOutOfBoundsException e) {
        }
    }

    public void popFragment() {
        mCurrentFrgStack.pop();
        peekFragment();
    }

    public void pushFragment(PushFrgType type, Fragment fragment, String tag, boolean shouldAdd) {
        try {
            FragmentManager manager = mBaseFragment.getChildFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
            if (type == PushFrgType.REPLACE) {
                ft.replace(mContainerId, fragment, tag);
                ft.addToBackStack(tag);
                ft.commitAllowingStateLoss();
            } else if (type == PushFrgType.ADD) {
                ft.add(mContainerId, fragment, tag);
                ft.disallowAddToBackStack();
                ft.commit();
            }
            manager.executePendingTransactions();
        } catch (IllegalStateException e) {
        }
        if (shouldAdd) {
            mCurrentFrgStack.add(new FragmentStack(fragment, tag));
        }
    }

    public enum PushFrgType {
        REPLACE, ADD
    }
}
