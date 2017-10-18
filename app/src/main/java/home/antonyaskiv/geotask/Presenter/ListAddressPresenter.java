package home.antonyaskiv.geotask.Presenter;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AntonYaskiv on 02.10.2017.
 */

public class ListAddressPresenter {
    public List<Address> getListOfAddress(Geocoder geocoder, String place)
    {
        List<Address> addressList=new ArrayList<>();

        try {
            addressList=  geocoder.getFromLocationName(place, 7);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressList;
    }
    @NonNull
    public  List<String> getListFromGeocoderToAdapter(List<Address> listOfAddress) {
        List<String> list = new ArrayList<>();

        for (Address address : listOfAddress) {
            String text = address.getAddressLine(0) + " , " + address.getAddressLine(1);
            list.add(text);
        }
        return list;
    }
}
