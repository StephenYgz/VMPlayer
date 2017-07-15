package com.itheima.vmplayer.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.itheima.vmplayer.model.MvItemBean;

import java.util.List;

/**
 * Created by wschun on 2016/12/21.
 */

public class MvViewPagerAdapter extends FragmentStatePagerAdapter {
//    FragmentStatePagerAdapter (默认是只加载当前页和下一页，如果有上一个也会加载)
//    FragmentPagerAdapter    (全部加载)

    List<Fragment> fragments;
    List<MvItemBean> mvItemBeanList;
    public MvViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<MvItemBean> mvItemBeanList) {
        super(fm);
        this.fragments=fragments;
        this.mvItemBeanList=mvItemBeanList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments==null?0:fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mvItemBeanList.get(position).getName();
    }
}
