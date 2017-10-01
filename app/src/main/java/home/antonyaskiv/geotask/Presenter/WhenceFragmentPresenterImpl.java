package home.antonyaskiv.geotask.Presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import home.antonyaskiv.geotask.Presenter.Interface.IWhenceFragmentPresenter;
import home.antonyaskiv.geotask.View.Fragments.WhenceFragment;

/**
 * Created by AntonYaskiv on 27.09.2017.
 */

public class WhenceFragmentPresenterImpl implements IWhenceFragmentPresenter {

    private final Context context;
    private WhenceFragment fragment;
    public void init(WhenceFragment fragment)
    {
        this.fragment=fragment;
    }
    public WhenceFragmentPresenterImpl(Context context)
    {
        this.context=context;
    }
    @Override
    public void SetTExt(String text) {
        List<String> list=new ArrayList<>();
        list.add("df");
        list.add("dfwe");
        fragment.UpdateListItems(list);
    }
}
