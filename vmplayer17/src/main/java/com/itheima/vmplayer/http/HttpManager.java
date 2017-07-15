package com.itheima.vmplayer.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wschun on 2016/12/20.
 */

public class HttpManager {

    private static HttpManager httpManager;
    private final OkHttpClient okHttpClient;
    private final Gson gson;
    private final Handler handler;
    private final HashMap<String, String> params;

    private HttpManager() {
        //创建OkHttpClient的实例
        okHttpClient = new OkHttpClient();
        //连接超时
        okHttpClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        //读取超时
        okHttpClient.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        //写入超时
        okHttpClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS);
        //解析JSON
        gson = new Gson();
        //子线程中更新Ui用到Handler
        handler = new Handler(Looper.getMainLooper());

        params = new HashMap<>();

    }

   //单例
    public static HttpManager getHttpManager() {

        if (httpManager == null) {
            synchronized (HttpManager.class) {
                if (httpManager == null) {
                    httpManager = new HttpManager();
                }
            }
        }
        return httpManager;
    }

    /**
     * get请求
     * @param url
     * @param baseCallBack
     */
    public void get(String url,BaseCallBack baseCallBack){
        Request request=buildRequest(url, RequestTyep.GET);
        doRequest(request,baseCallBack);
    }

    /**
     * post请求
     * @param url
     * @param baseCallBack
     */
    public void post(String url,BaseCallBack baseCallBack){
        Request request=buildRequest(url, RequestTyep.POST);
        doRequest(request,baseCallBack);
    }


    /**
     * 创建Request
     * @return
     */
    private Request buildRequest(String url,RequestTyep type) {
        Request.Builder build=new Request.Builder();
        if (type== RequestTyep.GET){
            url=doUrl(url);
            build.get();

        }else if (type== RequestTyep.POST){
            build.post(buildRequestBody());
        }
        build.url(url);
       return build.build();
    }

    /**
     * 拼接GET请求参数
     * @param url
     * @return
     */
    private String doUrl(String url) {

        if (params.size()==0){
            return url;
        }
        StringBuilder sb=new StringBuilder();

        sb.append(url);

        for (Map.Entry<String,String> entry:params.entrySet()){
            //将请求参数设置到FormBody中
//            & v = 4
            sb.append("&"+entry.getKey()+"="+entry.getValue());
        }
        return sb.toString();
    }

    /**
     * 创建post请求体
     * @return
     */
    private RequestBody buildRequestBody() {
//        RequestBody body=new FormBody.Builder();
        FormBody.Builder builder=new FormBody.Builder();
         for (Map.Entry<String,String> entry:params.entrySet()){
             //将请求参数设置到FormBody中
             builder.add(entry.getKey(),entry.getValue());
         }
        return builder.build();
    }

    public HttpManager addParam(String key, String value) {
        params.put(key,value);
        return this;
    }

    /**
     * 请求类型
     */
    enum RequestTyep{
        GET,
        POST
    }



    private void doRequest(Request request, final BaseCallBack baseCallBack){


        okHttpClient.newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {

                senFailure(baseCallBack,call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                boolean successful = response.isSuccessful();
                if (successful){
                    String json = response.body().string();
                    sendSuccess(baseCallBack,call,json);

                }else {
                   senFailure(baseCallBack,call,null);
                }

            }
        });
    }

    /**
     * 请求成功的处理
     * @param baseCallBack
     * @param call
     * @param json
     */
    private void sendSuccess(final BaseCallBack baseCallBack, final Call call, final String json) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (baseCallBack.type==String.class){
                    baseCallBack.onSuccess(call,json);
                }else {
                    Object object = gson.fromJson(json, baseCallBack.type);
                    baseCallBack.onSuccess(call,object);
                }
            }
        });
    }
    /**
     * 请求失败的处理
     * @param baseCallBack
     * @param call
     *
     */
    private void senFailure(final BaseCallBack baseCallBack, final Call call, final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                baseCallBack.onFailure(call,e);
            }
        });
    }


}
