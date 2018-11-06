package com.shushan.homework101.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.homework101.Constants;
import com.shushan.homework101.HttpHelper.service.entity.register.Captcha;
import com.shushan.homework101.HttpHelper.service.entity.register.Register;
import com.shushan.homework101.HttpHelper.service.entity.register.VerifyCaptcha;
import com.shushan.homework101.HttpHelper.service.presenter.CaptchaPresenter;
import com.shushan.homework101.HttpHelper.service.presenter.RegisterPresenter;
import com.shushan.homework101.HttpHelper.service.presenter.VerifyCaptchaPresenter;
import com.shushan.homework101.HttpHelper.service.view.CaptchaView;
import com.shushan.homework101.HttpHelper.service.view.RegisterView;
import com.shushan.homework101.HttpHelper.service.view.VerifyCaptchaView;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.Utils.Utils;
import com.shushan.homework101.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.btn_register)
    Button btn_register;
    @Bind(R.id.tv_register_send_verification_code)
    TextView tv_register_send_verification_code;
    @Bind(R.id.et_register_phone)
    EditText et_register_phone;
    @Bind(R.id.et_register_verification_code)
    EditText et_register_verification_code;
    @Bind(R.id.et_register_password)
    EditText et_register_password;
    @Bind(R.id.et_register_confirm_password)
    EditText et_register_confirm_password;
    @Bind(R.id.iv_actionbar_left)
    ImageView iv_actionbar_left;
    private String baseUrl;
    private CaptchaPresenter captchaPresenter;
    private RegisterPresenter registerPresenter;
    private Register mRegister;
    private VerifyCaptcha mVerifyCaptcha;
    private Map<String, String> requestMap;
    private String phoneNum;
    private String captcha;
    private String psw;
    private String confirmPsw;
    private String imei;
    private VerifyCaptchaPresenter verifyCaptchaPresenter;
    private String verifyCaptchaResult;
    private String registerResult;

    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initData() {
        requestMap = new HashMap<>();
    }

    @Override
    protected void initViews() {
        tv_actionbar.setVisibility(View.GONE);
        baseUrl = "http://zuoye101.com";
        et_register_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        et_register_verification_code.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        et_register_password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        et_register_confirm_password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        captchaPresenter = new CaptchaPresenter(this);
        captchaPresenter.onCreate(baseUrl);
        captchaPresenter.attachView(captchaView);
        registerPresenter = new RegisterPresenter(this);
        registerPresenter.onCreate(baseUrl);
        registerPresenter.attachView(registerView);
        verifyCaptchaPresenter = new VerifyCaptchaPresenter(this);
        verifyCaptchaPresenter.onCreate(baseUrl);
        verifyCaptchaPresenter.attachView(verifyCaptchaView);
    }

    @Override
    protected void initEvents() {
        btn_register.setOnClickListener(this);
        iv_actionbar_left.setOnClickListener(this);
        tv_register_send_verification_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_actionbar_left:
                startActivitys(this,LoginActivity.class);
                break;
            case R.id.btn_register:
                captcha = et_register_verification_code.getText().toString().trim();
                phoneNum = et_register_phone.getText().toString().trim();
                psw = et_register_password.getText().toString().trim();
                confirmPsw = et_register_confirm_password.getText().toString().trim();

                if(!TextUtils.isEmpty(phoneNum)&&!TextUtils.isEmpty(captcha)){
                    verifyCaptchaPresenter.getVerifyCaptchaMsg(phoneNum,captcha);
                    WaitVerifyCaptchaThread waitVerifyCaptchaThread=new WaitVerifyCaptchaThread();
                    waitVerifyCaptchaThread.start();
                }else if(TextUtils.isEmpty(phoneNum)){
                    //todo
                }else if(TextUtils.isEmpty(captcha)){
                    //todo
                }

                break;
            case R.id.tv_register_send_verification_code:
                phoneNum = et_register_phone.getText().toString().trim();
                if(TextUtils.isEmpty(phoneNum)){
                  //todo
                }else{
                    if(isMobileNO(phoneNum)){
                        //cate  register或者findPwd
                        captchaPresenter.getCaptchaMsg(phoneNum,"register");
                    }else{
                        //todo
                    }
                }
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

    /** 判断字符串是否符合手机号码格式
     *
     * *@param str
     *  @return 待检测的字符串
     */
    public static boolean isMobileNO(String mobileNums) {

        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        // "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,
        // \\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }


    private class WaitThread extends Thread {

        boolean isNull = true;

        public WaitThread() {

        }
        @Override
        public void run() {

            while (isNull) {
                if (mRegister != null && !mRegister.equals("")) {
                    isNull = false;
                    if(mRegister.getError()==0){
                        handler.sendEmptyMessage(Constants.REGISTER_STAER_ACTIVITY);
                    }else{
                        //todo
                    }
                }
                if(!TextUtils.isEmpty(registerResult)){
                    isNull=false;
                }
            }
        }
    }
    private class WaitVerifyCaptchaThread extends Thread {

        boolean isNull = true;

        public WaitVerifyCaptchaThread() {
        }
        @Override
        public void run() {

            while (isNull) {
                if (mVerifyCaptcha != null && !mVerifyCaptcha.equals("")) {
                    isNull = false;
                    if(mVerifyCaptcha.getError()==0){
                        handler.sendEmptyMessage(Constants.REGISTER_VERIFY_CAPTCHA);
                    }else{
                        //todo
                    }
                }
                if(!TextUtils.isEmpty(verifyCaptchaResult)){
                    isNull=false;
                }

            }
        }
    }
    private CaptchaView captchaView = new CaptchaView() {
        @Override
        public void onSuccess(Captcha captcha) {
            LogUtils.d(captcha.toString());
        }

        @Override
        public void onError(String result) {
            LogUtils.e(result);
        }
    };

    private VerifyCaptchaView verifyCaptchaView = new VerifyCaptchaView() {
        @Override
        public void onSuccess(VerifyCaptcha verifyCaptcha) {
            LogUtils.d(verifyCaptcha.toString());
            mVerifyCaptcha = verifyCaptcha;

        }

        @Override
        public void onError(String result) {
            LogUtils.e(result);
            verifyCaptchaResult = result;
        }
    };
    private RegisterView registerView = new RegisterView() {
        @Override
        public void onSuccess(Register register) {
            LogUtils.d(register.toString());
            mRegister = register;

        }

        @Override
        public void onError(String result) {
            LogUtils.e(result);
            registerResult = result;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
        String phoneNum=sharedPreferences.getString("phoneNum",getResources().getString(R.string.login_please_input_phone_number));
        if(phoneNum.equals(getResources().getString(R.string.login_please_input_phone_number))){

            et_register_phone.setHint(phoneNum);
        }else{
            et_register_phone.setText(phoneNum);
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constants.REGISTER_STAER_ACTIVITY:
                    SharedPreferences sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("phoneNum",phoneNum);
                    editor.putString("third_id",mRegister.getData().getThird_id());
                    editor.putString("token",mRegister.getData().getToken());
                    editor.putString("userid",mRegister.getData().getUserid()+"");
                    editor.putString("third_token",mRegister.getData().getThird_token());
                    editor.commit();

                    startActivitys(RegisterActivity.this, PersonalDetailsActivity.class);
                    break;
                case Constants.REGISTER_VERIFY_CAPTCHA:

                    if(!TextUtils.isEmpty(phoneNum)
                            &&!TextUtils.isEmpty(captcha)
                            &&!TextUtils.isEmpty(psw)
                            &&!TextUtils.isEmpty(confirmPsw)
                            &&psw.equals(confirmPsw)
                            &&psw.length()>=6
                            &&confirmPsw.length()>=6
                            ){

                        requestMap.put("mobile",phoneNum);
                        requestMap.put("password", Utils.stringToMD5(psw));
                        requestMap.put("code",captcha);
                        requestMap.put("deviceId",getIMEI(RegisterActivity.this));
                        requestMap.put("platform","android");

                        LogUtils.d("mobile:"+phoneNum+
                                "\n"+"password:"+Utils.stringToMD5(psw)+
                                "\n"+"code:"+captcha+
                                "\n"+"deviceId:"+getIMEI(RegisterActivity.this)+
                                "\n"+"platform:"+"android");

                        registerPresenter.getCallRegisterMsg(requestMap);
                        WaitThread waitThread=new WaitThread();
                        waitThread.start();
                    }else{
                        //todo
                    }
                    break;
            }
        }
    };

}
