package home.antonyaskiv.geotask.Presenter;

import android.content.Context;
import android.util.Log;

import home.antonyaskiv.geotask.Presenter.Interface.IHomePresenter;


/**
 * Created by AntonYaskiv on 19.09.2017.
 */

public class HomePresenter implements IHomePresenter {
    final Context iHomeActivity;

    public HomePresenter(Context iHomeActivity) {
        this.iHomeActivity = iHomeActivity;
    }

    @Override
    public void Search(String text) {


        text = text + "Yes";
        Log.d("DagDorAnd", text);
        // iHomeActivity.setText(text);

    }
}
