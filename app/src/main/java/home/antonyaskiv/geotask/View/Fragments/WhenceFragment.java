package home.antonyaskiv.geotask.View.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
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
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
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
    private List<Address> listOfAddress;
    private View view;
    private ListView listView;
    MapView mMapView;


    private GoogleMap googleMap;

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
        mMapView = view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately
        button.setOnClickListener(ButClick);
        listView = view.findViewById(R.id.list_of_3_elements);
        List<String> ls = new ArrayList<>();
        ls.add("ok");
        ls.add("not");
        ls.add("bla");
        listView.setAdapter(new ListOf3ElementsAdapter(getContext(), ls));
        listView.setOnItemClickListener(ItemClick);
        if (ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        } else {
            Log.e("DB", "PERMISSION GRANTED");
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        whenceFragmentPresentr.init(this);
        listOfAddress = new ArrayList<>();
    }

    View.OnClickListener ButClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            List<String> list = new ArrayList<>();
            String place = text.getText().toString();
            Geocoder geocoder = new Geocoder(getContext());
            try {
                listOfAddress = geocoder.getFromLocationName(place, 7);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Address address : listOfAddress) {
                String text = address.getAddressLine(0) + " , " + address.getAddressLine(1);
                list.add(text);
            }
            listView.setAdapter(new ListOf3ElementsAdapter(getContext(), list));

        }
    };
    AdapterView.OnItemClickListener ItemClick = new AdapterView.OnItemClickListener() {
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


                   // googleMap.setMyLocationEnabled(true);
                    Log.d("ItemClick","yes");
                        // For dropping a marker at a point on the Map
                        LatLng sydney = new LatLng(listOfAddress.get(i).getLatitude(),listOfAddress.get(i).getLongitude());
                        Log.d("LatLng", String.valueOf(listOfAddress.get(i).getLatitude()));
                        googleMap.addMarker(new MarkerOptions().position(sydney).title("Tutaj").snippet("Opis"));

                        // For zooming automatically to the location of the marker
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(18).build();
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    }
                });
            mMapView.onResume();






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
