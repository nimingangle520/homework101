package com.shushan.homework101.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shushan.homework101.R;

public class MineTutorshipGridviewAdapter extends BaseAdapter {
    private String [] job_pic;
    private Context context;

    public MineTutorshipGridviewAdapter(String [] job_pic, Context context) {
        this.job_pic = job_pic;
        this.context = context;
    }

    @Override
    public int getCount() {
        return job_pic.length;
    }

    @Override
    public Object getItem(int position) {
        return job_pic[position];
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
            convertView = LayoutInflater.from(context).inflate(R.layout.mine_tutorship_gridview_item, parent, false);
            viewHolder.iv_mine_tutorship_gridview_item=convertView.findViewById(R.id.iv_mine_tutorship_gridview_item);
            viewHolder.iv_mine_tutorship_gridview_check =  convertView.findViewById(R.id.iv_mine_tutorship_gridview_check);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        RequestOptions options = new RequestOptions()
                .error(R.drawable.homework)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(job_pic[position]).apply(options).into(viewHolder.iv_mine_tutorship_gridview_item);
        viewHolder.iv_mine_tutorship_gridview_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent=new Intent(context, MyHomeworkDetailsActivity.class);
                intent.putExtra("job",jobs);
                context.startActivity(intent);*/
            }
        });
        return convertView;
    }

    public class ViewHolder {
        private ImageView iv_mine_tutorship_gridview_item,iv_mine_tutorship_gridview_check;
    }
}
