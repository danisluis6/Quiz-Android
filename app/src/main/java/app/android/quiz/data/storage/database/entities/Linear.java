package app.android.quiz.data.storage.database.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import app.android.quiz.data.storage.DatabaseInfo;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

public class Linear implements Parcelable {

    @SerializedName(DatabaseInfo.Linear.COLUMN_ID)
    private int id;

    @SerializedName(DatabaseInfo.Linear.COLUMN_QUESTION)
    private String question;

    @SerializedName(DatabaseInfo.Linear.COLUMN_ANSWERA)
    private String a;

    @SerializedName(DatabaseInfo.Linear.COLUMN_ANSWERB)
    private String b;

    @SerializedName(DatabaseInfo.Linear.COLUMN_ANSWERC)
    private String c;

    @SerializedName(DatabaseInfo.Linear.COLUMN_ANSWERD)
    private String d;

    @SerializedName(DatabaseInfo.Linear.COLUMN_RESULT)
    private String x;

    public Linear() {
    }

    public Linear(int id, String question, String a, String b, String c, String d, String x) {
        this.id = id;
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.x = x;
    }

    protected Linear(Parcel in) {
        id = in.readInt();
        question = in.readString();
        a = in.readString();
        b = in.readString();
        c = in.readString();
        d = in.readString();
        x = in.readString();
    }

    public static final Creator<Linear> CREATOR = new Creator<Linear>() {
        @Override
        public Linear createFromParcel(Parcel in) {
            return new Linear(in);
        }

        @Override
        public Linear[] newArray(int size) {
            return new Linear[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(question);
        parcel.writeString(a);
        parcel.writeString(b);
        parcel.writeString(c);
        parcel.writeString(d);
        parcel.writeString(x);
    }
}
