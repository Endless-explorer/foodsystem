package com.jmy.dao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmy.foodsystem.R;
import com.jmy.model.NoUser;

import java.util.List;

/**
 * Created by johnseg on 2017/6/6.
 */

public class NoUseAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater myinflater;
    private List<NoUser> list;


    public NoUseAdapter(Context context, List<NoUser> list) {
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
        TextView dateline;
        TextView number;
        TextView price;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = myinflater.inflate(R.layout.nouselist, null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.nouseimg);
            viewHolder.name = (TextView) convertView.findViewById(R.id.nouse_name);
            viewHolder.dateline = (TextView) convertView.findViewById(R.id.nousetime);
            viewHolder.number= (TextView) convertView.findViewById(R.id.nouse_number);
            viewHolder.price = (TextView) convertView.findViewById(R.id.nousesum);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.img.setImageResource(list.get(position).getImg());
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.dateline.setText("到期时间:"+list.get(position).getDateline());
        viewHolder.number.setText("数量:"+list.get(position).getNumber());
        viewHolder.price.setText("总价:"+list.get(position).getPrice());


        return convertView;


    }



}