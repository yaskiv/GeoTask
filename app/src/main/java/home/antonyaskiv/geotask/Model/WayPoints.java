
package home.antonyaskiv.geotask.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WayPoints implements Parcelable {

    @SerializedName("geocoded_waypoints")
    @Expose
    private List<GeocodedWaypoint> geocodedWaypoints = null;
    @SerializedName("routes")
    @Expose
    private List<Route> routes = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<GeocodedWaypoint> getGeocodedWaypoints() {
        return geocodedWaypoints;
    }

    public void setGeocodedWaypoints(List<GeocodedWaypoint> geocodedWaypoints) {
        this.geocodedWaypoints = geocodedWaypoints;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.geocodedWaypoints);
        dest.writeList(this.routes);
        dest.writeString(this.status);
    }

    public WayPoints() {
    }

    protected WayPoints(Parcel in) {
        this.geocodedWaypoints = new ArrayList<GeocodedWaypoint>();
        in.readList(this.geocodedWaypoints, GeocodedWaypoint.class.getClassLoader());
        this.routes = new ArrayList<Route>();
        in.readList(this.routes, Route.class.getClassLoader());
        this.status = in.readString();
    }

    public static final Parcelable.Creator<WayPoints> CREATOR = new Parcelable.Creator<WayPoints>() {
        @Override
        public WayPoints createFromParcel(Parcel source) {
            return new WayPoints(source);
        }

        @Override
        public WayPoints[] newArray(int size) {
            return new WayPoints[size];
        }
    };
}
