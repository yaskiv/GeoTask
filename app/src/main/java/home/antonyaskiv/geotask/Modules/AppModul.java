package home.antonyaskiv.geotask.Modules;

import android.content.Context;
import android.support.annotation.NonNull;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by AntonYaskiv on 24.09.2017.
 */
@Module
public class AppModul {
    private Context AppContext;
    public AppModul(@NonNull Context context)
    {
        this.AppContext=context;
    }
    @Provides
    @Singleton
    Context provideContext()
    {
        return this.AppContext;
    }
}
