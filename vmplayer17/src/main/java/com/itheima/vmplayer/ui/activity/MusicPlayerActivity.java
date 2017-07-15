package com.itheima.vmplayer.ui.activity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.vmplayer.R;
import com.itheima.vmplayer.model.MusicBean;
import com.itheima.vmplayer.service.MusicPlayerService;
import com.itheima.vmplayer.utils.CommUtils;
import com.itheima.vmplayer.widget.LyricView;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicPlayerActivity extends AppCompatActivity {

    private static final String TAG = "MusicPlayerActivity";


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_artist)
    TextView tvArtist;
    @BindView(R.id.iv_animation)
    ImageView ivAnimation;
    @BindView(R.id.lyricView)
    LyricView lyricView;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.sb_seekbar)
    SeekBar sbSeekbar;
    @BindView(R.id.iv_play_mode)
    ImageView ivPlayMode;
    @BindView(R.id.iv_pre)
    ImageView ivPre;
    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    private MyServiceConnection myServiceConnection;
    private MyBroadCastReceiver myBroadCastReceiver;
    //服务的代理类
    private MusicPlayerService.MusicPlayerProxy musicPlayerProxy;
    private AnimationDrawable background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        ButterKnife.bind(this);
        //数据集合
        List<MusicBean> musicBeanList = (List<MusicBean>) getIntent().getSerializableExtra("musicBeanList");
        //点击条目的位置
        int position = getIntent().getIntExtra("position", -1);

        //注册广播接收者
        registFromServiceBroadCast();

        setListener();

        background = (AnimationDrawable) ivAnimation.getBackground();

        Log.i(TAG, "onCreate: 数据的个数=" + musicBeanList.size() + "----位置=" + position);
        Intent mIntent = new Intent(this, MusicPlayerService.class);
        mIntent.putExtra("musicBeanList", (Serializable) musicBeanList);
        mIntent.putExtra("position", position);
        //只是为了单纯的传递数据
        startService(mIntent);
        myServiceConnection = new MyServiceConnection();
//        Service.BIND_AUTO_CREATE 绑定自动创建
        bindService(mIntent, myServiceConnection, Service.BIND_AUTO_CREATE);
    }


    private void setListener() {
        sbSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * 当进度发生变化的时候
             * @param seekBar
             * @param progress
             * @param fromUser 来自于用户
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    musicPlayerProxy.seekTo(progress);
                }
            }

            /**
             * 当手指按下的时候回调方法
             * @param seekBar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            /**
             * 当手指抬起时回调的方法
             * @param seekBar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);
        musicPlayerProxy.stop();
        super.onBackPressed();
    }

    @OnClick({R.id.iv_back, R.id.iv_play_mode, R.id.iv_pre, R.id.iv_play, R.id.iv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                handler.removeCallbacksAndMessages(null);
                musicPlayerProxy.stop();
                finish();
                break;
            case R.id.iv_play_mode:
                musicPlayerProxy.switchPlay();
                switchPlayModeIcon();
                break;
            case R.id.iv_pre:
                if (musicPlayerProxy.isFrist()) {
                    Toast.makeText(MusicPlayerActivity.this, "当前已经是第一首", Toast.LENGTH_SHORT).show();
                } else {
                    musicPlayerProxy.pre();
                }


                break;
            case R.id.iv_next:
                if (musicPlayerProxy.isLast()) {
                    Toast.makeText(MusicPlayerActivity.this, "当前已经是最后一首", Toast.LENGTH_SHORT).show();
                } else
                    musicPlayerProxy.playNext();
                break;
            case R.id.iv_play:
                togglePlay();
                if (musicPlayerProxy.isPlaying()) {
                    ivPlay.setBackgroundResource(R.drawable.selector_btn_audio_play);
                    background.start();
                } else {
                    ivPlay.setBackgroundResource(R.drawable.selector_btn_audio_pause);
                    background.stop();
                }

                break;

        }
    }

    /**
     * 修改播放模式的图标
     */
    private void switchPlayModeIcon() {
        switch (musicPlayerProxy.getCurrentMode()){
            case MusicPlayerService.ORDER:
                ivPlayMode.setBackgroundResource(R.drawable.selector_btn_playmode_order);
                break;
            case MusicPlayerService.RANDOM:
                ivPlayMode.setBackgroundResource(R.drawable.selector_btn_playmode_random);
                break;
            case MusicPlayerService.SINGLE:
                ivPlayMode.setBackgroundResource(R.drawable.selector_btn_playmode_single);
                break;

        }
    }

    /**
     * 控制播放与暂停
     */
    private void togglePlay() {
        musicPlayerProxy.togglePlay();
    }

    private void registFromServiceBroadCast() {
        myBroadCastReceiver = new MyBroadCastReceiver();
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(MusicPlayerService.UPDATE_UI);
        intentfilter.addAction(MusicPlayerService.UPDATE_COMPLETION);
        registerReceiver(myBroadCastReceiver, intentfilter);
    }

    private class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MusicPlayerService.UPDATE_UI.equals(intent.getAction())) {
                MusicBean musicBean = (MusicBean) intent.getSerializableExtra("musicBean");
                updateUi(musicBean);
            } else if (MusicPlayerService.UPDATE_COMPLETION.equals(intent.getAction())) {
                //播放完成
                ivPlay.setBackgroundResource(R.drawable.selector_btn_audio_pause);
                handler.removeMessages(UPDATE_TIME_SB);

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        unregisterReceiver(myBroadCastReceiver);
        unbindService(myServiceConnection);
    }

    /**
     * 刷新数据
     *
     * @param musicBean
     */
    private void updateUi(MusicBean musicBean) {
        tvTitle.setText(CommUtils.formatName(musicBean.getTitle()));
        tvArtist.setText(musicBean.getArtist());
        sbSeekbar.setMax(musicPlayerProxy.getDuration());
        ivPlay.setBackgroundResource(R.drawable.selector_btn_audio_play);
        switchPlayModeIcon();
        background.start();
        updateTimeAndSb();
        File file=new File(Environment.getExternalStorageDirectory()+"/test/audio/"+CommUtils.formatName(musicBean.getTitle())+".lrc");
        lyricView.parserLyric(file);

        //开启滚动歌词
        startRoll();


    }

    private void startRoll() {
        lyricView.roll(musicPlayerProxy.getCurrentPosition(),musicPlayerProxy.getDuration());
        handler.sendEmptyMessage(UPDATE_LYRIC);
    }

    private static final int UPDATE_TIME_SB = 0;//更新时间和进度条
    private static final int UPDATE_LYRIC =2 ;//刷新歌词
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TIME_SB:
                    updateTimeAndSb();
                    break;
                case  UPDATE_LYRIC:
                    startRoll();
                    break;
            }
        }
    };

    private void updateTimeAndSb() {
        //设置时间
        tvTime.setText(CommUtils.formatTime(musicPlayerProxy.getCurrentPosition()) + "/" +
                CommUtils.formatTime(musicPlayerProxy.getDuration()));
//        设置进度条总长度
        sbSeekbar.setProgress(musicPlayerProxy.getCurrentPosition());

        handler.sendEmptyMessageDelayed(UPDATE_TIME_SB, 200);


    }


    private class MyServiceConnection implements ServiceConnection {


        /**
         * 当服务连接成功
         *
         * @param name
         * @param service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicPlayerProxy = (MusicPlayerService.MusicPlayerProxy) service;
            musicPlayerProxy.play();
        }


        /**
         * 当服务连接失败
         *
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
