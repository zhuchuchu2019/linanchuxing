package com.example.linan;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 发起http请求类
 * Created by 11357 on 2018/11/17.
 */
public class HttpUtil {

    public static void sendOkHttpRequest(final String address, final okhttp3.Callback callback ){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ///<声明客户端
                    OkHttpClient client = new OkHttpClient();
                    ///<声明请求
                    Request request = new Request.Builder()
                            .url(address)
                            .build();
                    ///<客户端发起请求
                    client.newCall(request).enqueue(callback);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
