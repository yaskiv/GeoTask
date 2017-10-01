package home.antonyaskiv.geotask.View.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import java.io.IOException;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import home.antonyaskiv.geotask.API.WayMapAPI;
import home.antonyaskiv.geotask.Application.App;
import home.antonyaskiv.geotask.Model.WayPoints;
import home.antonyaskiv.geotask.Presenter.HomePresenter;
import home.antonyaskiv.geotask.R;
import home.antonyaskiv.geotask.View.Adapters.ViewPagerAdapter;
import home.antonyaskiv.geotask.View.Fragments.WhenceFragment;
import home.antonyaskiv.geotask.View.Fragments.WhereFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {


    @Inject
    HomePresenter homePresenter;

    @BindView(R.id.tabs)
    private TabLayout tabLayout;
    @BindView(R.id.viewpager)
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        App.getAppComponent().inject(this);
        homePresenter.Search("DzialoForHome");
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void SearchWay(View view) throws IOException {

        WayPoints wayPoints = ApiInit("52.20660731,21.03445709", "52.20663361,21.03444636");


    }

    private WayPoints ApiInit(String from, String to) {
        final WayPoints[] response_Of_Api = new WayPoints[1];
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WayMapAPI service = retrofit.create(WayMapAPI.class);

        service.getWayFromCoordinate(from, to, getString(R.string.API_Map))
                .enqueue(new Callback<WayPoints>() {
                    @Override
                    public void onResponse(Call<WayPoints> call, Response<WayPoints> response) {
                        WayPoints response_Of_Api_new = response.body();
                        response_Of_Api[0] = response_Of_Api_new;
                    }

                    @Override
                    public void onFailure(Call<WayPoints> call, Throwable t) {
                        Toast.makeText(HomeActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                    }
                });
        return response_Of_Api[0];


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WhenceFragment(), "Whence");
        adapter.addFragment(new WhereFragment(), "Where");

        viewPager.setAdapter(adapter);
    }


}
