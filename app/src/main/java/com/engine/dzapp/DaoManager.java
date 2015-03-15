package com.engine.dzapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.engine.dzapp.dao.DaoMaster;
import com.engine.dzapp.dao.DaoSession;

/**
 * Created by cat1412266 on 15/3/15.
 */
public class DaoManager {
    private Context mContext ;
    private static DaoSession mDaoSession ;
    private static DaoManager instance;
    public static DaoManager getInstance(){
        if(instance==null){
            instance = new DaoManager() ;
        }
        return instance ;
    }
    private DaoManager(){
    }
    public void init(Context context){
        this.mContext = context ;
        SQLiteDatabase db = new DaoMaster.DevOpenHelper(context, "dz", null).getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession= daoMaster.newSession();
    }
    public static DaoSession getDaoSession(){
        return  mDaoSession ;
    }



}
