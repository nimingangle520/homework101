package com.shushan.homework101.homework;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shushan.homework101.ActivityStack;
import com.shushan.homework101.Constants;
import com.shushan.homework101.HttpHelper.service.entity.homework.JobCheck;
import com.shushan.homework101.HttpHelper.service.entity.homework.JobPublish;
import com.shushan.homework101.HttpHelper.service.entity.homework.JobTutorship;
import com.shushan.homework101.HttpHelper.service.entity.homework.UploadPic;
import com.shushan.homework101.HttpHelper.service.presenter.JobPublishPresenter;
import com.shushan.homework101.HttpHelper.service.presenter.UploadPresenter;
import com.shushan.homework101.HttpHelper.service.view.JobPublishView;
import com.shushan.homework101.HttpHelper.service.view.UploadView;
import com.shushan.homework101.MainActivity;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.Utils.Utils;
import com.shushan.homework101.adapter.HomeworkTeacherSelectedAdapter;
import com.shushan.homework101.bean.TeacherSelectBean;
import com.shushan.homework101.homework.select.CustomDate;
import com.shushan.homework101.homework.select.DateUtils;
import com.shushan.homework101.homework.select.JudgeDate;
import com.shushan.homework101.homework.select.WheelWeekMain;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;


public class HomeworkCheckPopupWindow extends PopupWindow implements View.OnClickListener {
    private TextView tv_homework_check_select_course;
    private ConstraintLayout cl_homework_check;
    private Animation animationIn, animationOut;
    private boolean isDismiss = false;
    private final TextView tv_homework_check_release;
    private final ImageView iv_homework_check_time;
    private final ImageView iv_homework_check_grade;
    private final ImageView iv_homework_check_add;
    private final ImageView iv_homework_check_minus;
    private Context context;
    private WheelWeekMain wheelWeekMainDate;
    private String beginTime;
    private View view;
    private CustomDate.WheelMain wheelClassMain;
    private final DisplayMetrics displayMetrics;
    private final int width;
    private final int height;
    private String[] classArrays;
    private String[] courseArrays;
    private final String selectGrade;
    private final String selectCourse;
    private final RecyclerView rv_homework_teacher_select;
    private ArrayList<TeacherSelectBean> teacherSelectBeans;
    private HomeworkTeacherSelectedAdapter homeworkTeacherSelectedAdapter;
    private LinearLayoutManager linearLayoutManager;
    private final ImageButton ib_homework_check_time_arrow;
    private final ImageButton ib_homework_check_class_arrow;
    private final ImageButton ib_homework_check_subject_arrow;
    private int homework_type;
    private final TextView tv_homework_check_title;
    private final Group group_homework_check;
    private final Group group_homework_tutorship;
    private Object object;
    private JobCheck jobCheck;
    private final TextView tv_homework_check_grade;
    private String[] picPathArrays;
    private String check_grade;
    private String check_subject;
    private final Button bt_homework_check_money;
    private String check_price;
    private UploadPresenter uploadPresenter;
    private String[] picArrays;
    private JobPublishPresenter jobPublishPresenter;
    private int type;
    private int check_grade_id;
    private String check_subject_id="1";
    private String tutorship_subject_id="1";
    private String userid;
    private String phoneNum;
    private String token;
    private Gson gson;
    private JobTutorship jobTutorship;
    private final TextView tv_homework_check_time;
    private String tutorship_grade;
    private int tutorship_grade_id;
    private String tutorship_subject;
    private String guidePrice;
    private long timeStamp;
    private String releaseTime;
    private int label;//作业类别1检查2辅导3辅导（实时）
    public HomeworkCheckPopupWindow(Context context, View view, int type, Object object, String[] picPathArrays) {
        super(context);
        this.context = context;
        this.view = view;
        this.homework_type = type;
        this.object = object;
        this.picPathArrays = picPathArrays;
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
        group_homework_check = inflate.findViewById(R.id.group_homework_check);
        group_homework_tutorship = inflate.findViewById(R.id.group_homework_tutorship);
        tv_homework_check_title = inflate.findViewById(R.id.tv_homework_check_title);
        cl_homework_check = (ConstraintLayout) inflate.findViewById(R.id.cl_homework_check);
        tv_homework_check_release = inflate.findViewById(R.id.tv_homework_check_release);
        iv_homework_check_time = inflate.findViewById(R.id.iv_homework_check_time);
        iv_homework_check_grade = inflate.findViewById(R.id.iv_homework_check_grade);
        tv_homework_check_select_course = inflate.findViewById(R.id.tv_homework_check_select_course);
        tv_homework_check_grade = inflate.findViewById(R.id.tv_homework_check_grade);
        iv_homework_check_add = inflate.findViewById(R.id.iv_homework_check_add);
        iv_homework_check_minus = inflate.findViewById(R.id.iv_homework_check_minus);
        rv_homework_teacher_select = inflate.findViewById(R.id.rv_homework_teacher_select);
        ib_homework_check_time_arrow = inflate.findViewById(R.id.ib_homework_check_time_arrow);
        ib_homework_check_class_arrow = inflate.findViewById(R.id.ib_homework_check_class_arrow);
        ib_homework_check_subject_arrow = inflate.findViewById(R.id.ib_homework_check_subject_arrow);
        bt_homework_check_money = inflate.findViewById(R.id.bt_homework_check_money);
        tv_homework_check_time = inflate.findViewById(R.id.tv_homework_check_time);
        ib_homework_check_time_arrow.setOnClickListener(this);
        ib_homework_check_class_arrow.setOnClickListener(this);
        ib_homework_check_subject_arrow.setOnClickListener(this);
        tv_homework_check_release.setOnClickListener(this);
        iv_homework_check_time.setOnClickListener(this);
        iv_homework_check_grade.setOnClickListener(this);
        tv_homework_check_select_course.setOnClickListener(this);
        iv_homework_check_add.setOnClickListener(this);
        iv_homework_check_minus.setOnClickListener(this);
        initData();
        initView();
        initListener();
    }

