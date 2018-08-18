package com.shushan.homework101.homework.select;

import android.content.Context;
import android.view.View;

import com.shushan.homework101.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomDate implements Serializable {
    
    
  private static final long serialVersionUID = 1L;  
  public int year;  
  public int month;  
  public int day;  
  public int week;  
    
  public CustomDate(int year, int month, int day){
      if(month > 12){  
          month = 1;  
          year++;  
      }else if(month <1){  
          month = 12;  
          year--;  
      }  
      this.year = year;  
      this.month = month;  
      this.day = day;  
  }  
    
  public CustomDate(){  
      this.year = DateUtils.getYear();
      this.month = DateUtils.getMonth();  
      this.day = DateUtils.getCurrentMonthDay();  
  }  
    
  public static CustomDate modifiDayForObject(CustomDate date,int day){  
      CustomDate modifiDate = new CustomDate(date.year,date.month,day);  
      return modifiDate;  
  }  
  @Override
  public String toString() {
      return year+"-"+month+"-"+day;  
  }  

  public int getYear() {  
      return year;  
  }  

  public void setYear(int year) {  
      this.year = year;  
  }  

  public int getMonth() {  
      return month;  
  }  

  public void setMonth(int month) {  
      this.month = month;  
  }  

  public int getDay() {  
      return day;  
  }  

  public void setDay(int day) {  
      this.day = day;  
  }  

  public int getWeek() {   
      return week;  
  }  

  public void setWeek(int week) {  
      this.week = week;  
  }

    public static class WheelMain {
        private View view;
        private WheelView wv_year;
        public int screenheight;
        private boolean hasSelect;
        List<String> dataList;
        private Context context;
        private String [] arrays;
        public View getView() {
            return view;
        }
        public void setView(View view) {
            this.view = view;
        }
        public WheelMain(View view, boolean hasSelect, Context context) {
            super();
            this.view = view;
            this.hasSelect = hasSelect;
            this.context=context;
            setView(view);
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
                    0, dataList.size(),dataList));
            wv_year.setCyclic(true);// 可循环滚动
            wv_year.setCurrentItem(1);// 初始化时显示的数据
            // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
            int textSize = 0;
            if (hasSelect)
                textSize = (screenheight / 140) * 4;
            else
                textSize = (screenheight / 140) * 4;
            wv_year.TEXT_SIZE = textSize;
        }
        public String getClassName(){
            StringBuffer sb = new StringBuffer();
            if (!hasSelect) {
                sb.append(dataList.get(wv_year.getCurrentItem()));
            }else{
                sb.append(dataList.get(wv_year.getCurrentItem()));
            }
            return sb.toString();
        }
    }

    public static class WheelMainAdapter implements WheelAdapter {

        /** The default min value */
        public static final int DEFAULT_MAX_VALUE = 9;

        /** The default max value */
        private static final int DEFAULT_MIN_VALUE = 0;
        List<String> dateList;
        // Values
        private int minValue;
        private int maxValue;

        // format
        private String format;

        /**
         * Default constructor
         */
    //    public WeekAdapter() {
    //        this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE,dateList);
    //    }

        /**
         * Constructor
         * @param minValue the wheel min value
         * @param maxValue the wheel max value
         */
    //    public WeekAdapter(int minValue, int maxValue, List<String>dateList) {
    //        this(minValue, maxValue,dateList, null);
    //    }

        /**
         * Constructor
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
    //            int value = (minValue + index*15)%60;
    //            if(value ==0){
    //                strMin = "00";
    //            }else{
    //                strMin = String.valueOf(value);
    //            }
                return strMin;
            }
            return null;
        }

        @Override
        public int getItemsCount() {
            return maxValue-minValue;
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