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

public class TeacherFocusListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Teacher> teacherArrayList;
    public TeacherFocusListViewAdapter(Context context, ArrayList<Teacher> teacherArrayList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.teacher_focus_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_teacher_focus_visa = (ImageView) convertView.findViewById(R.id.iv_teacher_focus_visa);
            viewHolder.tv_teacher_focus_name = (TextView) convertView.findViewById(R.id.tv_teacher_focus_name);
            viewHolder.tv_teacher_focus_grade = (TextView) convertView.findViewById(R.id.tv_teacher_focus_grade);
            viewHolder.tv_teacher_focus_course = (TextView) convertView.findViewById(R.id.tv_teacher_focus_course);
            viewHolder.bt_teacher_focus_cables = (Button) convertView.findViewById(R.id.bt_teacher_focus_cables);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(teacherArrayList!=null&&teacherArrayList.size()>0){
            //viewHolder.iv_teacher_focus_visa.setImageURI(Uri.parse(teacherArrayList.get(position).getIconUri()));
            viewHolder.tv_teacher_focus_name.setText(teacherArrayList.get(position).getName());
            viewHolder.tv_teacher_focus_grade.setText(teacherArrayList.get(position).getGrade());
            viewHolder.tv_teacher_focus_course.setText(teacherArrayList.get(position).getCourse());

        }
        return convertView;
    }
    private class ViewHolder {
        ImageView iv_teacher_focus_visa;
        TextView tv_teacher_focus_name;
        TextView tv_teacher_focus_grade;
        TextView tv_teacher_focus_course;
        Button bt_teacher_focus_cables;
    }
}
