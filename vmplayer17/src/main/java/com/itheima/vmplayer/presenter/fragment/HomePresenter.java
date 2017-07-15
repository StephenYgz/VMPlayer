package com.itheima.vmplayer.presenter.fragment;

import com.itheima.vmplayer.http.BaseCallBack;
import com.itheima.vmplayer.http.HttpManager;
import com.itheima.vmplayer.model.HomeBean;
import com.itheima.vmplayer.ui.fragment.HomeFragment;
import com.itheima.vmplayer.utils.Constant;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by wschun on 2016/12/20.
 */

public class HomePresenter {

    private HomeFragment homeFragment;

    public HomePresenter(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    /**
     * 分页加载思路： 1 ，第一次拿去服务器数据时，服务器已经返回总共的页数，当前是第几页，每一页的条目个数
     *              2 ，用户指定每一页的开始位置，以及该页的条目个数
     */
    public void getDada(int offset,int size) {
        HttpManager httpManager = HttpManager.getHttpManager();
        httpManager.addParam("offset",offset+"").addParam("size",size+"");

        httpManager.get(Constant.HOME, new BaseCallBack<List<HomeBean>>() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onSuccess(Call call, List<HomeBean> homeBeen) {
                int size=homeBeen.size();
                homeFragment.setData(homeBeen);
            }
        });
    }
}
