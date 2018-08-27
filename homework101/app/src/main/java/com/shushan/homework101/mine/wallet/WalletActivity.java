package com.shushan.homework101.mine.wallet;

import android.view.View;
import android.widget.Button;
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

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_wallet);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
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
            case R.id.btn_recharge:
                startActivitys(mContext,RechargeActivity.class);
                break;
            case R.id.tv_right:
                startActivitys(mContext,BillActivity.class);
                break;
        }
    }
}
