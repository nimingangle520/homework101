package com.shushan.homework101.mine;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shushan.homework101.Constants;
import com.shushan.homework101.HttpHelper.service.entity.mine.GetUserInfo;
import com.shushan.homework101.HttpHelper.service.presenter.GetUserInfoPresenter;
import com.shushan.homework101.HttpHelper.service.view.GetUserInfoView;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.banner.ImageViewRoundRect;
import com.shushan.homework101.base.BaseFragment;
import com.shushan.homework101.mine.tutorship.HelpActivity;
import com.shushan.homework101.mine.wallet.WalletActivity;

import butterknife.Bind;

public class FragmentMine extends BaseFragment implements View.OnClickListener{
    @Bind(R.id.rl_mine_via)
    RelativeLayout rl_mine_via;
    @Bind(R.id.rl_mine_help)
    RelativeLayout rl_mine_help;
    @Bind(R.id.rl_mine_wallet)
    RelativeLayout rl_mine_wallet;
    @Bind(R.id.rl_mine_use_help)
    RelativeLayout rl_mine_use_help;
    @Bind(R.id.rl_mine_share)
    RelativeLayout rl_mine_share;
    @Bind(R.id.rl_mine_contact)
    RelativeLayout rl_mine_contact;
    @Bind(R.id.rl_mine_set)
    RelativeLayout rl_mine_set;
    @Bind(R.id.iv_stu_visa)
    ImageViewRoundRect iv_stu_visa;
    @Bind(R.id.tv_stu_name)
    TextView tv_stu_name;
    @Bind(R.id.tv_stu_class)
    TextView tv_stu_class;
    private SharedPreferences sharedPreferences;
    private String userid;
    private String token;
    private GetUserInfoPresenter getUserInfoPresenter;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initViews(View view) {
        View mStatusBar = view.findViewById(R.id.mine_fillStatusBarView);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mStatusBar.getLayoutParams();
        lp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        lp.height = getStatusBarHeight(mContext);
        mStatusBar.setLayoutParams(lp);
        getUserInfoPresenter = new GetUserInfoPresenter(mContext);
        getUserInfoPresenter.onCreate(Constants.BASE_URL);
        getUserInfoPresenter.attachView(getUserInfoView);
    }

    @Override
    protected void initData() {
        sharedPreferences = mContext.getSharedPreferences("info", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("userid","");
        token = sharedPreferences.getString("token","");
    }

    @Override
    public void initEvents() {
        rl_mine_via.setOnClickListener(this);
        rl_mine_help.setOnClickListener(this);
        rl_mine_wallet.setOnClickListener(this);
        rl_mine_use_help.setOnClickListener(this);
        rl_mine_share.setOnClickListener(this);
        rl_mine_contact.setOnClickListener(this);
        rl_mine_set.setOnClickListener(this);
    }
    private GetUserInfoView getUserInfoView=new GetUserInfoView() {
        @Override
        public void onSuccess(GetUserInfo getUserInfo) {
            if(getUserInfo!=null&&getUserInfo.getError()==0){
                LogUtils.d(getUserInfo.toString());

                RequestOptions options = new RequestOptions()
                        .error(R.drawable.visa)
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(mContext).load(getUserInfo.getData().getTrait()).apply(options).into(iv_stu_visa);
                tv_stu_name.setText(getUserInfo.getData().getUsername());

                tv_stu_class.setText(getUserInfo.getData().getGrade()+"/"+getResources().getString(R.string.my_chinese));
            }
        }

        @Override
        public void onError(String result) {

        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_mine_via:
                BaseFragment.startActivitys(mContext,PersonalInformationActivity.class);
                break;
            case R.id.rl_mine_help:
                BaseFragment.startActivitys(mContext,HelpActivity.class);
                break;
            case R.id.rl_mine_wallet:
                startActivitys(mContext, WalletActivity.class);
                break;
            case R.id.rl_mine_use_help:
                break;
            case R.id.rl_mine_share:
                break;
            case R.id.rl_mine_contact:
                BaseFragment.startActivitys(mContext,ContactActivity.class);
                break;
            case R.id.rl_mine_set:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        changeStatusBarTextImgColor(false);
        if(!TextUtils.isEmpty(userid)&&!TextUtils.isEmpty(token)){
            getUserInfoPresenter.getUserInfoMsg(userid,token);
        }
    }
}
