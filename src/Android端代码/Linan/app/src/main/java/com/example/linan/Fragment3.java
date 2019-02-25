package com.example.linan;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

    import static android.app.Activity.RESULT_OK;

/**
 * 碎片三，为线路查询的实现
 */
public class Fragment3 extends Fragment {

    public static final String webh = "http://192.168.71.1:8080/Linan/huancheng.jsp";
    public static final String webh1 = "http://192.168.71.1:8080/Linan/huancheng1.jsp";

    Button _f3query;
    EditText _f3startstop;
    EditText _f3endstop;
    ImageButton _f3_imagebutton;

    HuanChengAdapter adapter;
    private List<huancheng> huanchengList = new ArrayList<>();

    protected Activity mActivity;

    /**
     * 为Fragment创建View(加载布局)时调用
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {//
        return LayoutInflater.
                from(mActivity).
                inflate(R.layout.fragment3, container, false);
    }

    /**
     * Fragment和Activity建立关联时调用(获取Activity传递的值)
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }

    /**
     * Activity的onCreate()方法执行完毕后调用
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _f3query = (Button)mActivity.findViewById(R.id.btn_f3_query);
        _f3startstop = (EditText) mActivity.findViewById(R.id.et_f3_inputStart);
        _f3endstop = (EditText) mActivity.findViewById(R.id.et_f3_inputEnd);
        _f3_imagebutton = (ImageButton) mActivity.findViewById(R.id.btn_f3_change);


        RecyclerView recyclerView = (RecyclerView)mActivity.findViewById(R.id.rv_f3_1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HuanChengAdapter(huanchengList);
        recyclerView.setAdapter(adapter);
        /**
         * 起点输入框与终点输入框设置接触监听，跳转到地址搜索活动，使用INTENT与startActivityForResult()方法
         */
        _f3startstop.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        String stop = _f3startstop.getText().toString();
                        Intent intent = new Intent(mActivity,activity_address_search.class);
                        intent.putExtra("extra_data",stop);
                        mActivity.startActivityForResult(intent,1);
                        break;
                    default:
                        break;
                }

                return false;
            }
        });

        _f3endstop.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        String stop = _f3endstop.getText().toString();
                        Intent intent = new Intent(mActivity,activity_address_search.class);
                        intent.putExtra("extra_data",stop);
                        mActivity.startActivityForResult(intent,2);
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
        /**
         * 交换按钮设置点击监听,点击后交换两个输入框的值
         */
        _f3_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stp = _f3startstop.getText().toString();
                _f3startstop.setText(_f3endstop.getText().toString());
                _f3endstop.setText(stp);
                System.out.println("交换");

            }
        });
        /**
         * 查询按钮的设置点击监听，首先判断地点是否为空或相等。再使用sendRequestBystop方法查询。
         * 同时使用适配器的notifyDataSetChanged更新列表。
         */
        _f3query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("点击");
                if(_f3startstop.getText().toString().replaceAll(" ","").equals("")
                        || _f3endstop.getText().toString().replaceAll(" ","").equals("")){
                    Toast.makeText(mActivity, "地点不能为空", Toast.LENGTH_LONG).show();
                }else if(_f3startstop.getText().toString() == _f3endstop.getText().toString()){
                    Toast.makeText(mActivity, "地点不能相同", Toast.LENGTH_LONG).show();
                }else{
                    huanchengList.clear();
                    sendRequestBystop(webh,0);
                    sendRequestBystop(webh1,1);
                    adapter.notifyDataSetChanged();
                }



            }
        });


    }

    /**
     * 查询方法，输入值为具体网址与操作标记
     * 操作标记判断访问直连查找网页还是换乘查找网页
     * 同时对于网址中的中文汉字进行UTF-8编码
     * @param web
     * @param type
     */
    private void sendRequestBystop(final String web, final int type) {
        final String startstop = _f3startstop.getText().toString();
        final String endstop = _f3endstop.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(web + "?startstop=" + URLEncoder.encode(startstop, "UTF-8") + "&endstop=" + URLEncoder.encode(endstop, "UTF-8"))
                            .build();

                    Response response = client.newCall(request).execute();

                    final String data = response.body().string();

                    final List<huancheng> result;
                    if(type == 0)
                        result = parseXmlhuangcheng(data);
                    else
                        result = parseXmlhuangcheng1(data);
                    ///<在fragment里运行runOnUiThread，不能写为runOnUiThread(new Runnable(){});
                    ///<而应写为mActivity().runOnUiThread(new Runnable()
                    mActivity.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    //huanchengList.clear();
                                                    huanchengList.addAll(result);
                                                    Iterator<huancheng> iter = huanchengList.iterator();
                                                    System.out.println(huanchengList.size());
                                                    adapter.notifyDataSetChanged();
                                                }
                                            }
                    );
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
    /**
     * 适用于直达路线的xml数据流的解析方法，返回huancheng类的数列，
     * 由于list中add方法为浅拷贝，要定义变量暂存解析获得的数据，new 出新的实例，添加到list中
     */

    private List<huancheng> parseXmlhuangcheng(String data) {

        List<huancheng>list = new ArrayList<>();

        String startstop = null;
        String endstop = null;
        String route = null;
        String stopcount = null;
        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            XmlPullParser parser = factory.newPullParser();

            parser.setInput(new StringReader(data));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = parser.getName();
                switch (eventType) {

                    case XmlPullParser.START_TAG: {

                        if ("startstop".equals(nodeName)) {
                            startstop = parser.nextText();
                        }
                        if ("endstop".equals(nodeName)) {
                            endstop = parser.nextText();
                        }
                        if ("route".equals(nodeName)) {
                            route = parser.nextText();
                        }
                        if ("stopcount".equals(nodeName)) {
                            stopcount = parser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        if ("huancheng".equals(nodeName)) {
                            list.add(new huancheng("",route,"","PS:为直达班次",startstop,Integer.parseInt(stopcount)));
                        }
                        break;
                    }

                }

                eventType = parser.next();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;

    }
    /**
     * 适用于换乘路线的xml数据流的解析方法，返回huancheng类的数列，
     * 由于list中add方法为浅拷贝，要定义变量暂存解析获得的数据，new 出新的实例，添加到list中
     */
    private List<huancheng> parseXmlhuangcheng1(String data) {

        List<huancheng> list = new ArrayList<>();
        huancheng huancheng = new huancheng();


        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            XmlPullParser parser = factory.newPullParser();

            parser.setInput(new StringReader(data));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                String nodeName = parser.getName();
                switch (eventType) {

                    case XmlPullParser.START_TAG: {
                        if ("startstop".equals(nodeName)) {
                            String startstop = parser.nextText();
                            huancheng.setStartstop(startstop);
                        }
                        if("midstop".equals(nodeName)){
                            String midstop = parser.nextText();
                            huancheng.setMidstop(midstop);
                        }
                        if ("endstop".equals(nodeName)) {
                            String endstop = parser.nextText();
                        }
                        if ("routefir".equals(nodeName)) {
                            String routefir = parser.nextText();
                            huancheng.setRoutefir(routefir);
                        }
                        if ("routesec".equals(nodeName)){
                            String routesec = parser.nextText();
                            huancheng.setRoutersec(routesec);
                        }
                        if ("stopcount".equals(nodeName)) {
                            String stopcount = parser.nextText();
                            huancheng.setStopcount(Integer.parseInt(stopcount));
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        if ("huancheng1".equals(nodeName)) {
                            list.add(huancheng);
                        }
                        break;
                    }
                }
                eventType = parser.next();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;

    }

}

