package com.epicodus.addressgeocoder.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.addressgeocoder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.addressEditText) EditText mAddressEditText;
    @Bind(R.id.latitudeTextView) TextView mLatitudeTextView;
    @Bind(R.id.longitudeTextView) TextView mLongitudeTextView;
    @Bind(R.id.getCoordinatesButton) Button mGetCoordinatesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mGetCoordinatesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String newAddress = mAddressEditText.getText().toString().trim();

        if (view == mGetCoordinatesButton) {
            if (newAddress.equals("")) {
                mAddressEditText.setError("Please enter an address");
            } else {
                Log.v("Address: ", newAddress);

                mAddressEditText.setText("");
            }
        }
    }
}
