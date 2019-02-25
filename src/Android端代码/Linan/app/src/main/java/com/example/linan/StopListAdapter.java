package com.example.linan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 站点列表的适配器
 * Created by 黄睿 on 2018/12/16.
 */

public class StopListAdapter extends ArrayAdapter<StopList> {
    private int resourceId;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StopList stopList = getItem(position);//获取当前实例
        View view;
        ViewHolder viewHolder;

        //代码优化
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.stoplistimage = (ImageView)view.findViewById(R.id.iv_sl_1);
            viewHolder.stoplistname = (TextView)view.findViewById(R.id.tv_sl_1);
            view.setTag(viewHolder);//将viewholder存储至view中
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();//重新获取viewholder
        }

        viewHolder.stoplistimage.setImageResource(stopList.getImageId());
        viewHolder.stoplistname.setText(stopList.getName());
        return view;

    }

    public StopListAdapter(Context context, int resource, List<StopList> objects) {
        super(context, resource, objects);
        resourceId = resource;

    }
    class ViewHolder{
        ImageView stoplistimage;
        TextView stoplistname;
    }
}
