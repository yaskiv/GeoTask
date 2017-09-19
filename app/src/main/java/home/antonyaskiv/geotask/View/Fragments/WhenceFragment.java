package home.antonyaskiv.geotask.View.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import home.antonyaskiv.geotask.Presenter.HomePresenter;
import home.antonyaskiv.geotask.Presenter.Interface.IHomePresenter;
import home.antonyaskiv.geotask.R;
import home.antonyaskiv.geotask.View.Activities.Interface.IHomeActivity;


public class WhenceFragment extends Fragment {

    private EditText text;
    private IHomePresenter pHome;
    private  View view;
    public WhenceFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_whence, container, false);
        text=(EditText)view.findViewById(R.id.whence_edit_text);
        Button button=(Button)view.findViewById(R.id.search_of_button);
        pHome=new HomePresenter(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pHome.Search(text.getText().toString());
            }
        });
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public void setText(String text) {
        this.text.setText( text);
    }
}
