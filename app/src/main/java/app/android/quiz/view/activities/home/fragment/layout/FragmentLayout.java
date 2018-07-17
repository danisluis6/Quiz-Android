package app.android.quiz.view.activities.home.fragment.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.linroid.filtermenu.library.FilterMenu;
import com.linroid.filtermenu.library.FilterMenuLayout;

import javax.inject.Inject;

import app.android.quiz.R;
import app.android.quiz.app.Application;
import app.android.quiz.di.module.HomeModule;
import app.android.quiz.di.module.LayoutModule;
import app.android.quiz.other.Constants;
import app.android.quiz.other.utilitize.FragmentUtils;
import app.android.quiz.service.asyntask.DownloadImage;
import app.android.quiz.view.activities.home.HomeActivity;
import app.android.quiz.view.activities.home.loading.FragmentLoading;
import app.android.quiz.view.dialog.DialogLoading;
import app.android.quiz.view.fragments.BaseFragment;
import app.android.quiz.view.fragments.constraint.FragmentConstraint;
import app.android.quiz.view.fragments.coordinate.FragmentCoordinate;
import app.android.quiz.view.fragments.linear.FragmentLinear;
import app.android.quiz.view.fragments.relative.FragmentRelative;
import butterknife.BindView;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@SuppressLint("ValidFragment")
public class FragmentLayout extends BaseFragment implements LayoutView, HomeActivity.HomeInterface {

    @Inject
    Context mContext;

    @Inject
    HomeActivity mHomeActivity;

    @Inject
    DownloadImage mDownloadImage;

    @Inject
    FragmentLoading mFragmentLoading;

    @Inject
    FragmentLayout mFragmentLayout;

    @Inject
    DialogLoading loadingDialog;

    @Inject
    FragmentUtils mFragmentUtils;

    @Inject
    FragmentLinear mFragmentLinear;

    @Inject
    FragmentRelative mFragmentRelative;

    @Inject
    FragmentConstraint mFragmentConstraint;

    @Inject
    FragmentCoordinate mFragmentCoordinate;

    @BindView(R.id.filter_menu)
    FilterMenuLayout filterMenuLayout;

    public void distributedDaggerComponents() {
        Application.getInstance()
                .getAppComponent()
                .plus(new HomeModule((HomeActivity) getActivity()))
                .plus(new LayoutModule((HomeActivity) getActivity(), this, this))
                .inject(this);
    }

    public FragmentLayout() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        distributedDaggerComponents();
        bindView(view);
        mHomeActivity.attachHomeInterface(this);
        new FilterMenu.Builder(mContext)
                .inflate(R.menu.menu_filter)
                .attach(filterMenuLayout)
                .withListener(listener)
                .build();
        return view;
    }

    public void pushFragment(Fragment fragment, String tag) {
        mFragmentUtils.pushFragment(FragmentUtils.PushFrgType.REPLACE, fragment, tag, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    FilterMenu.OnMenuChangeListener listener = new FilterMenu.OnMenuChangeListener() {
        @Override
        public void onMenuItemClick(View view, int position) {
            switch (position) {
                case 0:
                    pushFragment(mFragmentLinear, getString(R.string.TAG_FRAGMENT_LINEAR));
                    break;
                case 1:
                    pushFragment(mFragmentRelative, getString(R.string.TAG_FRAGMENT_RELATIVE));
                    break;
                case 2:
                    pushFragment(mFragmentConstraint, getString(R.string.TAG_FRAGMENT_CONSTRAINT));
                    break;
                case 3:
                    pushFragment(mFragmentCoordinate, getString(R.string.TAG_FRAGMENT_COORDINATE));
                    break;
            }
        }

        @Override
        public void onMenuCollapse() {

        }

        @Override
        public void onMenuExpand() {

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.PERMISSION_CAMERA:
                for (int permissionId : grantResults) {
                    if (permissionId != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(mContext, getString(R.string.error_permission), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressOnFragment() {
    }

}