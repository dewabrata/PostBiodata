package com.juaracodinglima.postbiodata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.location.aravind.getlocation.GeoLocator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import ir.androidexception.andexmaphelper.AndExMapHelper;

public class TestTimeStamp extends AppCompatActivity  implements OnMapReadyCallback, LocationListener {

    String jam = "1600140708";
    //GeoLocator geoLocator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_time_stamp);
      //  geoLocator = new GeoLocator(getApplicationContext(),TestTimeStamp.this);

        Date d1 = new Date(Long.valueOf(jam) * 1000);
        Date d2 = new Date(System.currentTimeMillis());

        findDifference(d1,d2);

        SimpleDateFormat sdf
                = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss");
        Date d3 =null;
        try {
             d3 = sdf.parse("20-09-2020 12:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        findDifference(d2,d3);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // initialize map
        AndExMapHelper.Init(this, googleMap,TestTimeStamp.this, 0, 100);

        // define statuses and related marker icons for markers
        /*AndExMapHelper.setStatuses(new HashMap<String, Integer>(){{
            put("status_1", R.drawable.marker1);
            put("status_2", R.drawable.marker2);
            put("status_3", R.drawable.marker3);
        }});*/
        LatLng L1 = new LatLng(35.730991,51.344566);
        AndExMapHelper.addMarker(this,AndExMapHelper.locationInstance(L1.latitude,L1.longitude), "Title 1", "Snippet 1", "Test");
        // get current location and move camera to it
        AndExMapHelper.animateCamera(AndExMapHelper.getCurrentLocation(),AndExMapHelper.DEFAULT_ZOOM);

        // set info windows adapter
        AndExMapHelper.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                LayoutInflater inflater = (LayoutInflater) TestTimeStamp.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.item_marker_location, null);
                TextView tvTitle   = rowView.findViewById(R.id.tvMarkerTitle);
                TextView tvSnippet = rowView.findViewById(R.id.tvMarkerSnippet);
                tvTitle.setText(marker.getTitle());
                tvSnippet.setText(marker.getSnippet());
                return rowView;
            }
        });
    }


    @Override
    public void onLocationChanged(Location location) {
        // show current location on map with arbitary title, snippet and icon
        AndExMapHelper.showCurrentLocationOnMap(this,"Title", "Snippet", R.drawable.ic_current_location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    // Function to print difference in
        // time start_date and end_date
        public  void findDifference(Date d1,
                                   Date d2) {
            // SimpleDateFormat converts the
            // string format to date object



                // parse method is used to parse
                // the text from a string to
                // produce the date

                // Calucalte time difference
                // in milliseconds
                long difference_In_Time
                        = d2.getTime() - d1.getTime();

                // Calucalte time difference in seconds,
                // minutes, hours, years, and days
                long difference_In_Seconds
                        = TimeUnit.MILLISECONDS
                        .toSeconds(difference_In_Time)
                        % 60;

                long difference_In_Minutes
                        = TimeUnit
                        .MILLISECONDS
                        .toMinutes(difference_In_Time)
                        % 60;

                long difference_In_Hours
                        = TimeUnit
                        .MILLISECONDS
                        .toHours(difference_In_Time)
                        % 24;

                long difference_In_Days
                        = TimeUnit
                        .MILLISECONDS
                        .toDays(difference_In_Time)
                        % 365;

                long difference_In_Years
                        = TimeUnit
                        .MILLISECONDS
                        .toDays(difference_In_Time)
                        / 365l;

                // Print the date difference in
                // years, in days, in hours, in
                // minutes, and in seconds
            Log.d( "Days" , String.valueOf(difference_In_Days));
            Log.d( "hour" , String.valueOf(difference_In_Hours));
            Log.d( "minutes" , String.valueOf(difference_In_Minutes));
            Log.d( "seconds" , String.valueOf(difference_In_Seconds));




        }
}
