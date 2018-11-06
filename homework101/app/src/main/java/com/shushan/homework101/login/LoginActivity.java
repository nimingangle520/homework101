package com.shushan.homework101.login;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.homework101.Constants;
import com.shushan.homework101.HttpHelper.service.entity.register.Login;
import com.shushan.homework101.HttpHelper.service.presenter.LoginPresenter;
import com.shushan.homework101.HttpHelper.service.view.LoginView;
import com.shushan.homework101.MainActivity;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.Utils.Utils;
import com.shushan.homework101.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.bt_login)
    Button bt_login;
    @Bind(R.id.tv_login_register)
    TextView tv_login_register;
    @Bind(R.id.tv_login_forget_password)
    TextView tv_login_forget_password;
    @Bind(R.id.et_login_phone)
    EditText et_login_phone;
    @Bind(R.id.et_login_password)
    EditText et_login_password;
    @Bind(R.id.iv_login_agree)
    ImageView iv_login_agree;
    private boolean isAgree=false;
    private String imei;
    private LoginPresenter loginPresenter;
    private Login mLogin;
    private String phoneNum;
    private String psw;
    private Map<String, String> requestMap;
    private String[] classArrays;

    protected void initContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initViews() {
        et_login_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        et_login_password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        loginPresenter = new LoginPresenter(this);
        loginPresenter.onCreate(Constants.BASE_URL);
        loginPresenter.attachView(loginView);
    }

    @Override
    protected void initEvents() {
        bt_login.setOnClickListener(this);
        tv_login_register.setOnClickListener(this);
        tv_login_forget_password.setOnClickListener(this);
        iv_login_agree.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        requestMap = new HashMap<>();
        classArrays = getResources().getStringArray(R.array.preference_primary_class);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
        String phoneNum=sharedPreferences.getString("phoneNum",getResources().getString(R.string.login_please_input_phone_number));
        if(phoneNum.equals(getResources().getString(R.string.login_please_input_phone_number))){
            et_login_phone.setHint(phoneNum);
        }else{
            et_login_phone.setText(phoneNum);
        }
        et_login_password.setText("");
    }

    private LoginView loginView=new LoginView() {
        @Override
        public void onSuccess(Login login) {
            LogUtils.d(login.toString());
            mLogin=login;
            if(login!=null&&!login.equals("")&&login.getError()==0){
                SharedPreferences sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("phoneNum",phoneNum);
                editor.putString("third_id",mLogin.getData().getThird_id());
                editor.putString("token",mLogin.getData().getToken());
                editor.putString("userid",mLogin.getData().getUserid()+"");
                editor.putString("third_token",mLogin.getData().getThird_token());
                for (int i = 0; i < classArrays.length; i++) {

                    if(mLogin.getData().getGrade().equals(classArrays[i])){

                        editor.putString("grade_position",i+"");
                    }
                }
                editor.commit();
                BaseActivity.startActivitys(LoginActivity.this, MainActivity.class);
            }
        }

        @Override
        public void onError(String result) {
            LogUtils.e(result.toString());
        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_agree:
                if(!isAgree){
                    iv_login_agree.setImageResource(R.drawable.login_agreement_agree);
                    isAgree=true;
                }else{
                    iv_login_agree.setImageResource(R.drawable.login_agreement);
                    isAgree=false;
                }
                break;
            case R.id.bt_login:
                phoneNum = et_login_phone.getText().toString().trim();
                psw = et_login_password.getText().toString().trim();
                if(!TextUtils.isEmpty(phoneNum)&&!TextUtils.isEmpty(psw)&&psw.length()>=6&&isAgree){

                    requestMap.put("mobile",phoneNum);
                    requestMap.put("password", Utils.stringToMD5(psw));
                    requestMap.put("deviceId",getIMEI(LoginActivity.this));
                    requestMap.put("platform","android");
                    loginPresenter.getLoginMsg(requestMap);
                }
                break;
            case R.id.tv_login_register:
                BaseActivity.startActivitys(this,RegisterActivity.class);
                break;
            case R.id.tv_login_forget_password:
                BaseActivity.startActivitys(this,ForgetPasswordActivity.class);
                break;
        }
    }

    /**
     * 获取手机IMEI
     *
     * @param context
     * @return
     */
    public  String getIMEI(Context context) {
        try {
            //实例化TelephonyManager对象
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMEI号
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                this.requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 200);

            }else{
                imei = telephonyManager.getDeviceId();
            }
            //在次做个验证，也不是什么时候都能获取到的啊
            if (imei == null) {
                imei = "";
            }
            return imei;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
}

