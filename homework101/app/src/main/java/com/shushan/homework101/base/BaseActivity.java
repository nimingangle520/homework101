package com.shushan.homework101.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.ViewConfiguration;
import android.view.Window;

import com.shushan.homework101.ActivityStack;
import com.shushan.homework101.R;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

public abstract class BaseActivity extends Activity {

	protected Context mContext			= null;
	private AlertDialog mAlertDialog;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		mContext = this;
        ActivityStack.getActivityStack().addActivity(this);
		setOverflowShowingAlways();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		initContentView();
		ButterKnife.bind(this);
		initViews();
		initEvents();
		initData();
	}

	/**
	 * 设置总是显示溢出菜单
	 *
	 * @return void
	 * @date 2015-7-25 12:01:31
	 */
	private void setOverflowShowingAlways() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
	}

	public void finish() {
		super.finish();
	}

	/**
	 * 初始化布局
	 */
	protected abstract void initContentView();

	/**
	 * 初始化View
	 */
	protected abstract void initViews();

	/**
	 * 初始化事件
	 */
	protected abstract void initEvents();

	/**
	 * 初始化
	 */
	protected abstract void initData();

	/**
	 * 请求权限
	 *
	 * 如果权限被拒绝过，则提示用户需要权限
	 */
	protected void requestPermission(final String permission, String rationale, final int requestCode) {
		if (shouldShowRequestPermissionRationale(permission)) {
			showAlertDialog(getString(R.string.permission_title_rationale), rationale,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							requestPermissions(new String[]{permission}, requestCode);
						}
					}, getString(R.string.label_ok), null, getString(R.string.label_cancel));
		} else {
			requestPermissions(new String[]{permission}, requestCode);
		}
	}
	/**
	 * 显示指定标题和信息的对话框
	 *
	 * @param title                         - 标题
	 * @param message                       - 信息
	 * @param onPositiveButtonClickListener - 肯定按钮监听
	 * @param positiveText                  - 肯定按钮信息
	 * @param onNegativeButtonClickListener - 否定按钮监听
	 * @param negativeText                  - 否定按钮信息
	 */
	protected void showAlertDialog(@Nullable String title, @Nullable String message,
                                   @Nullable DialogInterface.OnClickListener onPositiveButtonClickListener,
                                   @NonNull String positiveText,
                                   @Nullable DialogInterface.OnClickListener onNegativeButtonClickListener,
                                   @NonNull String negativeText) {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(positiveText, onPositiveButtonClickListener);
		builder.setNegativeButton(negativeText, onNegativeButtonClickListener);
		mAlertDialog = builder.show();
	}
	public  static void startActivitys(Context context,Class<?> tClass){
		Intent intent=new Intent(context,tClass);
		context.startActivity(intent);

	}
	public static void startActivityWithBundle(Context context, Class<?> tClass,Bundle bundle){
		Intent intent=new Intent(context,tClass);
		intent.putExtras(bundle);
		context.startActivity(intent);

	}

}
