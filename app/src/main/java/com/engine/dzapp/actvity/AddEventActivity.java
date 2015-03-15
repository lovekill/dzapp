package com.engine.dzapp.actvity;

import android.os.Bundle;
import com.engine.dzapp.fragment.AddEventFragment;

/**
 * Created by cat1412266 on 15/3/15.
 */
public class AddEventActivity extends BaseActivity {
    public static final String DATA="data";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AddEventFragment addEventFragment = new AddEventFragment();
        Bundle bundle = getIntent().getExtras();
        addEventFragment.setArguments(bundle);
        addFragment(addEventFragment, true);
    }
}
