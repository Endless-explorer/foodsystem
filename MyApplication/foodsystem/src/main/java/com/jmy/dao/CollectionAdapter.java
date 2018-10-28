package com.jmy.dao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmy.foodsystem.R;
import com.jmy.model.Collection;

import java.util.List;

/**
 * Created by johnseg on 2017/6/7.
 */

public class CollectionAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater myinflater;
    private List<Collection> list;


    public CollectionAdapter(Context context, List<Collection> list) {
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
        ImageView star;
        TextView location;
        TextView  distance;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = myinflater.inflate(R.layout.collectionlist, null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.collection_img);
            viewHolder.name = (TextView) convertView.findViewById(R.id.collection_name);
            viewHolder.star = (ImageView) convertView.findViewById(R.id.collection_star);
            viewHolder.location= (TextView) convertView.findViewById(R.id.collection_loc);
            viewHolder.distance = (TextView) convertView.findViewById(R.id.collection_dist);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.img.setImageBitmap(list.get(position).getImg());
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.star.setImageResource(list.get(position).getStar());
        viewHolder.location.setText(list.get(position).getLocation());
        viewHolder.distance.setText("<<"+list.get(position).getDistance());


        return convertView;


    }



}