    private void initListener() {
        if(homework_type==2){
            //点击滑动到中心，同时中心放大
            homeworkTeacherSelectedAdapter.setOnTeacherClickListener(new HomeworkTeacherSelectedAdapter.OnTeacherClickListener() {
                @Override
                public void scrollMid(int position) {
                    //因为一屏幕是3个，将点击的控件-1置为第一个，中间位置则为第二个
                    if (position - 1 >= 0) {
                        homeworkTeacherSelectedAdapter.changePostion(position);
                        linearLayoutManager.scrollToPositionWithOffset(position - 1, 0);
                        linearLayoutManager.setStackFromEnd(true);
                    } else {
                        homeworkTeacherSelectedAdapter.changePostion(position);
                        linearLayoutManager.scrollToPositionWithOffset(position, 0);
                        linearLayoutManager.setStackFromEnd(true);
                    }
                    guidePrice = jobTutorship.getData().getLevel().get(position).getPrice()+"";
                    LogUtils.d("guidePrice:"+ guidePrice);
                }
            });
            rv_homework_teacher_select.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    //滚动暂停
                    if (newState == SCROLL_STATE_IDLE) {
                        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                        homeworkTeacherSelectedAdapter.changePostion(firstVisibleItemPosition + 1);
                        linearLayoutManager.scrollToPositionWithOffset(firstVisibleItemPosition, 0);
                        linearLayoutManager.setStackFromEnd(true);

                        guidePrice=jobTutorship.getData().getLevel().get(firstVisibleItemPosition+1).getPrice()+"";
                        LogUtils.d("guidePrice:"+guidePrice);
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                }
            });
        }
    }

    private void initView() {

        if (homework_type == 1) {
            label=1;
            tv_homework_check_title.setText(context.getResources().getString(R.string.homepage_operation_check));
            group_homework_check.setVisibility(View.VISIBLE);
            group_homework_tutorship.setVisibility(View.GONE);
            jobCheck = (JobCheck) object;
        } else {
            label=2;
            jobTutorship = (JobTutorship) object;
            tv_homework_check_title.setText(context.getResources().getString(R.string.homepage_homework_help));
            group_homework_check.setVisibility(View.GONE);
            group_homework_tutorship.setVisibility(View.VISIBLE);

            //设置recycleview
            linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            rv_homework_teacher_select.setLayoutManager(linearLayoutManager);
            if (homeworkTeacherSelectedAdapter == null) {
                homeworkTeacherSelectedAdapter = new HomeworkTeacherSelectedAdapter(context, jobTutorship.getData().getLevel());
                rv_homework_teacher_select.setAdapter(homeworkTeacherSelectedAdapter);
            } else {
                homeworkTeacherSelectedAdapter.notifyDataSetChanged();
            }
        }


        uploadPresenter = new UploadPresenter(context);
        uploadPresenter.onCreate(Constants.BASE_URL);
        uploadPresenter.attachView(uploadView);
        jobPublishPresenter = new JobPublishPresenter(context);
        jobPublishPresenter.onCreate(Constants.BASE_URL);
        jobPublishPresenter.attachView(jobPublishView);
        gson = new Gson();
    }

    private void initData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("userid", "");
        phoneNum = sharedPreferences.getString("phoneNum", "");
        token = sharedPreferences.getString("token", "");
    }

    private UploadView uploadView = new UploadView() {
        @Override
        public void onSuccess(UploadPic uploadPic) {
            LogUtils.d(uploadPic.toString());
            SharedPreferences sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
            check_grade_id = sharedPreferences.getInt("check_grade_id", 0);
            check_subject_id = sharedPreferences.getString("check_subject_id", "");
            tutorship_grade_id = sharedPreferences.getInt("tutorship_grade_id", 0);
            tutorship_subject_id = sharedPreferences.getString("tutorship_subject_id", "");

                    if(label==1){

                        if(
                                !TextUtils.isEmpty(userid)&&uploadPic!=null&&
                                !TextUtils.isEmpty(check_subject_id)&&
                                !TextUtils.isEmpty(check_price)&&
                                !TextUtils.isEmpty(token)){

                            jobPublishPresenter.getJobPublishMsg(userid,gson.toJson(uploadPic.getData()),Constants.TYPE_HOMEWORK_CHECK,
                                    check_grade_id+"",check_subject_id,Float.parseFloat(check_price),
                                    (System.currentTimeMillis()/1000)+"",
                                    token);

                        }

                    }else if(label==2){
                        if(
                                !TextUtils.isEmpty(userid)&&uploadPic!=null&&
                                !TextUtils.isEmpty(tutorship_subject_id)&&
                                !TextUtils.isEmpty(guidePrice)&&
                                !TextUtils.isEmpty(token)){

                            jobPublishPresenter.getJobPublishMsg(userid,gson.toJson(uploadPic.getData()),Constants.TYPE_HOMEWORK_TUTORSHIP,
                                    tutorship_grade_id+"",tutorship_subject_id,Float.parseFloat(guidePrice),
                                    timeStamp+"",
                                    token);
                        }

                    }else{

                    }

        }

        @Override
        public void onError(String result) {
            LogUtils.e(result.toLowerCase());
        }
    };

    private JobPublishView jobPublishView = new JobPublishView() {
        @Override
        public void onSuccess(JobPublish jobPublish) {
            if(jobPublish!=null&&jobPublish.getError()==0){
                LogUtils.d(jobPublish.toString());
                ActivityStack.getActivityStack().finishAllActivity();
                HomeworkCheckPopupWindow.super.dismiss();
                context.startActivity(new Intent(context, MainActivity.class));
            }
        }

        @Override
        public void onError(String result) {
            LogUtils.d(result.toString());
        }
    };

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

            if (homework_type == 1) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
                check_grade = sharedPreferences.getString("check_grade", context.getResources().getString(R.string.homework_please_select_class));
                check_subject = sharedPreferences.getString("check_subject", context.getResources().getString(R.string.homework_please_select_subject));

                if (check_grade.equals(context.getResources().getString(R.string.homework_please_select_class))) {
                    tv_homework_check_grade.setHint(check_grade);
                } else {
                    tv_homework_check_grade.setText(check_grade);
                }
                if (check_subject.equals(context.getResources().getString(R.string.homework_please_select_subject))) {
                    tv_homework_check_select_course.setHint(check_subject);
                } else {
                    tv_homework_check_select_course.setText(check_subject);
                }

                if (jobCheck != null) {

                    if (jobCheck.getData().getSubject() != null && jobCheck.getData().getSubject().size() > 0) {

                        classArrays = new String[jobCheck.getData().getSubject().size()];

                        for (int i = 0; i < jobCheck.getData().getSubject().size(); i++) {

                            classArrays[i] = jobCheck.getData().getSubject().get(i).getGrade();

                        }
                        for (int j = 0; j < jobCheck.getData().getSubject().size(); j++) {

                            if (jobCheck.getData().getSubject().get(j).getGrade().equals(check_grade)) {

                                if (jobCheck.getData().getSubject().get(j).getData() != null && jobCheck.getData().getSubject().get(j).getData().size() > 0) {

                                    courseArrays = new String[jobCheck.getData().getSubject().get(j).getData().size()];

                                    for (int k = 0; k < jobCheck.getData().getSubject().get(j).getData().size(); k++) {

                                        courseArrays[k] = jobCheck.getData().getSubject().get(j).getData().get(k).getSubject();

                                    }
                                    break;

                                }
                            }
                        }

                    }

                }

            } else {

                SharedPreferences sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
                tutorship_grade = sharedPreferences.getString("tutorship_grade", context.getResources().getString(R.string.homework_please_select_class));
                tutorship_subject = sharedPreferences.getString("tutorship_subject", context.getResources().getString(R.string.homework_please_select_subject));

                if (tutorship_grade.equals(context.getResources().getString(R.string.homework_please_select_class))) {
                    tv_homework_check_grade.setHint(tutorship_grade);
                } else {
                    tv_homework_check_grade.setText(tutorship_grade);
                }
                if (tutorship_subject.equals(context.getResources().getString(R.string.homework_please_select_subject))) {
                    tv_homework_check_select_course.setHint(tutorship_subject);
                } else {
                    tv_homework_check_select_course.setText(tutorship_subject);
                }

                if (jobTutorship != null) {

                    if (jobTutorship.getData().getSubject() != null && jobTutorship.getData().getSubject().size() > 0) {

                        classArrays = new String[jobTutorship.getData().getSubject().size()];

                        for (int i = 0; i < jobTutorship.getData().getSubject().size(); i++) {

                            classArrays[i] = jobTutorship.getData().getSubject().get(i).getGrade();

                        }
                        for (int j = 0; j < jobTutorship.getData().getSubject().size(); j++) {

                            if (jobTutorship.getData().getSubject().get(j).getGrade().equals(tutorship_grade)) {

                                if (jobTutorship.getData().getSubject().get(j).getData() != null && jobTutorship.getData().getSubject().get(j).getData().size() > 0) {

                                    courseArrays = new String[jobTutorship.getData().getSubject().get(j).getData().size()];

                                    for (int k = 0; k < jobTutorship.getData().getSubject().get(j).getData().size(); k++) {

                                        courseArrays[k] = jobTutorship.getData().getSubject().get(j).getData().get(k).getSubject();

                                    }
                                    break;

                                }
                            }
                        }

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = bgcolor;
        ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) context).getWindow().setAttributes(lp);

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
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
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

    /**
     * 将图片转换成Base64编码的字符串
     *
     * @param path
     * @return base64编码的字符串
     */
    public String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_homework_check_release) {
            if (homework_type == 1) {
                check_grade = tv_homework_check_grade.getText().toString().trim();
                check_subject = tv_homework_check_select_course.getText().toString().trim();
                check_price = bt_homework_check_money.getText().toString().trim();

                if (picPathArrays != null && picPathArrays.length > 0
                        ) {
                    LogUtils.d("check_grade:" + check_grade + "\ncheck_subject:" + check_subject + "\ncheck_price:" + check_price + "\npicArrays:" + Arrays.toString(picPathArrays));

                    new ImageToBase64AsyncTask().execute(picPathArrays);

                }
            } else {

                tutorship_grade = tv_homework_check_grade.getText().toString().trim();
                tutorship_subject = tv_homework_check_select_course.getText().toString().trim();
                guidePrice = TextUtils.isEmpty(guidePrice)?jobTutorship.getData().getLevel().get(0).getPrice()+"":guidePrice;

                if (picPathArrays != null && picPathArrays.length > 0
                        ) {
                    LogUtils.d("tutorship_grade:" + tutorship_grade + "\ntutorship_subject:" + tutorship_subject + "\nguidePrice:" + guidePrice + "\npicArrays:" + Arrays.toString(picPathArrays));

                    new ImageToBase64AsyncTask().execute(picPathArrays);

                }

            }

            //HomeworkCheckPopupWindow.super.dismiss();
            //dismiss();

        }
        if (id == R.id.ib_homework_check_time_arrow) {
            showWeekBottoPopupWindow();
        }
        if (id == R.id.ib_homework_check_class_arrow) {
            showBottoPopupWindow(classArrays, selectGrade, Constants.TYPE_GRADE);
        }
        if (id == R.id.ib_homework_check_subject_arrow) {
            showBottoPopupWindow(courseArrays, selectCourse, Constants.TYPE_SUBJECT);
        }
        if (id == R.id.iv_homework_check_add) {

        }
        if (id == R.id.iv_homework_check_minus) {

        }


    }

    private class ImageToBase64AsyncTask extends AsyncTask<String[], Void, String[]> {
        @Override
        protected String[] doInBackground(String[]... strings) {

            picArrays = new String[strings[0].length];

            for (int i = 0; i < strings[0].length; i++) {

                picArrays[i] = imageToBase64(strings[0][i]);

            }
            return picArrays;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            Gson g=new Gson();
            uploadPresenter.getUploadMsg(g.toJson(strings), "1", "2");
            //LogUtils.d("picArrays:"+ Arrays.toString(strings));

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
        View menuView = LayoutInflater.from(context).inflate(R.layout.show_week_popup_window, null);
        final PopupWindow mPopupWindow = new PopupWindow(menuView, (int) (width),
                ActionBar.LayoutParams.WRAP_CONTENT);
        wheelWeekMainDate = new WheelWeekMain(menuView, true,context);
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
        wheelWeekMainDate.initDateTimePicker(year, month, day, hours, minute);
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        mPopupWindow.setOnDismissListener(new poponDismissListener());
        cl_homework_check.setBackground(context.getResources().getDrawable(R.drawable.homework_release_popup_bg));
        TextView tv_time_cancel = (TextView) menuView.findViewById(R.id.tv_time_cancel);
        TextView tv_time_ensure = (TextView) menuView.findViewById(R.id.tv_time_ensure);
        tv_time_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mPopupWindow.dismiss();
                cl_homework_check.setBackground(context.getResources().getDrawable(R.drawable.tab_bg));
            }
        });
        tv_time_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                beginTime = wheelWeekMainDate.getTime().toString();
                //tv_week_house_time.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
                tv_homework_check_time.setText(beginTime);
                releaseTime = "2018"+context.getResources().getString(R.string.year)+
                        beginTime.trim().replaceAll(" ","").substring(0,6)+" "+
                        beginTime.trim().replaceAll(" ","").substring(9,beginTime.trim().replaceAll(" ","").length());
                LogUtils.d("releaseTime:"+ releaseTime);
                try {
                    timeStamp=Utils.stringToLong(releaseTime,context.getResources().getString(R.string.data_format))/1000;
                    LogUtils.d("timeStamp:"+timeStamp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mPopupWindow.dismiss();
                cl_homework_check.setBackground(context.getResources().getDrawable(R.drawable.tab_bg));

            }
        });
    }

    class poponDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            //darkenBackground(1f);
            cl_homework_check.setBackground(context.getResources().getDrawable(R.drawable.tab_bg));
        }
    }

    public void showBottoPopupWindow(String[] arrays, String title, final int type) {
        this.type = type;
        View menuView = LayoutInflater.from(context).inflate(R.layout.show_popup_window, null);
        final PopupWindow mPopupWindow = new PopupWindow(menuView, (int) (width),
                ActionBar.LayoutParams.WRAP_CONTENT);
        wheelClassMain = new CustomDate.WheelMain(menuView, true, context, homework_type, type);
        wheelClassMain.screenheight = height;
        wheelClassMain.initDateTimePicker(arrays);
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        mPopupWindow.setOnDismissListener(new poponDismissListener());
        cl_homework_check.setBackground(context.getResources().getDrawable(R.drawable.homework_release_popup_bg));
        TextView tv_cancle = (TextView) menuView.findViewById(R.id.tv_cancel);
        TextView tv_ensure = (TextView) menuView.findViewById(R.id.tv_ensure);
        TextView tv_pop_title = menuView.findViewById(R.id.tv_pop_title);
        tv_pop_title.setText(title);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mPopupWindow.dismiss();
                cl_homework_check.setBackground(context.getResources().getDrawable(R.drawable.tab_bg));

            }
        });
        tv_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //tv_week_house_time.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
                mPopupWindow.dismiss();
                cl_homework_check.setBackground(context.getResources().getDrawable(R.drawable.tab_bg));
                if (homework_type == 1) {

                    if (type == Constants.TYPE_GRADE) {
                        check_grade = wheelClassMain.getName();
                        tv_homework_check_grade.setText(wheelClassMain.getName());
                        for (int i = 0; i < jobCheck.getData().getSubject().size(); i++) {

                            if (jobCheck.getData().getSubject().get(i).getGrade().equals(wheelClassMain.getName())) {

                                check_grade_id = jobCheck.getData().getSubject().get(i).getGrade_id();
                                SharedPreferences sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("check_grade", wheelClassMain.getName());
                                editor.putInt("check_grade_id", check_grade_id);


                                if (jobCheck.getData().getSubject().get(i).getData() != null && jobCheck.getData().getSubject().get(i).getData().size() > 0) {

                                    courseArrays = new String[jobCheck.getData().getSubject().get(i).getData().size()];
                                    for (int j = 0; j < jobCheck.getData().getSubject().get(i).getData().size(); j++) {

                                        courseArrays[j] = jobCheck.getData().getSubject().get(i).getData().get(j).getSubject();
                                    }

                                    tv_homework_check_select_course.setText(courseArrays[0]);
                                    editor.putString("check_subject", tv_homework_check_select_course.getText().toString());
                                    editor.putString("check_subject_id", check_subject_id);
                                    editor.commit();
                                    break;
                                }
                            }
                        }

                    } else {
                        tv_homework_check_select_course.setText(wheelClassMain.getName());

                        for (int i = 0; i < jobCheck.getData().getSubject().size(); i++) {

                            if (jobCheck.getData().getSubject().get(i).getGrade().equals(check_grade)) {

                                if (jobCheck.getData().getSubject().get(i).getData() != null && jobCheck.getData().getSubject().get(i).getData().size() > 0) {

                                    for (int j = 0; j < jobCheck.getData().getSubject().get(i).getData().size(); j++) {

                                        if (jobCheck.getData().getSubject().get(i).getData().get(j).getSubject().equals(wheelClassMain.getName())) {

                                            check_subject_id = jobCheck.getData().getSubject().get(i).getData().get(j).getSub_id();

                                            SharedPreferences sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("check_subject", wheelClassMain.getName());
                                            editor.putString("check_subject_id", check_subject_id);
                                            editor.commit();
                                            break;
                                        }
                                    }

                                }
                            }
                        }
                    }
                } else {

                    if (type == Constants.TYPE_GRADE) {
                        tutorship_grade = wheelClassMain.getName();
                        tv_homework_check_grade.setText(wheelClassMain.getName());
                        for (int i = 0; i < jobTutorship.getData().getSubject().size(); i++) {

                            if (jobTutorship.getData().getSubject().get(i).getGrade().equals(wheelClassMain.getName())) {

                                tutorship_grade_id = jobTutorship.getData().getSubject().get(i).getGrade_id();
                                SharedPreferences sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("tutorship_grade", wheelClassMain.getName());
                                editor.putInt("tutorship_grade_id", tutorship_grade_id);


                                if (jobTutorship.getData().getSubject().get(i).getData() != null && jobTutorship.getData().getSubject().get(i).getData().size() > 0) {

                                    courseArrays = new String[jobTutorship.getData().getSubject().get(i).getData().size()];
                                    for (int j = 0; j < jobTutorship.getData().getSubject().get(i).getData().size(); j++) {

                                        courseArrays[j] = jobTutorship.getData().getSubject().get(i).getData().get(j).getSubject();
                                    }

                                    tv_homework_check_select_course.setText(courseArrays[0]);
                                    editor.putString("tutorship_subject", tv_homework_check_select_course.getText().toString());
                                    editor.putString("tutorship_subject_id", tutorship_subject_id);
                                    editor.commit();
                                    break;
                                }
                            }
                        }

                    } else {
                        tv_homework_check_select_course.setText(wheelClassMain.getName());

                        for (int i = 0; i < jobTutorship.getData().getSubject().size(); i++) {

                            if (jobTutorship.getData().getSubject().get(i).getGrade().equals(tutorship_grade)) {

                                if (jobTutorship.getData().getSubject().get(i).getData() != null && jobTutorship.getData().getSubject().get(i).getData().size() > 0) {

                                    for (int j = 0; j < jobTutorship.getData().getSubject().get(i).getData().size(); j++) {

                                        if (jobTutorship.getData().getSubject().get(i).getData().get(j).getSubject().equals(wheelClassMain.getName())) {

                                            tutorship_subject_id = jobTutorship.getData().getSubject().get(i).getData().get(j).getSub_id();

                                            SharedPreferences sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("tutorship_subject", wheelClassMain.getName());
                                            editor.putString("tutorship_subject_id", tutorship_subject_id);
                                            editor.commit();
                                            break;
                                        }
                                    }

                                }
                            }
                        }
                    }

                }
            }
        });
    }

}
