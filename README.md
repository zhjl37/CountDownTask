CountDownTask
=============

CountDownTask is a countdown library. It can work with a single view or a
adapter view, etc.

![](https://raw.githubusercontent.com/mo0n1andin/CountDownTask/master/screen_record_1.gif) ![](https://raw.githubusercontent.com/mo0n1andin/CountDownTask/master/screen_record_2.gif) ![](https://raw.githubusercontent.com/mo0n1andin/CountDownTask/master/screen_record_3.gif)

Gradle
------

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
compile 'com.github.erehmi:countdowntask:1.0.1'
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

Example
-------

-   [SimpleActivity](https://github.com/mo0n1andin/CountDownTask/blob/master/samples/src/main/java/io/github/mo0n1andin/samples/SimpleActivity.java)

-   [ListActivity](https://github.com/mo0n1andin/CountDownTask/blob/master/samples/src/main/java/io/github/mo0n1andin/samples/ListActivity.java)

-   [GridActivity](https://github.com/mo0n1andin/CountDownTask/blob/master/samples/src/main/java/io/github/mo0n1andin/samples/GridActivity.java)

