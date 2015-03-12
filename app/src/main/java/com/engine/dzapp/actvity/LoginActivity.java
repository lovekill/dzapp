package com.engine.dzapp.actvity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.engine.dzapp.R;
import com.engine.dzapp.view.GestureLockViewGroup;
import mehdi.sakout.fancybuttons.FancyButton;

public class LoginActivity extends Activity{
    @InjectView(R.id.account)
    EditText account;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.login)
    FancyButton login;
    @InjectView(R.id.loginLayout)
    LinearLayout loginLayout;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.login)
    public void login(View view) {
        Intent intent = new Intent(this,GePasswordActivity.class);
        startActivity(intent);
    }
}
