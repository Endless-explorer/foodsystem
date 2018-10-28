package com.jmy.dao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jmy.foodsystem.R;
import com.jmy.model.NoPay;

import java.util.List;




/**
 * Created by johnseg on 2017/6/5.
 */

public class NoPayAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater myinflater;
    private List<NoPay> list;


    public NoPayAdapter(Context context, List<NoPay> list) {
        this.context = context;
        myinflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder {
        ImageView img;
        TextView name;
        TextView time;
        ImageView pay;
        TextView  sum;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = myinflater.inflate(R.layout.waitpayitem, null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.waitpay_img);
            viewHolder.name = (TextView) convertView.findViewById(R.id.waitpay_name);
            viewHolder.time = (TextView) convertView.findViewById(R.id.waitpay_time);
            viewHolder.pay = (ImageView) convertView.findViewById(R.id.waitpay_pay);
            viewHolder.sum = (TextView) convertView.findViewById(R.id.waitpay_sum);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.img.setImageResource(list.get(position).getImg());
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.pay.setImageResource(R.mipmap.pay1);
        viewHolder.time.setText(list.get(position).getTime());
        viewHolder.sum.setText("总价:¥"+list.get(position).getSum());
        viewHolder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"支付成功!",Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;


    }



}
