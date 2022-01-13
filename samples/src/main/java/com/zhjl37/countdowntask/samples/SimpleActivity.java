package com.zhjl37.countdowntask.samples;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zhjl37.countdowntask.CountDownTask;
import com.zhjl37.countdowntask.CountDownTimers.OnCountDownListener;

public class SimpleActivity extends AppCompatActivity implements OnCountDownListener {
    private static final String TAG = SimpleActivity.class.getSimpleName();

    private TextView mCountDownTextView;
    private long mDeadlineMillis;

    private CountDownTask mCountDownTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_text);

        mCountDownTextView = (TextView) findViewById(R.id.count_down);
        mDeadlineMillis = CountDownTask.elapsedRealtime() + 1000 * 60;
    }

    @Override
    protected void onResume() {
        super.onResume();

        mCountDownTask = CountDownTask.create()
                .until(mCountDownTextView, mDeadlineMillis, 1000, this);
    }

    @Override
    public void onTick(View view, long millisUntilFinished) {
        mCountDownTextView.setText(String.valueOf(millisUntilFinished / 1000));
    }

    @Override
    public void onFinish(View view) {
        mCountDownTextView.setText("DONE.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCountDownTask.cancel();
    }
}
