package com.itheima.vmplayer.utils;

import android.content.Intent;

import com.itheima.vmplayer.model.LyricBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wschun on 2016/12/24.
 */

public class ParserLyric {
    /**
     * 解析歌词
     * @param file
     * @return
     */
    public static List<LyricBean> parserLyricFromFile(File file){
        List<LyricBean> lyricBeanList=new ArrayList<>();
        if (file==null || !file.exists()){
            lyricBeanList.add(new LyricBean(0,"未加载到歌词"));
        }

        try {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));

            String readLine = bufferedReader.readLine();
            while (readLine!=null){
               List<LyricBean> lyricBeans= parserReadLine(readLine);
                lyricBeanList.addAll(lyricBeans);
                readLine=bufferedReader.readLine();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(lyricBeanList);
        return lyricBeanList;
    }

    /**
     * 解析一行歌词
     * @param readLine
     * @return
     */
    private static List<LyricBean> parserReadLine(String readLine) {
//        [00:44.40 [00:32.40  小雨
        List<LyricBean> lyricBeens=new ArrayList<>();
        String[] strings = readLine.split("]");
        for (int i=0;i<strings.length-1;i++){
            String time = strings[i];
            int startpoint=parserTime(time);
            lyricBeens.add(new LyricBean(startpoint,strings[strings.length-1]));
        }
        return lyricBeens;
    }

    /**
     * 解析时间
     * @param time
     * @return
     */
    private static int parserTime(String time) {
//        [00:44.40
//        [00  44.40
        //分钟
        String[] times = time.split(":");
        String min=times[0].substring(1);
//        44  40
        times = times[1].split("\\.");
        String sec=times[0];
        String mSec=times[1];

        return   Integer.parseInt(min) * 60 * 1000+Integer.parseInt(sec) * 1000+Integer.parseInt(mSec) * 10;
    }
}
