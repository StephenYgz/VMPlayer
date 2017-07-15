package com.itheima.vmplayer.dagger.module.fragment;

import com.itheima.vmplayer.presenter.fragment.MusicPresenter;
import com.itheima.vmplayer.ui.fragment.MusicFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wschun on 2016/12/23.
 */
@Module
public class MusicModule {
    private MusicFragment musicFragment;

    public MusicModule(MusicFragment musicFragment) {
        this.musicFragment = musicFragment;
    }

    @Provides
    MusicPresenter provideMusicPresenter(){
        return  new MusicPresenter(musicFragment);
    }


}
