package com.pusun.pusuntennis;

import android.app.Application;
import com.pusun.pusuntennis.utils.dao.DaoMaster;
import com.pusun.pusuntennis.utils.dao.DaoSession;

/* loaded from: classes3.dex */
public class MyApplication extends Application {
    public static String BASE_URL = "https://www.pusuntech.com/";
    private static MyApplication mApp;
    private static DaoSession mDaoSession;
    public static int machineNum;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        mApp = this;
        initGreenDao();
    }

    private void initGreenDao() {
        mDaoSession = new DaoMaster(new DaoMaster.DevOpenHelper(mApp, "mydb.db").getWritableDatabase()).newSession();
    }

    public static DaoSession getmDaoSession() {
        return mDaoSession;
    }
}
