package io.github.erehmi.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.erehmi.countdown.CountDownTask;

public class GridActivity extends AbsListActivity {
    private static final String TAG = GridActivity.class.getSimpleName();

    @Override
    protected AbsListView createListView() {
        return (AbsListView) View.inflate(this, R.layout.grid_content, null);
    }
}
