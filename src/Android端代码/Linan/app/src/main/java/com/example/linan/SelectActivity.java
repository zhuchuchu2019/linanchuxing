package com.example.linan;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @brief 查询线路活动
 * Created by 11357 on 2018/12/20.
 */
public class SelectActivity extends AppCompatActivity {
    public static final String webGetStartAndEnd = "http://192.168.71.1:8080/Linan/get_start_end.jsp";

    private List<RouteInfo> trainList = new ArrayList<>();
    StringBuilder builder = new StringBuilder();
    Button btn_okttp;
    TextView _tvResult;
    ImageView backImage;
    RouteAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        backImage = (ImageView)findViewById(R.id.back_image);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
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
                Intent intent = new Intent(SelectActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
   }

    /**
     * 使用封装的网络操作类HttpUtil发出http请求并处理返回的数据
     * @param[in] void
     * @return void
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parseJSON(data);
                        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.route_recycler_view);
                        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
                            recyclerView.setLayoutManager(layoutManager);
                            adapter = new RouteAdapter(trainList);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    int num = trainList.get(position).getNum();
                                    String name = trainList.get(position).getName();
                                    Intent intent = new Intent(SelectActivity.this,StationActivity.class);
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
    /**
     * 解析json格式的数据
     * @param[in] jsonData 网页请求返回的数据，类型为String
     * @return void
     */
    private void parseJSON(String jsonData){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int num = jsonObject.getInt("num");
                String name = jsonObject.getString("name");
                String start = jsonObject.getString("start");
                String end = jsonObject.getString("end");
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
    /**
     * 实现本地刷新操作
     * @param[in] void
     * @return void
     */
    private void refreshRoutes(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
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
