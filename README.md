CountDownTask
=============

CountDownTask is a countdown library. It can work with a single view or a
adapter view, etc.

中文: <http://erehmi.github.io/android-case-abslistview-count-down>

![](https://raw.githubusercontent.com/erehmi/CountDownTask/master/screen_record_1.gif)

![](https://raw.githubusercontent.com/erehmi/CountDownTask/master/screen_record_2.gif)

![](https://raw.githubusercontent.com/erehmi/CountDownTask/master/screen_record_3.gif)

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

-   [SimpleActivity](https://github.com/erehmi/CountDownTask/blob/master/samples/src/main/java/io/github/erehmi/samples/SimpleActivity.java)

-   [ListActivity](https://github.com/erehmi/CountDownTask/blob/master/samples/src/main/java/io/github/erehmi/samples/ListActivity.java)

-   [GridActivity](https://github.com/erehmi/CountDownTask/blob/master/samples/src/main/java/io/github/erehmi/samples/GridActivity.java)

License
-------

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Copyright 2016, erehmi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
