package com.engine.dzapp.actvity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.engine.dzapp.fragment.BaseFragment;

/**
 * Created by engine on 15/3/12.
 */
public class BaseActivity extends FragmentActivity {
    private FragmentManager baseFragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseFragmentManager = getSupportFragmentManager();

    }

    public void addFragment(BaseFragment f, boolean isAddToBackStack) {
        if (getSupportFragmentManager().findFragmentByTag(f.toString()) == null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, f);
            if (isAddToBackStack) {
                ft.addToBackStack(f.toString());
            }
            ft.commit();
        }

    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

}
