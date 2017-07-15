package com.itheima.vmplayer.dagger.module.fragment;

import com.itheima.vmplayer.presenter.fragment.MvPresenter;
import com.itheima.vmplayer.ui.fragment.MvFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wschun on 2016/12/21.
 */
@Module
public class MvModule {
    private MvFragment mvFragment;

    public MvModule(MvFragment mvFragment) {
        this.mvFragment = mvFragment;
    }

    @Provides
    MvPresenter provideMvPresenter(){
        return new MvPresenter(mvFragment);
    }
}
