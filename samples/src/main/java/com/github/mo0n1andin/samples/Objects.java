package com.github.mo0n1andin.samples;

/**
 * @author WhatsAndroid
 */
public class Objects {
    private static final String TAG = "Objects";

    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }
}
