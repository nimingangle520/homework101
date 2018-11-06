package com.shushan.homework101.mine.tutorship;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.shushan.homework101.Constants;
import com.shushan.homework101.HttpHelper.service.entity.orders.Orders;
import com.shushan.homework101.HttpHelper.service.entity.orders.OrdersData;
import com.shushan.homework101.HttpHelper.service.presenter.OrdersPresenter;
import com.shushan.homework101.HttpHelper.service.view.OrdersView;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.LogUtils;
import com.shushan.homework101.adapter.MineHelpListViewAdapter;
import com.shushan.homework101.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class WholeHelpFragment extends BaseFragment {
    @Bind(R.id.lv_mine_help)
    ListView lv_mine_help;
    private MineHelpListViewAdapter mineHelpListViewAdapter;
    private Button mine_help_pay;
    private GoPaymentPopupWindows goPaymentPopupWindows;
    private View view;
    private int mine_order_label;
    private Map<String, String> mapRequest;
    private OrdersPresenter ordersPresenter;
    private String userid;
    private String token;
    private Orders mOrders;

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
        mapRequest = new HashMap<>();
        ordersPresenter = new OrdersPresenter(getContext());
        ordersPresenter.onCreate(Constants.BASE_URL);
        ordersPresenter.attachView(ordersView);

    }

    @Override
    protected void initData() {
     SharedPreferences sharedPreferences=mContext.getSharedPreferences("info", Context.MODE_PRIVATE);
     mine_order_label = sharedPreferences.getInt("mine_order_label",1);
     userid = sharedPreferences.getString("userid", "");
     token = sharedPreferences.getString("token", "");
    }

    @Override
    public void initEvents() {

          if(!TextUtils.isEmpty(userid)&&!TextUtils.isEmpty(token)&&!TextUtils.isEmpty(mine_order_label+"")){
              mapRequest.put("userid",userid);
              mapRequest.put("token",token);
              mapRequest.put("label",mine_order_label+"");
              //cate = all, doing, nopay,complete,cancel
              mapRequest.put("cata","all");
              mapRequest.put("page","0");
              ordersPresenter.getOrdersMsg(mapRequest);
          }

    }
    private OrdersView ordersView=new OrdersView() {
        @Override
        public void onSuccess(final Orders orders) {
           if(orders!=null){
               LogUtils.d(orders.toString());
               mOrders=orders;
               if(orders.getData()!=null&&orders.getData().size()>0){
                   ArrayList<OrdersData> ordersDatas=orders.getData();

                   mineHelpListViewAdapter = new MineHelpListViewAdapter(mContext, ordersDatas);
                   lv_mine_help.setAdapter(mineHelpListViewAdapter);

                   mineHelpListViewAdapter.setOnItemClickListener(new MineTutorshipWholeInterface() {
                       @Override
                       public void onPayClick(int potition) {
                           LogUtils.d("onPayClick"+"\n"+orders.getData().get(potition).toString());
                           goPaymentPopupWindows = new GoPaymentPopupWindows(mContext,orders.getData().get(potition));
                           if (goPaymentPopupWindows != null) {
                               if (goPaymentPopupWindows.isShowing()) {
                                   goPaymentPopupWindows.dismiss();
                               }
                               goPaymentPopupWindows.showAsDropDown(view);
                           }
                       }

                       @Override
                       public void onPlaybackClick(int potition) {

                       }
                   });

               }

           }
        }

        @Override
        public void onError(String result) {

        }
    };
}
