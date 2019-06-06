package com.github.mo0n1andin.samples;

import android.view.View;
import android.widget.AbsListView;

public class ListActivity extends AbsListActivity {
    private static final String TAG = ListActivity.class.getSimpleName();

    @Override
    protected AbsListView createListView() {
        return (AbsListView) View.inflate(this, R.layout.list_content, null);
    }
}
