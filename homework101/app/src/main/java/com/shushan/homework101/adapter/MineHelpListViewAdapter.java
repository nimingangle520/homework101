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

public class MineHelpListViewAdapter  extends BaseAdapter{
    private Context context;
    private ArrayList<Teacher> teacherArrayList;
    public MineHelpListViewAdapter(Context context, ArrayList<Teacher> teacherArrayList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.mine_help_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_mine_help_teacher = (ImageView) convertView.findViewById(R.id.iv_mine_help_teacher);
            viewHolder.tv_mine_help_teacher_name = (TextView) convertView.findViewById(R.id.tv_mine_help_teacher_name);
            viewHolder.tv_mine_help_teacher_honor = (TextView) convertView.findViewById(R.id.tv_mine_help_teacher_honor);
            viewHolder.tv_mine_help_teacher_course = (TextView) convertView.findViewById(R.id.tv_mine_help_teacher_course);
            viewHolder.tv_mine_help_teacher_help_price = (TextView) convertView.findViewById(R.id.tv_mine_help_teacher_help_price);
            viewHolder.mine_help_pay = (Button) convertView.findViewById(R.id.mine_help_pay);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(teacherArrayList!=null&&teacherArrayList.size()>0){
            //viewHolder.iv_teacher_via.setImageURI(Uri.parse(teacherArrayList.get(position).getIconUri()));
            viewHolder.tv_mine_help_teacher_name.setText(teacherArrayList.get(position).getName());
            viewHolder.tv_mine_help_teacher_honor.setText(teacherArrayList.get(position).getHonor());
            viewHolder.tv_mine_help_teacher_course.setText(teacherArrayList.get(position).getCourse());
            viewHolder.tv_mine_help_teacher_help_price.setText(teacherArrayList.get(position).getCheckPrice());

        }
        return convertView;
    }
    private class ViewHolder {
        ImageView iv_mine_help_teacher;
        TextView tv_mine_help_teacher_name;
        TextView tv_mine_help_teacher_honor;
        TextView tv_mine_help_teacher_course;
        TextView tv_mine_help_teacher_help_price;
        Button mine_help_pay;
    }
}
