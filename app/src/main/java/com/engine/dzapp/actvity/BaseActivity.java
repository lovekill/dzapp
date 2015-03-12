package com.engine.dzapp.actvity;

import android.os.Bundle;
import android.os.PersistableBundle;
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

    public void addFragment(BaseFragment f, int id, boolean isAddToBackStack) {
        String tag = f.getClass().getSimpleName();
        if (baseFragmentManager.findFragmentByTag(tag) == null) {
            final FragmentTransaction ft = baseFragmentManager.beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.add(id, f, tag);
            if (isAddToBackStack) {
                ft.addToBackStack(tag);
            }
            ft.commit();
        }
    }

}
