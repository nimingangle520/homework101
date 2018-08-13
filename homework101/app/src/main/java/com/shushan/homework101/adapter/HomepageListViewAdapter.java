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
import com.shushan.homework101.bean.Teacher;

import java.util.ArrayList;

public class HomepageListViewAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Teacher> teacherArrayList;
    public HomepageListViewAdapter(Context context, ArrayList<Teacher> teacherArrayList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.homepage_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_homepage_teacher = (ImageView) convertView.findViewById(R.id.iv_homepage_teacher);
            viewHolder.tv_homepage_teacher_name = (TextView) convertView.findViewById(R.id.tv_homepage_teacher_name);
            viewHolder.bt_homepage_teacher_honor = (Button) convertView.findViewById(R.id.bt_homepage_teacher_honor);
            viewHolder.tv_homepage_teacher_grade = (TextView) convertView.findViewById(R.id.tv_homepage_teacher_grade);
            viewHolder.tv_homepage_teacher_help_count = (TextView) convertView.findViewById(R.id.tv_homepage_teacher_help_count);
            viewHolder.tv_homepage_teacher_help_price = (TextView) convertView.findViewById(R.id.tv_homepage_teacher_help_price);
            viewHolder.bt_homepage_cables_teacher = (Button) convertView.findViewById(R.id.bt_homepage_cables_teacher);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(teacherArrayList!=null&&teacherArrayList.size()>0){
            //viewHolder.iv_homepage_teacher.setImageURI(Uri.parse(teacherArrayList.get(position).getIconUri()));
            viewHolder.tv_homepage_teacher_name.setText(teacherArrayList.get(position).getName());
            viewHolder.tv_homepage_teacher_grade.setText(teacherArrayList.get(position).getGrade());
            viewHolder.bt_homepage_teacher_honor.setText(teacherArrayList.get(position).getHonor());
            viewHolder.tv_homepage_teacher_help_count.setText(teacherArrayList.get(position).getHelpCount());
            viewHolder.tv_homepage_teacher_help_price.setText(teacherArrayList.get(position).getHelpPrice());

        }
        return convertView;
    }
    private class ViewHolder {
        ImageView iv_homepage_teacher;
        TextView tv_homepage_teacher_name;
        Button bt_homepage_teacher_honor;
        TextView tv_homepage_teacher_grade;
        TextView tv_homepage_teacher_help_count;
        TextView tv_homepage_teacher_help_price;
        Button bt_homepage_cables_teacher;
    }
}
