package app.android.quiz.service;

import java.util.List;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public interface IDisposableListener<T> {
    void onComplete();

    void onHandleData(List<T> items);

    void onApiFailure(Throwable error);

    void onRequestWrongData(int code);
}
