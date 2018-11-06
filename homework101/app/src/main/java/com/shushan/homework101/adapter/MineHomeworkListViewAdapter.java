package com.shushan.homework101.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shushan.homework101.HttpHelper.service.entity.homepage.Job;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.Utils;

import java.text.ParseException;
import java.util.ArrayList;

public class MineHomeworkListViewAdapter  extends BaseAdapter{
    private Context context;
    private ArrayList<Job> releaseHomeworkBeans;
    private boolean isCheckType;
    public MineHomeworkListViewAdapter(Context context, ArrayList<Job> releaseHomeworkBeans,boolean isCheckType) {
        this.context=context;
        this.releaseHomeworkBeans=releaseHomeworkBeans;
        this.isCheckType=isCheckType;
    }

    @Override
    public int getCount() {
        return releaseHomeworkBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return releaseHomeworkBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
       ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.mine_homework_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_mine_homework_class = (TextView) convertView.findViewById(R.id.tv_mine_homework_class);
            viewHolder.tv_mine_homework_state = (TextView) convertView.findViewById(R.id.tv_mine_homework_state);
            viewHolder.grid_mine_homework = (GridView) convertView.findViewById(R.id.grid_mine_homework);
            viewHolder.tv_mine_homework_money_default = (TextView) convertView.findViewById(R.id.tv_mine_homework_money_default);
            viewHolder.tv_mine_homework_money=convertView.findViewById(R.id.tv_mine_homework_money);
            viewHolder.iv_mine_homework_time=convertView.findViewById(R.id.iv_mine_homework_time);
            viewHolder.tv_mine_homework_time=convertView.findViewById(R.id.tv_mine_homework_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(releaseHomeworkBeans!=null&&releaseHomeworkBeans.size()>0){
            viewHolder.tv_mine_homework_class.setText(releaseHomeworkBeans.get(position).getGrade()+"/"+releaseHomeworkBeans.get(position).getSubject());
            if(releaseHomeworkBeans.get(position).getIs_receive()==1){
                viewHolder.tv_mine_homework_state.setText(context.getResources().getString(R.string.homepage_release_homework_not_answer_sheet));
            }else{
                viewHolder.tv_mine_homework_state.setText(context.getResources().getString(R.string.homepage_release_homework_state));
            }
            if(isCheckType){
                viewHolder.iv_mine_homework_time.setVisibility(View.GONE);
                viewHolder.tv_mine_homework_time.setVisibility(View.GONE);
                viewHolder.tv_mine_homework_money.setText(context.getResources().getString(R.string.mine_help_check_price));
                viewHolder.tv_mine_homework_money_default.setText(releaseHomeworkBeans.get(position).getPrice()+context.getResources().getString(R.string.homework_check_money));
            }else{
                viewHolder.iv_mine_homework_time.setVisibility(View.VISIBLE);
                viewHolder.tv_mine_homework_time.setVisibility(View.VISIBLE);
                try {
                    viewHolder.tv_mine_homework_time.setText(Utils.longToString(releaseHomeworkBeans.get(position).getStart_time()*1000L,"MM-dd HH:mm"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                viewHolder.tv_mine_homework_money.setText(context.getResources().getString(R.string.mine_help_tutorship_price));
                viewHolder.tv_mine_homework_money_default.setText(releaseHomeworkBeans.get(position).getPrice()+context.getResources().getString(R.string.teacher_help_price_units));
            }

            MineHomeworkGrideViewAdapter mineHomeworkGrideViewAdapter = new MineHomeworkGrideViewAdapter(releaseHomeworkBeans.get(position),context);

            int size=releaseHomeworkBeans.get(position).getImages().length;
            int length = (int)context.getResources().getDimension(R.dimen.x100);
            int gridviewWidth =  size * (length + (int)context.getResources().getDimension(R.dimen.x10))+(int)context.getResources().getDimension(R.dimen.x7);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);

            viewHolder.grid_mine_homework.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
            viewHolder.grid_mine_homework.setColumnWidth((int)context.getResources().getDimension(R.dimen.x100)); // 设置列表项宽
            viewHolder.grid_mine_homework.setHorizontalSpacing((int)context.getResources().getDimension(R.dimen.x10)); // 设置列表项水平间距
            viewHolder.grid_mine_homework.setStretchMode(GridView.NO_STRETCH);
            viewHolder.grid_mine_homework.setNumColumns(size); // 设置列数量=列表集合数

            viewHolder.grid_mine_homework.setAdapter(mineHomeworkGrideViewAdapter);

        }
        return convertView;
    }
    private class ViewHolder {
        GridView grid_mine_homework;
        TextView tv_mine_homework_class;
        TextView tv_mine_homework_state,tv_mine_homework_time;
        TextView tv_mine_homework_money_default,tv_mine_homework_money;
        ImageView iv_mine_homework_time;
    }
}
