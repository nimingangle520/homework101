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
import com.shushan.homework101.HttpHelper.service.entity.teachers.Recommend;
import com.shushan.homework101.R;
import com.shushan.homework101.banner.ImageViewRoundRect;

import java.util.ArrayList;
import java.util.Arrays;

public class TeacherRecommendListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Recommend> recommends;
    public TeacherRecommendListViewAdapter(Context context, ArrayList<Recommend> recommends) {
        this.context=context;
        this.recommends=recommends;
    }

    @Override
    public int getCount() {
        return recommends.size();
    }

    @Override
    public Object getItem(int i) {
        return recommends.get(i);
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
            viewHolder.iv_teacher_via = (ImageViewRoundRect) convertView.findViewById(R.id.iv_teacher_via);
            viewHolder.tv_teacher_name = (TextView) convertView.findViewById(R.id.tv_teacher_name);
            viewHolder.tv_teacher_class = (TextView) convertView.findViewById(R.id.tv_teacher_class);
            viewHolder.tv_teacher_subject = (TextView) convertView.findViewById(R.id.tv_teacher_subject);
            viewHolder.tv_teacher_help_price_default = (TextView) convertView.findViewById(R.id.tv_teacher_help_price_default);
            viewHolder.tv_teacher_cables = (TextView) convertView.findViewById(R.id.tv_teacher_cables);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(recommends!=null&&recommends.size()>0){

            RequestOptions options = new RequestOptions()
                    .error(R.drawable.visa)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(recommends.get(position).getTrait()).apply(options).into(viewHolder.iv_teacher_via);
            viewHolder.tv_teacher_name.setText(recommends.get(position).getTch_name());
            viewHolder.tv_teacher_class.setText((Arrays.toString(recommends.get(position).getGrade()))
                    .replaceAll(",","/")
                    .replace("[","")
                    .replace("]",""));
            viewHolder.tv_teacher_subject.setText(recommends.get(position).getSubject());
            viewHolder.tv_teacher_help_price_default.setText(recommends.get(position).getGuide_price()+context.getResources().getString(R.string.teacher_help_price_units));

        }
        return convertView;
    }
    private class ViewHolder {
        ImageViewRoundRect iv_teacher_via;
        TextView tv_teacher_name;
        TextView tv_teacher_class;
        TextView tv_teacher_subject;
        TextView tv_teacher_help_price_default;
        TextView tv_teacher_cables;
    }
}
