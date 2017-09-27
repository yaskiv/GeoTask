package home.antonyaskiv.geotask.Application;

import android.app.Application;

import home.antonyaskiv.geotask.Components.DaggerAppComponent;
import home.antonyaskiv.geotask.Modules.AppModul;
import home.antonyaskiv.geotask.Components.AppComponent;

/**
 * Created by AntonYaskiv on 24.09.2017.
 */

public class App extends Application {
    private static AppComponent appComponent;
    public static AppComponent getAppComponent()
    {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent=buildComponent();
    }

    protected AppComponent buildComponent()
    {
        return DaggerAppComponent.builder()
                .appModul(new AppModul(this))
                .build();
    }
}
