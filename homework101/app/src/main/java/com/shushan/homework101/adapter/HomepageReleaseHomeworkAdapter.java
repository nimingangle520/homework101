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
import com.shushan.homework101.HttpHelper.service.entity.homepage.Job;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.Utils;
import com.shushan.homework101.banner.ImageViewRoundRect;

import java.text.ParseException;
import java.util.ArrayList;

public class HomepageReleaseHomeworkAdapter extends BaseAdapter {
    private ArrayList<Job> releaseHomworkBeanList;
    private Context context;

    public HomepageReleaseHomeworkAdapter(ArrayList<Job> releaseHomworkBeanList, Context context) {
        this.releaseHomworkBeanList = releaseHomworkBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return releaseHomworkBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return releaseHomworkBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.homepage_release_homework_item, parent, false);

            viewHolder.iv_homepage_release_homework = (ImageViewRoundRect) convertView.findViewById(R.id.iv_homepage_release_homework);
            viewHolder.tv_homepage_release_homework_type = (TextView) convertView.findViewById(R.id.tv_homepage_release_homework_type);
            viewHolder.tv_homepage_release_homework_time = (TextView) convertView.findViewById(R.id.tv_homepage_release_homework_time);
            viewHolder.tv_homepage_release_homework_state = (TextView) convertView.findViewById(R.id.tv_homepage_release_homework_state);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv_homepage_release_homework.setType(ImageViewRoundRect.TYPE_ROUND);
        viewHolder.iv_homepage_release_homework.setRoundRadius((int) context.getResources().getDimension(R.dimen.x4));

        RequestOptions options = new RequestOptions()
                .error(R.drawable.visa)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(releaseHomworkBeanList.get(position).getImages()[0]).apply(options).into(viewHolder.iv_homepage_release_homework);
        if(releaseHomworkBeanList.get(position).getLabel()==1){
            viewHolder.tv_homepage_release_homework_type.setText(context.getResources().getString(R.string.homepage_operation_check));
            try {
                viewHolder.tv_homepage_release_homework_time.setText(Utils.longToString(releaseHomworkBeanList.get(position).getCreate_time()*1000L,"MM-dd HH:mm"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            viewHolder.tv_homepage_release_homework_type.setText(context.getResources().getString(R.string.homepage_homework_help));
            try {
                viewHolder.tv_homepage_release_homework_time.setText(Utils.longToString(releaseHomworkBeanList.get(position).getStart_time()*1000L,"MM-dd HH:mm"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(releaseHomworkBeanList.get(position).getIs_receive()==1){
            viewHolder.tv_homepage_release_homework_state.setText(context.getResources().getString(R.string.homepage_release_homework_not_answer_sheet));
        }else{
            viewHolder.tv_homepage_release_homework_state.setText(context.getResources().getString(R.string.homepage_release_homework_state));

        }
        return convertView;
    }

    public class ViewHolder {
        public ImageViewRoundRect iv_homepage_release_homework;
        public TextView tv_homepage_release_homework_type, tv_homepage_release_homework_time, tv_homepage_release_homework_state;

    }

}
