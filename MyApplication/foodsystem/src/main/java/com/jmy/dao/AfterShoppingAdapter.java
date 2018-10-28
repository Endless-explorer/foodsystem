package com.jmy.dao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmy.foodsystem.R;
import com.jmy.model.AfterShopping;

import java.util.List;

/**
 * Created by johnseg on 2017/6/6.
 */

public class AfterShoppingAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater myinflater;
    private List<AfterShopping> list;


    public AfterShoppingAdapter(Context context, List<AfterShopping> list) {
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
        TextView number;
        TextView sum;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = myinflater.inflate(R.layout.aftershoppinglist, null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.aftershoppingimg);
            viewHolder.name = (TextView) convertView.findViewById(R.id.aftershoppingname);
            viewHolder.number = (TextView) convertView.findViewById(R.id.aftershoppingnumber);
            viewHolder.sum = (TextView) convertView.findViewById(R.id.aftershoppingsum);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.img.setImageResource(list.get(position).getImg());
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.number.setText("数量:"+list.get(position).getNumber());
        viewHolder.sum.setText("总价:"+list.get(position).getSum());


        return convertView;


    }



}
