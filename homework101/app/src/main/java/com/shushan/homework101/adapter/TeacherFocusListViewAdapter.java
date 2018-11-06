package com.shushan.homework101.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shushan.homework101.HttpHelper.service.entity.teachers.Attention;
import com.shushan.homework101.R;
import com.shushan.homework101.banner.ImageViewRoundRect;

import java.util.ArrayList;
import java.util.Arrays;

public class TeacherFocusListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Attention> attentions;
    public TeacherFocusListViewAdapter(Context context, ArrayList<Attention> attentions) {
        this.context=context;
        this.attentions=attentions;
    }

    @Override
    public int getCount() {
        return attentions.size();
    }

    @Override
    public Object getItem(int i) {
        return attentions.get(i);
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
            viewHolder.iv_teacher_focus_visa = (ImageViewRoundRect) convertView.findViewById(R.id.iv_teacher_focus_visa);
            viewHolder.tv_teacher_focus_name = (TextView) convertView.findViewById(R.id.tv_teacher_focus_name);
            viewHolder.tv_teacher_focus_course = (TextView) convertView.findViewById(R.id.tv_teacher_focus_course);
            viewHolder.tv_teacher_focus_cables = (TextView) convertView.findViewById(R.id.tv_teacher_focus_cables);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(attentions!=null&&attentions.size()>0){
            RequestOptions options = new RequestOptions()
                    .error(R.drawable.visa)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(attentions.get(position).getTrait()).apply(options).into(viewHolder.iv_teacher_focus_visa);
            viewHolder.tv_teacher_focus_name.setText(attentions.get(position).getTch_name());
            viewHolder.tv_teacher_focus_course.setText((Arrays.toString(attentions.get(position).getGrade()))
                    .replaceAll(",","/")
                    .replace("[","")
                    .replace("]","")+"/"+
                    attentions.get(position).getSubject());

        }
        return convertView;
    }
    private class ViewHolder {
        ImageViewRoundRect iv_teacher_focus_visa;
        TextView tv_teacher_focus_name;
        TextView tv_teacher_focus_course;
        TextView tv_teacher_focus_cables;
    }
}
