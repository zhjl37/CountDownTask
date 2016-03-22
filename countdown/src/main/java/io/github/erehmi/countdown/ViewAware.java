package io.github.erehmi.countdown;

import android.view.View;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author WhatsAndroid
 */
class ViewAware {
    private static final String TAG = "ViewAware";

    protected Reference<View> mViewRef;

    public ViewAware(View view) {
        if (view == null) throw new IllegalArgumentException("view must not be null");
        mViewRef = new WeakReference<View>(view);
    }

    public View getWrappedView() {
        return mViewRef.get();
    }

    public boolean isCollected() {
        return mViewRef.get() == null;
    }

    public int getId() {
        View view = mViewRef.get();
        return view == null ? super.hashCode() : view.hashCode();
    }

}
