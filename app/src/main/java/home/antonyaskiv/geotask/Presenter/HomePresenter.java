package home.antonyaskiv.geotask.Presenter;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import home.antonyaskiv.geotask.Presenter.Interface.IHomePresenter;
import home.antonyaskiv.geotask.R;
import home.antonyaskiv.geotask.View.Activities.Interface.IHomeActivity;
import home.antonyaskiv.geotask.View.Fragments.WhenceFragment;


/**
 * Created by AntonYaskiv on 19.09.2017.
 */

public class HomePresenter implements IHomePresenter {
    final WhenceFragment iHomeActivity;
    public HomePresenter(WhenceFragment iHomeActivity)
    {
        this.iHomeActivity=iHomeActivity;
    }
    @Override
    public void Search(String text) {


        text=text+"Yes";
        iHomeActivity.setText(text);

    }
}
