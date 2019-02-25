package com.example.linan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linan.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 碎片二，查询页面
 */
public class Fragment2 extends Fragment {
    protected Activity mActivity;
    public static final String webGetStartAndEnd = "http://192.168.71.1:8080/Linan/get_start_end.jsp";

    private List<RouteInfo> trainList = new ArrayList<>();
    StringBuilder builder = new StringBuilder();
    Button btn_okttp;
    TextView _tvResult;
    ImageView backImage;
    RouteAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

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
                inflate(R.layout.fragment2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        backImage = (ImageView)mActivity.findViewById(R.id.back_image);
        swipeRefreshLayout = (SwipeRefreshLayout)mActivity.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRoutes();
            }
        });
        UseHttpUtilForQuest();
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    /**
     *  使用封装的网络操作类HttpUtil发出和接收（回调机制）
     */
    private void UseHttpUtilForQuest(){
        HttpUtil.sendOkHttpRequest(webGetStartAndEnd,new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parseJSON(data);
                        RecyclerView recyclerView =(RecyclerView)mActivity.findViewById(R.id.route_recycler_view);
                        GridLayoutManager layoutManager = new GridLayoutManager(mActivity.getApplicationContext(),1);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new RouteAdapter(trainList);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                int num = trainList.get(position).getNum();
                                String name = trainList.get(position).getName();
                                Intent intent = new Intent(mActivity,StationActivity.class);
                                intent.putExtra("clickedNum",num+"");
                                intent.putExtra("clickedName",name);
                                startActivity(intent);
                            }

                        });
                    }
                });
            }
        });
    }
    private void parseJSON(String jsonData){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int num = jsonObject.getInt("num");
                String name = jsonObject.getString("name");
                String start = jsonObject.getString("start");
                String end = jsonObject.getString("end");
                // _tvResult.setText(num+start+end);
                builder.append(num);
                builder.append(start);
                builder.append(end);
                RouteInfo one = new RouteInfo(num,name,start,end);
                trainList.add(one);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void refreshRoutes(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        UseHttpUtilForQuest();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

}
