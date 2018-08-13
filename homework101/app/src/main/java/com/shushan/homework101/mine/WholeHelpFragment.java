package com.shushan.homework101.mine;

import android.view.View;
import android.widget.ListView;

import com.shushan.homework101.R;
import com.shushan.homework101.adapter.MineHelpListViewAdapter;
import com.shushan.homework101.base.BaseFragment;

import butterknife.Bind;

public class WholeHelpFragment extends BaseFragment {
    @Bind(R.id.lv_mine_help)
    ListView lv_mine_help;
    private HelpModel helpModel;
    private MineHelpListViewAdapter mineHelpListViewAdapter;

    public WholeHelpFragment() {
        // Required empty public constructor
    }
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_whole_help;
    }

    @Override
    public void initViews(View view) {
        helpModel = new HelpModel(mContext);
        mineHelpListViewAdapter = new MineHelpListViewAdapter(mContext, helpModel.getHelpModelList());
        lv_mine_help.setAdapter(mineHelpListViewAdapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvents() {

    }

}
