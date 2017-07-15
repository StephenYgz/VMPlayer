package com.itheima.vmplayer.utils;

import android.app.Activity;
import android.database.Cursor;
import android.nfc.Tag;
import android.util.Log;

import com.itheima.vmplayer.model.MusicBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by wschun on 2016/12/23.
 */

public class CommUtils {
    private static final String TAG = "CommUtils";

    /**
     * 打印Cursor
     *
     * @param cursor
     */
    public static void printCursor(Cursor cursor) {

        if (cursor == null) return;

        int count = cursor.getCount();

        Log.i(TAG, "数据的个数" + count);

        while (cursor.moveToNext()) {
            Log.i(TAG, "======================================");
            int columnCount = cursor.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = cursor.getColumnName(i);
                String value = cursor.getString(i);
                Log.i(TAG, "printCursor: columnName=" + columnName + ":::value=" + value);
            }

        }
    }

    /**
     * 去掉.mp3后缀
     *
     * @param name
     * @return
     */
    public static String formatName(String name) {
        int indexOf = name.lastIndexOf(".");
        String newName = name.substring(0, indexOf);
        return newName;
    }

    /**
     * 通过Cursor获取数据集合
     *
     * @param cursor
     * @return
     */
    public static List<MusicBean> getMusicLists(Cursor cursor) {

        List<MusicBean> lists = new ArrayList<>();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            MusicBean musicBean = MusicBean.getMusicBean(cursor);
            lists.add(musicBean);
        }
        return lists;
    }

    /**
     * 格式化时间 00:00:00
     *
     * @param time
     * @return
     */
    public static String formatTime(int time) {

        int HOUR = 60 * 60 * 1000;
        int MIN = 60 * 1000;
        int SEC = 1000;
        //小时
        int hour = time / HOUR;
        //算完小时候的剩余时间
        int offset = time % HOUR;
        //分钟
        int min = offset / MIN;
        //算完分钟候的剩余时间
        offset = offset % MIN;
        //秒
        int sec = offset / SEC;

        if (hour == 0) {
            return String.format("%02d:%02d", min, sec);
        } else {
            return String.format("%02d:%02d:%02d", hour, min, sec);
        }
    }

    /**
     * 获取屏幕的宽
     * @param activity
     * @return
     */
    public static int screenWidth(Activity activity){
        return  activity.getWindowManager().getDefaultDisplay().getWidth();
    }
}
