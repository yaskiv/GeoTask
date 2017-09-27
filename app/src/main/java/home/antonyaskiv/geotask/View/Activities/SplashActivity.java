package home.antonyaskiv.geotask.View.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import home.antonyaskiv.geotask.Application.App;
import home.antonyaskiv.geotask.Presenter.HomePresenter;

/**
 * Created by AntonYaskiv on 19.09.2017.
 */

public class SplashActivity extends AppCompatActivity {

    @Inject
    HomePresenter homePresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        App.getAppComponent().inject(this);
        homePresenter.Search("DzialoForSplash");
        Intent intent = new Intent(this, HomeActivity.class);

        startActivity(intent);
        finish();
    }
}
