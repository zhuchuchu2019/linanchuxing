package com.example.linan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by 黄睿 on 2018/12/19.
 */
import com.example.linan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 碎片四，“我的”页面
 */
public class Fragment4 extends Fragment{
    protected Activity mActivity;
    private List<MyInfo> myList = new ArrayList<>();
    ImageView login_Image;
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
                inflate(R.layout.fragment4, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLabels();
        RecyclerView recyclerView =(RecyclerView)mActivity.findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(layoutManager);
        MyInfoAdapter adapter = new MyInfoAdapter(myList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int imageId= myList.get(position).getImageId();
                MyInfo info = myList.get(position);
                switch (imageId){
                    case R.drawable.suggestion:
                        Intent intent = new Intent(mActivity,LoginActivity.class);
                        startActivity(intent);
                        break;
                    case R.drawable.news:
                        Intent intent_one = new Intent(mActivity,AnnounceActivity.class);
                        startActivity(intent_one);
                        break;
                    default:
                        Toast.makeText(view.getContext(),info.getName(),Toast.LENGTH_SHORT).show();
                        break;
                }
            }

        });
        login_Image = (ImageView)mActivity.findViewById(R.id.img_loginicon);
        login_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化列表单项
     */
    private void initLabels(){
        MyInfo zero = new MyInfo(R.drawable.version,"发现新版",R.drawable.icon_list_btn_right);
        MyInfo one = new MyInfo(R.drawable.settings,"工具设置",R.drawable.icon_list_btn_right);
        MyInfo two = new MyInfo(R.drawable.suggestion,"意见留言",R.drawable.icon_list_btn_right);
        MyInfo three = new MyInfo(R.drawable.news,"公告资讯",R.drawable.icon_list_btn_right);
        MyInfo four = new MyInfo(R.drawable.lostfind,"失物招领",R.drawable.icon_list_btn_right);
        MyInfo five = new MyInfo(R.drawable.scanscan,"扫一扫",R.drawable.icon_list_btn_right);
        MyInfo six = new MyInfo(R.drawable.list,"列表清单",R.drawable.icon_list_btn_right);
        MyInfo seven = new MyInfo(R.drawable.aboutus,"关于我们",R.drawable.icon_list_btn_right);
        myList.add(zero);
        myList.add(one);
        myList.add(two);
        myList.add(three);
        myList.add(four);
        myList.add(five);
        myList.add(six);
        myList.add(seven);
    }
}
