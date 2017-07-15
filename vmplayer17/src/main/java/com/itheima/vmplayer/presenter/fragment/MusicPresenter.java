package com.itheima.vmplayer.presenter.fragment;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio;

import com.itheima.vmplayer.ui.fragment.MusicFragment;
import com.itheima.vmplayer.utils.CommUtils;
import com.itheima.vmplayer.utils.MyCursorAdapter;
import com.itheima.vmplayer.utils.MyQueryHandler;

/**
 * Created by wschun on 2016/12/23.
 */

public class MusicPresenter {
    private MusicFragment musicFragment;

    public MusicPresenter(MusicFragment musicFragment) {
        this.musicFragment = musicFragment;
    }


    public void queryData(Context content, MyCursorAdapter myCursorAdapter) {
        //对于系统如果发现有新的音频或者视频出现，系统有一个数据库来存放视频或音频的信息
        //要通过内容提供者访问数据
        ContentResolver contentResolver = content.getContentResolver();
        //资源路径
        Uri uri=Audio.Media.EXTERNAL_CONTENT_URI;
        //查询列的信息
        String[] projection={
                 Audio.Media._ID,
                Audio.Media.DISPLAY_NAME, //名称
                Audio.Media.ARTIST, //作者
                Audio.Media.SIZE,   //文件大小
                Audio.Media.DATA,    //路径
                Audio.Media.DURATION  //时长
        };
        //同步查询，当数据过多的时候会造成UI的卡顿
//        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
        //使用异步的查询方式
        MyQueryHandler myQueryHandler = new MyQueryHandler(contentResolver);
        //查询音乐，查询视频，
        myQueryHandler.startQuery(0,myCursorAdapter,uri, projection, null, null, null);


//        CommUtils.printCursor(cursor);

    }
}
