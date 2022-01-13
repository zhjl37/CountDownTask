package com.zhjl37.countdowntask.samples;

import android.view.View;
import android.widget.AbsListView;

public class GridActivity extends AbsListActivity {
    private static final String TAG = GridActivity.class.getSimpleName();

    @Override
    protected AbsListView createListView() {
        return (AbsListView) View.inflate(this, R.layout.grid_content, null);
    }
}
