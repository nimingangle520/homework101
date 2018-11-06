package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.orders.Orders;

public interface OrdersView extends View{

    void onSuccess(Orders orders);
    void onError(String result);
}

