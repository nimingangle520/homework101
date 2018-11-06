package com.shushan.homework101.HttpHelper.service.view;

import com.shushan.homework101.HttpHelper.service.entity.orders.OrdersDetails;

public interface OrdersDetailsView extends View {
    void onSuccess(OrdersDetails ordersDetails);
    void onError(String result);
}
