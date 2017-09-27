package home.antonyaskiv.geotask.Components;

import javax.inject.Singleton;

import dagger.Component;
import home.antonyaskiv.geotask.Modules.AppModul;
import home.antonyaskiv.geotask.Modules.FragmentWhencePresenterModul;
import home.antonyaskiv.geotask.Modules.UtilModul;
import home.antonyaskiv.geotask.Presenter.WhenceFragmentPresentrImpl;
import home.antonyaskiv.geotask.View.Activities.HomeActivity;
import home.antonyaskiv.geotask.View.Activities.SplashActivity;
import home.antonyaskiv.geotask.View.Fragments.WhenceFragment;

/**
 * Created by AntonYaskiv on 24.09.2017.
 */
@Component(modules = {AppModul.class, UtilModul.class, FragmentWhencePresenterModul.class})
@Singleton
public interface AppComponent {
    void inject(HomeActivity homeActivity);
    void inject(SplashActivity homeActivity);
    void inject(WhenceFragment whenceFragment);
}
