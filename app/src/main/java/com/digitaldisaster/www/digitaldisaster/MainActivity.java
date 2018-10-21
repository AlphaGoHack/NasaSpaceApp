package com.digitaldisaster.www.digitaldisaster;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity  implements LocationListener {
    double latitudes,longitudes;
    int count=0,global=0;
    void checkCount(String ur, final double lat, final double lon){
        Date c = Calendar.getInstance().getTime();
        OkHttpClient okHttpClient=new OkHttpClient();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);
        final String url=ur+formattedDate+"&latitude="+lat+"&longitude="+lon;
        Request request= new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    Log.i(TAG,"it reached");

                    Log.e(TAG,url);
                    String stringResponse = response.body().string();
                    Log.e(TAG,stringResponse);
                    char value=stringResponse.charAt(9);
                    if(value!='0') {

                        getCheckList("http://saphenous-nomenclat.000webhostapp.com/index.php?lat=" + lat + "&long=" + lon);

                    }

                    else{

                        Log.e(TAG,String.valueOf(value)+" "+String.valueOf(stringResponse.charAt(9)));
                    }
                }
                else
                {   Log.i(TAG,url);
                    Log.e(TAG,url);

                }

            }
        });


    }
    void getCheckList(String url) {
        Intent intent = new Intent(MainActivity.this, CheckListActivity.class);
        intent.putExtra("message", url);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    LocationManager locationManager;

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
        getLocation();
        WebView webview=(WebView)findViewById(R.id.website);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_IF_CONTENT_SCROLLS);
        webview.loadUrl("http://saphenous-nomenclat.000webhostapp.com/homepage.php");


    }

    @Override
    public void onLocationChanged(Location location) {
        latitudes=location.getLatitude();
        longitudes=location.getLongitude();
        Toast.makeText(getApplicationContext(), String.valueOf(location.getLatitude()) + " " + String.valueOf(location.getLongitude()), Toast.LENGTH_LONG).show();
        if(global!=0)
        {
            global++;
            checkCount("https://earthquake.usgs.gov/fdsnws/event/1/count?format=geojson&minmagnitude=3&maxradius=100&starttime=",location.getLatitude(), location.getLongitude());
        }

    }


    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }


}
