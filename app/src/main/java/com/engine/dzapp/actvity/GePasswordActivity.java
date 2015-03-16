package com.engine.dzapp.actvity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.engine.dzapp.DaoManager;
import com.engine.dzapp.R;
import com.engine.dzapp.dao.UserTable;
import com.engine.dzapp.dao.UserTableDao;
import com.engine.dzapp.view.GestureLockViewGroup;
import com.engine.dzapp.view.GestureLockViewGroup.OnGestureLockViewListener;

import java.util.List;

public class GePasswordActivity extends Activity implements OnGestureLockViewListener {

    @InjectView(R.id.id_gestureLockViewGroup)
    GestureLockViewGroup idGestureLockViewGroup;
    @InjectView(R.id.tips)
    TextView tips;
    private UserTableDao mUserDao ;
    private int[] password;
    private UserTable mUser ;
    public static final String KEY_ACCOUTN="account" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge_password);
        ButterKnife.inject(this);
        idGestureLockViewGroup.setOnGestureLockViewListener(this);
        mUserDao = DaoManager.getDaoSession().getUserTableDao() ;
        String account = getIntent().getStringExtra(KEY_ACCOUTN);
        mUser = mUserDao.load(account);
        initGePass();
    }
    private void initGePass(){
        if(TextUtils.isEmpty(mUser.getGespassword())){
            tips.setText(R.string.settingpass);
            idGestureLockViewGroup.setSetting(true);
        }else {
            String passwordString = mUser.getGespassword();
            String[] p = passwordString.split(",") ;
            password = new int[p.length] ;
            for (int i=0;i<p.length;i++){
                password[i]=Integer.parseInt(p[i]) ;
            }
            idGestureLockViewGroup.setAnswer(password);
            tips.setText(R.string.ge_password);
            idGestureLockViewGroup.setSetting(false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_ge_password_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBlockSelected(int cId) {

    }

    @Override
    public void onGestureEvent(boolean matched) {
        if (matched) {
            Intent intent = new Intent(this,MainActivity.class) ;
            startActivity(intent);
            finish();
        }else {
        }
    }

    @Override
    public void onUnmatchedExceedBoundary() {
        Toast.makeText(this, "手势密码错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSetingEnd(List<Integer> pos) {
        if (password == null) {
            password = new int[pos.size()];
            for (int i = 0; i < pos.size(); i++) {
                password[i] = pos.get(i);
            }
            idGestureLockViewGroup.reset();
            tips.setText(R.string.settingpass_again);
        } else {
            if (checkAnswer(pos)) {
                Toast.makeText(this, "密码设置成功"+pos.toArray().toString(), Toast.LENGTH_SHORT).show();
                idGestureLockViewGroup.reset();
                idGestureLockViewGroup.setAnswer(password);
                idGestureLockViewGroup.setSetting(false);
                tips.setText(R.string.ge_password);
                StringBuilder sb = new StringBuilder();
                for (int po:password){
                   sb.append(po).append(",");
                }
                mUser.setGespassword(sb.toString());
                mUserDao.update(mUser);
                Log.e("GePas",mUser.getGespassword()) ;
                Log.e("GePas",sb.toString()) ;
            } else {
                Toast.makeText(this, "密码设置失败,和原密码不同", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkAnswer(List<Integer> mChoose) {
        if (password.length != mChoose.size())
            return false;

        for (int i = 0; i < password.length; i++) {
            if (password[i] != mChoose.get(i))
                return false;
        }

        return true;
    }
}
