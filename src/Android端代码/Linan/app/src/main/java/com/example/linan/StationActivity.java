package com.example.linan;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @brief 站点信息活动
 */
public class StationActivity extends AppCompatActivity {

    public static final String web = "http://192.168.71.1:8080/Linan/get_station_by_num.jsp";
    public static final String webInfo = "http://192.168.71.1:8080/Linan/get_cheInfo_by_num.jsp";
    String num,name;
    TextView _tvToolBar;
    ImageView backImage;
    ImageView collectImage;
    TextView _tvDir,_tvStartTime,_tvEndTime,_tvPrice;
    static boolean isCollected = false;
    private List<Station> stationList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        _tvToolBar = (TextView)findViewById(R.id.tvToolBar);
        backImage = (ImageView)findViewById(R.id.back_back_image);
        collectImage = (ImageView)findViewById(R.id.img_collect);
        _tvDir = (TextView)findViewById(R.id.tvDir);
        _tvStartTime = (TextView)findViewById(R.id.tvStartTime);
        _tvEndTime = (TextView)findViewById(R.id.tvEndTime);
        _tvPrice = (TextView)findViewById(R.id.tvPrice);
        Intent intent = getIntent();
        num = intent.getStringExtra("clickedNum");
        name = intent.getStringExtra("clickedName");
        _tvToolBar.setText(name);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StationActivity.this,SelectActivity.class);
                startActivity(intent);
            }
        });
        collectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isCollected){
                    Drawable drawable = collectImage.getDrawable();
                    collectImage.setImageResource(R.drawable.fav_star_selected);
                    isCollected = true;
                    Toast.makeText(StationActivity.this,"增加了一条收藏 ",Toast.LENGTH_SHORT).show();
                    Fragment1.collect_Name = name;
                    Fragment1.collect_Num = num;
                    Collection one = new Collection(num,name);
                    Fragment1.collectionList.add(one);
                    Fragment1.deleteImg.setVisibility(View.VISIBLE);
                }
                else{
                    collectImage.setImageResource(R.drawable.fav_star_unselect);
                    isCollected = false;
                    Toast.makeText(StationActivity.this,"删除了一条收藏 ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        requestStationByNum();
        requestCheciByNum();
        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.station_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(StationActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        StationAdapter adapter = new StationAdapter(stationList);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 发出http请求，将num加在url后面进行获取查询站点信息
     */
    private void requestStationByNum(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("num",num)
                            .build();
                    Request request = new Request.Builder().
                            url(web)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    final String responseTxt = response.body().string();
                    parseStationJSON(responseTxt);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 发出http请求，将num加在url后面进行动态获取车辆方向、首班、末班等车次信息
     */
    private void requestCheciByNum(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("num",num)
                            .build();
                    Request request = new Request.Builder().
                            url(webInfo)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    final String responseTxt = response.body().string();
                    parseCheciJSON(responseTxt);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 将http返回的站点信息json格式数据解析
     * @param jsonData 页面数据
     */
    private void parseStationJSON(String jsonData){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String station= i + 1 + jsonObject.getString("station");
                Station item;
                if(i==0){
                    item = new Station(station,R.drawable.img_drive_start,R.drawable.img_drive_big_link);
                }else if(i==jsonArray.length()-1){
                    item = new Station(station,R.drawable.img_drive_end);
                }else
                    item = new Station(station,R.drawable.img_drive_pass,R.drawable.img_drive_link);
                stationList.add(item);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 将http返回的车次信息json格式数据解析
     * @param jsonData 页面数据
     */
    private void parseCheciJSON(String jsonData){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String dir= jsonObject.getString("dir");
                String startTime= jsonObject.getString("startTime");
                String endTime= jsonObject.getString("endTime");
                String price= jsonObject.getString("price");
                _tvDir.setText("方向:"+ dir);
                _tvStartTime.setText(startTime);
                _tvEndTime.setText(endTime);
                _tvPrice.setText("票价:"+price);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
