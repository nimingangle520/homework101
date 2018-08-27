package com.shushan.homework101.mine.wallet;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.shushan.homework101.R;
import com.shushan.homework101.Utils.Utils;
import com.shushan.homework101.base.BaseActivity;
import com.shushan.homework101.homework.select.DateUtils;
import com.shushan.homework101.homework.select.JudgeDate;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;

public class BillActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.tv_bill_time)
    TextView tv_bill_time;
    @Bind(R.id.ll_bill)
    LinearLayout ll_bill;
    @Bind(R.id.tv_right)
    TextView tv_right;
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    private WheelTimeSelect wheelTimeSelect;
    private DisplayMetrics displayMetrics;
    private int width;
    private int height;
    private PopupWindow mPopupWindow;
    private View menuView;
    private TextView tv_bill_time_cancel;
    private TextView tv_bill_time_ensure;
    private BillFilterPopup billFilterPopup;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_bill);
    }

    @Override
    protected void initViews() {
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText(getResources().getString(R.string.mine_wallet_filter));
        tv_actionbar.setText(getResources().getString(R.string.mine_wallet_bill));
        billFilterPopup = new BillFilterPopup(mContext);

    }

    @Override
    protected void initEvents() {
        tv_bill_time.setOnClickListener(this);
        tv_right.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        displayMetrics = Utils.getScreenWH(mContext);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_bill_time:
                showTimeSelectPopu();
                break;
                case R.id.tv_right:
                    if(billFilterPopup!=null){
                        if(billFilterPopup.isShowing()){
                            billFilterPopup.dismiss();
                        }
                        billFilterPopup.showAsDropDown(ll_bill);
                    }
                    break;
        }
    }
    class popuDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }
    public void showTimeSelectPopu() {
        menuView = LayoutInflater.from(mContext).inflate(R.layout.bill_time_select,null);
        mPopupWindow = new PopupWindow(menuView, (int)(width),
                ActionBar.LayoutParams.WRAP_CONTENT);
        wheelTimeSelect = new WheelTimeSelect(menuView, true);
        wheelTimeSelect.screenheight = height;
        String time = DateUtils.currentMonth().toString();
        Calendar calendar = Calendar.getInstance();
        if (JudgeDate.isDate(time, "yyyy-MM-DD")) {
            try {
                calendar.setTime(new Date(time));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        wheelTimeSelect.initDateTimePicker(year, month);
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.showAtLocation(ll_bill, Gravity.BOTTOM, 0, 0);
        mPopupWindow.setOnDismissListener(new BillActivity.popuDismissListener());
        backgroundAlpha(0.5f);
        tv_bill_time_cancel = (TextView) menuView.findViewById(R.id.tv_bill_time_cancel);
        tv_bill_time_ensure = (TextView) menuView.findViewById(R.id.tv_bill_time_ensure);
        tv_bill_time_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mPopupWindow.dismiss();
               backgroundAlpha(1f);

            }
        });
        tv_bill_time_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //tv_week_house_time.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
    }
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

}
