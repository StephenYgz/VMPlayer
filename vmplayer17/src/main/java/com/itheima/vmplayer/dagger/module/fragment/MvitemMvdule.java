package com.itheima.vmplayer.dagger.module.fragment;

import com.itheima.vmplayer.presenter.fragment.MvItemPresenter;
import com.itheima.vmplayer.ui.fragment.MvItemFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wschun on 2016/12/21.
 */
@Module
public class MvitemMvdule {
    private MvItemFragment mvItemFragment;

    public MvitemMvdule(MvItemFragment mvItemFragment) {
        this.mvItemFragment = mvItemFragment;
    }

    @Provides
    MvItemPresenter provideMvItemPresenter(){
        return new MvItemPresenter(mvItemFragment);
    }
}
