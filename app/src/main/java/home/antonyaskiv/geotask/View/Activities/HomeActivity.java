package home.antonyaskiv.geotask.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import home.antonyaskiv.geotask.Application.App;
import home.antonyaskiv.geotask.Model.DataToMap;
import home.antonyaskiv.geotask.Presenter.HomePresenter;
import home.antonyaskiv.geotask.R;
import home.antonyaskiv.geotask.View.Adapters.ViewPagerAdapter;
import home.antonyaskiv.geotask.View.Fragments.WhenceFragment;
import home.antonyaskiv.geotask.View.Fragments.WhereFragment;

public class HomeActivity extends AppCompatActivity  {


    @Inject
    HomePresenter homePresenter;

    @BindView(R.id.tabs)
     TabLayout tabLayout;
    @BindView(R.id.viewpager)
     ViewPager viewPager;


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
        Intent intent=new Intent(HomeActivity.this, MapsActivity.class);

        intent.putExtra("dataToMap",  new DataToMap("52.20660731,21.03445709", "52.20663361,21.03444636"));
        startActivity(intent);




    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WhenceFragment(), "Whence");
        adapter.addFragment(new WhereFragment(), "Where");

        viewPager.setAdapter(adapter);
    }



}

