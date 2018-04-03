//http://www.zoftino.com/android-google-maps-tutorial
//test comment for push

package com.example.user.oumobileapp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class bus extends AppCompatActivity implements OnMapReadyCallback {

    //Map variable
    private GoogleMap mMap;

    //OU base location
    LatLng oakland = new LatLng(42.6729472,-83.2184753);

    //Init map
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.g_map);
        mapFragment.getMapAsync(this);
    }

    //When map is ready
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Static bus locations
        LatLng bus1 = new LatLng(42.6776306,-83.2196052);
        LatLng bus2 = new LatLng(42.6734264,-83.2210523);
        LatLng bus3 = new LatLng(42.6719582,-83.2159923);
        LatLng bus4 = new LatLng(42.6742201,-83.2071659);

        //Customize map zoom and type
        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(20);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Add markers
        mMap.addMarker(new MarkerOptions().position(bus1).title("Bus #1"));
        mMap.addMarker(new MarkerOptions().position(bus2).title("Bus #2"));
        mMap.addMarker(new MarkerOptions().position(bus3).title("Bus #3"));
        mMap.addMarker(new MarkerOptions().position(bus4).title("Bus #4"));

        //Set camera to base locatoin
        mMap.moveCamera(CameraUpdateFactory.newLatLng(oakland));

        //Zoom to street level
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(oakland, 15));

        //Call getBus
        String username = "tester@tester.com";
        String password = "shouldnt_need";
        String type = "login";
        getBus getBus = new getBus(this);
        getBus.execute(type, username, password);
    }

    //Method to reset view to base locatoin
    public void resetMap(View view) {
        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(oakland)      // Sets the center of the map to Mountain View
                .zoom(15)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(00)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    //Datbase connection
    //CAN PROBABLY ONLY HANDLE A SINGLE LINE AS OF 4/2
    public class getBus extends AsyncTask<String, Void, String> {
        Context context;
        getBus(Context ctx){
            context = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String login_url = "http://www.secs.oakland.edu/~ksmith/trackBus.php";

            try{
                String useremail = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("useremail","UTF-8")+"="+URLEncoder.encode(useremail,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }   catch (MalformedURLException e) {
                e.printStackTrace();
            }   catch (IOException e) {
                e.printStackTrace();
            }



            return null;


        }


        @Override
        protected void onPreExecute() {
            //PreExecute tasks
        }

        //Convert PHP output to LatLng var and create marker
        @Override
        protected void onPostExecute(String result) {

            String[] latlong =  result.split(",");
            double latitude = Double.parseDouble(latlong[0]);
            double longitude = Double.parseDouble(latlong[1]);

            LatLng location = new LatLng(latitude, longitude);

            mMap.addMarker(new MarkerOptions().position(location).title("Bus #5"));

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }

}
