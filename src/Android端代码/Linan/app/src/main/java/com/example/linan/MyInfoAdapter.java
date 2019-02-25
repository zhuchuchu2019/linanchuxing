package com.example.linan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @brief my__recycler_view的适配器
 * Created by 11357 on 2018/12/21.
 */

public class MyInfoAdapter extends RecyclerView.Adapter<MyInfoAdapter.ViewHolder>{
    private List<MyInfo> mInfoList;
    private OnItemClickListener mOnItemClickListener;
    static  class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView _iconImage;
        ImageView _RightImage;
        TextView _myInfo;
        View myView;
        public ViewHolder(View view){
            super(view);
            myView = view;
            _iconImage = (ImageView)view.findViewById(R.id.img_list_icon);
            _RightImage = (ImageView)view.findViewById(R.id.img_list_right);
            _myInfo = (TextView)view.findViewById(R.id.tv_list_content);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    /**
     * 构造函数
     * @param myList 存放子项数据的List
     */
    public MyInfoAdapter(List<MyInfo> myList) {
        mInfoList = myList;
    }


    /**
     * 创建ViewHolder实例
     * @param parent
     * @param viewType
     * @return ViewHolder实例
     */
    @Override
    public MyInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_item,parent,false);
        final MyInfoAdapter.ViewHolder holder = new MyInfoAdapter.ViewHolder(view);
        holder.myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MyInfo myInfo = mInfoList.get(position);
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
    public void onBindViewHolder(MyInfoAdapter.ViewHolder holder, int position) {
        MyInfo myInfo = mInfoList.get(position);
        holder._iconImage.setImageResource(myInfo.getImageId());
        holder._myInfo.setText(myInfo.getName());
        holder._RightImage.setImageResource(myInfo.getImageIdR());
    }

    /**
     * 获取RecyclerView中的子项个数
     * @return 数据源长度
     */
    @Override
    public int getItemCount() {
        return mInfoList.size();
    }
}
