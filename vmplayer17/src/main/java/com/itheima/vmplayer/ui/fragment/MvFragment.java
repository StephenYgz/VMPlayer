package com.itheima.vmplayer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.vmplayer.R;
import com.itheima.vmplayer.dagger.component.fragment.DaggerMvComponent;
import com.itheima.vmplayer.dagger.module.fragment.MvModule;
import com.itheima.vmplayer.model.MvItemBean;
import com.itheima.vmplayer.presenter.fragment.MvPresenter;
import com.itheima.vmplayer.ui.adapter.MvViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wschun on 2016/12/20.
 */

public class MvFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Inject
    public MvPresenter mvPresenter;
    private MvViewPagerAdapter mvViewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mv, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerMvComponent.builder().mvModule(new MvModule(this)).build().in(this);
        mvPresenter.getData();

    }
    private List<Fragment> fragments=new ArrayList<>();
    public void setData(List<MvItemBean> data) {

        for (int i = 0; i < data.size(); i++) {
            fragments.add(MvItemFragment.getInstance(data.get(i).getCode()));

        }

        initView(data);
    }

    private void initView(List<MvItemBean> data) {
        mvViewPagerAdapter = new MvViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments,data);
        viewPager.setAdapter(mvViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
