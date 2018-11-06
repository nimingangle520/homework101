package com.shushan.homework101.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shushan.homework101.HttpHelper.service.entity.homepage.Job;
import com.shushan.homework101.R;
import com.shushan.homework101.banner.ImageViewRoundRect;
import com.shushan.homework101.homepage.MyHomeworkDetailsActivity;

public class MineHomeworkGrideViewAdapter extends BaseAdapter{
    private Job jobs;
    private Context context;

    public MineHomeworkGrideViewAdapter(Job jobs, Context context) {
        this.jobs = jobs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return jobs.getImages().length;
    }

    @Override
    public Object getItem(int position) {
        return jobs.getImages()[position];
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
            convertView = LayoutInflater.from(context).inflate(R.layout.mine_homework_gridview_item, parent, false);
            viewHolder.rl_mine_homework_grid_view=convertView.findViewById(R.id.rl_mine_homework_grid_view);
            viewHolder.iv_mine_homework = (ImageViewRoundRect) convertView.findViewById(R.id.iv_mine_homework);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv_mine_homework.setType(ImageViewRoundRect.TYPE_ROUND);
        viewHolder.iv_mine_homework.setRoundRadius((int) context.getResources().getDimension(R.dimen.x2));
        RequestOptions options = new RequestOptions()
                .error(R.drawable.homework)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(jobs.getImages()[position]).apply(options).into(viewHolder.iv_mine_homework);
        viewHolder.rl_mine_homework_grid_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MyHomeworkDetailsActivity.class);
                intent.putExtra("job",jobs);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public class ViewHolder {
        private RelativeLayout rl_mine_homework_grid_view;
        public ImageViewRoundRect iv_mine_homework;

    }

}
