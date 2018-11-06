package com.shushan.homework101.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shushan.homework101.R;
import com.shushan.homework101.banner.ImageViewRoundRect;
import com.shushan.homework101.bean.TeacherBean;

import java.util.ArrayList;

public class HomepageListViewAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<TeacherBean> teacherArrayList;
    public HomepageListViewAdapter(Context context, ArrayList<TeacherBean> teacherArrayList) {
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
            viewHolder.iv_homepage_teacher = (ImageViewRoundRect) convertView.findViewById(R.id.iv_homepage_teacher);
            viewHolder.tv_homepage_teacher_name = (TextView) convertView.findViewById(R.id.tv_homepage_teacher_name);
            viewHolder.tv_homepage_teacher_honor = (TextView) convertView.findViewById(R.id.tv_homepage_teacher_honor);
            viewHolder.tv_homepage_teacher_grade = (TextView) convertView.findViewById(R.id.tv_homepage_teacher_grade);
            viewHolder.tv_homepage_teacher_help_count = (TextView) convertView.findViewById(R.id.tv_homepage_teacher_help_count);
            viewHolder.tv_homepage_teacher_help_price = (TextView) convertView.findViewById(R.id.tv_homepage_teacher_help_price);
            viewHolder.iv_homepage_cables_teacher = (ImageView) convertView.findViewById(R.id.iv_homepage_cables_teacher);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(teacherArrayList!=null&&teacherArrayList.size()>0){
            RequestOptions options = new RequestOptions()
                    .error(R.drawable.visa)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(teacherArrayList.get(position).getIconUri()).apply(options).into(viewHolder.iv_homepage_teacher);

            viewHolder.tv_homepage_teacher_name.setText(teacherArrayList.get(position).getName());
            viewHolder.tv_homepage_teacher_grade.setText(teacherArrayList.get(position).getGrade());
            if(TextUtils.isEmpty(teacherArrayList.get(position).getHonor())){
                viewHolder.tv_homepage_teacher_honor.setVisibility(View.GONE);
            }else{
                viewHolder.tv_homepage_teacher_honor.setVisibility(View.VISIBLE);
                viewHolder.tv_homepage_teacher_honor.setText(teacherArrayList.get(position).getHonor());
            }
            viewHolder.tv_homepage_teacher_honor.setText(teacherArrayList.get(position).getHonor());
            viewHolder.tv_homepage_teacher_help_count.setText(teacherArrayList.get(position).getHelpCount());
            viewHolder.tv_homepage_teacher_help_price.setText(teacherArrayList.get(position).getHelpPrice());

        }
        return convertView;
    }
    private class ViewHolder {
        ImageViewRoundRect iv_homepage_teacher;
        TextView tv_homepage_teacher_name;
        TextView tv_homepage_teacher_honor;
        TextView tv_homepage_teacher_grade;
        TextView tv_homepage_teacher_help_count;
        TextView tv_homepage_teacher_help_price;
        ImageView iv_homepage_cables_teacher;
    }
}
