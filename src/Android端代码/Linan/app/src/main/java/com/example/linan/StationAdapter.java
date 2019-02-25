package com.example.linan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * @brief station_recycler_view的适配器
 * Created by 11357 on 2018/12/15.
 */

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ViewHolder>{
    private List<Station> mStationList;
    private OnItemClickListener mOnItemClickListener;
    static  class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView stationSign;
        ImageView stationLink;
        TextView stationName;
        public ViewHolder(View view){
            super(view);
            stationName = (TextView)view.findViewById(R.id.station_name);
            stationSign = (ImageView)view.findViewById(R.id.station_sign);
            stationLink = (ImageView)view.findViewById(R.id.station_link);
        }
    }
    /**
     * 构造函数
     * @param StationList 存放子项数据的List
     */
    public StationAdapter(List<Station> StationList) {
        mStationList = StationList;
    }

    /**
     * 创建ViewHolder实例
     * @param parent
     * @param viewType
     * @return ViewHolder实例
     */
    @Override
    public StationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_item,parent,false);
        final StationAdapter.ViewHolder holder = new StationAdapter.ViewHolder(view);
        return holder;
    }

    /**
     *对RecyclerView子项数据进行赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(StationAdapter.ViewHolder holder, int position) {
        Station station = mStationList.get(position);
        holder.stationName.setText(station.getName());
        holder.stationSign.setImageResource(station.getImageId());
        holder.stationLink.setImageResource(station.getImageIdL());
    }

    /**
     * 获取RecyclerView中的子项个数
     * @return 数据源长度
     */
    @Override
    public int getItemCount() {
        return mStationList.size();
    }
}
