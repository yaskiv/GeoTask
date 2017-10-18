package home.antonyaskiv.geotask.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AntonYaskiv on 10.10.2017.
 */

public class DataToMap implements Parcelable {
    private String from;
    private String to;

    public DataToMap(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getfrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.from);
        dest.writeString(this.to);
    }

    protected DataToMap(Parcel in) {
        this.from = in.readString();
        this.to = in.readString();
    }

    public static final Parcelable.Creator<DataToMap> CREATOR = new Parcelable.Creator<DataToMap>() {
        @Override
        public DataToMap createFromParcel(Parcel source) {
            return new DataToMap(source);
        }

        @Override
        public DataToMap[] newArray(int size) {
            return new DataToMap[size];
        }
    };
}
