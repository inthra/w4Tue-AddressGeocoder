package com.epicodus.addressgeocoder.ui;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.addressgeocoder.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.addressEditText) EditText mAddressEditText;
    @Bind(R.id.coordinateTextView) TextView mCoordinateTextView;
    @Bind(R.id.getCoordinatesButton) Button mGetCoordinatesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mGetCoordinatesButton.setOnClickListener(this);
    }


    // get latlng from address
    public LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(MainActivity.this, Locale.getDefault());
        List<Address> address;
        LatLng coordinates = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            coordinates = new LatLng(location.getLatitude(), location.getLongitude());


            return coordinates;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coordinates;
    }


    @Override
    public void onClick(View view) {
        String newAddress = mAddressEditText.getText().toString().trim();

        if (view == mGetCoordinatesButton) {
            if (newAddress.equals("")) {
                mAddressEditText.setError("Please enter a valid address");
            } else {
                Log.v("Address: ", newAddress);
                LatLng newCoordinates = getLocationFromAddress(newAddress);
                Log.v("coords: ", newCoordinates.toString());
                mCoordinateTextView.setText(newCoordinates.toString());
//                mAddressEditText.setText("");
            }
        }
    }
}
