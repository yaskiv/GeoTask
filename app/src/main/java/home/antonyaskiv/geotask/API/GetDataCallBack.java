package home.antonyaskiv.geotask.API;

import home.antonyaskiv.geotask.Model.WayPoints;

/**
 * Created by AntonYaskiv on 10.10.2017.
 */

public interface GetDataCallBack {
    void onGetMapData(WayPoints wayPoints);
    void onError();
}
