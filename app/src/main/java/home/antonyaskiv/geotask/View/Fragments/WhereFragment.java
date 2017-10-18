package home.antonyaskiv.geotask.View.Fragments;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import home.antonyaskiv.geotask.Presenter.ListAddressPresenter;
import home.antonyaskiv.geotask.R;
import home.antonyaskiv.geotask.View.Adapters.ListOf3ElementsAdapter;


public class WhereFragment extends Fragment {


    @BindView(R.id.where_edit_text)
    EditText whereEditText;
    @BindView(R.id.search_of_button_where)
    Button searchOfButtonWhere;
    @BindView(R.id.list_of_3_elements_where)
    ListView listOf3ElementsWhere;
    @BindView(R.id.mapView_where)
    MapView mapViewWhere;
    Unbinder unbinder;
    private GoogleMap googleMap;
    private  List<Address> listOfAddress;
    private Geocoder geocoder;
    ListAddressPresenter listAddressPresenter=new ListAddressPresenter();
    View.OnClickListener butClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listOfAddress= listAddressPresenter
                            .getListOfAddress
                                    (geocoder,
                            whereEditText.getText().toString());
            listOf3ElementsWhere.setAdapter(
                    new ListOf3ElementsAdapter
                            (getContext(),listAddressPresenter
                                    .getListFromGeocoderToAdapter
                                            (listOfAddress)
                                            )
                            );
        }
    };

    private AdapterView.OnItemClickListener ItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {


            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }

            mapViewWhere.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mMap) {
                    googleMap = mMap;
                    LatLng sydney = new LatLng(listOfAddress.get(i).getLatitude(), listOfAddress.get(i).getLongitude());
                    Log.d("LatLng", String.valueOf(listOfAddress.get(i).getLatitude()));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(18).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            });
            mapViewWhere.onResume();


        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        geocoder=new Geocoder(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_where, container, false);
        unbinder = ButterKnife.bind(this, view);
        searchOfButtonWhere.setOnClickListener(butClick);
        listOf3ElementsWhere.setOnItemClickListener(ItemClick);
        mapViewWhere.onCreate(savedInstanceState);
        mapViewWhere.onResume();
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

    @Override
    public void onResume() {
        super.onResume();
        listOfAddress = new ArrayList<>();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
