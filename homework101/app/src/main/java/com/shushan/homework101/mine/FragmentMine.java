package com.shushan.homework101.mine;

import android.view.View;
import android.widget.RelativeLayout;

import com.shushan.homework101.R;
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
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    protected void initData() {

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
}
