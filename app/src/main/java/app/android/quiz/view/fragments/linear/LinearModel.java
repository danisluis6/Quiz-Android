package app.android.quiz.view.fragments.linear;

import app.android.quiz.service.JsonData;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public interface LinearModel {
    void attachPresenter(LinearPresenter presenter);

    void attachJsonData(JsonData jsonData);

    void getQuestionFromAPI();
}
