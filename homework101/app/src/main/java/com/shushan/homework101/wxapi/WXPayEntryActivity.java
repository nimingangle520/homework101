package com.shushan.homework101.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    private SharedPreferences sharedPreferences;
    private String appid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);

        sharedPreferences = this.getSharedPreferences("info", Context.MODE_PRIVATE);
        if(sharedPreferences !=null){

            appid = sharedPreferences.getString("appid","");

            if(!TextUtils.isEmpty(appid)){

                api = WXAPIFactory.createWXAPI(this, appid);
                api.handleIntent(getIntent(), this);
            }
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        LogUtils.d( "onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
            } else {
                LogUtils.e( "onResp: " + resp.errCode);
                Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}
