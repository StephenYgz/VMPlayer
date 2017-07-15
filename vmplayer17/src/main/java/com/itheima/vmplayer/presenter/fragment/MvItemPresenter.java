package com.itheima.vmplayer.presenter.fragment;

import com.itheima.vmplayer.http.BaseCallBack;
import com.itheima.vmplayer.http.HttpManager;
import com.itheima.vmplayer.model.MvBean;
import com.itheima.vmplayer.ui.fragment.MvItemFragment;
import com.itheima.vmplayer.utils.Constant;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by wschun on 2016/12/21.
 */

public class MvItemPresenter {

    private MvItemFragment mvItemFragment;

    public MvItemPresenter(MvItemFragment mvItemFragment) {
        this.mvItemFragment = mvItemFragment;
    }

    /**
     * 获取网络数据
     * @param code
     * @param offset
     * @param size
     */
    public void getData(String code, int offset, int size) {
        HttpManager httpManager = HttpManager.getHttpManager();
        httpManager.addParam("area",code)
                .addParam("offset",offset+"")
                .addParam("size",size+"");

        httpManager.get(Constant.MV_ITEM,new BaseCallBack<MvBean>(){

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onSuccess(Call call, MvBean mvBean) {
                mvItemFragment.setData(mvBean.getVideos());
            }


        });
    }
}
