package com.shushan.homework101.mine.tutorship;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shushan.homework101.Constants;
import com.shushan.homework101.HttpHelper.service.entity.orders.OrdersData;
import com.shushan.homework101.HttpHelper.service.entity.orders.OrdersDetails;
import com.shushan.homework101.HttpHelper.service.presenter.OrdersDetailsPresenter;
import com.shushan.homework101.HttpHelper.service.view.OrdersDetailsView;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.Utils.Utils;
import com.shushan.homework101.adapter.MineTutorshipGridviewAdapter;
import com.shushan.homework101.banner.ImageViewRoundRect;
import com.shushan.homework101.base.BaseActivity;

import java.text.ParseException;

import butterknife.Bind;

public class TutorshipDetailsActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.tv_right)
    TextView tv_right;
    @Bind(R.id.iv_actionbar_left)
    ImageView iv_actionbar_left;
    @Bind(R.id.view_video)
    ImageViewRoundRect view_video;
    @Bind(R.id.view_video_bg)
    ImageView view_video_bg;
    @Bind(R.id.tv_tutorship)
    TextView tv_tutorship;
    @Bind(R.id.tv_tutorship_state)
    TextView tv_tutorship_state;
    @Bind(R.id.iv_teacher_visa)
    ImageViewRoundRect iv_teacher_visa;
    @Bind(R.id.tv_teacher_name)
    TextView tv_teacher_name;
    @Bind(R.id.tv_teacher_class)
    TextView tv_teacher_class;
    @Bind(R.id.tv_teacher_honor)
    TextView tv_teacher_honor;
    @Bind(R.id.iv_video)
    ImageView iv_video;

    @Bind(R.id.tv_tutorship_money)
    TextView tv_tutorship_money;
    @Bind(R.id.tv_tutorship_money_default)
    TextView tv_tutorship_money_default;

    @Bind(R.id.tv_amount_paid)
    TextView tv_amount_paid;
    @Bind(R.id.tv_amount_paid_default)
    TextView tv_amount_paid_default;

    @Bind(R.id.tv_tutorship_orders_num)
    TextView tv_tutorship_orders_num;
    @Bind(R.id.tv_tutorship_orders_num_default)
    TextView tv_tutorship_orders_num_default;

    @Bind(R.id.tv_tutorship_orders_time)
    TextView tv_tutorship_orders_time;
    @Bind(R.id.tv_tutorship_orders_time_default)
    TextView tv_tutorship_orders_time_default;

    @Bind(R.id.tv_tutorship_pay_way)
    TextView tv_tutorship_pay_way;
    @Bind(R.id.tv_tutorship_pay_way_default)
    TextView tv_tutorship_pay_way_default;

    @Bind(R.id.tv_tutorship_pay_time)
    TextView tv_tutorship_pay_time;
    @Bind(R.id.tv_tutorship_pay_time_default)
    TextView tv_tutorship_pay_time_default;

    @Bind(R.id.tv_tutorship_duration)
    TextView tv_tutorship_duration;
    @Bind(R.id.tv_tutorship_duration_default)
    TextView tv_tutorship_duration_default;

    @Bind(R.id.btn_contact_again)
    TextView btn_contact_again;

    @Bind(R.id.btn_go_pay)
    Button btn_go_pay;
    @Bind(R.id.ll_mine_tutorship_gridview)
    LinearLayout ll_mine_tutorship_gridview;
    @Bind(R.id.gridView_mine_tutorship)
    GridView gridView_mine_tutorship;
    private MineTutorshipGridviewAdapter mineTutorshipGridviewAdapter;
    private GoPaymentPopupWindows goPaymentPopupWindows;
    private OrdersDetails mOrdersDetails;
    private OrdersData mOrdersData;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    private String oid;
    private OrdersDetailsPresenter ordersDetailsPresenter;
    private String userid;
    private String token;

    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_tutorship_details);
    }

    @Override
    protected void initViews() {
        tv_actionbar.setText(getResources().getString(R.string.order_details));
        tv_actionbar.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        TextPaint textPaint=tv_actionbar.getPaint();
        textPaint.setFakeBoldText(true);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText(getResources().getString(R.string.mine_complain));
        view_video.setType(ImageViewRoundRect.TYPE_ROUND);
        view_video.setRoundRadius((int)getResources().getDimension(R.dimen.x2));
        ordersDetailsPresenter = new OrdersDetailsPresenter(this);
        ordersDetailsPresenter.onCreate(Constants.BASE_URL);
        ordersDetailsPresenter.attachView(ordersDetailsView);
    }

    @Override
    protected void initEvents() {
        btn_go_pay.setOnClickListener(this);
        iv_actionbar_left.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        if(intent!=null){
            oid = intent.getStringExtra("oid");
            LogUtils.d("oid:"+oid);
        }
        SharedPreferences sharedPreferences=mContext.getSharedPreferences("info", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("userid", "");
        token = sharedPreferences.getString("token", "");
        if(!TextUtils.isEmpty(userid)&&!TextUtils.isEmpty(token)&&!TextUtils.isEmpty(oid)){
            ordersDetailsPresenter.getOrdersDetailsMsg(userid,token,oid);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_go_pay:
                mOrdersData = new OrdersData();
                mOrdersData.setCreate_time((int) mOrdersDetails.getData().getCreate_time());
                mOrdersData.setGrade(mOrdersDetails.getData().getGrade());
                mOrdersData.setJob_pic(mOrdersDetails.getData().getJob_pic());
                mOrdersData.setJobid(mOrdersDetails.getData().getJobid());
                mOrdersData.setLabel(mOrdersDetails.getData().getLabel());
                mOrdersData.setLevel(mOrdersDetails.getData().getLevel());
                mOrdersData.setName(mOrdersDetails.getData().getName());
                mOrdersData.setOid(mOrdersDetails.getData().getOid());
                mOrdersData.setPay_money(mOrdersDetails.getData().getPay_money());
                mOrdersData.setPrice(mOrdersDetails.getData().getPrice());
                mOrdersData.setStatus(mOrdersDetails.getData().getStatus());
                mOrdersData.setSubject(mOrdersDetails.getData().getSubject());
                mOrdersData.setTch_id(mOrdersDetails.getData().getTch_id());
                mOrdersData.setTrait(mOrdersDetails.getData().getTrait());

                goPaymentPopupWindows = new GoPaymentPopupWindows(mContext, mOrdersData);
                if (goPaymentPopupWindows != null) {
                    if (goPaymentPopupWindows.isShowing()) {
                        goPaymentPopupWindows.dismiss();
                    }
                    goPaymentPopupWindows.showAsDropDown(view);
                }
                break;
            case R.id.iv_actionbar_left:
                finish();
                break;
        }
    }
    private OrdersDetailsView ordersDetailsView=new OrdersDetailsView() {
        @Override
        public void onSuccess(OrdersDetails ordersDetails) {
            if(ordersDetails!=null&&ordersDetails.getError()==0){
                LogUtils.d(ordersDetails.toString());
                mOrdersDetails = ordersDetails;
                if(ordersDetails.getData().getLabel()==1){
                    if(ordersDetails.getData().getStatus()==0) {
                        tv_tutorship_state.setText(getResources().getString(R.string.mine_help_checking));
                    }
                    tv_tutorship_money.setVisibility(View.VISIBLE);
                    tv_tutorship_money.setText(getResources().getString(R.string.mine_check_money));

                    tv_tutorship.setText(getResources().getString(R.string.homepage_operation_check));
                    view_video_bg.setVisibility(View.GONE);
                    view_video.setVisibility(View.GONE);
                    iv_video.setVisibility(View.GONE);
                    tv_tutorship_duration.setVisibility(View.GONE);
                    tv_tutorship_duration_default.setVisibility(View.GONE);
                    ll_mine_tutorship_gridview.setVisibility(View.VISIBLE);
                    gridView_mine_tutorship.setVisibility(View.VISIBLE);
                    mineTutorshipGridviewAdapter = new MineTutorshipGridviewAdapter(ordersDetails.getData().getJob_pic(),TutorshipDetailsActivity.this);
                    gridView_mine_tutorship.setAdapter(mineTutorshipGridviewAdapter);

                }else{
                    if(ordersDetails.getData().getStatus()==0){
                        tv_tutorship_state.setText(getResources().getString(R.string.mine_help_tutorship_ing));
                    }
                    tv_tutorship_money.setVisibility(View.VISIBLE);
                    tv_tutorship_money.setText(getResources().getString(R.string.mine_tutorship_money));
                    tv_tutorship.setText(getResources().getString(R.string.homepage_homework_help));
                    view_video_bg.setVisibility(View.VISIBLE);
                    view_video.setVisibility(View.VISIBLE);
                    iv_video.setVisibility(View.VISIBLE);
                    tv_tutorship_duration.setVisibility(View.VISIBLE);
                    tv_tutorship_duration_default.setVisibility(View.VISIBLE);
                    ll_mine_tutorship_gridview.setVisibility(View.GONE);
                    gridView_mine_tutorship.setVisibility(View.GONE);

                }

                if(ordersDetails.getData().getStatus()==0){

                    tv_tutorship_money_default.setVisibility(View.VISIBLE);
                    tv_tutorship_money_default.setText("гд"+ordersDetails.getData().getPrice());
                    tv_amount_paid.setVisibility(View.GONE);
                    tv_amount_paid_default.setVisibility(View.GONE);
                    tv_tutorship_orders_num.setVisibility(View.GONE);
                    tv_tutorship_orders_num_default.setVisibility(View.GONE);
                    tv_tutorship_orders_time.setVisibility(View.GONE);
                    tv_tutorship_orders_time_default.setVisibility(View.GONE);
                    tv_tutorship_pay_way.setVisibility(View.GONE);
                    tv_tutorship_pay_way_default.setVisibility(View.GONE);
                    tv_tutorship_pay_time.setVisibility(View.GONE);
                    tv_tutorship_pay_time_default.setVisibility(View.GONE);
                    btn_contact_again.setVisibility(View.VISIBLE);
                    btn_go_pay.setVisibility(View.GONE);

                }else if(ordersDetails.getData().getStatus()==1){

                    tv_tutorship_money_default.setVisibility(View.VISIBLE);
                    tv_tutorship_money_default.setText("гд"+ordersDetails.getData().getPrice());
                    tv_amount_paid.setVisibility(View.VISIBLE);
                    tv_amount_paid_default.setVisibility(View.VISIBLE);
                    tv_amount_paid_default.setText("гд"+ordersDetails.getData().getPay_money());
                    tv_tutorship_orders_num.setVisibility(View.VISIBLE);
                    tv_tutorship_orders_num_default.setVisibility(View.VISIBLE);
                    tv_tutorship_orders_num_default.setText(ordersDetails.getData().getOid());
                    tv_tutorship_orders_time.setVisibility(View.VISIBLE);
                    tv_tutorship_orders_time_default.setVisibility(View.VISIBLE);
                    try {
                        tv_tutorship_orders_time_default.setText(Utils.longToString((ordersDetails.getData().getCreate_time()*1000L),"yyyy-MM-dd HH:mm"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    tv_tutorship_pay_way.setVisibility(View.GONE);
                    tv_tutorship_pay_way_default.setVisibility(View.GONE);
                    tv_tutorship_pay_time.setVisibility(View.GONE);
                    tv_tutorship_pay_time_default.setVisibility(View.GONE);
                    btn_contact_again.setVisibility(View.INVISIBLE);
                    btn_go_pay.setVisibility(View.VISIBLE);

                }else if(ordersDetails.getData().getStatus()==2){

                    tv_tutorship_money_default.setVisibility(View.VISIBLE);
                    tv_tutorship_money_default.setText("гд"+ordersDetails.getData().getPrice());
                    tv_amount_paid.setVisibility(View.VISIBLE);
                    tv_amount_paid_default.setVisibility(View.VISIBLE);
                    tv_amount_paid_default.setText("гд"+ordersDetails.getData().getPay_money());
                    tv_tutorship_orders_num.setVisibility(View.VISIBLE);
                    tv_tutorship_orders_num_default.setVisibility(View.VISIBLE);
                    tv_tutorship_orders_num_default.setText(ordersDetails.getData().getOid());
                    tv_tutorship_orders_time.setVisibility(View.VISIBLE);
                    tv_tutorship_orders_time_default.setVisibility(View.VISIBLE);
                    try {
                        tv_tutorship_orders_time_default.setText(Utils.longToString((ordersDetails.getData().getCreate_time()*1000L),"yyyy-MM-dd HH:mm"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    tv_tutorship_pay_way.setVisibility(View.VISIBLE);
                    tv_tutorship_pay_way_default.setVisibility(View.VISIBLE);

                    if(ordersDetails.getData().getCate()==1){
                        tv_tutorship_pay_way_default.setText(getResources().getString(R.string.mine_pay_weixin));
                    }else if(ordersDetails.getData().getCate()==2){
                        tv_tutorship_pay_way_default.setText(getResources().getString(R.string.mine_pay_alipay));
                    }else{
                        tv_tutorship_pay_way_default.setText(getResources().getString(R.string.mine_pay_balance));
                    }

                    try {
                        if(ordersDetails.getData().getPay_time()!=0){
                            tv_tutorship_pay_time.setVisibility(View.VISIBLE);
                            tv_tutorship_pay_time_default.setVisibility(View.VISIBLE);
                            tv_tutorship_pay_time_default.setText(Utils.longToString(ordersDetails.getData().getPay_time()*1000L,"yyyy-MM-dd HH:mm"));
                        }else{
                            tv_tutorship_pay_time.setVisibility(View.GONE);
                            tv_tutorship_pay_time_default.setVisibility(View.GONE);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    btn_contact_again.setVisibility(View.VISIBLE);
                    btn_go_pay.setVisibility(View.GONE);
                }else{
                    tv_tutorship_money.setVisibility(View.GONE);
                    tv_tutorship_money_default.setVisibility(View.GONE);
                    tv_amount_paid.setVisibility(View.GONE);
                    tv_amount_paid_default.setVisibility(View.GONE);
                    tv_tutorship_orders_num.setVisibility(View.VISIBLE);
                    tv_tutorship_orders_num_default.setVisibility(View.VISIBLE);
                    tv_tutorship_orders_num_default.setText(ordersDetails.getData().getOid());
                    tv_tutorship_orders_time.setVisibility(View.VISIBLE);
                    tv_tutorship_orders_time_default.setVisibility(View.VISIBLE);
                    try {
                        tv_tutorship_orders_time_default.setText(Utils.longToString((ordersDetails.getData().getCreate_time()*1000L),"yyyy-MM-dd HH:mm"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    tv_tutorship_pay_way.setVisibility(View.GONE);
                    tv_tutorship_pay_way_default.setVisibility(View.GONE);
                    tv_tutorship_pay_time.setVisibility(View.GONE);
                    tv_tutorship_pay_time_default.setVisibility(View.GONE);
                    btn_contact_again.setVisibility(View.VISIBLE);
                    btn_go_pay.setVisibility(View.GONE);

                }

                tv_teacher_honor.setText(ordersDetails.getData().getLevel());
                tv_teacher_class.setText(ordersDetails.getData().getGrade()+"/"+ordersDetails.getData().getSubject());
                tv_teacher_name.setText(ordersDetails.getData().getName());
                RequestOptions options = new RequestOptions()
                        .error(R.drawable.visa)
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(TutorshipDetailsActivity.this).load(ordersDetails.getData().getTrait()).apply(options).into(iv_teacher_visa);

                if(ordersDetails.getData().getStatus()==1){
                    tv_tutorship_state.setText(getResources().getString(R.string.mine_help_obligation));
                }else if(ordersDetails.getData().getStatus()==2){
                    tv_tutorship_state.setText(getResources().getString(R.string.mine_help_done));
                }else if(ordersDetails.getData().getStatus()==3){
                    tv_tutorship_state.setText(getResources().getString(R.string.mine_help_canceled));
                }

            }
        }

        @Override
        public void onError(String result) {

        }
    };
}
