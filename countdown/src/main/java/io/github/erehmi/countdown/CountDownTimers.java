package io.github.erehmi.countdown;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WhatsAndroid
 */
public class CountDownTimers {
    private static final String TAG = "CountDownTimers";

    private final long mCountDownInterval;
    private long mMaxMillis;

    private CountDownTimer mCountDownTimer;

    public interface OnCountDownListener {
        void onTick(View view, long millisUntilFinished);

        void onFinish(View view);
    }

    private static class CountDownInfo {
        ViewAware viewAware;
        long millis;
        OnCountDownListener listener;

        public CountDownInfo(ViewAware viewAware, long millis, OnCountDownListener listener) {
            this.viewAware = viewAware;
            this.millis = millis;
            this.listener = listener;
        }
    }

    private SparseArray<CountDownInfo> mCountDownInfoSparseArray;

    CountDownTimers(long countDownInterval) {
        mCountDownInterval = countDownInterval;
    }

    public long getMaxMillis() {
        return mMaxMillis;
    }

    public long getCountDownInterval() {
        return mCountDownInterval;
    }

    protected void until(View view, long millis, OnCountDownListener listener) {
        ViewAware viewAware = new ViewAware(view);

        // NOTICE THAT:
        // Because of the mechanism of CountDownTimer and
        // the time interval error of Handler#sendMessageDelayed(Message, long),
        // the target millis must be adjusted.
        long adjustMillis = adjustTargetMillis(millis);
        CountDownInfo countDownInfo = new CountDownInfo(viewAware, adjustMillis, listener);
        ensureCountDownInfoSparseArray();

        long currentMillis = SystemClock.elapsedRealtime();
        int id = viewAware.getId();
        if (doOnTickOrFinish(countDownInfo, currentMillis)) {
            mCountDownInfoSparseArray.remove(id);
            return;
        }

        mCountDownInfoSparseArray.append(id, countDownInfo);

        long millisInFuture = adjustMillis - currentMillis;
        if ((millisInFuture > 0) && (adjustMillis > mMaxMillis)) {
            LogUtils.d("create CountDownTimer: " + millisInFuture);

            mMaxMillis = adjustMillis;
            cancelCountDownTimer();

            mCountDownTimer = new CountDownTimer(millisInFuture, mCountDownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    LogUtils.d("CountDownTimer#onTick() # millisUntilFinished: " + millisUntilFinished);
                    doOnTick(millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    LogUtils.d("onFinish()");
                    doOnFinish();
                }
            }.start();
        }
    }

    private void ensureCountDownInfoSparseArray() {
        if (mCountDownInfoSparseArray == null) {
            mCountDownInfoSparseArray = new SparseArray<>();
        }
    }

    // reduce error for the last one
    private long adjustTargetMillis(long millis) {
        return millis + mCountDownInterval - 1;
    }

    private void doOnTick(long maxMillisUntilFinished) {
        if (mCountDownInfoSparseArray != null) {
            List<CountDownInfo> removeList = new ArrayList<>();
            long currentMillis = SystemClock.elapsedRealtime();
            for (int i = 0; i < mCountDownInfoSparseArray.size(); i++) {
                CountDownInfo countDownInfo = mCountDownInfoSparseArray.valueAt(i);
                if (doOnTickOrFinish(countDownInfo, currentMillis)) {
                    removeList.add(countDownInfo);
                }
            }

            if (!removeList.isEmpty()) {
                for (CountDownInfo countDownInfo : removeList) {
                    int id = countDownInfo.viewAware.getId();
                    mCountDownInfoSparseArray.remove(id);
                }
            }
        }
    }

    private boolean doOnTickOrFinish(CountDownInfo countDownInfo, long currentMillis) {
        LogUtils.d("doOnTickOrFinish() # id: " + countDownInfo.viewAware.getId());

        long targetMillis = countDownInfo.millis;
        long deltaMillis = targetMillis - currentMillis;
        if (targetMillis > currentMillis && deltaMillis > mCountDownInterval) {
            doOnTick(countDownInfo, currentMillis);
            return false;
        } else {
            doOnFinish(countDownInfo);
            return true;
        }
    }

    private void doOnTick(CountDownInfo countDownInfo, long currentMillis) {
        long millis = countDownInfo.millis;
        OnCountDownListener listener = countDownInfo.listener;

        ViewAware viewAware = countDownInfo.viewAware;
        View view = viewAware.getWrappedView();
        if (millis > currentMillis && view != null && listener != null) {
            listener.onTick(view, millis - currentMillis);
        }
    }

    private void doOnFinish(CountDownInfo countDownInfo) {
        ViewAware viewAware = countDownInfo.viewAware;
        int id = viewAware.getId();
        LogUtils.d("doOnFinish() # id: " + id);

        View view = viewAware.getWrappedView();
        OnCountDownListener listener = countDownInfo.listener;
        if (view != null && listener != null) {
            listener.onFinish(view);
        }
    }

    private void doOnFinish() {
        if (mCountDownInfoSparseArray != null) {
            for (int i = 0; i < mCountDownInfoSparseArray.size(); i++) {
                CountDownInfo countDownInfo = mCountDownInfoSparseArray.valueAt(i);
                doOnFinish(countDownInfo);
            }
            resetCountDownInfos();
        }
    }

    protected void cancel(View view) {
        ViewAware viewAware = new ViewAware(view);
        int id = viewAware.getId();
        if (mCountDownInfoSparseArray != null) {
            CountDownInfo countDownInfo = mCountDownInfoSparseArray.get(id);
            if (countDownInfo != null) {
                mCountDownInfoSparseArray.remove(id);
            }
        }
    }

    protected void cancel() {
        resetCountDownInfos();
        cancelCountDownTimer();
    }

    private void resetCountDownInfos() {
        if (mCountDownInfoSparseArray != null) {
            mCountDownInfoSparseArray.clear();
            mCountDownInfoSparseArray = null;
        }
    }

    private void cancelCountDownTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }
}
