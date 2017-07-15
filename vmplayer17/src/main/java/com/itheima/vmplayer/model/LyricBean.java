package com.itheima.vmplayer.model;

/**
 * Created by wschun on 2016/12/24.
 */

public class LyricBean implements Comparable<LyricBean>{
    private int startPoint;
    private String content;

    public LyricBean(int startPoint, String content) {
        this.startPoint = startPoint;
        this.content = content;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public int compareTo(LyricBean another) {
        return startPoint-another.getStartPoint();
    }
}
