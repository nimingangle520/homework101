package com.shushan.homework101.mine.wallet;

import android.view.View;

import com.shushan.homework101.R;
import com.shushan.homework101.homework.select.NumericWheelAdapter;
import com.shushan.homework101.homework.select.WheelView;

public class WheelTimeSelect {
    private View view;
    private WheelView wv_year;
    private WheelView wv_month;
    public int screenheight;
    private boolean hasSelectTime;
    private static int START_YEAR = 1990, END_YEAR = 2100;
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public WheelTimeSelect(View view, boolean hasSelectTime) {
        super();
        this.view = view;
        this.hasSelectTime = hasSelectTime;
        setView(view);
    }


    /**
     * @Description: TODO 弹出日期时间选择器
     */
    public void initDateTimePicker(int year, int month) {
        // 年
        wv_year = (WheelView) view.findViewById(R.id.year);
        wv_year.setAdapter(new NumericWheelAdapter(
                START_YEAR, END_YEAR));
        wv_year.setCyclic(true);// 可循环滚动
        wv_year.setLabel("年");// 添加文字
        wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据
        // 月
        wv_month = (WheelView) view.findViewById(R.id.month);
        wv_month.setAdapter(new NumericWheelAdapter(
                1, 12));
        wv_month.setCyclic(true);
        wv_month.setLabel("月");
        wv_month.setCurrentItem(month);
        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
        int textSize = 0;
        if (hasSelectTime){
            textSize = (screenheight / 140) * 4;
        }else{
            textSize = (screenheight / 140) * 4;
        }
        wv_month.TEXT_SIZE = textSize;
        wv_year.TEXT_SIZE = textSize;

    }

    public String getTime() {
        StringBuffer sb = new StringBuffer();
        String strMon;
        int month = wv_month.getCurrentItem() + 1;
        if (month <= 9) {
            strMon = "0" + month;
        } else {
            strMon = String.valueOf(month);
        }
        if (!hasSelectTime) {
            sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-")
                    .append(strMon).append("-");
        }else{
            sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-")
                    .append(strMon).append("-");
        }
        return sb.toString();
    }
}
