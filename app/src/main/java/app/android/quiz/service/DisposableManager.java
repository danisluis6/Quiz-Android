package app.android.quiz.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.android.quiz.data.storage.database.entities.Linear;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class DisposableManager {

    private IDisposableListener listener;
    private Disposable disposable;

    @Inject
    public DisposableManager() {
    }

    public Disposable callLinearData(Observable<List<Linear>> observable) {
        disposable = observable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<List<Linear>>() {
            @Override
            public void onComplete() {
                listener.onComplete();
            }

            @Override
            public void onNext(List<Linear> items) {
                if (items != null) {
                    listener.onHandleData(items);
                } else {
                    listener.onRequestWrongData(-1);
                }
            }

            @Override
            public void onError(Throwable e) {
                listener.onApiFailure(e);
            }
        });
        return disposable;

    }

    public void setLinearInterface(IDisposableListener<Linear> disposableInterface) {
        this.listener = disposableInterface;
    }
}