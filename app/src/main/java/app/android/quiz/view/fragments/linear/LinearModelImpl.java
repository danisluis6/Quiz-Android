package app.android.quiz.view.fragments.linear;

import android.content.Context;
import android.os.Handler;

import java.util.List;

import app.android.quiz.R;
import app.android.quiz.data.storage.database.entities.Linear;
import app.android.quiz.other.Utils;
import app.android.quiz.service.DisposableManager;
import app.android.quiz.service.IDisposableListener;
import app.android.quiz.service.JsonData;
import io.reactivex.Observable;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class LinearModelImpl implements LinearModel {

    private Context mContext;
    private JsonData mJsonData;
    private LinearPresenter mPresenter;
    private DisposableManager mLinearDisposableManager;

    public LinearModelImpl(Context context) {
        mContext = context;
        mLinearDisposableManager = new DisposableManager();
    }

    @Override
    public void attachPresenter(LinearPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void attachJsonData(JsonData jsonData) {
        mJsonData = jsonData;
    }

    @Override
    public void getQuestionFromAPI() {
        if (Utils.isInternetOn(mContext)) {
            mPresenter.setDisposable(mLinearDisposableManager.callLinearData(Observable.just(mJsonData.getItemsFromJson())));
            mLinearDisposableManager.setLinearInterface(new IDisposableListener<Linear>() {
                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(final List<Linear> items) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.getDataSuccess(items);
                        }
                    }, 1000);
                }

                @Override
                public void onRequestWrongData(int code) {

                }

                @Override
                public void onApiFailure(Throwable error) {
                    mPresenter.getDataFailure(error.getMessage());
                }
            });
        } else {
            mPresenter.getDataFailure(mContext.getString(R.string.no_internet_connection));
        }
    }
}
