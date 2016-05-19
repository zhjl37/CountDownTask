package io.github.erehmi.samples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.erehmi.countdown.CountDownTask;
import io.github.erehmi.countdown.CountDownTimers.OnCountDownListener;

/**
 * @author WhatsAndroid
 */
public class CountDownAdapter extends ArrayAdapter<CountDownInfo> {
    private static final String TAG = "CountDownAdapter";

    private CountDownTask mCountDownTask;

    public CountDownAdapter(Context context, List<CountDownInfo> objects) {
        super(context, 0, objects);
    }

    public void setCountDownTask(CountDownTask countDownTask) {
        if (!Objects.equals(mCountDownTask, countDownTask)) {
            mCountDownTask = countDownTask;
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        final CountDownInfo countDownInfo = getItem(position);
        if (countDownInfo.millis > 0) {
            startCountDown(position, countDownInfo, convertView);
        } else {
            cancelCountDown(position, countDownInfo, convertView);
        }
        return convertView;
    }

    private void startCountDown(final int position, final CountDownInfo countDownInfo, View convertView) {
        if (mCountDownTask != null) {
            mCountDownTask.until(convertView, countDownInfo.millis,
                    countDownInfo.countDownInterval, new OnCountDownListener() {
                        @Override
                        public void onTick(View view, long millisUntilFinished) {
                            doOnTick(position, view, millisUntilFinished, countDownInfo.countDownInterval);
                        }

                        @Override
                        public void onFinish(View view) {
                            doOnFinish(position, view);
                        }
                    });
        }
    }

    private void doOnTick(int position, View view, long millisUntilFinished, long countDownInterval) {
        TextView textView1 = (TextView) view.findViewById(android.R.id.text1);
        textView1.setText(String.valueOf(position));

        TextView textView2 = (TextView) view.findViewById(android.R.id.text2);
        textView2.setText(String.valueOf(millisUntilFinished / countDownInterval));
    }

    private void doOnFinish(int position, View view) {
        TextView textView1 = (TextView) view.findViewById(android.R.id.text1);
        textView1.setText(String.valueOf(position));

        TextView textView2 = (TextView) view.findViewById(android.R.id.text2);
        textView2.setText("DONE.");
    }

    private void cancelCountDown(int position, CountDownInfo countDownInfo, View view) {
        if (mCountDownTask != null) {
            mCountDownTask.cancel(view);
        }

        TextView textView1 = (TextView) view.findViewById(android.R.id.text1);
        textView1.setText(String.valueOf(position));

        TextView textView2 = (TextView) view.findViewById(android.R.id.text2);
        textView2.setText(null);
    }
}
