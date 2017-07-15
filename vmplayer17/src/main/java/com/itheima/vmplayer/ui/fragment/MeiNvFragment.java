package com.itheima.vmplayer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.vmplayer.R;
import com.itheima.vmplayer.dagger.component.fragment.DaggerMeinvComponent;
import com.itheima.vmplayer.dagger.module.fragment.MeinvModule;
import com.itheima.vmplayer.model.MeiNvBean;
import com.itheima.vmplayer.presenter.fragment.MeinvPresenter;
import com.itheima.vmplayer.ui.adapter.MeiNvAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wschun on 2016/12/20.
 */

public class MeiNvFragment extends BaseFragment  {

    @BindView(R.id.rv_meinv)
    RecyclerView rvMeinv;

    @Inject
    public MeinvPresenter meinvPresenter;
    private MeiNvAdapter meiNvAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragmen_meinv, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        StaggeredGridLayoutManager sglm=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rvMeinv.setLayoutManager(sglm);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerMeinvComponent.builder().meinvModule(new MeinvModule(this)).build().in(this);

        meinvPresenter.getData();

    }

    /**
     * 设置数据
     * @param data
     */
    public void setData(MeiNvBean data) {
        meiNvAdapter = new MeiNvAdapter(data.getItems(), getActivity());
        rvMeinv.setAdapter(meiNvAdapter);
    }
}
