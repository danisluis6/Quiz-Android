package app.android.quiz.view.fragments.linear;

import android.content.Context;

import java.util.List;

import app.android.quiz.data.storage.database.entities.Linear;
import app.android.quiz.service.JsonData;
import app.android.quiz.view.activities.home.HomeActivity;
import io.reactivex.disposables.Disposable;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class LinearPresenterImpl implements LinearPresenter {

    private Context mContext;
    private HomeActivity mHomeActivity;
    private LinearView mLinearView;
    private LinearModel mLinearModel;

    public LinearPresenterImpl(Context context, HomeActivity activity, LinearView view, LinearModel model, JsonData jsonData) {
        mContext = context;
        mHomeActivity = activity;
        mLinearView = view;
        mLinearModel = model;
        mLinearModel.attachJsonData(jsonData);
        mLinearModel.attachPresenter(this);
    }

    @Override
    public void setDisposable(Disposable disposable) {
        mLinearView.setDisposable(disposable);
    }

    @Override
    public void getQuestionFromAPI() {
        mLinearModel.getQuestionFromAPI();
    }

    @Override
    public void getDataSuccess(List<Linear> items) {
        mLinearView.getDataSuccess(items);
    }

    @Override
    public void getDataFailure(String message) {
        mLinearView.getDataFailure(message);
    }
}
