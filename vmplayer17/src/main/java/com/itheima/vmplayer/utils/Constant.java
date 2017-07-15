package com.itheima.vmplayer.utils;

/**
 * Created by wschun on 2016/11/17.
 */

public interface Constant {

    //首页Url 支持分页加载 &offset=“开始位置” ,&size="条目个数"
    String HOME = "http://mapi.yinyuetai.com/suggestions/front_page.json?deviceinfo=\n" +
            "{\"aid\":\"10201036\",\"os\":\"Android\",\"ov\":\"6.0\",\"rn\":\"480*800\",\n" +
            "\"dn\":\"Android SDK built for x86\",\"cr\":\"46000\",\"as\":\"WIFI\",\n" +
            "\"uid\":\"dbcaa6c4482bc05ecb0bf39dabf207d2\",\"clid\":110025000}&v=4&rn=640*540";

    //MV Url 获取的数据是条目类型
    String MV = "http://mapi.yinyuetai.com/video/get_mv_areas.json?deviceinfo=\n" +
            "{\"aid\":\"10201036\",\"os\":\"Android\",\"ov\":\"6.0\",\"rn\":\"480*800\",\n" +
            "\"dn\":\"Android SDK built for x86\",\"cr\":\"46000\",\"as\":\"WIFI\",\n" +
            "\"uid\":\"dbcaa6c4482bc05ecb0bf39dabf207d2\",\"clid\":110025000}";

    //Mv item 根据code获取数据 &offset=“开始位置” ,&size="条目个数"  ,&area="code"
    String MV_ITEM = "http://mapi.yinyuetai.com/video/list.json?deviceinfo=" +
            "{\"aid\":\"10201036\",\"os\":\"Android\",\"ov\":\"6.0\",\"rn\":\"480*800\",\n" +
            "\"dn\":\"Android SDK built for x86\",\"cr\":\"46000\",\"as\":\"WIFI\",\n" +
            "\"uid\":\"dbcaa6c4482bc05ecb0bf39dabf207d2\",\"clid\":110025000}";

    //美女
    String MEINV = "http://pic.sogou.com/pics?reqType=ajax&reqFrom=result&query=美女&start=0";

    //根据Id播放视频  id="VideoBean中的id"
    String VIDEO_PATH = "http://mapi.yinyuetai.com/video/show.json?deviceinfo=\n" +
            "{\"aid\":\"10201036\",\"os\":\"Android\",\"ov\":\"6.0\",\"rn\":\"480*800\",\n" +
            "\"dn\":\"Android SDK built for x86\",\"cr\":\"46000\",\"as\":\"WIFI\",\n" +
            "\"uid\":\"dbcaa6c4482bc05ecb0bf39dabf207d2\",\"clid\":110025000}\n" +
            "&relatedVideos=true";
    //根据Id播放视频  id="资源id"
    String YUEDAN_PATH = "http://mapi.yinyuetai.com/playlist/show.json?deviceinfo=\n" +
            "    {\"aid\":\"10201036\",\"os\":\"Android\",\"ov\":\"6.0\",\"rn\":\"480*800\",\n" +
            "            \"dn\":\"Android SDK built for x86_64\",\"cr\":\"46000\",\"as\":\"WIFI\",\n" +
            "            \"uid\":\"dbcaa6c4482bc05ecb0bf39dabf207d2\",\"clid\":110025000}";


}
