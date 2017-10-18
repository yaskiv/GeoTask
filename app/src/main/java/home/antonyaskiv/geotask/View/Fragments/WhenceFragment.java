package home.antonyaskiv.geotask.View.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import home.antonyaskiv.geotask.Application.App;
import home.antonyaskiv.geotask.Presenter.ListAddressPresenter;
import home.antonyaskiv.geotask.Presenter.WhenceFragmentPresenterImpl;
import home.antonyaskiv.geotask.R;
import home.antonyaskiv.geotask.View.Activities.HomeActivity;
import home.antonyaskiv.geotask.View.Adapters.ListOf3ElementsAdapter;


public class WhenceFragment extends Fragment {

    @Inject
    WhenceFragmentPresenterImpl whenceFragmentPresenter;
    @BindView(R.id.whence_edit_text)
    EditText text;
    @BindView(R.id.list_of_3_elements)
    ListView listView;
    Unbinder unbinder;
    @BindView(R.id.search_of_button)
    Button searchOfButton;
    @BindView(R.id.mapView)
    MapView mMapView;
    private GoogleMap googleMap;
    private List<Address> listOfAddress;
    private Geocoder geocoder;
    ListAddressPresenter listAddressPresenter=new ListAddressPresenter();
    View.OnClickListener ButClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        listOfAddress=listAddressPresenter.getListOfAddress(
                geocoder,
                text.getText().toString());
            listView.setAdapter(
                    new ListOf3ElementsAdapter
                            (
                            getContext(),
                                    listAddressPresenter.getListFromGeocoderToAdapter
                                    (listOfAddress
                            )
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

            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mMap) {
                    googleMap = mMap;
                    LatLng sydney = new LatLng(listOfAddress.get(i).getLatitude(), listOfAddress.get(i).getLongitude());
                    Log.d("LatLng", String.valueOf(listOfAddress.get(i).getLatitude()));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(18).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            });
            mMapView.onResume();


        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        geocoder=new Geocoder(getContext());
        App.getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_whence, container, false);
        unbinder = ButterKnife.bind(this, view);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        searchOfButton.setOnClickListener(ButClick);
        listView.setOnItemClickListener(ItemClick);
        CheckPermission();
        return view;
    }

    private void CheckPermission() {
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        } else {
            Log.e("DB", "PERMISSION GRANTED");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        whenceFragmentPresenter.init(this);
        listOfAddress = new ArrayList<>();
    }

    public void UpdateListItems(List<String> list) {
        listView.setAdapter(new ListOf3ElementsAdapter(getContext(), list));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
