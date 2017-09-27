package home.antonyaskiv.geotask.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import home.antonyaskiv.geotask.Application.App;
import home.antonyaskiv.geotask.Presenter.WhenceFragmentPresentrImpl;
import home.antonyaskiv.geotask.R;
import home.antonyaskiv.geotask.View.Adapters.ListOf3ElementsAdapter;


public class WhenceFragment extends Fragment {

    @Inject
     WhenceFragmentPresentrImpl whenceFragmentPresentr;

    private EditText text;

    private View view;
    private ListView listView;

    public WhenceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_whence, container, false);
        text = view.findViewById(R.id.whence_edit_text);
        Button button = view.findViewById(R.id.search_of_button);

        button.setOnClickListener(ButClick);
        listView=view.findViewById(R.id.list_of_3_elements);
        List<String> ls=new ArrayList<>();
        ls.add("ok");
        ls.add("not");
        ls.add("bla");
        listView.setAdapter(new ListOf3ElementsAdapter(getContext(),ls));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        whenceFragmentPresentr.init(this);
    }

    View.OnClickListener ButClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

           whenceFragmentPresentr.SetTExt("ok");
        }
    };

    public void UpdateListItems(List<String> list)
    {
        listView.setAdapter(new ListOf3ElementsAdapter(getContext(),list));
    }
    public void SetTextInView(String text) {
        this.text.setText(text);
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
        this.text.setText(text);
    }
}
