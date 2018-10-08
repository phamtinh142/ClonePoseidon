package com.arsenal.santiha.cloneposeidon.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arsenal.santiha.cloneposeidon.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng poseidon = new LatLng(21.0070079, 105.801282);


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poseidon, 16));

        map.addMarker(new MarkerOptions()
                .title("Nhà hàng Poseidon")
                .snippet("Chuyên hải sản tươi ngon nhất VBB")
                .position(poseidon));
    }
}
