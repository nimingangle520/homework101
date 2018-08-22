package com.shushan.homework101.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.homework101.R;
import com.shushan.homework101.bean.TeacherBean;

import java.util.ArrayList;

public class TeacherRecommendListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TeacherBean> teacherArrayList;
    public TeacherRecommendListViewAdapter(Context context, ArrayList<TeacherBean> teacherArrayList) {
        this.context=context;
        this.teacherArrayList=teacherArrayList;
    }

    @Override
    public int getCount() {
        return teacherArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return teacherArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.teacher_recommend_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_teacher_via = (ImageView) convertView.findViewById(R.id.iv_teacher_via);
            viewHolder.tv_teacher_name = (TextView) convertView.findViewById(R.id.tv_teacher_name);
            viewHolder.tv_teacher_course = (TextView) convertView.findViewById(R.id.tv_teacher_course);
            viewHolder.tv_teacher_exp = (TextView) convertView.findViewById(R.id.tv_teacher_exp);
            viewHolder.tv_teacher_help_count = (TextView) convertView.findViewById(R.id.tv_teacher_help_count);
            viewHolder.tv_teacher_help_price = (TextView) convertView.findViewById(R.id.tv_teacher_help_price);
            viewHolder.bt_teacher_cables = (Button) convertView.findViewById(R.id.bt_teacher_cables);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(teacherArrayList!=null&&teacherArrayList.size()>0){
            //viewHolder.iv_teacher_via.setImageURI(Uri.parse(teacherArrayList.get(position).getIconUri()));
            viewHolder.tv_teacher_name.setText(teacherArrayList.get(position).getName());
            viewHolder.tv_teacher_course.setText(teacherArrayList.get(position).getCourse());
            viewHolder.tv_teacher_exp.setText(teacherArrayList.get(position).getExp());
            viewHolder.tv_teacher_help_count.setText(teacherArrayList.get(position).getHelpCount());
            viewHolder.tv_teacher_help_price.setText(teacherArrayList.get(position).getHelpPrice());

        }
        return convertView;
    }
    private class ViewHolder {
        ImageView iv_teacher_via;
        TextView tv_teacher_name;
        TextView tv_teacher_course;
        TextView tv_teacher_exp;
        TextView tv_teacher_help_count;
        TextView tv_teacher_help_price;
        Button bt_teacher_cables;
    }
}
