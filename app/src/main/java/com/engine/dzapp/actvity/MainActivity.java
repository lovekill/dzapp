package com.engine.dzapp.actvity;

import android.os.Bundle;
import android.widget.*;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.engine.dzapp.R;

/**
 * Created by engine on 15/3/12.
 */
public class MainActivity extends BaseActivity {
    @InjectView(R.id.event)
    TextView event;
    @InjectView(R.id.price)
    TextView price;
    @InjectView(R.id.pass)
    TextView pass;
    @InjectView(R.id.work)
    TextView work;
    @InjectView(R.id.other)
    TextView other;
    @InjectView(R.id.tablayout)
    LinearLayout tablayout;
    @InjectView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @InjectView(android.R.id.tabs)
    TabWidget tabs;
    @InjectView(R.id.tabHost)
    TabHost tabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        initTabHost();
    }

    private void initTabHost() {
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("").setIndicator("").setContent(R.id.eventFragment));
        tabHost.addTab(tabHost.newTabSpec("").setIndicator("").setContent(R.id.priceFragment));
        tabHost.addTab(tabHost.newTabSpec("").setIndicator("").setContent(R.id.passFragment));
        tabHost.addTab(tabHost.newTabSpec("").setIndicator("").setContent(R.id.workFragment));
        tabHost.addTab(tabHost.newTabSpec("").setIndicator("").setContent(R.id.otherFragment));
    }


}
