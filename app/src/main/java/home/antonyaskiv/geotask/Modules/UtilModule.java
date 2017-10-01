package home.antonyaskiv.geotask.Modules;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import home.antonyaskiv.geotask.Presenter.HomePresenter;

/**
 * Created by AntonYaskiv on 24.09.2017.
 */
@Module
public class UtilModule {

    @Provides
    @NonNull
    @Singleton
    public HomePresenter providePresenterUtils(Context context)
    {
        return new HomePresenter(context);
    }
}
