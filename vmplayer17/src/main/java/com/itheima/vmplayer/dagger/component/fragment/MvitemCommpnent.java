package com.itheima.vmplayer.dagger.component.fragment;

import com.itheima.vmplayer.dagger.module.fragment.MvitemMvdule;
import com.itheima.vmplayer.ui.fragment.MvItemFragment;

import dagger.Component;

/**
 * Created by wschun on 2016/12/21.
 */
@Component(modules = MvitemMvdule.class)
public interface MvitemCommpnent {
    void in(MvItemFragment mvItemFragment);
}
