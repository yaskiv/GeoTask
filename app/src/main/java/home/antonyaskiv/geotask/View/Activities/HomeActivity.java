package home.antonyaskiv.geotask.View.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import home.antonyaskiv.geotask.Presenter.HomePresenter;
import home.antonyaskiv.geotask.Presenter.Interface.IHomePresenter;
import home.antonyaskiv.geotask.R;
import home.antonyaskiv.geotask.View.Activities.Interface.IHomeActivity;
import home.antonyaskiv.geotask.View.Adapters.ViewPagerAdapter;
import home.antonyaskiv.geotask.View.Fragments.WhenceFragment;
import home.antonyaskiv.geotask.View.Fragments.WhereFragment;

public class HomeActivity extends AppCompatActivity  {



    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);





        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WhenceFragment(), "Whence");
        adapter.addFragment(new WhereFragment(), "Where");

        viewPager.setAdapter(adapter);
    }


}
