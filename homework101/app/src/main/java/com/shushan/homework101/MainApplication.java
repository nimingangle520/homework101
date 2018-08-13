package com.shushan.homework101;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {
    protected static MainApplication mainApplication = null;
    /** 上下文 */
    protected Context mContext          = null;
    /** Activity 栈 */
    public ActivityStack mActivityStack = null;

    @Override
    public void onCreate() {
        super.onCreate();
        // 由于Application类本身已经单例，所以直接按以下处理即可
        mainApplication = this;
        mContext = getApplicationContext();     // 获取上下文
        CrashHandler catchHandler = CrashHandler.getInstance();
        catchHandler.init(getApplicationContext());
        initConfiguration();
    }

    /**
     * 获取当前类实例对象
     * @return
     */
    public static MainApplication getInstance(){
        return mainApplication;
    }

    /**
     * @description 初始化配置文件
     *
     * @return void
     */
    private void initConfiguration() {

    }
}
