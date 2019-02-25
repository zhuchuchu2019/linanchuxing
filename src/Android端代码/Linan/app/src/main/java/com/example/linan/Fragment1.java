package com.example.linan;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 碎片一，首页
 */
public class Fragment1 extends Fragment implements View.OnClickListener{
    protected Activity mActivity;
    ImageView iv_go_where;
    ImageView iv_selcet;
    ImageView iv_map;
    ImageView iv_my;
    public static ImageView deleteImg;
    CollectAdapter adapter;
    static String collect_Name,collect_Num;
    public static List<Collection> collectionList = new ArrayList<>();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return LayoutInflater.
                from(getActivity()).
                inflate(R.layout.fragment1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iv_go_where = (ImageView)mActivity.findViewById(R.id.iv_go_where1);
        iv_selcet = (ImageView)mActivity.findViewById(R.id.iv_select1);
        iv_map = (ImageView)mActivity.findViewById(R.id.iv_map1);
        iv_my = (ImageView)mActivity.findViewById(R.id.iv_my1);
        iv_go_where.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,GoWhereActivity.class);
                startActivity(intent);
            }
        });
        iv_selcet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,SelectActivity.class);
                startActivity(intent);
            }
        });
        iv_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,MyActivity.class);
                startActivity(intent);
            }
        });
        iv_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,MapActivity.class);
                startActivity(intent);
            }
        });
        deleteImg = (ImageView) mActivity.findViewById(R.id.img_delete);
        if(collectionList.isEmpty()){
            deleteImg.setVisibility(View.INVISIBLE);
        }
        deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity);
                dialog.setMessage("删除全部历史记录吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        collectionList.clear();
                        deleteImg.setVisibility(View.INVISIBLE);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();

            }
        });
        RecyclerView recyclerView =(RecyclerView)mActivity.findViewById(R.id.collect_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CollectAdapter(collectionList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                if(collectionList!=null) {
                    String num = collectionList.get(position).getNum();
                    String name = collectionList.get(position).getName();
                    Intent intent = new Intent(mActivity, StationActivity.class);
                    intent.putExtra("clickedName", name);
                    intent.putExtra("clickedNum", num + "");
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onClick(View v) {

    }
}
