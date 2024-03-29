CountDownTask
=============

CountDownTask is a countdown library. It can work with a single view or a
adapter view, etc.

![](https://raw.githubusercontent.com/zhjl37/CountDownTask/master/screen_record_1.gif) ![](https://raw.githubusercontent.com/zhjl37/CountDownTask/master/screen_record_2.gif) ![](https://raw.githubusercontent.com/zhjl37/CountDownTask/master/screen_record_3.gif)

Gradle
------

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
implementation 'com.zhjl37.countdowntask:countdowntask:1.0.0'
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Usage
-----

Create a countdown task:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
CountDownTask countDownTask = CountDownTask.create()
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Build the target time since the system was booted, and include deep sleep. For
example, 6 seconds later:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
long targetMillis = CountDownTask.elapsedRealtime() + 1000 * 60;
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Apply the countdown to a view, e.g. a TextView, and 1 second countdown interval:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
final int CD_INTERVAL = 1000;

countDownTask.until(textView, targetMillis, CD_INTERVAL, new OnCountDownListener() {
    @Override
    public void onTick(View view, long millisUntilFinished) {
        ((TextView)view).setText(String.valueOf(millisUntilFinished / CD_INTERVAL));
    }
    @Override
    public void onFinish(View view) {
        ((TextView)view).setText("DONE.");
    }
});
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

When the count task is no longer needed, cancel it:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
countDownTask.cancel(textView);
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

or

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
countDownTask.cancel();
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

