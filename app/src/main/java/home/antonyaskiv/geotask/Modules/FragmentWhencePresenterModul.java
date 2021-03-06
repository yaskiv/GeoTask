package home.antonyaskiv.geotask.Modules;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import home.antonyaskiv.geotask.Presenter.WhenceFragmentPresenterImpl;

/**
 * Created by AntonYaskiv on 27.09.2017.
 */
@Module
public class FragmentWhencePresenterModul {
    @Provides
    @NonNull
    @Singleton
    public WhenceFragmentPresenterImpl provideWhenceFragmentPresentrUtils(Context context)
    {
        return new WhenceFragmentPresenterImpl(context);
    }
}
