package com.shushan.homework101.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shushan.homework101.HttpHelper.service.entity.orders.OrdersData;
import com.shushan.homework101.R;
import com.shushan.homework101.Utils.Utils;
import com.shushan.homework101.banner.ImageViewRoundRect;
import com.shushan.homework101.mine.tutorship.MineTutorshipWholeInterface;
import com.shushan.homework101.mine.tutorship.TutorshipDetailsActivity;

import java.text.ParseException;
import java.util.ArrayList;

public class MineHelpListViewAdapter  extends BaseAdapter{
    private Context context;
    private ArrayList<OrdersData> ordersArrayList;
    MineTutorshipWholeInterface mineTutorshipWholeInterface;
    public MineHelpListViewAdapter(Context context, ArrayList<OrdersData> ordersArrayList) {
        this.context=context;
        this.ordersArrayList=ordersArrayList;
    }

    @Override
    public int getCount() {
        return ordersArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return ordersArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
       ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.mine_help_listview_item, viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.tv_mine_help_time=convertView.findViewById(R.id.tv_mine_help_time);
            viewHolder.tv_mine_help_obligation=convertView.findViewById(R.id.tv_mine_help_obligation);
            viewHolder.iv_mine_help_teacher = (ImageViewRoundRect) convertView.findViewById(R.id.iv_mine_help_teacher);
            viewHolder.tv_mine_help_teacher_name = (TextView) convertView.findViewById(R.id.tv_mine_help_teacher_name);
            viewHolder.tv_mine_help_teacher_honor = (TextView) convertView.findViewById(R.id.tv_mine_help_teacher_honor);
            viewHolder.tv_mine_help_teacher_course = (TextView) convertView.findViewById(R.id.tv_mine_help_teacher_course);
            viewHolder.tv_mine_help_teacher_help_price=convertView.findViewById(R.id.tv_mine_help_teacher_help_price);
            viewHolder.tv_mine_help_teacher_help_price_default=convertView.findViewById(R.id.tv_mine_help_teacher_help_price_default);
            viewHolder.bt_mine_help_obligation_price_default = (TextView) convertView.findViewById(R.id.bt_mine_help_obligation_price_default);
            viewHolder.mine_help_pay = (Button) convertView.findViewById(R.id.mine_help_pay);
            viewHolder.iv_mine_help_playback=(ImageViewRoundRect)convertView.findViewById(R.id.iv_mine_help_playback);
            viewHolder.tv_mine_help_contact_again=convertView.findViewById(R.id.tv_mine_help_contact_again);
            viewHolder.group_orders=convertView.findViewById(R.id.group_orders);
            viewHolder.ctl_orders=convertView.findViewById(R.id.ctl_orders);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(ordersArrayList!=null&&ordersArrayList.size()>0){

            RequestOptions options = new RequestOptions()
                    .error(R.drawable.visa)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(ordersArrayList.get(position).getTrait()).apply(options).into(viewHolder.iv_mine_help_teacher);

            if(ordersArrayList.get(position).getLabel()==1){
                viewHolder.group_orders.setVisibility(View.GONE);
                viewHolder.tv_mine_help_teacher_help_price_default.setText("￥"+ordersArrayList.get(position).getPrice());
                viewHolder.tv_mine_help_teacher_help_price.setText(context.getResources().getString(R.string.mine_help_check_price));

            }else{
                viewHolder.group_orders.setVisibility(View.VISIBLE);
                viewHolder.iv_mine_help_playback.setType(ImageViewRoundRect.TYPE_ROUND);
                viewHolder.iv_mine_help_playback.setRoundRadius((int) context.getResources().getDimension(R.dimen.x2));
                viewHolder.tv_mine_help_teacher_help_price.setText(context.getResources().getString(R.string.mine_help_tutorship_price));
                viewHolder.tv_mine_help_teacher_help_price_default.setText("￥"+ordersArrayList.get(position).getPrice()+context.getResources().getString(R.string.teacher_help_price_units));
            }
            int create_time=ordersArrayList.get(position).getCreate_time();
            try {
                viewHolder.tv_mine_help_time.setText(Utils.longToString(create_time*1000L,"yyyy-MM-dd HH:mm"));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //0辅导中检查中1待付款2已付款3已取消
            switch (ordersArrayList.get(position).getStatus()){
                case 0:
                    if(ordersArrayList.get(position).getLabel()==1){
                        viewHolder.tv_mine_help_obligation.setText(context.getResources().getString(R.string.mine_help_checking));
                    }else{
                        viewHolder.tv_mine_help_obligation.setText(context.getResources().getString(R.string.mine_help_tutorship_ing));
                    }
                    viewHolder.mine_help_pay.setVisibility(View.GONE);
                    viewHolder.tv_mine_help_contact_again.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    viewHolder.tv_mine_help_contact_again.setVisibility(View.GONE);
                    viewHolder.mine_help_pay.setVisibility(View.VISIBLE);
                    viewHolder.tv_mine_help_obligation.setText(context.getResources().getString(R.string.mine_help_obligation));
                    viewHolder.mine_help_pay.setText(context.getResources().getString(R.string.mine_pay_go_payment));
                    break;
                case 2:
                    viewHolder.mine_help_pay.setVisibility(View.GONE);
                    viewHolder.tv_mine_help_obligation.setText(context.getResources().getString(R.string.mine_help_done));
                    viewHolder.tv_mine_help_contact_again.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    viewHolder.mine_help_pay.setVisibility(View.GONE);
                    viewHolder.tv_mine_help_obligation.setText(context.getResources().getString(R.string.mine_help_canceled));
                    viewHolder.tv_mine_help_contact_again.setVisibility(View.VISIBLE);
                    break;
            }
            viewHolder.tv_mine_help_teacher_name.setText(ordersArrayList.get(position).getName());
            viewHolder.tv_mine_help_teacher_honor.setText(ordersArrayList.get(position).getLevel());
            viewHolder.tv_mine_help_teacher_course.setText(ordersArrayList.get(position).getGrade()+"/"+ordersArrayList.get(position).getSubject());
            viewHolder.bt_mine_help_obligation_price_default.setText("￥"+ordersArrayList.get(position).getPay_money());


        }
        viewHolder.mine_help_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              mineTutorshipWholeInterface.onPayClick(position);
            }
        });
        viewHolder.ctl_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,TutorshipDetailsActivity.class);
                intent.putExtra("oid",ordersArrayList.get(position).getOid());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
    private class ViewHolder {
        ConstraintLayout ctl_orders;
        TextView tv_mine_help_time;
        TextView tv_mine_help_obligation;
        ImageViewRoundRect iv_mine_help_teacher,iv_mine_help_playback;
        TextView tv_mine_help_teacher_name;
        TextView tv_mine_help_teacher_honor;
        TextView tv_mine_help_teacher_course;
        TextView tv_mine_help_teacher_help_price_default,tv_mine_help_teacher_help_price;
        TextView bt_mine_help_obligation_price_default;
        Button mine_help_pay;
        Group group_orders;
        TextView tv_mine_help_contact_again;
    }
    public void setOnItemClickListener(MineTutorshipWholeInterface mineTutorshipWholeInterface) {
        this.mineTutorshipWholeInterface = mineTutorshipWholeInterface;
    }
}
