package com.itheima.vmplayer.dagger.module.fragment;

import com.itheima.vmplayer.presenter.fragment.MeinvPresenter;
import com.itheima.vmplayer.ui.fragment.MeiNvFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wschun on 2016/12/24.
 */
@Module
public class MeinvModule {
    private MeiNvFragment meiNvFragment;

    public MeinvModule(MeiNvFragment meiNvFragment) {
        this.meiNvFragment = meiNvFragment;
    }

    @Provides
    MeinvPresenter provideMeinvPresenter(){
        return new MeinvPresenter(meiNvFragment);
    }
}
