package com.itheima.vmplayer.dagger.component.fragment;

import com.itheima.vmplayer.dagger.module.fragment.MusicModule;
import com.itheima.vmplayer.ui.fragment.MusicFragment;

import dagger.Component;

/**
 * Created by wschun on 2016/12/23.
 */
@Component(modules = MusicModule.class)
public interface MusicComponent {
    void in(MusicFragment musicFragment);
}
