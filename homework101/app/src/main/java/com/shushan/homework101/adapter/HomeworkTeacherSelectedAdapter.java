package com.shushan.homework101.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shushan.homework101.HttpHelper.service.entity.homework.Level;
import com.shushan.homework101.R;

import java.util.List;

public class HomeworkTeacherSelectedAdapter extends RecyclerView.Adapter<HomeworkTeacherSelectedAdapter.MyViewHolder>{
    private Context context;
    private List<Level> teacherSelectBeans;
    private int mClickPositon;

    public HomeworkTeacherSelectedAdapter(Context context, List<Level> teacherSelectBeans) {
        this.context = context;
        this.teacherSelectBeans = teacherSelectBeans;
    }

    private OnTeacherClickListener onTeacherClickListener;
    public interface OnTeacherClickListener{
        void scrollMid(int position);
    }
    public void setOnTeacherClickListener(OnTeacherClickListener onTeacherClickListener){
        this.onTeacherClickListener = onTeacherClickListener;
    };

    public void changePostion(int mClickPositon){
        if(this.mClickPositon != mClickPositon){
            this.mClickPositon = mClickPositon;
            notifyDataSetChanged();
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View weekView = View.inflate(context, R.layout.homework_check_teacher_select_item, null);
        MyViewHolder viewHolder = new MyViewHolder(weekView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Level teacherSelectBean = teacherSelectBeans.get(position);
        RequestOptions options = new RequestOptions()
                .error(R.drawable.visa)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(teacherSelectBean.getIcon()).apply(options).into(holder.iv_homework_teacher_icon);
        holder.tv_homework_teacher_honor.setText(teacherSelectBean.getName());
        holder.tv_homework_teacher_check_price.setText(teacherSelectBean.getPrice()+context.getResources().getString(R.string.teacher_help_price_units));
        holder.itemView.setTag(position);
        if (mClickPositon ==position){
            //将点击到的控件变大
            /*holder.tvText.setTextSize(15);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) holder.tvText.getLayoutParams();
            layoutParams.width =  (int)Utils.dip2px(60);
            layoutParams.height = (int)Utils.dip2px(60);
            holder.tvText.setLayoutParams(layoutParams);*/
            holder.visaBg.setVisibility(View.VISIBLE);
            holder.iv_homework_teacher_selected.setVisibility(View.VISIBLE);
            holder.tv_homework_teacher_honor.setTextColor(context.getResources().getColor(R.color.font_color_select));
            holder.tv_homework_teacher_check_price.setBackground(context.getResources().getDrawable(R.drawable.homework_teacher_select_tv_select_bg));
            holder.tv_homework_teacher_check_price.setTextColor(context.getResources().getColor(R.color.font_color_select));

        }else {
            //将未点击的控件还原
            /*holder.tvText.setTextSize(13);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) holder.tvText.getLayoutParams();
            layoutParams.width =  (int) Utils.dip2px(50);
            layoutParams.height = (int)Utils.dip2px(50);
            holder.tvText.setLayoutParams(layoutParams);*/
            holder.visaBg.setVisibility(View.GONE);
            holder.iv_homework_teacher_selected.setVisibility(View.GONE);
            holder.tv_homework_teacher_honor.setTextColor(context.getResources().getColor(R.color.font_color_unselected));
            holder.tv_homework_teacher_check_price.setBackground(context.getResources().getDrawable(R.drawable.homework_teacher_select_tv_bg));
            holder.tv_homework_teacher_check_price.setTextColor(context.getResources().getColor(R.color.font_color_unselected));

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTeacherClickListener!=null){
                    onTeacherClickListener.scrollMid(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return teacherSelectBeans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_homework_teacher_honor,tv_homework_teacher_check_price;
        View visaBg;
        ImageView iv_homework_teacher_icon,iv_homework_teacher_selected;
        MyViewHolder(View view) {
            super(view);
            visaBg=view.findViewById(R.id.view_homework_teacher_bg);
            iv_homework_teacher_icon=view.findViewById(R.id.iv_homework_teacher_icon);
            iv_homework_teacher_selected=view.findViewById(R.id.iv_homework_teacher_selected);
            tv_homework_teacher_honor=view.findViewById(R.id.tv_homework_teacher_honor);
            tv_homework_teacher_check_price=view.findViewById(R.id.tv_homework_teacher_check_price);

        }
    }
}
