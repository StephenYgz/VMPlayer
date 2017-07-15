package com.itheima.vmplayer.dagger.module.fragment;

import com.itheima.vmplayer.presenter.fragment.HomePresenter;
import com.itheima.vmplayer.ui.fragment.HomeFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wschun on 2016/12/20.
 */
@Module
public class HomeModule {
    private HomeFragment homeFragment;

    public HomeModule(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Provides
    HomePresenter provideHomePresenter(){
        return new HomePresenter(homeFragment);
    }
}
