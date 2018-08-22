package com.shushan.homework101.mine.tutorship;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
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

import com.shushan.homework101.R;

public class GoPaymentPopupWindows extends PopupWindow implements View.OnClickListener{

    private Context context;
    private Animation animationIn, animationOut;
    private final ConstraintLayout cl_go_payment;
    private final ImageView iv_cancel_pay;
    private final Button btn_pay_ensure;
    private boolean isDismiss = false;

    public GoPaymentPopupWindows(Context context) {
        super(context);
        this.context=context;
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
        iv_cancel_pay.setOnClickListener(this);
        btn_pay_ensure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

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
}
