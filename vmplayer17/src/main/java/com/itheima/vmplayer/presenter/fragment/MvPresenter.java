package com.itheima.vmplayer.presenter.fragment;

import com.itheima.vmplayer.http.BaseCallBack;
import com.itheima.vmplayer.http.HttpManager;
import com.itheima.vmplayer.model.MvItemBean;
import com.itheima.vmplayer.ui.fragment.MvFragment;
import com.itheima.vmplayer.utils.Constant;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by wschun on 2016/12/21.
 */

public class MvPresenter {
    private MvFragment mvFragment;

    public MvPresenter(MvFragment mvFragment) {
        this.mvFragment = mvFragment;
    }

    public void getData() {
        HttpManager.getHttpManager().get(Constant.MV,new BaseCallBack<List<MvItemBean>>() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onSuccess(Call call, List<MvItemBean> mvItemBeen) {
                 mvFragment.setData(mvItemBeen);
            }

        });

    }
}
