package app.android.quiz.view.activities.home.loading;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import javax.inject.Inject;

import app.android.quiz.R;
import butterknife.BindView;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class FragmentLoading extends Fragment {

    @BindView(R.id.statusProgressBar)
    ProgressBar mProgressBar;

    @Inject
    public FragmentLoading() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loading, container, false);
        mProgressBar = v.findViewById(R.id.statusProgressBar);
        return v;
    }

    public boolean isShown() {
        return mProgressBar.isShown();
    }
}
