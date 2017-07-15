package com.itheima.vmplayer.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.itheima.vmplayer.model.MusicBean;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by wschun on 2016/12/23.
 */

public class MusicPlayerService extends Service {

    public static final String UPDATE_UI ="update_ui" ;
    public static final String UPDATE_COMPLETION ="update_completion" ;
    private MusicPlayerProxy musicPlayerProxy;
    private List<MusicBean> musicBeanList;
    private int position;
    private MediaPlayer mediaPlayer;

    public static final int ORDER=0;//顺序
    public static final int RANDOM=1; //随机
    public static final int SINGLE=2; //单曲
    private int currentMode=ORDER;//默认
    private SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();
        musicPlayerProxy = new MusicPlayerProxy();
        sp = getSharedPreferences("config", MODE_PRIVATE);
        currentMode= sp.getInt("mode",ORDER);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicPlayerProxy;
    }


    public class  MusicPlayerProxy extends Binder{

        private boolean playing;

        public void play() {
            //保证每次只有一个mediaPlayer存在
             if (mediaPlayer!=null){
                 mediaPlayer.reset();
                 mediaPlayer.release();;
                 mediaPlayer=null;
             }

            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(musicBeanList.get(position).getPath());
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        sendBroadCastUpdateUi();
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        sendCompletion();
                        playMusicByMode();
                    }
                });
                mediaPlayer.prepare();


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        /**
         * 控制播放与暂停
         */
        public void togglePlay() {
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }else {
                mediaPlayer.start();
            }
        }

        /**
         * 判断播放与暂停
         * @return
         */
        public boolean isPlaying() {
            return mediaPlayer.isPlaying();
        }

        /**
         * 当前播放时间
         * @return
         */
        public int getCurrentPosition() {
            return mediaPlayer.getCurrentPosition();
        }
        //歌曲总时长
        public int getDuration() {
            return mediaPlayer.getDuration();
        }
        //滚动位置
        public void seekTo(int progress) {
            mediaPlayer.seekTo(progress);
        }
        //播放上一首
        public void pre() {
            if (position>0){
                position--;
                play();
            }
        }

        /**
         * 播放下一首
         */
        public void playNext() {
            if (currentMode==RANDOM){
                //如果是随机，就随机播放下一首
                position=new Random().nextInt(musicBeanList.size());
                play();
            }else {
                if (position<musicBeanList.size()-1){
                    position++;
                    play();
                }
            }


        }

        public boolean isFrist() {
            return position == 0;
        }

        public boolean isLast() {
            return position == musicBeanList.size() - 1;
        }

        public void switchPlay() {
            switch (currentMode){
                case ORDER:
                    currentMode=RANDOM;
                    break;
                case RANDOM:
                    currentMode=SINGLE;
                    break;
                case SINGLE:
                    currentMode=ORDER;
                    break;
            }
            SharedPreferences.Editor edit = sp.edit();
            edit.putInt("mode",currentMode);
            edit.commit();
        }

        public int getCurrentMode() {
            return currentMode;
        }

        public void stop() {
            if (mediaPlayer!=null){
                mediaPlayer.reset();
                mediaPlayer.release();;
                mediaPlayer=null;
            }
        }
    }

    /**
     * 根据模式播放音乐
     */
    private void playMusicByMode() {
        switch (currentMode){
            case ORDER:
                if (musicPlayerProxy.isLast()){
                    position=0;
                    musicPlayerProxy.play();
                } else {
                    musicPlayerProxy.playNext();
                }

                break;
            case RANDOM:
                position= new Random().nextInt(musicBeanList.size());

                musicPlayerProxy.play();
                break;
            case SINGLE:
                musicPlayerProxy.play();
                break;

        }
    }

    /**
     * 播放完成的广播
     */
    private void sendCompletion() {
        Intent mIntent=new Intent();
        mIntent.setAction(UPDATE_COMPLETION);
        sendBroadcast(mIntent);
    }

    /**
     * 开启播放，发送广播通知界面更新数据
     */
    private void sendBroadCastUpdateUi() {
        Intent mIntent=new Intent();
        mIntent.setAction(UPDATE_UI);
        int i=position;
        mIntent.putExtra("musicBean",musicBeanList.get(position));
        sendBroadcast(mIntent);

    }



    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    /**
     * 只有当startService的时候onStartCommand才会执行
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent!=null){
            musicBeanList = (List<MusicBean>) intent.getSerializableExtra("musicBeanList");
            //点击条目的位置
            position = intent.getIntExtra("position", -1);

        }
        //就服务被系统杀死，指定返回类型为START_STICKY，当系统恢复正常了，服务会被重新开启
        return START_STICKY;
    }


}
