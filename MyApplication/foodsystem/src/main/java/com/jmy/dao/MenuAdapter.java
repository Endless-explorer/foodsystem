package com.jmy.dao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmy.foodsystem.R;
import com.jmy.model.MyItem;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by johnseg on 2017/5/25.
 */

public class MenuAdapter extends BaseAdapter {
    private List<MyItem> list=null;
    private Context context;
    private LayoutInflater myinflater;

    public MenuAdapter(List<MyItem> list, Context context)
    {
        this.list=list;
        this.context=context;
        myinflater = LayoutInflater.from(context);
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
    static class ViewHolder
    {
        public ImageView img;
        public TextView  title;
        public ImageView star;
        public TextView  price;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            convertView=myinflater.inflate(R.layout.listmenu,null);
            viewHolder=new ViewHolder();
            viewHolder.img= (ImageView) convertView.findViewById(R.id.img);
            viewHolder.title= (TextView) convertView.findViewById(R.id.title);
            viewHolder.star= (ImageView) convertView.findViewById(R.id.mystar);
            viewHolder.price= (TextView) convertView.findViewById(R.id.price);

            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.img.setImageBitmap(list.get(position).getImg());
        viewHolder.title.setText(list.get(position).getTitle());
        viewHolder.star.setImageResource(list.get(position).getStar());
        viewHolder.price.setText("¥"+list.get(position).getPrice()+"");

        return convertView;
    }
    public String encode(int position)  {
        String str=list.get(position).getTitle();
        try {
            str=new String(str.getBytes("gbk"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  str;
    }



    }

