package com.shushan.homework101.homework;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
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
import android.widget.TextView;

import com.shushan.homework101.R;
import com.shushan.homework101.Utils.Utils;
import com.shushan.homework101.homework.select.CustomDate;
import com.shushan.homework101.homework.select.DateUtils;
import com.shushan.homework101.homework.select.JudgeDate;
import com.shushan.homework101.homework.select.WheelWeekMain;

import java.util.Calendar;
import java.util.Date;


public class HomeworkCheckPopupWindow extends PopupWindow implements View.OnClickListener {
    private TextView tv_homework_check_select_course;
    private ConstraintLayout cl_homework_check;
    private Animation animationIn, animationOut;
    private boolean isDismiss = false;
    private final ImageView bt_homework_check_close;
    private final ImageView iv_homework_check_time;
    private final ImageView iv_homework_check_grade;
    private final ImageView iv_homework_check_add;
    private final ImageView iv_homework_check_minus;
    private final Button bt_homework_check_release;
    private  Context context;
    private WheelWeekMain wheelWeekMainDate;
    private String beginTime;
    private View view;
    private CustomDate.WheelMain wheelClassMain;
    private final DisplayMetrics displayMetrics;
    private final int width;
    private final int height;
    private final String[] classArrays;
    private final String[] courseArrays;
    private final String selectGrade;
    private final String selectCourse;

    public HomeworkCheckPopupWindow(Context context,View view) {
        super(context);
        this.context=context;
        this.view=view;
        View inflate = LayoutInflater.from(context).inflate(R.layout.homework_check_pop, null);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setBackgroundDrawable(new ColorDrawable());
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable(new ColorDrawable());
        this.setContentView(inflate);
        displayMetrics = Utils.getScreenWH(context);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        classArrays = context.getResources().getStringArray(R.array.preference_primary_class);
        courseArrays = context.getResources().getStringArray(R.array.preference_primary_course);
        selectGrade = context.getResources().getString(R.string.select_grade);
        selectCourse = context.getResources().getString(R.string.homework_check_select_course);
        animationIn = AnimationUtils.loadAnimation(context, R.anim.up_in);
        animationOut = AnimationUtils.loadAnimation(context, R.anim.down_out);
        cl_homework_check = (ConstraintLayout) inflate.findViewById(R.id.cl_homework_check);
        bt_homework_check_close = inflate.findViewById(R.id.bt_homework_check_close);
        iv_homework_check_time = inflate.findViewById(R.id.iv_homework_check_time);
        iv_homework_check_grade = inflate.findViewById(R.id.iv_homework_check_grade);
        tv_homework_check_select_course=inflate.findViewById(R.id.tv_homework_check_select_course);
        iv_homework_check_add = inflate.findViewById(R.id.iv_homework_check_add);
        iv_homework_check_minus = inflate.findViewById(R.id.iv_homework_check_minus);
        bt_homework_check_release = inflate.findViewById(R.id.bt_homework_check_release);
        bt_homework_check_close.setOnClickListener(this);
        iv_homework_check_time.setOnClickListener(this);
        iv_homework_check_grade.setOnClickListener(this);
        tv_homework_check_select_course.setOnClickListener(this);
        iv_homework_check_add.setOnClickListener(this);
        iv_homework_check_minus.setOnClickListener(this);
        bt_homework_check_release.setOnClickListener(this);

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
            cl_homework_check.startAnimation(animationIn);
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
        cl_homework_check.startAnimation(animationOut);
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
                    HomeworkCheckPopupWindow.super.dismiss();
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
                HomeworkCheckPopupWindow.super.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bt_homework_check_close) {
            HomeworkCheckPopupWindow.super.dismiss();
            dismiss();

        }
        if (id == R.id.iv_homework_check_time) {
            showWeekBottoPopupWindow();
        }
        if(id==R.id.iv_homework_check_grade){
            showBottoPopupWindow(classArrays,selectGrade);
        }
        if(id==R.id.tv_homework_check_select_course){
            showBottoPopupWindow(courseArrays,selectCourse);
        }
        if(id==R.id.iv_homework_check_add){

        }
        if(id==R.id.iv_homework_check_minus){

        }
        if(id==R.id.bt_homework_check_release){

        }

    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int positon);
    }

    public void showWeekBottoPopupWindow() {
        View menuView = LayoutInflater.from(context).inflate(R.layout.show_week_popup_window,null);
        final PopupWindow mPopupWindow = new PopupWindow(menuView, (int)(width),
                ActionBar.LayoutParams.WRAP_CONTENT);
        wheelWeekMainDate = new WheelWeekMain(menuView, true);
        wheelWeekMainDate.screenheight = height;
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
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelWeekMainDate.initDateTimePicker(year, month, day, hours,minute);
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        mPopupWindow.setOnDismissListener(new poponDismissListener());
        cl_homework_check.setBackgroundColor(context.getResources().getColor(R.color.button_gray));
        TextView tv_time_cancel = (TextView) menuView.findViewById(R.id.tv_time_cancel);
        TextView tv_time_ensure = (TextView) menuView.findViewById(R.id.tv_time_ensure);
        tv_time_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mPopupWindow.dismiss();
                cl_homework_check.setBackgroundColor(context.getResources().getColor(R.color.white));

            }
        });
        tv_time_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                beginTime = wheelWeekMainDate.getTime().toString();
                //tv_week_house_time.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
                mPopupWindow.dismiss();
                cl_homework_check.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
        });
    }
    class poponDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            //darkenBackground(1f);
            cl_homework_check.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
    }
    public void showBottoPopupWindow(String[] arrays,String title) {
        View menuView = LayoutInflater.from(context).inflate(R.layout.show_popup_window,null);
        final PopupWindow mPopupWindow = new PopupWindow(menuView, (int)(width),
                ActionBar.LayoutParams.WRAP_CONTENT);
        wheelClassMain = new CustomDate.WheelMain(menuView, true,context);
        wheelClassMain.screenheight = height;
        wheelClassMain.initDateTimePicker(arrays);
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        mPopupWindow.setOnDismissListener(new poponDismissListener());
        cl_homework_check.setBackgroundColor(context.getResources().getColor(R.color.button_gray));
        TextView tv_cancle = (TextView) menuView.findViewById(R.id.tv_cancel);
        TextView tv_ensure = (TextView) menuView.findViewById(R.id.tv_ensure);
        TextView tv_pop_title=menuView.findViewById(R.id.tv_pop_title);
        tv_pop_title.setText(title);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mPopupWindow.dismiss();
                cl_homework_check.setBackgroundColor(context.getResources().getColor(R.color.white));

            }
        });
        tv_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //tv_week_house_time.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
                mPopupWindow.dismiss();
                cl_homework_check.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
        });
    }

}
