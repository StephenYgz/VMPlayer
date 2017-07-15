package com.itheima.vmplayer.model;

import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by wschun on 2016/12/23.
 */

public class MusicBean implements Serializable {

    //查询列的信息
//    String[] projection={MediaStore.Audio.Media.DISPLAY_NAME, //名称
//            MediaStore.Audio.Media.ARTIST, //作者
//            MediaStore.Audio.Media.SIZE,   //文件大小
//            MediaStore.Audio.Media.DATA,    //路径
//            MediaStore.Audio.Media.DURATION  //时长
//    };

    private String title;
    private String artist;
    private long size;
    private String path;
    private long duration;

    public static MusicBean getMusicBean(Cursor cursor){
        MusicBean musicBean=new MusicBean();
        musicBean.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
        musicBean.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
        musicBean.setSize(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)));
        musicBean.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
        musicBean.setDuration(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));

        return musicBean;

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
