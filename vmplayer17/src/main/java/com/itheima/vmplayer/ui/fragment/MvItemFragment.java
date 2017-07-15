package com.itheima.vmplayer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.vmplayer.R;
import com.itheima.vmplayer.dagger.component.fragment.DaggerMvitemCommpnent;
import com.itheima.vmplayer.dagger.module.fragment.MvitemMvdule;
import com.itheima.vmplayer.model.MvBean;
import com.itheima.vmplayer.presenter.fragment.MvItemPresenter;
import com.itheima.vmplayer.ui.adapter.MvItemAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wschun on 2016/12/21.
 */

public class MvItemFragment extends BaseFragment {

    @BindView(R.id.rv_mvitem)
    RecyclerView rvMvitem;
    @BindView(R.id.srfl_mvItem)
    SwipeRefreshLayout srflMvItem;
    private String code;

    @Inject
    public MvItemPresenter mvItemPresenter;
    private List<MvBean.VideosBean> data;
    private List<MvBean.VideosBean> videosBeans;
    private MvItemAdapter mvItemAdapter;


    public static MvItemFragment getInstance(String code) {
        MvItemFragment mvItemFragment = new MvItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        mvItemFragment.setArguments(bundle);

        return mvItemFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        code = bundle.getString("code");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_item, container, false);
        ButterKnife.bind(this, rootView);
        videosBeans = new ArrayList<>();
        observerView(640,360);
        initView();
        return rootView;
    }

    private void initView() {
        rvMvitem.setLayoutManager(new LinearLayoutManager(getContext()));
        mvItemAdapter = new MvItemAdapter(videosBeans, getActivity(), mWidth, mHeight);
        rvMvitem.setAdapter(mvItemAdapter);

        srflMvItem.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isFresh=true;
                mvItemPresenter.getData(code,0,SIZE);
            }
        });

        rvMvitem.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition= ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if ((lastVisibleItemPosition+1)==mvItemAdapter.getItemCount() && hasMode){
                    showDialog();
                    mvItemPresenter.getData(code,offset,SIZE);
                }
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerMvitemCommpnent.builder().mvitemMvdule(new MvitemMvdule(this)).build().in(this);
        showDialog();
        mvItemPresenter.getData(code,offset,SIZE);
    }

    /**
     * 设置数据
     * @param data
     */
    public void setData(List<MvBean.VideosBean> data){
        if (isFresh){
            videosBeans.clear();
            isFresh=false;
        }

        hasMode=(data.size()>0);

        offset+=data.size();
        videosBeans.addAll(data);
        mvItemAdapter.notifyDataSetChanged();
        srflMvItem.setRefreshing(false);

        dismiss();


    }
}
