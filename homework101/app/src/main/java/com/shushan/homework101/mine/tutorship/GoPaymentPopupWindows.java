package com.shushan.homework101.mine.tutorship;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.shushan.homework101.Constants;
import com.shushan.homework101.HttpHelper.service.entity.orders.Alipay;
import com.shushan.homework101.HttpHelper.service.entity.orders.OrdersData;
import com.shushan.homework101.HttpHelper.service.entity.orders.PayResult;
import com.shushan.homework101.HttpHelper.service.entity.orders.WeChatPay;
import com.shushan.homework101.HttpHelper.service.presenter.AlipayPresenter;
import com.shushan.homework101.HttpHelper.service.presenter.WeChatPayPresenter;
import com.shushan.homework101.HttpHelper.service.view.AlipayView;
import com.shushan.homework101.HttpHelper.service.view.WeChatPayView;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.Utils.Utils;
import com.shushan.homework101.WXPayUtils;

import java.util.HashMap;
import java.util.Map;

public class GoPaymentPopupWindows extends PopupWindow implements View.OnClickListener{

    private Context context;
    private Animation animationIn, animationOut;
    private  ConstraintLayout cl_go_payment;
    private  ImageView iv_cancel_pay;
    private  Button btn_pay_ensure;
    private boolean isDismiss = false;
    private  RadioButton rb_balance_pay;
    private  RadioButton rb_wechat_pay;
    private  RadioButton rb_alipay_pay;
    private OrdersData ordersData;
    private int payment=3;//支付方式1支付宝2微信3余额
    private int label;
    private TextView tv_pay_sum_owing_details;
    private  Map<String, String> mapRequest;
    private  SharedPreferences sharedPreferences;
    private  String userid;
    private  String token;
    private  String oid;
    private  String pay_money;
    private final WeChatPayPresenter weChatPayPresenter;
    private final AlipayPresenter alipayPresenter;
    private static final int SDK_PAY_FLAG=100;
    private String sign;

    public GoPaymentPopupWindows(Context context, OrdersData ordersData) {
        super(context);
        this.context=context;
        this.ordersData=ordersData;
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_go_payment, null);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setBackgroundDrawable(new ColorDrawable());
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable(new ColorDrawable());
        this.setContentView(inflate);
        animationIn = AnimationUtils.loadAnimation(context, R.anim.up_in);
        animationOut = AnimationUtils.loadAnimation(context, R.anim.down_out);
        cl_go_payment = inflate.findViewById(R.id.cl_go_payment);
        iv_cancel_pay = inflate.findViewById(R.id.iv_cancel_pay);
        btn_pay_ensure = inflate.findViewById(R.id.btn_pay_ensure);
        rb_balance_pay = inflate.findViewById(R.id.rb_balance_pay);
        rb_wechat_pay = inflate.findViewById(R.id.rb_wechat_pay);
        rb_alipay_pay = inflate.findViewById(R.id.rb_alipay_pay);
        tv_pay_sum_owing_details=inflate.findViewById(R.id.tv_pay_sum_owing_details);
        rb_balance_pay.setOnClickListener(this);
        rb_wechat_pay.setOnClickListener(this);
        rb_alipay_pay.setOnClickListener(this);
        iv_cancel_pay.setOnClickListener(this);
        btn_pay_ensure.setOnClickListener(this);
        mapRequest = new HashMap<>();
        sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
        label=sharedPreferences.getInt("label",1);
        userid = sharedPreferences.getString("userid","");
        token = sharedPreferences.getString("token","");
        if(ordersData!=null){
            tv_pay_sum_owing_details.setText(ordersData.getPay_money()+"");
            oid = ordersData.getOid();
            pay_money = ordersData.getPay_money()+"";
        }
        weChatPayPresenter = new WeChatPayPresenter(context);
        weChatPayPresenter.onCreate(Constants.BASE_URL);
        weChatPayPresenter.attachView(weChatPayView);
        alipayPresenter = new AlipayPresenter(context);
        alipayPresenter.onCreate(Constants.BASE_URL);
        alipayPresenter.attachView(alipayView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_cancel_pay:
                dismiss();
                break;
            case R.id.btn_pay_ensure:
                mapRequest.put("userid",userid);
                mapRequest.put("token",token);
                mapRequest.put("oid",oid);
                mapRequest.put("label",label+"");
                mapRequest.put("cate",payment+"");
                mapRequest.put("pay_money",pay_money);
                sign=Utils.getFormatParams(mapRequest);
                LogUtils.e("sign:"+ sign);
                mapRequest.put("sign",Utils.stringToMD5(sign));
                LogUtils.e(mapRequest.toString());
                if(payment==2){
                    weChatPayPresenter.getPayMsg(mapRequest);
                }else if(payment==1){
                        alipayPresenter.getPayMsg(mapRequest);

                }else{

                }
                break;
            case R.id.rb_balance_pay:
                rb_balance_pay.setChecked(true);
                rb_wechat_pay.setChecked(false);
                rb_alipay_pay.setChecked(false);
                payment=3;
                break;
            case R.id.rb_wechat_pay:
                rb_balance_pay.setChecked(false);
                rb_wechat_pay.setChecked(true);
                rb_alipay_pay.setChecked(false);
                payment=2;
                break;
            case R.id.rb_alipay_pay:
                rb_balance_pay.setChecked(false);
                rb_wechat_pay.setChecked(false);
                rb_alipay_pay.setChecked(true);
                payment=1;
                break;
        }

    }
    @Override
    public void showAsDropDown(View parent) {
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                int[] location = new int[2];
                parent.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1] + parent.getHeight();
                this.showAtLocation(parent, Gravity.BOTTOM, x, y);
            } else {
                this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            }

            isDismiss = false;
            cl_go_payment.startAnimation(animationIn);
            darkenBackground(0.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void darkenBackground(Float bgcolor){
        WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
        lp.alpha = bgcolor;
        ((Activity)context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity)context).getWindow().setAttributes(lp);

    }
    @Override
    public void dismiss() {
        if (isDismiss) {
            return;
        }
        isDismiss = true;
        cl_go_payment.startAnimation(animationOut);
        dismiss();
        animationOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isDismiss = false;
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN){
                    dismiss4Pop();
                } else {
                    GoPaymentPopupWindows.super.dismiss();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        darkenBackground(1f);
    }

    /**
     * 在android4.1.1和4.1.2版本关闭PopWindow
     */
    private void dismiss4Pop() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                GoPaymentPopupWindows.super.dismiss();
            }
        });
    }

    private WeChatPayView weChatPayView = new WeChatPayView() {
        @Override
        public void onSuccess(WeChatPay weChatPay) {
            if(weChatPay!=null){
                LogUtils.d(weChatPay.toString());
                SharedPreferences sharedPreferences=context.getSharedPreferences("info",Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("appid",weChatPay.getData().getAppid()).commit();
                new WXPayUtils(weChatPay.getData()).toWXPay(context);
            }
        }
        @Override
        public void onError(String result) {

        }
    };

    private AlipayView alipayView=new AlipayView() {
        @Override
        public void onSuccess(final Alipay alipays) {
            if(alipays!=null){
                LogUtils.d(alipays.toString());

                final String orderInfo = alipays.getData();   // 订单信息

                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask((Activity) context);
                        Map<String, String> result = alipay.payV2(orderInfo,true);

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        }

        @Override
        public void onError(String result) {

        }
    };
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==SDK_PAY_FLAG){

                PayResult payResult = new PayResult((Map<String, String>) msg.obj);

                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }

        }
    };
}
