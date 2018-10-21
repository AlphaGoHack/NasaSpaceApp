package com.digitaldisaster.www.digitaldisaster;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by ASPIRE on 10/20/2018.
 */

public class BackgroundRequests extends Service {
    private OkHttpClient okHttpClient=new OkHttpClient();
    private Request request;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public BackgroundRequests() {
        super();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"hii");
// Vibrate for 500 milliseconds
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }

        String url="https://earthquake.usgs.gov/fdsnws/event/1/count?format=geojson&starttime=2018-10-06&minmagnitude=5";
        request= new Request.Builder().url(url).build();
        Log.i(TAG,"it reached 1");
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    Log.i(TAG,"it reached");
                    Log.e(TAG, response.body().string());

                }

            }
        });
        */
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
