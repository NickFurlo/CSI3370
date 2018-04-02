//http://www.zoftino.com/android-google-maps-tutorial
//test comment for push

package com.example.user.oumobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class bus extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    LatLng oakland = new LatLng(42.6729472,-83.2184753);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.g_map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng bus1 = new LatLng(42.6776306,-83.2196052);
        LatLng bus2 = new LatLng(42.6734264,-83.2210523);
        LatLng bus3 = new LatLng(42.6719582,-83.2159923);
        LatLng bus4 = new LatLng(42.6742201,-83.2071659);

        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(20);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);


        /*
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.6776306, -83.2184753))
                .title("Bus #1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_background))
                .draggable(false));
        */

        mMap.addMarker(new MarkerOptions().position(bus1).title("Bus #1"));
        mMap.addMarker(new MarkerOptions().position(bus2).title("Bus #2"));
        mMap.addMarker(new MarkerOptions().position(bus3).title("Bus #3"));
        mMap.addMarker(new MarkerOptions().position(bus4).title("Bus #4"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(oakland));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(oakland, 15));

    }

    public void resetMap(View view) {
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(oakland, 15));

        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(oakland)      // Sets the center of the map to Mountain View
                .zoom(15)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(00)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

}
