package com.shushan.homework101.mine;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.shushan.homework101.R;
import com.shushan.homework101.homework.select.WheelAdapter;
import com.shushan.homework101.homework.select.WheelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WheelGrade {
    private View view;
    private WheelView wv_year;
    public int screenheight;
    private boolean hasSelect;
    List<String> dataList;
    private Context context;
    private String grade_position;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public WheelGrade(View view, boolean hasSelect, Context context) {
        super();
        this.view = view;
        this.hasSelect = hasSelect;
        this.context = context;
        setView(view);

        SharedPreferences sharedPreferences = context.getSharedPreferences("info", Context.MODE_PRIVATE);
        grade_position = sharedPreferences.getString("grade_position", "");
    }

    /**
     * @Description: TODO 弹出选择器
     */
    public void initDateTimePicker(String[] arrays) {
        // 年
        wv_year = (WheelView) view.findViewById(R.id.year);
        dataList = new ArrayList<String>();
        dataList.addAll(Arrays.asList(arrays));
        wv_year.setAdapter(new WheelMainAdapter(
                0, dataList.size(), dataList));
        wv_year.setCyclic(true);// 可循环滚动

        for (int i = 0; i < dataList.size(); i++) {

            if (Integer.parseInt(grade_position) == i) {
                wv_year.setCurrentItem(i-1);
            }
        }
        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
        int textSize = 0;
        if (hasSelect)
            textSize = (screenheight / 140) * 4;
        else
            textSize = (screenheight / 140) * 4;
        wv_year.TEXT_SIZE = textSize;
    }

    public String getName() {
        StringBuffer sb = new StringBuffer();
        if (!hasSelect) {
            sb.append(dataList.get(wv_year.getCurrentItem()));
        } else {
            sb.append(dataList.get(wv_year.getCurrentItem()));
        }
        return sb.toString();
    }

    public static class WheelMainAdapter implements WheelAdapter {

        /**
         * The default min value
         */
        public static final int DEFAULT_MAX_VALUE = 9;

        /**
         * The default max value
         */
        private static final int DEFAULT_MIN_VALUE = 0;
        List<String> dateList;
        // Values
        private int minValue;
        private int maxValue;
        // format
        private String format;

        /**
         * Constructor
         *
         * @param minValue the wheel min value
         * @param maxValue the wheel max value
         */
        public WheelMainAdapter(int minValue, int maxValue, List<String> dateList) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.dateList = dateList;
        }

        @Override
        public String getItem(int index) {
            if (index >= 0 && index < getItemsCount()) {
                String strMin = dateList.get(index);
                return strMin;
            }
            return null;
        }

        @Override
        public int getItemsCount() {
            return maxValue - minValue;
        }

        @Override
        public int getMaximumLength() {
            int max = Math.max(Math.abs(maxValue), Math.abs(minValue));
            int maxLen = Integer.toString(max).length();
            if (minValue < 0) {
                maxLen++;
            }
            return maxLen;
        }
    }
}