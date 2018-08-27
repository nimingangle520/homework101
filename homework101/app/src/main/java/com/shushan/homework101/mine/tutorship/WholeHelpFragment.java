package com.shushan.homework101.mine.tutorship;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.adapter.MineHelpListViewAdapter;
import com.shushan.homework101.base.BaseFragment;

import butterknife.Bind;

public class WholeHelpFragment extends BaseFragment implements MineTutorshipWholeInterface,AdapterView.OnItemClickListener{
    @Bind(R.id.lv_mine_help)
    ListView lv_mine_help;
    private HelpModel helpModel;
    private MineHelpListViewAdapter mineHelpListViewAdapter;
    private Button mine_help_pay;
    private GoPaymentPopupWindows goPaymentPopupWindows;
    private View view;
    public WholeHelpFragment() {
        // Required empty public constructor
    }
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_whole_help;
    }

    @Override
    public void initViews(View view) {
        this.view=view;
        goPaymentPopupWindows = new GoPaymentPopupWindows(mContext);
        helpModel = new HelpModel(mContext);
        mineHelpListViewAdapter = new MineHelpListViewAdapter(mContext, helpModel.getHelpModelList());
        lv_mine_help.setAdapter(mineHelpListViewAdapter);
        mineHelpListViewAdapter.setOnItemClickListener(this);
        lv_mine_help.setOnItemClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvents() {

    }

    @Override
    public void onPayClick() {
        LogUtils.d("onPayClick");
        if (goPaymentPopupWindows != null) {
            if (goPaymentPopupWindows.isShowing()) {
                goPaymentPopupWindows.dismiss();
            }
            goPaymentPopupWindows.showAsDropDown(view);
        }
    }
    @Override
    public void onPlaybackClick() {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        LogUtils.d("onItemClick  adapter");
        startActivitys(mContext,TutorshipDetailsActivity.class);
    }
}
