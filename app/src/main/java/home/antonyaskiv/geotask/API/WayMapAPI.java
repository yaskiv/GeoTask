package home.antonyaskiv.geotask.API;



import home.antonyaskiv.geotask.Model.WayPoints;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AntonYaskiv on 29.09.2017.
 */

public interface WayMapAPI {
    @GET("maps/api/directions/json?")
    Call<WayPoints> getWayFromCoordinate(@Query("origin")String from, @Query("destination") String to, @Query("key") String api_key);


}
