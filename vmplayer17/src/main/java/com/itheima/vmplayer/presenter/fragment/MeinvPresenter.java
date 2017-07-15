package com.itheima.vmplayer.presenter.fragment;

import com.itheima.vmplayer.http.BaseCallBack;
import com.itheima.vmplayer.http.HttpManager;
import com.itheima.vmplayer.model.MeiNvBean;
import com.itheima.vmplayer.ui.fragment.MeiNvFragment;
import com.itheima.vmplayer.utils.Constant;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by wschun on 2016/12/24.
 */

public class MeinvPresenter {
    private MeiNvFragment meiNvFragment;

    public MeinvPresenter(MeiNvFragment meiNvFragment) {
        this.meiNvFragment = meiNvFragment;
    }

    public void getData() {
        HttpManager.getHttpManager().get(Constant.MEINV, new BaseCallBack<MeiNvBean>() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onSuccess(Call call, MeiNvBean meiNvBean) {
                int size=meiNvBean.getItems().size();
                meiNvFragment.setData(meiNvBean);
            }


        });
    }
}
