package com.itheima.vmplayer.dagger.component.fragment;

import com.itheima.vmplayer.dagger.module.fragment.MeinvModule;
import com.itheima.vmplayer.ui.fragment.MeiNvFragment;

import dagger.Component;

/**
 * Created by wschun on 2016/12/24.
 */
@Component(modules = MeinvModule.class)
public interface MeinvComponent {
    void in(MeiNvFragment meiNvFragment);
}
