package com.example.linan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * @breif route_recycler_view的适配器
 * Created by 11357 on 2018/12/15.
 */

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder>{

    private List<RouteInfo> mRouteList;
    private OnItemClickListener mOnItemClickListener;
    static  class  ViewHolder extends RecyclerView.ViewHolder{
        TextView _route;
        View routeView;
        public ViewHolder(View view){
            super(view);
            routeView = view;
            _route = (TextView)view.findViewById(R.id.tv_route);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * 构造函数
     * @param TrainList 存放子项数据的List
     */
    public RouteAdapter(List<RouteInfo> TrainList) {
        mRouteList = TrainList;
    }

    /**
     * 创建ViewHolder实例
     * @param parent
     * @param viewType
     * @return ViewHolder实例
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.routeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                RouteInfo routeInfo = mRouteList.get(position);
                mOnItemClickListener.onItemClick(v, position);
            }
        });
        return holder;
    }

    /**
     *对RecyclerView子项数据进行赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RouteInfo routeInfo = mRouteList.get(position);
        StringBuilder builder = new StringBuilder();
        builder.append(" ").append(routeInfo.getName()).append("  ");
        builder.append(routeInfo.getStart()).append("----->").append(routeInfo.getEnd());
        holder._route.setText(builder);
    }

    /**
     * 获取RecyclerView中的子项个数
     * @return 数据源长度
     */
    @Override
    public int getItemCount() {
        return mRouteList.size();
    }

}
