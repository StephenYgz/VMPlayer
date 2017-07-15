package com.itheima.vmplayer.dagger.component.fragment;

import com.itheima.vmplayer.dagger.module.fragment.MvModule;
import com.itheima.vmplayer.ui.fragment.MvFragment;

import dagger.Component;

/**
 * Created by wschun on 2016/12/21.
 */
@Component(modules = MvModule.class)
public interface MvComponent {
    void in(MvFragment mvFragment);
}
