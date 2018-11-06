package com.shushan.homework101.mine.wallet;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.homework101.R;
import com.shushan.homework101.base.BaseActivity;

import butterknife.Bind;

public class WalletActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.btn_recharge)
    Button btn_recharge;
    @Bind(R.id.tv_right)
    TextView tv_right;
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.iv_actionbar_left)
    ImageView iv_actionbar_left;
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_wallet);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
      iv_actionbar_left.setOnClickListener(this);
      tv_right.setVisibility(View.VISIBLE);
      tv_right.setOnClickListener(this);
      btn_recharge.setOnClickListener(this);
      tv_actionbar.setText(getResources().getString(R.string.mine_wallet));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_actionbar_left:
                finish();
                break;
            case R.id.btn_recharge:
                startActivitys(mContext,RechargeActivity.class);
                break;
            case R.id.tv_right:
                startActivitys(mContext,BillActivity.class);
                break;
        }
    }
}
