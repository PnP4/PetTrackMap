package com.ucsc.pnp.pettrack;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by nrv on 9/17/16.
 */
public class NetService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

         super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }
}
