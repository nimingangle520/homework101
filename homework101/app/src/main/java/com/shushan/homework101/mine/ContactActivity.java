package com.shushan.homework101.mine;

import android.text.TextPaint;
import android.util.TypedValue;
import android.widget.TextView;

import com.shushan.homework101.R;
import com.shushan.homework101.base.BaseActivity;

import butterknife.Bind;

public class ContactActivity extends BaseActivity {
@Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_contact);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        tv_actionbar.setText(getResources().getString(R.string.mine_contact));
        tv_actionbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        TextPaint textPaint = tv_actionbar.getPaint();
        textPaint.setFakeBoldText(true);
    }

    @Override
    protected void initEvents() {

    }
}
