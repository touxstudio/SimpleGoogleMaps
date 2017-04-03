package org.coursera.mygooglemaps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double LTA, LNG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




        SharedPreferences prefs = this.getSharedPreferences("MAPS", Context.MODE_PRIVATE);

             LTA = Double.parseDouble(prefs.getString("LAT", "00000"));
             LNG = Double.parseDouble(prefs.getString("LNG", "00000"));
            Log.v("TOUX-LTA", String.valueOf(LTA));
            Log.v("TOUX-LNG", String.valueOf(LNG));


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(LTA, LNG);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Estás aquí" )
                .snippet("Población: 776733 habs")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.place_optimization)


        ));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(mMap.getCameraPosition().zoom - 9.5f));
        //mMap.animateCamera( CameraUpdateFactory.zoomTo( 287.0f ) );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.getCameraPosition();



        Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(LTA, LNG))
                .radius(500)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
            }
        });


    }


}
