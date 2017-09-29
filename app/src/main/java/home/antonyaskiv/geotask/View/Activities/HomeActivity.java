package home.antonyaskiv.geotask.View.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import home.antonyaskiv.geotask.API.WayMapAPI;
import home.antonyaskiv.geotask.Application.App;
import home.antonyaskiv.geotask.Model.WayPoints;
import home.antonyaskiv.geotask.Presenter.HomePresenter;
import home.antonyaskiv.geotask.Presenter.Interface.IHomePresenter;
import home.antonyaskiv.geotask.R;
import home.antonyaskiv.geotask.View.Activities.Interface.IHomeActivity;
import home.antonyaskiv.geotask.View.Adapters.ViewPagerAdapter;
import home.antonyaskiv.geotask.View.Fragments.WhenceFragment;
import home.antonyaskiv.geotask.View.Fragments.WhereFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity  {



    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Inject
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        App.getAppComponent().inject(this);
homePresenter.Search("DzialoForHome");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }
    List<WayPoints> wayPoints;
    public void SearchWay(View view) throws IOException {
        Log.d("On","Ok");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WayMapAPI service = retrofit.create(WayMapAPI.class);
       wayPoints =new ArrayList<>();
        // WayPoints wayPoints=new WayPoints();
        service.getWayFromCoordinate("52.20660731,21.03445709","52.20663361,21.03444636",getString(R.string.API_Map))
                .enqueue(new Callback<WayPoints>() {
            @Override
            public void onResponse(Call<WayPoints> call, Response<WayPoints> response) {
               wayPoints.add(response.body());
               Log.d("ResponeOk","Ok");
            }

            @Override
            public void onFailure(Call<WayPoints> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("Ok","Ok");






    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WhenceFragment(), "Whence");
        adapter.addFragment(new WhereFragment(), "Where");

        viewPager.setAdapter(adapter);
    }


}
