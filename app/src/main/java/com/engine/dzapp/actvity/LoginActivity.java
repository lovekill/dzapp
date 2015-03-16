package com.engine.dzapp.actvity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.engine.dzapp.DaoManager;
import com.engine.dzapp.R;
import com.engine.dzapp.dao.UserTable;
import com.engine.dzapp.dao.UserTableDao;
import com.engine.dzapp.view.GestureLockViewGroup;
import mehdi.sakout.fancybuttons.FancyButton;

import java.util.Date;
import java.util.List;

public class LoginActivity extends Activity {
    @InjectView(R.id.account)
    EditText account;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.login)
    FancyButton login;
    @InjectView(R.id.loginLayout)
    LinearLayout loginLayout;
    private UserTableDao mUserDao;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.inject(this);
        mUserDao = DaoManager.getDaoSession().getUserTableDao();
        List<UserTable> list = mUserDao.loadAll();
        if (list != null && !list.isEmpty()) {
            Intent intent = new Intent(this, GePasswordActivity.class);
            intent.putExtra(GePasswordActivity.KEY_ACCOUTN, list.get(0).getUserName());
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.login)
    public void login(View view) {
        if (validata()) {
            UserTable userTable = new UserTable();
            userTable.setUserName(account.getText().toString());
            userTable.setPassword(password.getText().toString());
            userTable.setLoginTime(new Date());
            mUserDao.insertOrReplace(userTable);
            Intent intent = new Intent(this, GePasswordActivity.class);
            intent.putExtra(GePasswordActivity.KEY_ACCOUTN, account.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validata() {
        if (TextUtils.isEmpty(account.getText().toString())) {
            return false;
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            return false;
        }
        return true;
    }
}
