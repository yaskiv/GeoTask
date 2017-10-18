package home.antonyaskiv.geotask.Presenter;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.internal.matchers.Null.*;

/**
 * Created by AntonYaskiv on 04.10.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ListAddressPresenterTest {

    @Mock
    Geocoder geocoder;
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getListOfAddress() throws Exception {

        ListAddressPresenter myObjectUnderTest = new ListAddressPresenter();
        List<Address> addressList=new ArrayList<>();
        addressList.add(new Address(Locale.ENGLISH));
        Mockito.when(geocoder.getFromLocationName("Gagarina 35",7)).thenReturn(addressList);
        List<Address> result = myObjectUnderTest.getListOfAddress(geocoder,"Gagarina 35");
        // ...then the result should be the expected one.
        assertFalse(result.isEmpty());
        //assertThat(mMockContext).isNotNull();

    }

    @Test
    public void getListFromGeocoderToAdapter() throws Exception {
        ListAddressPresenter myObjectUnderTest = new ListAddressPresenter();
        List<Address> addressList=new ArrayList<>();
        addressList.add(new Address(Locale.ENGLISH));
        List<String>result=myObjectUnderTest.getListFromGeocoderToAdapter(addressList);
        assertFalse(result.isEmpty());

    }

}