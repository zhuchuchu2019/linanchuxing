package com.example.linan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * @brief collect__recycler_view的适配器
 * Created by 11357 on 2018/12/21.
 */

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHolder>{
    private List<Collection> mCollectionList;
    private OnItemClickListener mOnItemClickListener;
    static  class  ViewHolder extends RecyclerView.ViewHolder{
        TextView _tvCollection;
        View collectView;

        public ViewHolder(View view){
            super(view);
            collectView = view;
            _tvCollection = (TextView)view.findViewById(R.id.tvCollection);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * 构造函数
     * @param CollectionList 存放子项数据的List
     */
    public CollectAdapter(List<Collection> CollectionList) {
        mCollectionList = CollectionList;
    }

    /**
     * 创建ViewHolder实例
     * @param parent
     * @param viewType
     * @return ViewHolder实例
     */
    @Override
    public CollectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collect_item,parent,false);
        final CollectAdapter.ViewHolder holder = new CollectAdapter.ViewHolder(view);
        holder.collectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String name = mCollectionList.get(position).getName();
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
    public void onBindViewHolder(CollectAdapter.ViewHolder holder, int position) {
        Collection collectionInfo = mCollectionList.get(position);
        holder._tvCollection.setText(collectionInfo.getName());
    }

    /**
     * 获取RecyclerView中的子项个数
     * @return 数据源长度
     */
    @Override
    public int getItemCount() {
        return mCollectionList.size();
    }
}
