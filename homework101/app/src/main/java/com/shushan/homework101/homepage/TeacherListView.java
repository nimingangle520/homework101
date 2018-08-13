package com.shushan.homework101.homepage;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class TeacherListView extends ListView{
    public TeacherListView(Context context) {
        super(context);
    }

    public TeacherListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TeacherListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TeacherListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

  /*  @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }*/

}
