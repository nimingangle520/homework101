package com.shushan.homework101;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;


public class ActivityStack {

    private static Stack<Activity> activityStack;
    private static ActivityStack instance;
    private ActivityStack(){}

    /**     * 单一实例     */
    public static ActivityStack getActivityStack(){
        if(instance==null){
            instance=new ActivityStack();
        }
        return instance;
    }

    /**     * 添加Activity到堆栈     */
    public void addActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**     * 获取当前Activity（堆栈中最后一个压入的）     */
    public Activity currentActivity(){
        Activity activity=activityStack.lastElement();
        return activity;
    }

    /**     * 结束当前Activity（堆栈中最后一个压入的）     */
    public void finishActivity(){
        Activity activity=activityStack.lastElement();
        if(activity!=null){
            activity.finish();
            activity=null;
        }
    }

    /**     * 结束指定的Activity     */
    public void finishActivity(Activity activity){
        if(activity!=null){
            activityStack.remove(activity);
            activity.finish();
            activity=null;
        }
    }

    /**     * 结束指定类名的Activity     */
    public void finishActivity(Class<?> cls){
        for (Activity activity : activityStack) {
            if(activity.getClass().equals(cls) ){
                finishActivity(activity);
            }
        }
    }

    /**     * 结束所有Activity     */
    public void finishAllActivity(){
        for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**     * 退出应用程序     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {

        }
    }

}
