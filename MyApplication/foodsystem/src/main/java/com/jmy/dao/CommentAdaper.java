package com.jmy.dao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmy.foodsystem.R;
import com.jmy.model.Comment;

import java.util.List;


/**
 * Created by johnseg on 2017/6/5.
 */

public class CommentAdaper extends BaseAdapter {

    private Context context;
    private LayoutInflater myinflater;
    private List<Comment> list;
    private ViewHolder viewHolder;


    public CommentAdaper(Context context, List<Comment> list) {
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
        TextView content;
        TextView time;
        ImageView star;
        ImageView like;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         viewHolder = null;

        if (convertView == null) {
            convertView = myinflater.inflate(R.layout.commentlist, null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.commentimg);
            viewHolder.name = (TextView) convertView.findViewById(R.id.commentname);
            viewHolder.star = (ImageView) convertView.findViewById(R.id.commentstar);
            viewHolder.time = (TextView) convertView.findViewById(R.id.commenttime);
            viewHolder.content = (TextView) convertView.findViewById(R.id.commentcontent);
            viewHolder.like = (ImageView) convertView.findViewById(R.id.commentlike);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.img.setImageResource(list.get(position).getImg());
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.star.setImageResource(list.get(position).getStar());
        viewHolder.time.setText(list.get(position).getTime());
        viewHolder.content.setText(list.get(position).getContent());
        viewHolder.like.setImageResource(list.get(position).getLike());
        viewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.like.setImageResource(R.mipmap.like);
            }
        });

        return convertView;


    }



}
