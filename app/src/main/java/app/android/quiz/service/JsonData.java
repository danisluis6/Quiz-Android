package app.android.quiz.service;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.android.quiz.data.storage.database.entities.Linear;
import app.android.quiz.view.activities.home.HomeActivity;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class JsonData {

    private Context mContext;

    @Inject
    public JsonData(Context context) {
        mContext = context;
    }

    /**
     * Adds {@link Linear}'s from a JSON file.
     */
    public List<Linear> getItemsFromJson() {
        Gson gson = new Gson();
        try {
            String jsonDataString = convertJsonToString();
            return gson.fromJson(jsonDataString, new TypeToken<ArrayList<Linear>>() {}.getType());
        } catch (IOException exception) {
            Log.e(HomeActivity.class.getName(), "Unable to parse JSON file.", exception);
        }
        return null;
    }

    /**
     * Reads the JSON file and converts the JSON data to a {@link String}.
     *
     * @return A {@link String} representation of the JSON data.
     * @throws IOException if unable to read the JSON file.
     */
    private String convertJsonToString() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        try {
            String jsonDataString;
            inputStream = mContext.getResources().openRawResource(mContext.getResources().getIdentifier("json_linear", "raw", mContext.getPackageName()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }

}
