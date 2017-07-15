package com.itheima.vmplayer.dagger.component.fragment;

import com.itheima.vmplayer.dagger.module.fragment.HomeModule;
import com.itheima.vmplayer.ui.fragment.HomeFragment;

import dagger.Component;

/**
 * Created by wschun on 2016/12/20.
 */
@Component(modules = HomeModule.class)
public interface HomeComponent {
    void in(HomeFragment homeFragment);

}
