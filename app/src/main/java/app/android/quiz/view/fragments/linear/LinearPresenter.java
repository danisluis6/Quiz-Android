package app.android.quiz.view.fragments.linear;

import java.util.List;

import app.android.quiz.data.storage.database.entities.Linear;
import io.reactivex.disposables.Disposable;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public interface LinearPresenter {
    void setDisposable(Disposable disposable);
    void getQuestionFromAPI();
    void getDataSuccess(List<Linear> items);
    void getDataFailure(String message);
}
