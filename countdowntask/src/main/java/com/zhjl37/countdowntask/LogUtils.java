package com.zhjl37.countdowntask;

import android.util.Log;

class LogUtils {
    private static final String TAG = "CountDown";
    private static final boolean DEBUG = false;

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }
}
