package com.jmy.dao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmy.foodsystem.R;
import com.jmy.foodsystem.home.DocommentActivity;
import com.jmy.model.NoComment;

import java.util.List;

/**
 * Created by johnseg on 2017/6/6.
 */

public class NoCommentAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater myinflater;
    private List<NoComment> list;
    private  int myposition;


    public NoCommentAdapter(Context context, List<NoComment> list) {
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
        ImageView comment;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
       myposition=position;
       ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = myinflater.inflate(R.layout.nocommentlist, null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.nocommentimg);
            viewHolder.name = (TextView) convertView.findViewById(R.id.nocommentname);
            viewHolder.time = (TextView) convertView.findViewById(R.id.nocommenttime);
            viewHolder.comment = (ImageView) convertView.findViewById(R.id.nocommentDI);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.img.setImageResource(list.get(position).getImg());
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.time.setText(list.get(position).getTime());
        viewHolder.comment.setImageResource(R.mipmap.nocommentdi);
        viewHolder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DocommentActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("myposition",myposition);
                bundle.putInt("img",list.get(myposition).getImg());
                bundle.putString("name",list.get(myposition).getName());
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);
            }
        });

        return convertView;


    }



}