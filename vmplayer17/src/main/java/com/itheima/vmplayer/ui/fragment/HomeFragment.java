package com.itheima.vmplayer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.vmplayer.R;
import com.itheima.vmplayer.dagger.component.fragment.DaggerHomeComponent;
import com.itheima.vmplayer.dagger.module.fragment.HomeModule;
import com.itheima.vmplayer.model.HomeBean;
import com.itheima.vmplayer.presenter.fragment.HomePresenter;
import com.itheima.vmplayer.ui.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wschun on 2016/12/20.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.srfl)
    SwipeRefreshLayout srfl;
    @Inject
    public HomePresenter homePresenter;
    private List<HomeBean> homeBeanLists;
    private HomeAdapter homeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);
        homeBeanLists = new ArrayList<>();
        observerView(640,560);
        initView();
        return rootView;
    }

    private void initView() {

        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(homeBeanLists, getActivity(), mWidth, mHeight);
        rvHome.setAdapter(homeAdapter);

         srfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                 // 下拉刷新，不管之前加载多少数据，全部归零，重新加载第一页数据
                 isFresh=true;
                 homePresenter.getDada(0,SIZE);
             }
         });
        //AddXXXListener 内部有一个集合将监听事件加入集合中
        rvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {

            /**
             * 当滚动的状态发生改变时回调
             * @param recyclerView
             * @param newState
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if ((lastVisibleItemPosition+1)==homeAdapter.getItemCount() && hasMode){
                    showDialog();
                    homePresenter.getDada(offset,SIZE);
                }
            }

            /**
             * 滚动时的监听
             * @param recyclerView
             * @param dx
             * @param dy
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                1，获取界面上最后一个Item
//                2，与数据个数比较，如果等于数据的个数，说明滚动到底部，触发加载更多的方法
                 lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().in(this);
//        HomePresenter homePresenter = new HomePresenter(this);
        //耦合度的问题
        //引入Dagger2来解耦操作
//        1，创建一个容器 @module(p的实例在这里创建)
//        2，@Inject 给需要依赖注入字段标识
//        3，创建连接，连接创建P对象的容器，并指定要注入的View
//        4，重新编译一把，生成我们想要的代码
        showDialog();
        homePresenter.getDada(offset,SIZE);

    }

    /**
     * 设置数据，并展示
     * @param homeBeen
     */
    public void setData(List<HomeBean> homeBeen) {
        if (isFresh){
            homeBeanLists.clear();
            isFresh=false;
        }

//        if (homeBeen.size()>0){
//            hasMode=true;
//        }else {
//            hasMode=false;
//        }

        hasMode=(homeBeen.size()>0);

        //设置每次加载的位置
        offset+=homeBeen.size();

        homeBeanLists.addAll(homeBeen);
        //刷新数据
        homeAdapter.notifyDataSetChanged();
        //取消刷新状态
        srfl.setRefreshing(false);
        dismiss();
    }
}
