package home.antonyaskiv.geotask.View.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.List;

import home.antonyaskiv.geotask.API.GetDataCallBack;
import home.antonyaskiv.geotask.API.WayMapAPI;
import home.antonyaskiv.geotask.Model.DataToMap;
import home.antonyaskiv.geotask.Model.Leg;
import home.antonyaskiv.geotask.Model.WayPoints;
import home.antonyaskiv.geotask.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GetDataCallBack {

    private GoogleMap mMap;
    private DataToMap dataToMap;
    private ProgressDialog dialog;
    private WayPoints wp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait!!");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        Intent intent=this.getIntent();
        dataToMap=intent.getParcelableExtra("dataToMap");
        ApiInit(dataToMap.getfrom(),dataToMap.getTo());
       SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void ApiInit(String from, String to) {


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        Callback<WayPoints> wp=new Callback<WayPoints>() {
            @Override
            public void onResponse(Call<WayPoints> call, Response<WayPoints> response) {

                if (response.isSuccessful()){
                    onGetMapData(response.body());
                } else {
                    onError();
                }
            }

            @Override
            public void onFailure(Call<WayPoints> call, Throwable t) {
                Toast.makeText(MapsActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        };
        WayMapAPI service = retrofit.create(WayMapAPI.class);

        service.getWayFromCoordinate(from, to,getString(R.string.API_Map))
                .enqueue(wp);





    }
    @Override
    public void onGetMapData(WayPoints wayPoints) {

    Log.d("WayPoints",wayPoints.getStatus());
    List<Leg> legs= wayPoints.getRoutes().get(0).getLegs();
    for (Leg leg: legs)
    {
       mMap.addPolygon(new PolygonOptions()
        .add(new LatLng(leg.getStartLocation().getLat(),leg.getStartLocation().getLng()),new LatLng(leg.getEndLocation().getLat(),leg.getEndLocation().getLng()))
       .strokeColor(Color.RED) );

    }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(legs.get(0).getStartLocation().getLat(),legs.get(0).getStartLocation().getLng())));
        mMap.setMinZoomPreference(16);
//routes legs steps sart - end

    dialog.dismiss();
    }

    @Override
    public void onError() {
        Log.d("GetData","error");
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
