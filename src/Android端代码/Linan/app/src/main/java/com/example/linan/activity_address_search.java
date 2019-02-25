package com.example.linan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 换乘方案页面
 */
public class activity_address_search extends AppCompatActivity {

    private List<String> stoplistf = new ArrayList<>();
    private List<StopList> stoplist = new ArrayList<>();
    public static final String websc = "http://192.168.71.1:8080/Linan/stopsearch.jsp";

    EditText _et;///<地址输入框
    Button _back;///<返回按钮
    ProgressBar _ads_progbar;///<进度条
    StopListAdapter adapter;///<站点列的适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_search);

        _et = (EditText)findViewById(R.id.editText_place);
        _back = (Button)findViewById(R.id.btn_back);
        _ads_progbar = (ProgressBar)findViewById(R.id.pb_ads_progbar);

        _back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        String stop = intent.getStringExtra("extra_data");
        _et.setText(stop);
        _et.setSelection(stop.length());

        sendRequestBystop(stop);
        //EditText实时监控方法
        _et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sendRequestBystop(websc);
                System.out.println("websc");
            }
        });
        initStopList();//数据初始化
        adapter = new StopListAdapter(activity_address_search.this,R.layout.stoplist_item,stoplist);

        final ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StopList stop = stoplist.get(position);
                Intent intent = new Intent();
                intent.putExtra("data_return",stop.getName());
                setResult(RESULT_OK,intent);
                System.out.println("OK");
                finish();
            }
        });}

    /**
     * 站点名搜索方法，去空格,当站点非空时，获得地址中数据
     * 同时将中文字符使用UTF-8编码发送，获得值，添加到列表数列中，清空适配器，刷新列表
     * @param web
     */
    private void sendRequestBystop(final String web){
        final String stop = _et.getText().toString().replaceAll(" ","");
        if(stop.equals(""))
            return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client= new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(web + "?stop=" + URLEncoder.encode(stop, "UTF-8"))
                            .build();
                    Response response = client.newCall(request).execute();

                    final String data = response.body().string();
                    final List<String> result ;
                    result = parseXmlstop(data);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.clear();
                            stoplistf = result;
                            Iterator<String> it = stoplistf.iterator();
                            while (it.hasNext()){
                                StopList stop = new StopList(R.drawable.icon_hisline,it.next());
                                stoplist.add(stop);
                                System.out.println(stop.getName());
                                adapter.notifyDataSetChanged();
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 在sendRequestBystop中被调用，对于数据的分析，返回String类列表
     * @param data
     * @return
     */
    private List<String> parseXmlstop(String data) {

        List<String> list = new ArrayList<>();
        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            XmlPullParser parser = factory.newPullParser();

            parser.setInput(new StringReader(data));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                String nodeName = parser.getName();
                switch (eventType) {

                    case XmlPullParser.START_TAG: {
                        if ("stop".equals(nodeName)) {
                            String searchstop = parser.nextText();
                            list.add(searchstop);
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        if ("stopsearch".equals(nodeName)) {
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
     * 站点列表的初始化
     */
    private void initStopList(){
        Iterator<String> it = stoplistf.iterator();
        while (it.hasNext()){
            StopList stop = new StopList(R.drawable.icon_hisline,it.next());
            stoplist.add(stop);
        }

    }

    /**
     * 重写返回按钮方法，使用INTENT返回当前框内地址
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("data_return",_et.getText().toString());
        setResult(RESULT_OK,intent);
        System.out.println("OOOOOK");
        finish();
    }
}
