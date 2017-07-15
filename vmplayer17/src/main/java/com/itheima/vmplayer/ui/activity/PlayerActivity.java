package com.itheima.vmplayer.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.itheima.vmplayer.R;
import com.itheima.vmplayer.http.BaseCallBack;
import com.itheima.vmplayer.http.HttpManager;
import com.itheima.vmplayer.model.MvBean;
import com.itheima.vmplayer.model.VideoBean;
import com.itheima.vmplayer.utils.Constant;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;

public class PlayerActivity extends AppCompatActivity {

    @BindView(R.id.player)
    JCVideoPlayerStandard player;
    private int id;
    private int tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", -1);
        tag = getIntent().getIntExtra("tag", 1);
        getDatatById();



    }

    public void getDatatById() {

        HttpManager httpManager = HttpManager.getHttpManager().addParam("id", this.id + "");
        if (tag==1){
            httpManager.get(Constant.VIDEO_PATH, new BaseCallBack<VideoBean>() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onSuccess(Call call, VideoBean videoBean) {

//                    JSONObject jsonObject=new JSONObject(s);
                    //get 和 opt 的区别  使用GET必须保证解析的字段和服务器一致，否则异常退出，opt 可以不一致，但是解析不到数据
//                    String title = jsonObject.optString("title");
//                    String url = jsonObject.optString("url3");

//                VideoView (内部使用的是MediaPlayer+SurfaceView)
//                   VideoView (stopPlayback) 在界面退出是调用该方法释放资源
//                   VideoView 只能播放mp4,3gp  对于avi,rmvb...等不支持

//                第三方视频播放库 （Vitmio 维他蜜） 是收费的，学习免费
//                               （Ijkplayer）bilibili 免费的
//                                相同点：1，都能解析绝大多数的视频，基本满足我们视频播放的需求
//                                       2，都是基于FFmpeg，通过JNI实现视频播放功能的调用。
//                                不同点： 内部的封装处理不一样
//                FFmpeg 是用C语言开发的视频播放库，Android使用必须通过JNi调用

                    player.setUp(videoBean.getUrl(),videoBean.getTitle());
                    //相当于用户点击了播放按钮操作
                    player.startButton.performClick();


                }


            });

        }else if (tag==2){
            httpManager.get(Constant.YUEDAN_PATH, new BaseCallBack<MvBean>() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onSuccess(Call call, MvBean mvBean) {
                    int size=mvBean.getVideos().size();
                    MvBean.VideosBean videosBean = mvBean.getVideos().get(0);
                    player.setUp(videosBean.getUrl(),videosBean.getTitle());
                    //相当于用户点击了播放按钮操作
                    player.startButton.performClick();

                }


            });

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
