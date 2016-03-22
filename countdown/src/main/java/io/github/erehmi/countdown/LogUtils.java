package io.github.erehmi.countdown;

import android.util.Log;

/**
 * @author WhatsAndroid
 */
class LogUtils {
    private static final String TAG = "CountDown";
    private static final boolean DEBUG = false;

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }
}
